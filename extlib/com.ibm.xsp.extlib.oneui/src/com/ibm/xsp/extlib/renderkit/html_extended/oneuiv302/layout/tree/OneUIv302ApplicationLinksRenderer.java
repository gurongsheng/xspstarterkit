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





package com.ibm.xsp.extlib.renderkit.html_extended.oneuiv302.layout.tree;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.component.UIViewRootEx;
import com.ibm.xsp.extlib.renderkit.html_extended.oneui.layout.tree.OneUIApplicationLinksRenderer;
import com.ibm.xsp.extlib.renderkit.html_extended.oneuiv302.OneUIv302Resources;
import com.ibm.xsp.extlib.renderkit.html_extended.oneuiv302.outline.tree.OneUIv302DojoMenuRenderer;
import com.ibm.xsp.extlib.resources.ExtLibResources;
import com.ibm.xsp.extlib.tree.ITreeNode;
import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.util.JSUtil;

public class OneUIv302ApplicationLinksRenderer extends OneUIApplicationLinksRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected String getContainerStyle(TreeContextImpl node) {
        return "";
    }
    
    @Override
    protected void renderChildren(FacesContext context, ResponseWriter writer, TreeContextImpl tree) throws IOException {
        int depth = tree.getDepth();
        if(depth==1) {
            super.renderChildren(context, writer, tree);
        } else {
            if(tree.getNode().getType()!=ITreeNode.NODE_LEAF) {
                String prefix = (String)getProperty(PROP_MENUPREFIX);
                OneUIv302DojoMenuRenderer r = new OneUIv302DojoMenuRenderer();
                String clientId = tree.getClientId(context,prefix,1); // $NON-NLS-1$
                
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
    
     @Override
        protected void preRenderTree(FacesContext context, ResponseWriter writer, TreeContextImpl tree) throws IOException {
         UIViewRootEx rootEx = (UIViewRootEx) context.getViewRoot();   
         ExtLibResources.addEncodeResource(rootEx, OneUIv302Resources.extlibMenu);
          
         super.preRenderTree(context, writer, tree);
            
     }

}
