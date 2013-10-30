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





package com.ibm.xsp.extlib.renderkit.html_extended.oneuiv302.data;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.extlib.component.data.AbstractDataView;
import com.ibm.xsp.extlib.renderkit.html_extended.oneui.data.OneUIForumViewRenderer;
import com.ibm.xsp.extlib.resources.OneUIResources;

public class OneUIv302ForumViewRenderer extends OneUIForumViewRenderer {
    
     @Override
        protected Object getProperty(int prop) {
            switch(prop) {
                case PROP_BLANKIMG:                 return OneUIResources.get().BLANK_GIF;
                // note, for an Alt, there's a difference between the empty string and null
                case PROP_BLANKIMGALT:              return ""; //$NON-NLS-1$
                case PROP_ALTTEXTCLASS:             return "lotusAltText";   // $NON-NLS-1$
                
                
                case PROP_HEADERCLASS:              return "lotusPaging lotusPagingTop"; // $NON-NLS-1$
                case PROP_HEADERLEFTSTYLE:          return null; 
                case PROP_HEADERLEFTCLASS:          return "lotusLeft"; // $NON-NLS-1$
                case PROP_HEADERRIGHTSTYLE:         return null; 
                case PROP_HEADERRIGHTCLASS:         return "lotusRight lotusInlinelist"; // $NON-NLS-1$

                case PROP_FOOTERCLASS:              return "lotusPaging"; // $NON-NLS-1$
                case PROP_FOOTERLEFTSTYLE:          return null; 
                case PROP_FOOTERLEFTCLASS:          return "lotusLeft lotusInlinelist"; // $NON-NLS-1$
                case PROP_FOOTERRIGHTSTYLE:         return null; 
                case PROP_FOOTERRIGHTCLASS:         return "lotusRight lotusInlineList"; // $NON-NLS-1$

               
            }
            return super.getProperty(prop);
        }
     
     @Override
    protected void writeHeaderPagerArea(FacesContext context, ResponseWriter w, AbstractDataView c, ViewDefinition viewDef, UIComponent pagerTop, UIComponent pagerTopLeft, UIComponent pagerTopRight) throws IOException {
            String tag = (String)getProperty(PROP_HEADER_PAGER_AREA_TAG);
            if(StringUtil.isNotEmpty(tag)) {
                startElement(w, tag, PROP_HEADERSTYLE, PROP_HEADERCLASS, PROP_HEADERDOJOTYPE);
                w.writeAttribute("role","navigation", null); // $NON-NLS-1$ $NON-NLS-2$
                w.writeAttribute("aria-label", com.ibm.xsp.extlib.oneui.ResourceHandler.getString("OneUIv302ForumViewRenderer.Paging"), null); // $NON-NLS-1$ $NLS-OneUIv302ForumViewRenderer.Paging-2$
            }
            writeHeaderLeft(context, w, c, viewDef, pagerTopLeft);
            writeHeaderMiddle(context, w, c, viewDef, pagerTop);
            writeHeaderRight(context, w, c, viewDef, pagerTopRight);
            if(StringUtil.isNotEmpty(tag)) {
                w.endElement(tag);
            }
            newLine(w);
        }

}
