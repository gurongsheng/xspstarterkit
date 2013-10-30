/*
 * � Copyright IBM Corp. 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */

package com.ibm.xsp.extlib.social;


/**
 * Person resource.
 * @author Philippe Riand
 */
public interface Person extends Resource {

    // Person fields
    public static final String FIELD_ID             = "id";      // $NON-NLS-1$
    public static final String FIELD_NOTESID        = "notesId";     // $NON-NLS-1$
    public static final String FIELD_EMAIL          = "email";   // $NON-NLS-1$
    public static final String FIELD_DISPLAYNAME    = "displayName";     // $NON-NLS-1$
    public static final String FIELD_THUMBNAIL_URL  = "thumbnailUrl";    // $NON-NLS-1$

    // Pseudo fields
    public static final String FIELD_OWNER          = "owner";   // $NON-NLS-1$
    public static final String FIELD_VIEWER         = "viewer";      // $NON-NLS-1$
    
    public String getId();
    public String getIdentity(String target);
    public boolean isAuthenticatedUser();
    public String getDisplayName();
    
    public boolean isOwner();
    public boolean isViewer();
}