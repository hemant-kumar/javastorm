package com.javastorm.machineinfo.windows.model;

/**
 * This class is intended for providing information related to DvdDrive
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 03/03/2013
 */
public class DvdDrive 
{
	private String deviceId;
	private String deviceDescription;
	private String deviceName;
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getDeviceDescription() {
		return deviceDescription;
	}
	
	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
