/* ***************************************************************** */
/* Copyright IBM Corp. 1985, 2013 All Rights Reserved                */






dojo.provide("dwa.lv.miscs");

dojo.require("dwa.lv.globals");
dojo.require("dwa.lv.notesListViewProfileCache");

dwa.lv.miscs = {};

dwa.lv.miscs.getNotesListViewProfileCache = function(){
	    if(!dwa.lv.globals.get().oViewProfileCache)
	        dwa.lv.globals.get().oViewProfileCache = new dwa.lv.notesListViewProfileCache();
	    return dwa.lv.globals.get().oViewProfileCache;
};

dwa.lv.miscs.convertSnapshot = function(oResult){
		var aoResults = [];
		for (var i = 0; i < oResult.snapshotLength; i++)
			aoResults[i] = oResult.snapshotItem(i);
		return aoResults;
};

dwa.lv.miscs.deserialize = function(sXml){
	 if( dojo.isMozilla || dojo.isWebKit ){
			var parser = new DOMParser();
			oXmlDoc = parser.parseFromString(sXml, "text/xml");
			if (oXmlDoc.firstChild.tagName == 'parsererror') {
				throw new Error(oXmlDoc.firstChild.textContent);
			}
	 }else{ // GS
			var oXmlDoc = new ActiveXObject("MSXML2.DOMDocument");
			oXmlDoc.async=0;
			oXmlDoc.resolveExternals = 0;
			if(!oXmlDoc.loadXML(sXml)){
	  			var err = oXmlDoc.parseError;
	  			var sReason = err.reason + "Location " + oXmlDoc.url + " Line Number " + err.line + " Column " + err.linepos;
	  			dwa.lv.globals.get().oStatusManager.addEntry(0, '', sReason, true);
	  			throw new Error(sReason);
			}
	 } // end - I
		return oXmlDoc;
};

