/*
 * � Copyright IBM Corp. 2009,2010
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
package com.ibm.dots.event;

import com.ibm.dots.tasklet.events.DotsEventParams;

/**
 * @author dtaieb
 * 
 */
public class NSFDbCopyNoteEvent extends AbstractEMEvent {
	private static DotsEventParams[] params = { DotsEventParams.SourceDbpath, DotsEventParams.Noteid, DotsEventParams.DestDbpath };

	@Override
	public DotsEventParams[] getParams() {
		return params;
	}

	private String destPathName;

	/**
	 * @param eventId
	 */
	public NSFDbCopyNoteEvent(int eventId) {
		super(eventId);
	}

	/**
	 * 
	 */
	public NSFDbCopyNoteEvent() {
		super(IExtensionManagerEvent.EM_NSFDBCOPYNOTE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.dots.event.AbstractEMEvent#parseEventBuffer(java.lang.String[])
	 */
	@Override
	protected boolean parseEventBuffer(String[] values) throws InvalidEventException {
		// sprintf( szBuffer, "%s,%x,%s", szSrcPathName, srcNoteID, szDestPathName );
		checkValues(values, 3);
		setDbPath(values[0]);
		setNoteId(values[1]);
		setDestPathName(values[2]);
		return true;
	}

	/**
	 * @param destPathName
	 */
	private void setDestPathName(String destPathName) {
		this.destPathName = destPathName;
	}

	/**
	 * @return
	 */
	public String getDestPathName() {
		return destPathName;
	}

}
