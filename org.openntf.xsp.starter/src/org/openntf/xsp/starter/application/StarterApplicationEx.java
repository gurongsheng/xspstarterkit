/*
 * � Copyright GBS Inc 2011
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
package org.openntf.xsp.starter.application;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;

import org.openntf.xsp.starter.Activator;
import org.openntf.xsp.starter.listeners.ApplicationListener;
import org.openntf.xsp.starter.listeners.SessionListener;
import org.openntf.xsp.starter.listeners.VFSEvent;

import com.ibm.commons.vfs.VFS;
import com.ibm.domino.xsp.module.nsf.ModuleClassLoader;
import com.ibm.xsp.application.ApplicationEx;
import com.ibm.xsp.application.DesignerApplicationEx;
import com.ibm.xsp.controller.FacesController;

public class StarterApplicationEx extends DesignerApplicationEx {
	private final static boolean _debug = Activator.isDebug();
	private final ApplicationListener _localAppListener = new ApplicationListener();
	private final SessionListener _localSessionListener = new SessionListener();
	private final VFSEvent _vfsEvent = new VFSEvent();
	static {
		if (_debug)
			System.out.println(StarterApplicationEx.class.getName() + " loaded");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.xsp.application.ApplicationExImpl#getController()
	 */
	@Override
	public FacesController getController() {
		// TODO Auto-generated method stub
		return super.getController();
	}

	protected StarterApplicationEx(Application paramApplication) {
		super(paramApplication);
		initListeners();
		if (_debug) {
			System.out.println(getClass().getName() + " created from delegate application " + paramApplication.getClass().getName());
			// System.out.println("PropertyResolver: " + getPropertyResolver().getClass().getName());
			// System.out.println("VariableResolver: " + getVariableResolver().getClass().getName());
			// System.out.println("NavigationHandler: " + getNavigationHandler().getClass().getName());
			// System.out.println("StateManager: " + this.getStateManager().getClass().getName());
			// System.out.println("ViewHandler: " + this.getViewHandler().getClass().getName());
			// System.out.println("FacesController: " + (this.getController() == null ? "null" :
			// this.getController().getClass().getName()));

			// com.ibm.xsp.application.StateManagerImpl
			// com.ibm.xsp.application.ViewHandlerExImpl
			// com.ibm.xsp.el.PropertyResolverImpl
			// com.ibm.xsp.el.VariableResolverImpl
			// com.ibm.xsp.application.NavigationHandlerImpl
			// com.ibm.designer.runtime.domino.adapter.ComponentModule cm;
			// com.ibm.xsp.webapp.DesignerFacesServlet dfs;

		}
	}

	protected StarterApplicationEx(ApplicationEx paramApplication) {
		super(paramApplication);
		initListeners();
		if (_debug) {
			System.out.println(getClass().getName() + " created from ApplicationEx");
			// System.out.println("PropertyResolver: " + getPropertyResolver().getClass().getName());
			// System.out.println("VariableResolver: " + getVariableResolver().getClass().getName());
			// System.out.println("NavigationHandler: " + getNavigationHandler().getClass().getName());
		}

	}

	private void initListeners() {
		this.addApplicationListener(_localAppListener);
		_localAppListener.applicationCreated(this);
		this.addSessionListener(_localSessionListener);
		com.ibm.designer.runtime.Application designApp = getDesignerApplication();
		ModuleClassLoader nsfLoader = (ModuleClassLoader) designApp.getClassLoader();
		VFS vfs = designApp.getVFS();
		vfs.addVFSEventListener(_vfsEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.xsp.application.ApplicationExImpl#getViewHandler()
	 */
	@Override
	public ViewHandler getViewHandler() {
		// TODO Auto-generated method stub
		return super.getViewHandler();
	}

}
