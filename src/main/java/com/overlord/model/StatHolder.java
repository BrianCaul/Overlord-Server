/**
 * 
 */
package com.overlord.model;

import java.util.List;

/**
 * @author bcaul
 *
 */
public class StatHolder {

	private List<String> hours;
	private List<Integer> entries;
	/**
	 * @return the hours
	 */
	public List<String> getHours() {
		return hours;
	}
	/**
	 * @param hours the hours to set
	 */
	public void setHours(List<String> hours) {
		this.hours = hours;
	}
	/**
	 * @return the entries
	 */
	public List<Integer> getEntries() {
		return entries;
	}
	/**
	 * @param entries the entries to set
	 */
	public void setEntries(List<Integer> entries) {
		this.entries = entries;
	}
	
}
