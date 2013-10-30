/* ***************************************************************** */
/* Licensed Materials - Property of IBM                              */
/*                                                                   */
/* Copyright IBM Corp. 1985, 2013 All Rights Reserved                */
/*                                                                   */
/* US Government Users Restricted Rights - Use, duplication or       */
/* disclosure restricted by GSA ADP Schedule Contract with           */
/* IBM Corp.                                                         */
/*                                                                   */
/* ***************************************************************** */






package com.ibm.xsp.extlib.util.debug;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ibm.commons.extension.ExtensionManager;


public abstract class DumpAccessor {
    
    public static final String EXTENSION_NAME = "com.ibm.xsp.extlib.DumpAccessorFactory"; // $NON-NLS-1$
    
    private static List<DumpAccessorFactory> factories;
    static {
        try {
            factories = ExtensionManager.findServices(null,DumpAccessor.class.getClassLoader(),EXTENSION_NAME,DumpAccessorFactory.class);
        } catch(Throwable t) {}
    }

    public static DumpAccessor find(DumpContext dumpContext, Object o) {
        List<DumpAccessorFactory> f = factories;
        // Look if a registered factory handles it
        for(int i=0; i<factories.size(); i++) {
            DumpAccessor a = factories.get(i).find(dumpContext,o);
            if(a!=null) {
                return a;
            }
        }
        // Default to the basic Java factory
        return JavaDumpFactory.get().find(dumpContext,o);
    }
    
    public static final int TYPE_VALUE      = 1;
    public static final int TYPE_ARRAY      = 2;
    public static final int TYPE_MAP        = 3;
    public static final int TYPE_GRID       = 4;

    
    private DumpContext dumpContext;
    
    protected DumpAccessor(DumpContext dumpContext) {
        this.dumpContext = dumpContext;
    }
    
    public DumpContext getDumpContext() {
        return dumpContext;
    }
    
    public abstract int getType();
    public abstract String getTypeAsString();
    
    public static abstract class Value extends DumpAccessor {
        protected Value(DumpContext dumpContext) {
            super(dumpContext);
        }
        @Override
        public int getType() {
            return TYPE_VALUE;
        }
        public abstract String getValueAsString();
        public abstract Object getValue();
    }

    public static abstract class Array extends DumpAccessor {
        protected Array(DumpContext dumpContext) {
            super(dumpContext);
        }
        @Override
        public int getType() {
            return TYPE_ARRAY;
        }
        public abstract Iterator<Object> arrayIterator();
    }

    public static abstract class Map extends DumpAccessor {
        protected Map(DumpContext dumpContext) {
            super(dumpContext);
        }
        @Override
        public int getType() {
            return TYPE_MAP;
        }
        public abstract String getStringLabel();
        public abstract String[] getCategories();
        public abstract Iterator<Object> getPropertyKeys(String category);
        public abstract Object getProperty(Object key);
        public boolean shouldDisplay(String name, Object value) {
            return true;
        }
    }

    public static abstract class Grid extends DumpAccessor {
        protected Grid(DumpContext dumpContext) {
            super(dumpContext);
        }
        @Override
        public int getType() {
            return TYPE_GRID;
        }
        public abstract String[] getColumns();
        public abstract Iterator<Object> objectIterator(int first, int count);
        public abstract Object getValue(Object object, int col);
    }
}
