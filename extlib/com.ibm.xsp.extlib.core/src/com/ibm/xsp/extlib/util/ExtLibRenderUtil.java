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





/*
* Author: Maire Kehoe (mkehoe@ie.ibm.com)
* Date: 17 Oct 2011
* ExtLibRenderUtil.java
*/
package com.ibm.xsp.extlib.util;

import com.ibm.xsp.util.JSUtil;

/**
 * 
 */
public class ExtLibRenderUtil {

    /**
     * Note, do not use StringUtil.isNotEmpty for image alt text
     * because for accessibility reasons there's a difference
     * between alt="" and no alt attribute set.
     * @param alt
     * @return
     */
    public static boolean isAltPresent(String alt) {
        // so we treat null and "" as different for alt.
        // TODO throughout the ExtLib verify that alt="" is supported
        // and that we're not using StringUtil.isNotEmpty nor !StringUtil.isEmpty
        return null != alt;
    }
    
    /**
     * Note, do not use StringUtil.isNotEmpty for table simmary text
     * because for accessibility reasons there's a difference
     * between summary="" and no summary attribute set.
     * @param summary
     * @return
     */
    public static boolean isSummaryPresent(String summary) {
        // so we treat null and "" as different for summary.
        // TODO throughout the ExtLib verify that summary="" is supported
        // and that we're not using StringUtil.isNotEmpty nor !StringUtil.isEmpty
        return null != summary;
    }
    /**
     * Like {@link JSUtil#addString(StringBuilder, String)} except using ' instead of ".
     * Only used for strings in javascript: values in HTML attributes. 
     * This will be deprecated in the Extlib 9.0.1 stream, 
     * when the 9.0.1 JSUtil has a copy of method.
     * @param b
     * @param s
     * @deprecated Deprecated in 9.0.1 and later - use {@link JSUtil#addSingleQuoteString(StringBuilder, String)} instead. 
     */
    @Deprecated
    public static void addSingleQuoteString(StringBuilder b, String s) {
        JSUtil.addSingleQuoteString(b, s);
    }
    
}
