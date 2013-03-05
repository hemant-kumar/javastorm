package com.javastorm.windows.model;

/**
 * This class is intended for providing information related to NotWorkingDevice
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 03/03/2013
 */
public class NotWorkingDevice 
{
	private String classGUID;
	private String description;
	private String deviceId;
	private String manufacturer;
	private String deviceName;
	private String pnpDevice;
	private String service;
	
	public String getClassGUID() {
		return classGUID;
	}
	
	public void setClassGUID(String classGUID) {
		this.classGUID = classGUID;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getPnpDevice() {
		return pnpDevice;
	}
	
	public void setPnpDevice(String pnpDevice) {
		this.pnpDevice = pnpDevice;
	}
	
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
}
