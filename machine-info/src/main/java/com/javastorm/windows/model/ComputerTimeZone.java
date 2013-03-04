package com.javastorm.windows.model;

/**
 * This class is intended for providing information related to ComputerTimeZone
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 04/03/2013
 */
public class ComputerTimeZone {
    private String description;
    private String daylightName;
    private String standardName;
    
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDaylightName() {
		return daylightName;
	}
	
	public void setDaylightName(String daylightName) {
		this.daylightName = daylightName;
	}
	
	public String getStandardName() {
		return standardName;
	}
	
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
}
