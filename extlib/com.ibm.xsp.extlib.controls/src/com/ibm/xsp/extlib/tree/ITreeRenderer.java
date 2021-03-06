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

package com.ibm.xsp.extlib.tree;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


/**
 * Interface that defines a Tree renderer.
 * @author Philippe Riand
 */
public interface ITreeRenderer extends Serializable {

	public void render(FacesContext context, UIComponent component, ITree tree, ResponseWriter writer) throws IOException;
	public void render(FacesContext context, ResponseWriter writer, ITreeNode.TreeContext treeContext) throws IOException;	
}
