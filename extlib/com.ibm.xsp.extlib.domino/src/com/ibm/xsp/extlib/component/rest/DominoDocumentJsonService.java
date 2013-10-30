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






package com.ibm.xsp.extlib.component.rest;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lotus.domino.Document;

import com.ibm.commons.util.StringUtil;
import com.ibm.domino.services.rest.RestServiceEngine;
import com.ibm.domino.services.rest.das.document.RestDocumentJsonService;
import com.ibm.xsp.ajax.AjaxUtil;
import com.ibm.xsp.extlib.resources.ExtLibResources;
import com.ibm.xsp.model.domino.DominoUtils;
import com.ibm.xsp.resource.DojoModuleResource;


/**
 * Content coming from a document.
 * 
 * @author Stephen Auriemma
 */
public class DominoDocumentJsonService extends DominoDocumentService {

    public DominoDocumentJsonService() {
    }
    
    @Override
    public String getStoreDojoType() {
        return "extlib.dojo.data.XPagesRestStore"; // $NON-NLS-1$
    }
    
    @Override
    public DojoModuleResource getStoreDojoModule() {
        return ExtLibResources.extlibXPagesRestStore;
    }
    
    @Override
    public void writeDojoStoreAttributes(FacesContext context, UIBaseRestService parent, ResponseWriter writer, String dojoType) throws IOException {
        String pathInfo = parent.getPathInfo();
        String url = parent.getUrlPath(context,pathInfo,null);
        writer.writeAttribute("dojoType",dojoType,null); // $NON-NLS-1$
        writer.writeAttribute("target",url,null); // $NON-NLS-1$
        
        // Create the extra parameters
        StringBuilder b = new StringBuilder();
        String targetId = parent.getAjaxTarget(context,pathInfo);
        if(StringUtil.isNotEmpty(targetId)) {
            b.append(b.length()==0?'?':'&');
            b.append(AjaxUtil.AJAX_AXTARGET);
            b.append('=');
            b.append(targetId);
        }
        String extraArgs = context.getExternalContext().encodeActionURL(b.toString());
        if(StringUtil.isNotEmpty(extraArgs)) {
            // remove the leading '?'
            writer.writeAttribute("extraArgs",extraArgs.substring(1),null); // $NON-NLS-1$
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    @Override
    public RestServiceEngine createEngine(FacesContext context, UIBaseRestService parent, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Parameters params = new Parameters(context, parent, httpRequest);
        return new Engine(httpRequest,httpResponse,params);
    }
    
    private class Engine extends RestDocumentJsonService {
        Engine(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Parameters params) {
            super(httpRequest,httpResponse,params);
            setDefaultSession(DominoUtils.getCurrentSession());
            setDefaultDatabase(DominoUtils.getCurrentDatabase());
        }
        @Override
        public boolean queryNewDocument() {
            return DominoDocumentJsonService.this.queryNewDocument();
        }
        @Override
        public boolean queryOpenDocument(String id) {
            return DominoDocumentJsonService.this.queryOpenDocument(id);
        }
        @Override
        public boolean querySaveDocument(Document doc) {
            return DominoDocumentJsonService.this.querySaveDocument(doc);
        }
        @Override
        public boolean queryDeleteDocument(String id) {
            return DominoDocumentJsonService.this.queryDeleteDocument(id);
        }
        @Override
        public void postNewDocument(Document doc) {
            DominoDocumentJsonService.this.postNewDocument(doc);
        }
        @Override
        public void postOpenDocument(Document doc)  {
            DominoDocumentJsonService.this.postOpenDocument(doc);
        }
        @Override
        public void postSaveDocument(Document doc)  {
            DominoDocumentJsonService.this.postSaveDocument(doc);
        }   
        @Override
        public void postDeleteDocument(String id) {
            DominoDocumentJsonService.this.postDeleteDocument(id);
        }
    }
}
