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






package com.ibm.xsp.extlib.util;

import com.ibm.commons.util.io.json.JsonException;
import com.ibm.designer.runtime.DesignerRuntime;
import com.ibm.jscript.InterpretException;
import com.ibm.jscript.json.JsonJavaScriptFactory;
import com.ibm.jscript.std.ArrayObject;
import com.ibm.jscript.std.ObjectObject;
import com.ibm.jscript.types.FBSNull;
import com.ibm.jscript.types.FBSObject;
import com.ibm.jscript.types.FBSUndefined;
import com.ibm.jscript.types.FBSUtility;
import com.ibm.jscript.types.FBSValue;




/**
 * Some utility used to deal with JavaScript objects.
 */
public class JSJson {

    public static final JsonJavaScriptFactory factory = new JsonJavaScriptFactory(DesignerRuntime.getJSContext()); 

    private FBSObject value;
    
    public static final JSJson wrap(Object o) throws JsonException {
        if(o instanceof FBSNull) {
            return new JSJson();
        }
        if(o instanceof FBSUndefined) {
            return new JSJson();
        }
        if(o instanceof FBSObject) {
            return new JSJson((FBSObject)o);
        }
        throw new JsonException(null,com.ibm.xsp.extlib.core.ResourceHandler.getSpecialAudienceString("JSJson.Thevalue0isneitheraJavaScriptobje")); // $NLX-JSJson.Thevalue0isneitheraJavaScriptobje-1$
    }
    public static final JSJson createObject() throws JsonException {
        return new JSJson(new ObjectObject());
    }
    public static final JSJson createArray() throws JsonException {
        return new JSJson(new ArrayObject());
    }

    // ==================================================================
    // Null value related methods
    
    protected JSJson() {
    }
    protected JSJson(FBSObject value) {
        this.value = value;
    }

    
    // ==================================================================
    // Null value related methods

    public boolean isNull() {
        return value==null;
    }
    public boolean isNotNull() {
        return value!=null;
    }
    
    
    // ==================================================================
    // Object related methods
    
    private FBSObject asObject() throws JsonException {
        if(value!=null) {
            return value;
        }
        throw new JsonException(null,com.ibm.xsp.extlib.core.ResourceHandler.getSpecialAudienceString("JSJson.JavaScriptvalueisnotanobject")); // $NLX-JSJson.JavaScriptvalueisnotanobject-1$
    }
    private FBSValue getProperty(String propertyName) throws JsonException {
        try {
            return asObject().get(propertyName);
        } catch(InterpretException ex) {
            throw new JsonException(ex,com.ibm.xsp.extlib.core.ResourceHandler.getSpecialAudienceString("JSJson.Errorwhileaccessingobjectproperty"), propertyName); // $NLX-JSJson.Errorwhileaccessingobjectproperty-1$
        }
    }
    private void putProperty(String propertyName, FBSValue v) throws JsonException {
        try {
            asObject().put(propertyName,v);
        } catch(InterpretException ex) {
            throw new JsonException(ex,com.ibm.xsp.extlib.core.ResourceHandler.getSpecialAudienceString("JSJson.Errorwhilesettingobjectproperty0"), propertyName); // $NLX-JSJson.Errorwhilesettingobjectproperty0-1$
        }
    }

    public boolean isObject() {
        return value!=null;
    }

    public boolean exists(String propertyName) throws JsonException {
        FBSValue v=getProperty(propertyName);
        return !v.isUndefined(); 
    }
    
    
    public String getString(String propertyName) throws JsonException {
        FBSValue v = getProperty(propertyName);
        if(v.isUndefined() || v.isNull()) {
            return null;
        }
        return v.stringValue();
    }
    public void putString(String propertyName, String v) throws JsonException {
        putProperty(propertyName, FBSUtility.wrap(v));
    }
    
    public int getInt(String propertyName) throws JsonException {
        return getProperty(propertyName).intValue();
    }
    public void putInt(String propertyName, int v) throws JsonException {
        putProperty(propertyName, FBSUtility.wrap(v));
    }
    
    public long getLong(String propertyName) throws JsonException {
        return getProperty(propertyName).longValue();
    }
    public void putLong(String propertyName, long v) throws JsonException {
        putProperty(propertyName, FBSUtility.wrap(v));
    }
    
    public double getDouble(String propertyName) throws JsonException {
        return getProperty(propertyName).doubleValue();
    }
    public void putDouble(String propertyName, double v) throws JsonException {
        putProperty(propertyName, FBSUtility.wrap(v));
    }

    public boolean getBoolean(String propertyName) throws JsonException {
        return getProperty(propertyName).booleanValue();
    }
    public void putBoolean(String propertyName, boolean v) throws JsonException {
        putProperty(propertyName, FBSUtility.wrap(v));
    }

    public JSJson getObject(String propertyName) throws JsonException {
        FBSValue o = getProperty(propertyName);
        if(o.isObject()) {
            return new JSJson((FBSObject)o);
        } else if(o.isArray()) {
            return new JSJson((ArrayObject)o);
        } else if(o.isNull() || o.isUndefined()) {
            return new JSJson();
        }
        throw new JsonException(null,com.ibm.xsp.extlib.core.ResourceHandler.getSpecialAudienceString("JSJson.Property0isnotanobject"),propertyName); // $NLX-JSJson.Property0isnotanobject-1$
    }
    public void putObject(String propertyName, JSJson object) throws JsonException {
        if(value!=null) {
            putProperty(propertyName, value);
        } else {
            putProperty(propertyName, FBSNull.nullValue);
        }
    }

    
    
    // ==================================================================
    // Array related methods
    private ArrayObject asArray() throws JsonException {
        if(value instanceof ArrayObject) {
            return (ArrayObject)value;
        }
        throw new JsonException(null,com.ibm.xsp.extlib.core.ResourceHandler.getSpecialAudienceString("JSJson.JavaScriptvalueisnotanarray")); // $NLX-JSJson.JavaScriptvalueisnotanarray-1$
    }
    private FBSValue getProperty(int index) throws JsonException {
        try {
            return asArray().get(index);
        } catch(InterpretException ex) {
            throw new JsonException(ex,com.ibm.xsp.extlib.core.ResourceHandler.getSpecialAudienceString("JSJson.Errorwhileaccessingindexedpropert"), index); // $NLX-JSJson.Errorwhileaccessingindexedpropert-1$
        }
    }
    private void putProperty(int index, FBSValue v) throws JsonException {
        asArray().put(index,v);
    }

    public boolean isArray() {
        return value instanceof ArrayObject;
    }

    public int getArrayLength() throws JsonException {
        return asArray().getArrayLength();
    }
    
    public String getString(int index) throws JsonException {
        return getProperty(index).stringValue();
    }
    public void putString(int index, String v) throws JsonException {
        putProperty(index, FBSUtility.wrap(v));
    }
    
    public int getInt(int index) throws JsonException {
        return getProperty(index).intValue();
    }
    public void putInt(int index, int v) throws JsonException {
        putProperty(index, FBSUtility.wrap(v));
    }
    
    public long getLong(int index) throws JsonException {
        return getProperty(index).longValue();
    }
    public void putLong(int index, long v) throws JsonException {
        putProperty(index, FBSUtility.wrap(v));
    }
    
    public double getDouble(int index) throws JsonException {
        return getProperty(index).doubleValue();
    }
    public void putDouble(int index, double v) throws JsonException {
        putProperty(index, FBSUtility.wrap(v));
    }

    public boolean getBoolean(int index) throws JsonException {
        return getProperty(index).booleanValue();
    }
    public void putBoolean(int index, boolean v) throws JsonException {
        putProperty(index, FBSUtility.wrap(v));
    }

    public JSJson getObject(int index) throws JsonException {
        FBSValue o = getProperty(index);
        if(o.isObject()) {
            return new JSJson((FBSObject)o);
        } else if(o.isArray()) {
            return new JSJson((ArrayObject)o);
        } else if(o.isNull()) {
            return new JSJson();
        }
        throw new JsonException(null,com.ibm.xsp.extlib.core.ResourceHandler.getSpecialAudienceString("JSJson.Index0isnotanobject"),index); // $NLX-JSJson.Index0isnotanobject-1$
    }
    public void putObject(int index, JSJson object) throws JsonException {
        if(value!=null) {
            putProperty(index, value);
        } else {
            putProperty(index, FBSNull.nullValue);
        }
    }   
}
