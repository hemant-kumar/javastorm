package com.javastorm.windows.model;

/**
 * This class is intended for providing information related to PluggedUsbDevice
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 03/03/2013
 */
public class PluggedUsbDevice 
{
	private String deviceId;
	private String pnpDeviceId;
	private String description;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getPnpDeviceId() {
		return pnpDeviceId;
	}
	
	public void setPnpDeviceId(String pnpDeviceId) {
		this.pnpDeviceId = pnpDeviceId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
