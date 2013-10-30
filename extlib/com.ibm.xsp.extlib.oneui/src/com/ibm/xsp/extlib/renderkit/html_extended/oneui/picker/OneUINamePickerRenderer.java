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






package com.ibm.xsp.extlib.renderkit.html_extended.oneui.picker;

import javax.faces.context.FacesContext;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.component.UIViewRootEx;
import com.ibm.xsp.extlib.component.picker.AbstractPicker;
import com.ibm.xsp.extlib.component.picker.data.IPickerData;
import com.ibm.xsp.extlib.renderkit.html_extended.picker.NamePickerRenderer;
import com.ibm.xsp.extlib.resources.ExtLibResources;
import com.ibm.xsp.extlib.resources.OneUIResources;

/**
 *OneUI Name Picker renderer.
 */
public class OneUINamePickerRenderer extends NamePickerRenderer {

    @Override
    protected String encodeDojoType(String dojoType) {
        // Transform the basic controls into OneUI ones
        if (StringUtil.equals(dojoType, "extlib.dijit.PickerName")) { // $NON-NLS-1$
            return "extlib.dijit.OneUIPickerName"; // $NON-NLS-1$
        }
        return super.encodeDojoType(dojoType);
    }

    @Override
    protected void encodeExtraResources(FacesContext context, AbstractPicker picker, IPickerData data,
            UIViewRootEx rootEx, String dojoType) {
        if (StringUtil.equals(dojoType, "extlib.dijit.OneUIPickerName")) { // $NON-NLS-1$
            ExtLibResources.addEncodeResource(rootEx, OneUIResources.oneUIPickerName);
        }
        super.encodeExtraResources(context, picker, data, rootEx, dojoType);
    }
}
