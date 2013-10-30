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

import com.ibm.domino.services.rest.RestServiceEngine;
import com.ibm.domino.services.rest.das.view.RestViewItemFileService;
import com.ibm.xsp.extlib.resources.ExtLibResources;
import com.ibm.xsp.model.domino.DominoUtils;
import com.ibm.xsp.resource.DojoModuleResource;


/**
 * Content coming from a view.
 * 
 * @author Philippe Riand
 */
public class DominoViewItemFileService extends DominoViewService {

    public DominoViewItemFileService() {
    }
    
    @Override
    public String getStoreDojoType() {
        return "extlib.dojo.data.FileStore"; // $NON-NLS-1$
    }
    
    @Override
    public DojoModuleResource getStoreDojoModule() {
        return ExtLibResources.extlibFileStore;
    }
    
    @Override
    public void writeDojoStoreAttributes(FacesContext context, UIBaseRestService parent, ResponseWriter writer, String dojoType) throws IOException {
        String url = parent.getUrl(context,null);
        writer.writeAttribute("dojoType",dojoType,null); // $NON-NLS-1$
        writer.writeAttribute("url",url,null); // $NON-NLS-1$
    }

    
    ///////////////////////////////////////////////////////////////////////
    @Override
    public RestServiceEngine createEngine(FacesContext context, UIBaseRestService parent, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Parameters params = new Parameters(context, parent, httpRequest);
        return new Engine(httpRequest,httpResponse,params);
    }
    
    private class Engine extends RestViewItemFileService {
        Engine(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Parameters params) {
            super(httpRequest,httpResponse,params);
            setDefaultSession(DominoUtils.getCurrentSession());
            setDefaultDatabase(DominoUtils.getCurrentDatabase());
        }
        @Override
        public boolean queryNewDocument() {
            return DominoViewItemFileService.this.queryNewDocument();
        }
        @Override
        public boolean queryOpenDocument(String id) {
            return DominoViewItemFileService.this.queryOpenDocument(id);
        }
        @Override
        public boolean querySaveDocument(Document doc) {
            return DominoViewItemFileService.this.querySaveDocument(doc);
        }
        @Override
        public boolean queryDeleteDocument(String id) {
            return DominoViewItemFileService.this.queryDeleteDocument(id);
        }
        @Override
        public void postNewDocument(Document doc) {
            DominoViewItemFileService.this.postNewDocument(doc);
        }
        @Override
        public void postOpenDocument(Document doc)  {
            DominoViewItemFileService.this.postOpenDocument(doc);
        }
        @Override
        public void postSaveDocument(Document doc)  {
            DominoViewItemFileService.this.postSaveDocument(doc);
        }   
        @Override
        public void postDeleteDocument(String id) {
            DominoViewItemFileService.this.postDeleteDocument(id);
        }
    }
}
