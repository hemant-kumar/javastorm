package com.javastorm.machineinfo.windows.model;

/**
 * This class is intended for providing information related to DiskDrive
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 04/03/2013
 */
public class DiskDrive {
	private String deviceID;
	private String type;
	private String name;
	private String fileSystem;
	private long diskSize;
	private long freeDiskSpace;
	
	public String getDeviceID() {
		return deviceID;
	}
	
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFileSystem() {
		return fileSystem;
	}
	
	public void setFileSystem(String fileSystem) {
		this.fileSystem = fileSystem;
	}
	
	public long getDiskSize() {
		return diskSize;
	}
	
	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}
	
	public long getFreeDiskSpace() {
		return freeDiskSpace;
	}
	
	public void setFreeDiskSpace(long freeDiskSpace) {
		this.freeDiskSpace = freeDiskSpace;
	}
}
