/*
 * GENERATED FILE
 * Created on Thu May 02 07:48:07 BST 2024
 *
 */
package org.nrg.xdat.model;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author XDAT
 *
 */
public interface NirsNirsscandataI extends XnatImagescandataI {

	public String getXSIType();

	public void toXML(java.io.Writer writer) throws java.lang.Exception;

	/**
	 * @return Returns the task.
	 */
	public String getTask();

	/**
	 * Sets the value for task.
	 * @param v Value to Set.
	 */
	public void setTask(String v);
}
