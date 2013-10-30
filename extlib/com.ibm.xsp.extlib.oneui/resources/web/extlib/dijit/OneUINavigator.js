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





dojo.provide("extlib.dijit.OneUINavigator");

dojo.require("dijit.hccss"); // ensure high contrast is detected

XSP.oneUIMenuSwap = function xe_ouimswp(evt, effect, sfid) {
	// check if we're in high contrast mode...
	var isHcMode = dojo.hasClass(dojo.body(), "dijit_a11y");
	
	// now get a handle on the next sibling <li> element...
	var tg = (window.event) ? evt.srcElement : evt.target;
	var ct;
	if(!isHcMode){
		// in non-hc mode, the next <li> is obtained via <a>.parentNode...
		for(ct = tg.parentNode.nextSibling; ct && ct.nodeType != 1; ) {
			ct = ct.nextSibling;
		}
	}else{
		// in hc mode, the next <li> is obtained via <span>.parentNode.parentNode...
		for(ct = tg.parentNode.parentNode.nextSibling; ct && ct.nodeType != 1; ) {
			ct = ct.nextSibling;
		}
	}
	
	if(ct){
		if(ct.style.display=="none") {
			if(effect=="wipe") {
				dojo.fx.wipeIn({node:ct}).play();
			} else {
				ct.style.display = "block";
			}		
			dojo.removeClass(tg, "lotusTwistyClosedMenu");
			dojo.addClass(tg, "lotusTwistyOpenMenu");
			
			if(isHcMode){
				tg.innerHTML = "&#9660;"; // down arrow
			}
			
			if (sfid) {
				var field = XSP.getElementById(sfid);
				if (field) {
					field.value = 1;
				}
			}
		} else {
			if(effect=="wipe") {
				dojo.fx.wipeOut({node:ct}).play();
			} else {
				ct.style.display = "none";
			}		
			dojo.removeClass(tg, "lotusTwistyOpenMenu");
			dojo.addClass(tg, "lotusTwistyClosedMenu");
			
			if(isHcMode){
				tg.innerHTML = "&#9658;"; // right arrow
			}
			
			if (sfid) {
				var field = XSP.getElementById(sfid);
				if (field) {
					field.value = 0;
				}
			}
		}
	}
};
