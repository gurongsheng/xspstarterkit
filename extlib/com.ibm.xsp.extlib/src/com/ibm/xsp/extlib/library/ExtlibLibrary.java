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

package com.ibm.xsp.extlib.library;

import java.util.ArrayList;
import java.util.List;

import com.ibm.xsp.extlib.config.ControlsConfig;
import com.ibm.xsp.extlib.config.DominoConfig;
import com.ibm.xsp.extlib.config.ExtlibPluginConfig;
import com.ibm.xsp.extlib.config.MobileConfig;
import com.ibm.xsp.extlib.config.OneUIConfig;
import com.ibm.xsp.extlib.version.ExtlibVersion;
import com.ibm.xsp.library.AbstractXspLibrary;

/**
 * 
 */
public class ExtlibLibrary extends AbstractXspLibrary {

	private List<ExtlibPluginConfig> plugins;

	public ExtlibLibrary() {

	}

	// @Override
	// public Version getDojoVersion() {
	// return new DojoLibrary.Version(1,6,0);
	// }

	@Override
	public String getLibraryId() {
		return "com.ibm.xsp.extlib.library"; // $NON-NLS-1$
	}

	// Note, Extlib is not global scope (see superclass.isGlobalScope).

	@Override
	public String getPluginId() {
		return "com.ibm.xsp.extlib"; // $NON-NLS-1$
	}

	@Override
	public String[] getDependencies() {
		return new String[] { "com.ibm.xsp.core.library", // $NON-NLS-1$
				"com.ibm.xsp.extsn.library", // $NON-NLS-1$
				"com.ibm.xsp.domino.library" // $NON-NLS-1$
		};
	}

	@Override
	public String[] getXspConfigFiles() {
		String[] files = new String[] {};
		List<ExtlibPluginConfig> plugins = getExtlibPluginConfigs();
		for (ExtlibPluginConfig plugin : plugins) {
			files = plugin.getXspConfigFiles(files);
		}
		return files;
	}

	@Override
	public String[] getFacesConfigFiles() {
		String[] files = new String[] {};
		List<ExtlibPluginConfig> plugins = getExtlibPluginConfigs();
		for (ExtlibPluginConfig plugin : plugins) {
			files = plugin.getFacesConfigFiles(files);
		}
		return files;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.xsp.library.AbstractXspLibrary#getTagVersion()
	 */
	@Override
	public String getTagVersion() {
		return ExtlibVersion.getCurrentVersionString();
	}

	private List<ExtlibPluginConfig> getExtlibPluginConfigs() {
		if (plugins == null) {
			// List<ExtlibPluginConfig> _plugins =
			// ExtensionManager.findServices(null,
			// ExtlibPluginConfig.class.getClassLoader(),
			// ExtlibPluginConfig.EXTENSION_NAME,
			// ExtlibPluginConfig.class);
			/*
			 * We are blocking any extension point contribution to the Library.
			 * Agreed to do this way on Sep 30th, 2011 [AC, MK, PR]
			 */
			List<ExtlibPluginConfig> _plugins = new ArrayList<ExtlibPluginConfig>();
			// note, sort these plugins alphabetically by className
			_plugins.add(new ControlsConfig());
			_plugins.add(new DominoConfig());
			_plugins.add(new MobileConfig());
			_plugins.add(new OneUIConfig());
			plugins = _plugins;
		}
		return plugins;
	}

	/**
	 * @deprecated does nothing
	 */
	@Deprecated
	public void installResources() {
	}

	/**
	 * In 9.0.0 this method overrides a method in the superclass, in 8.5.3 the
	 * superclass method doesn't exist yet.
	 */
	@Override
	public boolean isTagVersionUseMicroUnderscore() {
		return true;
	}

	@Override
	public Boolean isCompareTagVersionQualifier() {
		// True meaning "9.0.0.20120920" != "9.0.0.20120930"
		return Boolean.TRUE;
	}

	@Override
	public int getBaseXspLibraryVersion() {
		// 4 meaning based on the version of AbstractXspLibrary
		// with extra methods isCompareTagVersionQualifier() and
		// getBaseXspLibraryVersion(), added in 9.0.0.
		return 4;
	}

}
