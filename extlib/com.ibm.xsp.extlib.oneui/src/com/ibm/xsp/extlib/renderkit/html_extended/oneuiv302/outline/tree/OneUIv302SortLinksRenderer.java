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





package com.ibm.xsp.extlib.renderkit.html_extended.oneuiv302.outline.tree;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.extlib.renderkit.html_extended.oneui.outline.tree.OneUISortLinksRenderer;
import com.ibm.xsp.extlib.tree.ITreeNode;
import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.util.JSUtil;

public class OneUIv302SortLinksRenderer extends OneUISortLinksRenderer {
    
    private static final long serialVersionUID = 1L;

    public OneUIv302SortLinksRenderer() {
    }

    public OneUIv302SortLinksRenderer(UIComponent component) {
        super(component);
    }
    
    @Override
    protected void preRenderList(FacesContext context, ResponseWriter writer, TreeContextImpl tree) throws IOException {
        writer.startElement("div", null); // $NON-NLS-1$
        
        String style = getContainerStyle(tree);
        if(StringUtil.isNotEmpty(style)) {
            writer.writeAttribute("style",style,null); // $NON-NLS-1$
        }
        String styleClass =(String)getProperty(PROP_SORTLINKS_SORT);
        if(StringUtil.isNotEmpty(styleClass)) {
            writer.writeAttribute("class",styleClass,null); // $NON-NLS-1$
        }

        startRenderContainer(context, writer, tree);
    }
    
    @Override
    protected void startRenderContainer(FacesContext context, ResponseWriter writer, TreeContextImpl tree) throws IOException {
        String containerTag = getContainerTag();
        if(StringUtil.isNotEmpty(containerTag)) {
            writer.startElement(containerTag,null);
            String style = null;
            String styleClass = null;
            if(tree.getDepth()==1) {
                // ac: LHEY92PFY3 - A11Y | RPT | xc:viewMenu : ID values must be unique
                if (!tree.isOuterTagEmitted()) {
                    String id = getClientId(context,tree);
                    if(StringUtil.isNotEmpty(id)) {
                        writer.writeAttribute("id",id,null); // $NON-NLS-1$
                    }
                }
                UIComponent c = tree.getComponent();
                if(c!=null) {
                    style = (String)c.getAttributes().get("style"); //$NON-NLS-1$
                    styleClass = (String)c.getAttributes().get("styleClass");//$NON-NLS-1$
                }
            }
            style = ExtLibUtil.concatStyles(style,getContainerStyle(tree));
            if(StringUtil.isNotEmpty(style)) {
                writer.writeAttribute("style",style,null); // $NON-NLS-1$
            }
            styleClass = ExtLibUtil.concatStyleClasses(styleClass,getContainerStyleClass(tree));
            if(StringUtil.isNotEmpty(styleClass)) {
                writer.writeAttribute("class",styleClass,null); // $NON-NLS-1$
            }
            writer.writeAttribute("role", "toolbar", null); // $NON-NLS-1$ $NON-NLS-2$
            writer.writeAttribute("aria-label", com.ibm.xsp.extlib.oneui.ResourceHandler.getString("OneUIv302SortLinksRenderer.sort"), null); // $NON-NLS-1$ $NLS-OneUIv302SortLinksRenderer.sort-2$
            
            JSUtil.writeln(writer);
        }
    }
    
    @Override
    protected void renderEntryItemLinkAttributes(FacesContext context, ResponseWriter writer, TreeContextImpl tree, boolean enabled, boolean selected) throws IOException {
        
        if(tree.getNode().getType()==ITreeNode.NODE_LEAF) {
            writer.writeAttribute("class", (String)getProperty(PROP_SORTLINKS_ACTIVESORT), null); // $NON-NLS-1$
        } else {
            writer.writeAttribute("class", (String)getProperty(PROP_SORTLINKS_MORESORTS), null); // $NON-NLS-1$
        }
        writer.writeAttribute("role", "button", null); // $NON-NLS-1$ $NON-NLS-2$
        super.renderEntryItemLinkAttributes(context, writer, tree, enabled, selected);
    }
    
    @Override
    protected String getItemRole(TreeContextImpl tree, boolean enabled, boolean selected) {
        return tree.getNode().getRole();
    }
    

}
