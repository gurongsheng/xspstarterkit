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






package com.ibm.xsp.extlib.renderkit.html_basic.calendar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.component.UIViewRootEx;
import com.ibm.xsp.extlib.component.calendar.UICalendarView;
import com.ibm.xsp.extlib.component.domino.ExtlibJsIdUtil;
import com.ibm.xsp.extlib.resources.domino.DojoResourceConstants;
import com.ibm.xsp.extlib.resources.domino.DojoResources;
import com.ibm.xsp.renderkit.FacesRenderer;

/**
 * @author akosugi
 * 
 *        renderer for calendar view
 */
public class CalendarViewRenderer extends FacesRenderer {

    @Override
    public void decode(FacesContext facescontext, UIComponent uicomponent) {
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
        ResponseWriter w = context.getResponseWriter();
        UICalendarView uiComponent = (UICalendarView) component;
        boolean rendered = component.isRendered();
        if (!rendered)
            return;

        UIViewRootEx rootEx = (UIViewRootEx) context.getViewRoot();
        rootEx.addEncodeResource(DojoResources.calendarView);
        rootEx.addEncodeResource(DojoResources.calendarViewCSS);
        rootEx.addEncodeResource(DojoResources.datepickCSS);
        rootEx.setDojoParseOnLoad(true);
        rootEx.setDojoTheme(true);

        //TODO may need to support multiple store.
        String store = ExtlibJsIdUtil.findDojoWidgetId(context, uiComponent, uiComponent.getStoreComponentId());

        w.startElement("div", uiComponent); // $NON-NLS-1$
        if(StringUtil.isNotEmpty(store))
            w.writeAttribute("store", store, null); // $NON-NLS-1$
        String style = uiComponent.getStyle();
        if (StringUtil.isNotEmpty(style))
            w.writeAttribute("style", style, null); // $NON-NLS-1$
        String classname = uiComponent.getStyleClass();
        if (StringUtil.isNotEmpty(classname))
            w.writeAttribute("class", classname, null); // $NON-NLS-1$
        // Always write the id since Dojo-based controls need an ID. 
        w.writeAttribute("id", uiComponent.getClientId(context), null); // $NON-NLS-1$
        String jsId = uiComponent.getDojoWidgetJsId(context);
        if (StringUtil.isNotEmpty(jsId))
            w.writeAttribute("jsId", jsId, null); // $NON-NLS-1$
        String type = uiComponent.getType();
        w.writeAttribute(DojoResourceConstants.dojoType,
                DojoResourceConstants.calendarView, null);
        if (StringUtil.isNotEmpty(type))
            w.writeAttribute("type", type, null); // $NON-NLS-1$
        w.writeAttribute("summarize", uiComponent.isSummarize(), // $NON-NLS-1$
                null);
        w.writeAttribute("tabindex", "0", null); // $NON-NLS-1$
        w.writeAttribute("role", "grid", null); // $NON-NLS-1$ //$NON-NLS-2$
        w.writeAttribute("aria-label", com.ibm.xsp.extlib.domino.ResourceHandler.getString("CalendarViewRenderer.CalendarView"), null); // $NON-NLS-1$ $NLS-CalendarViewRenderer.CalendarView-2$
        
        Date date = uiComponent.getDate();
        if (null != date){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.setTimeZone(TimeZone.getTimeZone("UTC")); // $NON-NLS-1$
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ssZ"); // $NON-NLS-1$
            String datetext = sdf.format(cal.getTime());
            w.writeAttribute("date", datetext, null); // $NON-NLS-1$
        }
        
        uiComponent.writeActionHandlerScripts(w);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent uicomponent)
            throws IOException {
        ResponseWriter w = context.getResponseWriter();
        w.endElement("div"); // $NON-NLS-1$
    }

    @Override
    public void encodeChildren(FacesContext facescontext,
            UIComponent uicomponent) throws IOException {

    }
}
