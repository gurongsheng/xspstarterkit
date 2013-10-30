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






package com.ibm.domino.commons.model;

import java.util.Date;

/**
 * Out of Office Status
 *
 */
public class OooStatus {
    
    private boolean _enabled;
    private Date _start;
    private Date _end;
    private String _subject;
    private String _message;
    private boolean _sendExternal;
    private boolean _appendReturnToSubject;
    
    public OooStatus(boolean enabled, Date start, Date end, String subject, String message,
            boolean sendExternal, boolean appendReturnToSubject) {
        
        _enabled = enabled;
        _start = start;
        _end = end;
        _subject = subject;
        _message = message;
        _sendExternal = sendExternal;
        _appendReturnToSubject = appendReturnToSubject;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return _enabled;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return _start;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return _end;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return _subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return _message;
    }

    /**
     * @return the sendExternal
     */
    public boolean isSendExternal() {
        return _sendExternal;
    }

    /**
     * @return the appendReturnToSubject
     */
    public boolean isAppendReturnToSubject() {
        return _appendReturnToSubject;
    }

}
