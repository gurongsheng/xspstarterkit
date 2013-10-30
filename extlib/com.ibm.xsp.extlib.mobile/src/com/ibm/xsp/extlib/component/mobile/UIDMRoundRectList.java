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






package com.ibm.xsp.extlib.component.mobile;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.ibm.xsp.extlib.component.dojo.UIDojoWidgetBase;

/**
 * Mobile rectangle with round corner.
 * <p>
 * </p>
 */
public class UIDMRoundRectList extends UIDojoWidgetBase {

    public static final String RENDERER_TYPE = "com.ibm.xsp.extlib.mobile.DojoRoundRectList"; //$NON-NLS-1$
    public static final String COMPONENT_FAMILY = "com.ibm.xsp.extlib.Mobile"; //$NON-NLS-1$    
    public static final String COMPONENT_TYPE = "com.ibm.xsp.extlib.mobile.RoundRectList"; //$NON-NLS-1$    
    private String transition;
    private String iconBase;
    private String iconPos;
    
    public UIDMRoundRectList() {
        setRendererType(RENDERER_TYPE);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }   

    public String getTransition() {
        if (null != this.transition) {
            return this.transition;
        }
        ValueBinding _vb = getValueBinding("transition"); //$NON-NLS-1$
        if (_vb != null) {
            String val = (String) _vb.getValue(FacesContext.getCurrentInstance());
            if(val!=null) {
                return val;
            }
        } 
        return null;
    }
    
    public void setTransition(String transition) {
        this.transition = transition;
    }       

    public String getIconBase() {
        if (null != this.iconBase) {
            return this.iconBase;
        }
        ValueBinding _vb = getValueBinding("iconBase"); //$NON-NLS-1$
        if (_vb != null) {
            String val = (String) _vb.getValue(FacesContext.getCurrentInstance());
            if(val!=null) {
                return val;
            }
        } 
        return null;
    }
    
    public void setIconBase(String iconBase) {
        this.iconBase = iconBase;
    }       

    public String getIconPos() {
        if (null != this.iconPos) {
            return this.iconPos;
        }
        ValueBinding _vb = getValueBinding("iconPos"); //$NON-NLS-1$
        if (_vb != null) {
            String val = (String) _vb.getValue(FacesContext.getCurrentInstance());
            if(val!=null) {
                return val;
            }
        } 
        return null;
    }
    
    public void setIconPos(String iconPos) {
        this.iconPos = iconPos;
    }       
    
    @Override
    public void restoreState(FacesContext _context, Object _state) {
        Object _values[] = (Object[]) _state;
        super.restoreState(_context, _values[0]);
                
        this.transition = (String)_values[1];
        this.iconBase = (String)_values[2];
        this.iconPos = (String)_values[3];
    }

    @Override
    public Object saveState(FacesContext _context) {
        Object _values[] = new Object[4];
        _values[0] = super.saveState(_context);
        
        _values[1] = transition;
        _values[2] = iconBase;
        _values[3] = iconPos;
        return _values;
    }

    public String getStyleKitFamily() {
        // TODO Auto-generated method stub
        return null;
    }

}
