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






package com.ibm.xsp.extlib.renderkit.html_extended.oneui.layout.tree;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.extlib.renderkit.html_extended.oneui.outline.tree.OneUITreePopupMenuRenderer;
import com.ibm.xsp.extlib.renderkit.html_extended.outline.tree.DojoMenuRenderer;
import com.ibm.xsp.extlib.resources.OneUIResources;
import com.ibm.xsp.extlib.tree.ITreeNode;
import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.renderkit.html_basic.HtmlRendererUtil;
import com.ibm.xsp.util.JSUtil;


public class OneUIPlaceBarActionsRenderer extends OneUITreePopupMenuRenderer {
    
    private static final long serialVersionUID = 1L;

    public OneUIPlaceBarActionsRenderer() {
    }

    @Override
    protected void renderPopupButton(FacesContext context, ResponseWriter writer, TreeContextImpl tree, boolean enabled, boolean selected) throws IOException {
        writer.startElement("a",null);
        
        // A popup button requires an id
        String clientId = tree.getClientId(context,"ab",1); // $NON-NLS-1$
        if(StringUtil.isNotEmpty(clientId)) {
            writer.writeAttribute("id", clientId, null); // $NON-NLS-1$
        }
        
        writer.writeAttribute("href", "javascript:;", null); // $NON-NLS-2$ $NON-NLS-1$

        String image = tree.getNode().getImage();
        boolean hasImage = StringUtil.isNotEmpty(image);
        if(hasImage) {
            writer.startElement("img",null); // $NON-NLS-1$
            if(StringUtil.isNotEmpty(image)) {
                image = HtmlRendererUtil.getImageURL(context, image);
                writer.writeAttribute("src",image,null); // $NON-NLS-1$
                String imageAlt = tree.getNode().getImageAlt();
                if (StringUtil.isNotEmpty(imageAlt)) {
                    writer.writeAttribute("alt",imageAlt,null); // $NON-NLS-1$
                }
                String imageHeight = tree.getNode().getImageHeight();
                if (StringUtil.isNotEmpty(imageHeight)) {
                    writer.writeAttribute("height",imageHeight,null); // $NON-NLS-1$
                }
                String imageWidth = tree.getNode().getImageWidth();
                if (StringUtil.isNotEmpty(imageWidth)) {
                    writer.writeAttribute("width",imageWidth,null); // $NON-NLS-1$
                }
            }
            writer.endElement("img"); // $NON-NLS-1$
        }
        
        // Render the text
        String label = tree.getNode().getLabel();
        if(StringUtil.isNotEmpty(label)) {
            writer.writeText(label, "label"); // $NON-NLS-1$
        }
        writer.writeText(" ",null); // $NON-NLS-1$

        // Render the popup image (down arrow)
        // Uniquely if it has multiple choices
        if(tree.getNode().getType()!=ITreeNode.NODE_LEAF) {
            writer.startElement("img",null); // $NON-NLS-1$
            //writer.writeAttribute("class","yourProductSprite yourProductSprite-btnDropDown2",null); // $NON-NLS-1$ $NON-NLS-2$
            writer.writeAttribute("src",HtmlRendererUtil.getImageURL(context,OneUIResources.get().DROPDOWN_PNG),null); // $NON-NLS-1$
            writer.writeAttribute("aria-label",com.ibm.xsp.extlib.oneui.ResourceHandler.getString("OneUIPlaceBarActionsRenderer.ShowMenu"),null);  // $NON-NLS-1$ $NLS-OneUIPlaceBarActionsRenderer.ShowMenu-2$
            writer.writeAttribute("alt",com.ibm.xsp.extlib.oneui.ResourceHandler.getString("OneUIPlaceBarActionsRenderer.ShowMenu.1"),null);  // $NON-NLS-1$ $NLS-OneUIPlaceBarActionsRenderer.ShowMenu.1-2$
            writer.endElement("img"); // $NON-NLS-1$
            writer.startElement("span",null); // $NON-NLS-1$
            writer.writeAttribute("class","lotusAltText",null); // $NON-NLS-1$ $NON-NLS-2$
            writer.writeText(com.ibm.xsp.extlib.oneui.ResourceHandler.getString("OneUIPlaceBarActionsRenderer.u25BC"), null); // $NLS-OneUIPlaceBarActionsRenderer.u25BC-1$
            writer.endElement("span"); // $NON-NLS-1$
        }

        writer.endElement("a");
        JSUtil.writeln(writer);
    }
    
    @Override
    protected String getContainerStyleClass(TreeContextImpl node) {
        return "lotusBtnContainer"; // $NON-NLS-1$
    }
    
    @Override
    protected String getItemStyleClass(TreeContextImpl tree, boolean enabled, boolean selected) {
        String value = "lotusBtn"; // $NON-NLS-1$
        String s = super.getItemStyleClass(tree,enabled,selected);
        return ExtLibUtil.concatStyleClasses(value, s);
    }
    
    @Override
    protected void renderChildren(FacesContext context, ResponseWriter writer, TreeContextImpl tree) throws IOException {
        int depth = tree.getDepth();
        if(depth==1) {
            super.renderChildren(context, writer, tree);
        } else {
            if(tree.getNode().getType()!=ITreeNode.NODE_LEAF) {
                DojoMenuRenderer r = new DojoMenuRenderer();
                String clientId = tree.getClientId(context,"ab",1); // $NON-NLS-1$
                
                String mid = clientId+"_mn"; // $NON-NLS-1$
                r.setMenuId(mid);

                if(StringUtil.isNotEmpty(clientId)) {
                    r.setConnectId(clientId);
                }

                r.setConnectEvent("onclick"); // $NON-NLS-1$
                r.render(context, writer, tree);
            }
        }
    }
}
