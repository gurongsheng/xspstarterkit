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






package com.ibm.xsp.extlib.renderkit.html_extended.oneui.outline.tree;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.component.UIViewRootEx;
import com.ibm.xsp.extlib.renderkit.html_extended.outline.tree.MenuRenderer;
import com.ibm.xsp.extlib.resources.ExtLibResources;
import com.ibm.xsp.extlib.resources.OneUIResources;


public class OneUIMenuRenderer extends MenuRenderer {
    
    private static final long serialVersionUID = 1L;

    public OneUIMenuRenderer() {
    }

    public OneUIMenuRenderer(UIComponent component) {
        super(component);
    }

    @Override
    protected void preRenderTree(FacesContext context, ResponseWriter writer, TreeContextImpl tree) throws IOException {
        // Add the JS support if necessary
        if(isExpandable()) {
            UIViewRootEx rootEx = (UIViewRootEx) context.getViewRoot();
            rootEx.setDojoTheme(true);
            ExtLibResources.addEncodeResource(rootEx, OneUIResources.oneUINavigator);
            // Specific dojo effects
            String effect = getExpandEffect();
            if(StringUtil.isNotEmpty(effect)) {
                rootEx.addEncodeResource(ExtLibResources.dojoFx);
                ExtLibResources.addEncodeResource(rootEx, ExtLibResources.dojoFx);
            }
        }
        super.preRenderTree(context, writer, tree);
    }

    @Override
    protected Object getProperty(int prop) {
        switch(prop) {
            case PROP_MENU_SECTION:             return "lotusMenuSection"; // $NON-NLS-1$
            case PROP_MENU_SUBSECTION:          return "lotusMenuSubsection"; // $NON-NLS-1$
            case PROP_MENU_MENU:                return "lotusMenu"; // $NON-NLS-1$
            case PROP_MENU_BOTTOMCORNER:        return "lotusBottomCorner"; // $NON-NLS-1$
            case PROP_MENU_INNER:               return "lotusInner"; // $NON-NLS-1$
            case PROP_MENU_HEADER:              return "lotusMenuHeader"; // $NON-NLS-1$
            case PROP_MENU_SELECTED:            return "lotusSelected"; // $NON-NLS-1$
            case PROP_MENU_EXPANDED:            return "lotusSprite lotusArrow lotusTwistyOpenMenu"; // $NON-NLS-1$
            case PROP_MENU_COLLAPSED:           return "lotusSprite lotusArrow lotusTwistyClosedMenu"; // $NON-NLS-1$
            case PROP_MENU_SECTION_HEADING:     return "lotusHeading"; // $NON-NLS-1$
        }
        return super.getProperty(prop);
    }
}
