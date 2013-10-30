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

package com.ibm.xsp.extlib.component.dojoext.form;

import com.ibm.xsp.extlib.stylekit.StyleKitExtLibDefault;
import com.ibm.xsp.extlib.util.ThemeUtil;


/**
 * Dojo component used to input user name. 
 * <p>
 * This is component is holding the value into a hidden field and displaying
 * then as an HTML list. Only the common name of the user is displayed in the
 * list.<br>
 * This is a custom dojo component.
 * </p>
 * @author Philippe Riand
 */
public class UIDojoExtNameTextBox extends AbstractDojoExtListTextBox {

    public static final String COMPONENT_TYPE = "com.ibm.xsp.extlib.dojoext.form.NameTextBox"; // $NON-NLS-1$
    public static final String RENDERER_TYPE = "com.ibm.xsp.extlib.dojoext.form.NameTextBox"; //$NON-NLS-1$
    
    public UIDojoExtNameTextBox() {
        setRendererType(RENDERER_TYPE);
    }

    @Override
    public String getStyleKitFamily() {
        return StyleKitExtLibDefault.DOJO_FORM_NAMETEXTBOX;
    }
}