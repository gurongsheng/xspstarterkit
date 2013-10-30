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






package com.ibm.xsp.extlib.renderkit.html_extended.oneui.containers;

import com.ibm.xsp.extlib.renderkit.html_extended.containers.WidgetContainerRenderer;
import com.ibm.xsp.extlib.renderkit.html_extended.oneui.outline.tree.OneUIWidgetDropDownRenderer;
import com.ibm.xsp.extlib.resources.OneUIResources;


/**
 * OneUI Widget container renderer.
 */
public class OneUIWidgetContainerRenderer extends WidgetContainerRenderer {
    
    public OneUIWidgetContainerRenderer() {
    }

    @Override
    protected Object getProperty(int prop) {
        switch(prop) {
            case PROP_BLANKIMG:             return OneUIResources.get().BLANK_GIF;
            
            //Main
            case PROP_CSSWIDGETBASIC:       return "lotusWidget2"; // $NON-NLS-1$
            case PROP_CSSWIDGETSIDEBAR:     return "lotusWidget2"; // $NON-NLS-1$
            case PROP_CSSWIDGETPLAIN:       return "lotusWidget lotusWidgetPlain"; // $NON-NLS-1$
            case PROP_CONTAINER_STYLE_DEFAULT: return null;
            
            // Title Bar
            case PROP_TAGTITLE:             return "h2"; // $NON-NLS-1$
            case PROP_TAGTITLETEXT:         return "span"; // $NON-NLS-1$
            case PROP_CSSTITLETEXT:         return "lotusLeft"; // $NON-NLS-1$
            case PROP_CSSTITLEIMG:          return "lotusRight"; // $NON-NLS-1$
            case PROP_STYLETITLEBAR:        return "margin: 0px; cursor: auto;"; // Not movable.... $NON-NLS-1$
            case PROP_TREEDROPDOWN:         return new OneUIWidgetDropDownRenderer();
            // title bar looks ok when no text present - no need to insert nbsp 
            case PROP_TITLE_PREVENT_BLANK:  return false;
            case PROP_TWISTYCLASSLINK:      return "lotusSprite lotusArrow"; // $NON-NLS-1$
            case PROP_TWISTYCLASSIMGOPEN:   return "lotusTwistyOpen"; // $NON-NLS-1$
            case PROP_TWISTYCLASSIMGCLOSE:  return "lotusTwistyClosed"; // $NON-NLS-1$

            // Header
            case PROP_TAGHEADER:            return "h3"; // $NON-NLS-1$
            
            // Body
            case PROP_CSSBODY:              return "lotusWidgetBody"; // $NON-NLS-1$
            case PROP_CSSSCROLLUP:          return "lotusWidgetScroll"; // $NON-NLS-1$
            case PROP_CSSSCROLLUPLINK:      return "lotusSprite lotusArrow lotusScrollUp"; // $NON-NLS-1$
            
            //case PROP_CSSSCROLLUPALTTEXT:   return "&#9650;"; //"&#x25b2;"; //$NON-NLS-1$
            case PROP_CSSSCROLLUPALTTEXT:   return "\u25B2";  //$NON-NLS-1$
            
            
            case PROP_CSSSCROLLDOWN:        return "lotusWidgetScroll"; // $NON-NLS-1$
            case PROP_CSSSCROLLDOWNLINK:    return "lotusSprite lotusArrow lotusScrollDown"; // $NON-NLS-1$
            
           // case PROP_CSSSCROLLDOWNALTTEXT: return "&#9660;"; //"&#x25bc;"; //$NON-NLS-1$
            case PROP_CSSSCROLLDOWNALTTEXT: return "\u25BC"; //$NON-NLS-1$
            
            // body looks ok when no text present - no need to insert nbsp 
            case PROP_BODY_PREVENT_BLANK:   return false;
            
            // Footer
            case PROP_CSSFOOTER:            return "lotusWidgetFooter"; // $NON-NLS-1$
        }
        return super.getProperty(prop);
    }
}
