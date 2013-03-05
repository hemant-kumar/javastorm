package com.javastorm.windows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.javastorm.windows.model.DvdDrive;
import com.javastorm.windows.model.NotWorkingDevice;
import com.javastorm.windows.model.PluggedUsbDevice;
import com.javastorm.windows.model.PointingDevice;

/**
 * This class is intended for providing information related to Windows Machine
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 03/03/2013
 */
public class WindowsMachine 
{
	/**
	 * This function provides MAC Address of the System 
	 * @return MAC Address
	 * @throws Exception
	 */
	public static ArrayList<String> getMacAddress() throws Exception {
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery " +
				"_ \n   (\"Select * from Win32_NetworkAdapterConfiguration\") \nFor Each objItem in colItems \n" +
				"if objItem.IPEnabled = 0 And objItem.ServiceName <> \"VMnetAdapter\" And isNull(objItem.MACAddress) = 0 " +
				"Then \n    Wscript.Echo objItem.MACAddress   \n   End if  \nNext \n";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = br.readLine();
		ArrayList<String> list = new ArrayList<String>();
		while(result != null) {
			list.add(result.trim());
			result = br.readLine();
		}
		br.close();
		return list;
	}

	/**
	 * This function provides Serial No of Processor 
	 * @return Processor Serial No
	 * @throws Exception
	 */
	public static String getProcessorSerialNo() throws Exception {
		String serialNo = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\") \n " +
					 "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\") \n " +
					 "For Each objItem in colItems \n Wscript.Echo objItem.ProcessorId \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = input.readLine();
		while(line != null) {
			serialNo = serialNo + line.trim();
			line = input.readLine();
		}
		input.close();
		return serialNo;
	}

	/**
	 * This function provides Serial No of Hard Disk 
	 * @return Hard Disk Serial No
	 * @throws Exception
	 */
	public static String getHardDiskSerialNo() throws Exception {
		String serialNo = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery" +
				" _ \n   (\"Select * from Win32_PhysicalMedia\") \nFor Each objItem in colItems \n    " +
				"Wscript.Echo objItem.SerialNumber  \n    exit for  ' do the first cpu only! \nNext \n";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null)
			serialNo = serialNo + result.trim();
		input.close();
		return serialNo;
	}

	/**
	 * This function provides Serial No of BIOS 
	 * @return BIOS Serial No
	 * @throws Exception
	 */
	public static String getBiosSerialNo() throws Exception {
		String serialNo = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = " +
				"objWMIService.ExecQuery _ \n   (\"Select * from Win32_BIOS\") \nFor Each objItem in colItems \n " +
				"   Wscript.Echo objItem.SerialNumber  \n    exit for  ' do the first cpu only! \nNext \n";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			serialNo = result.trim();
		input.close();
		return serialNo;
	}

	/**
	 * This function provides OEM name 
	 * @return OEM Name
	 * @throws Exception
	 */
	public static String getOEMName() throws Exception {
		String oemName = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\") \nSet colItems = objWMIService.ExecQuery " +
				"_ \n(\"Select * from Win32_BIOS\") \nFor Each objItem in colItems \nWscript.Echo objItem.Manufacturer " +
				"\nexit for  ' do the first cpu only! \nNext";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		while(result != null) {
			oemName = oemName + result.trim();
			result = input.readLine();
		}
		String[] invalid = { "Not Available", "NA", "To be filled by", "Provided by" };
		for(int i = 0; i < invalid.length; i++) {
			if((invalid[i].equalsIgnoreCase(oemName)) || (invalid[i].startsWith(oemName)) || (invalid[i].contains(oemName)))
				oemName = "";
		}
		return oemName;
	}

	/**
	 * This function provides Free Physical Memory 
	 * @return Free Physical Memory in bytes
	 * @throws Exception
	 */
	public static long getFreePhysicalMemory() throws Exception {
		String memory = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\") " + "\n" +
					 "Set colSettings = objWMIService.ExecQuery (\"Select * from Win32_OperatingSystem\") " + "\n" +
					 "For Each objOperatingSystem in colSettings \n Wscript.Echo objOperatingSystem.FreePhysicalMemory \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			memory = result.trim();
		input.close();
		return Long.parseLong(memory);
	}
	
	/**
	 * This function provides Total Physical Memory 
	 * @return Total Physical Memory in bytes
	 * @throws Exception
	 */
	public static long getTotalPhysicalMemory() throws Exception {
		String memory = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\") \n" + 
					 "Set colSettings = objWMIService.ExecQuery (\"Select * from Win32_ComputerSystem\") \n" +
					 "For Each objComputer in colSettings \n Wscript.Echo objComputer.TotalPhysicalMemory \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			memory = result.trim();
		input.close();
		return Long.parseLong(memory);
	}
	
	/**
	 * This function provides DVD Drive info 
	 * @return DVD Drive info
	 * @throws Exception
	 */
	public static ArrayList<DvdDrive> getDvdDriveInfo() throws Exception {
		ArrayList<DvdDrive> list = new ArrayList<DvdDrive>();
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\") \n " +
					 "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_CDROMDrive\") \n " +
					 "For Each objItem in colItems \n Wscript.Echo objItem.DeviceID \n " +
					 "Wscript.Echo objItem.Description \n Wscript.Echo objItem.Name \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = br.readLine();
		while(result != null) {
			DvdDrive drive = new DvdDrive();
			drive.setDeviceId(result.trim());
			result = br.readLine();
			drive.setDeviceDescription(result.trim());
			result = br.readLine();
			drive.setDeviceName(result.trim());
			list.add(drive);
			result = br.readLine();
		}
		br.close();
		return list;
	}
	
	/**
	 * This function provides No of Processors 
	 * @return No of Processors
	 * @throws Exception
	 */
	public static int getNoOfProcessors() throws Exception {
		String processors = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\") \n" +
					 "Set colSettings = objWMIService.ExecQuery (\"Select * from Win32_ComputerSystem\") \n" +
					 "For Each objComputer in colSettings \n Wscript.Echo objComputer.NumberOfProcessors \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			processors = result.trim();
		input.close();
		return Integer.parseInt(processors);
	}
	
	/**
	 * This function provides No of PCMCIA slots 
	 * @return No of PCMCIA slots
	 * @throws Exception
	 */
	public static int getNoOfPcmciaSlots() throws Exception {
		String slots = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\") \n" +
					 "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_PCMCIAController\") \n" +
					 "Wscript.Echo colItems.Count";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			slots = result.trim();
		input.close();
		return Integer.parseInt(slots);
	}
	
	/**
	 * This function provides information about devices that are not working  
	 * @return Not Working Devices Info
	 * @throws Exception
	 */
	public static ArrayList<NotWorkingDevice> getNotWorkingDevicesInfo() throws Exception {
		ArrayList<NotWorkingDevice> list = new ArrayList<NotWorkingDevice>();
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\") \n" +
					 "Set colItems = objWMIService.ExecQuery (\"Select * from Win32_PnPEntity " +
					 "WHERE ConfigManagerErrorCode <> 0\")  \n For Each objItem in colItems \n " +
					 "Wscript.Echo objItem.ClassGuid \n Wscript.Echo objItem.Description \n " +
					 "Wscript.Echo objItem.DeviceID \n Wscript.Echo objItem.Manufacturer \n " +
					 "Wscript.Echo objItem.Name \n Wscript.Echo objItem.PNPDeviceID \n " +
					 "Wscript.Echo objItem.Service \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = br.readLine();
		while(result != null) {
			NotWorkingDevice device = new NotWorkingDevice();
			device.setClassGUID(result.trim());
			result = br.readLine();
			device.setDescription(result.trim());
			result = br.readLine();
			device.setDeviceId(result.trim());
			result = br.readLine();
			device.setManufacturer(result.trim());
			result = br.readLine();
			device.setDeviceName(result.trim());
			result = br.readLine();
			device.setPnpDevice(result.trim());
			result = br.readLine();
			device.setService(result.trim());
			list.add(device);
			result = br.readLine();
		}
		br.close();
		return list;
	}

	/**
	 * This function provides the Computer Name 
	 * @return Computer Name
	 * @throws Exception
	 */
	public static String getComputerName() throws Exception {
		String name = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\") \n " +
					 "Set colSettings = objWMIService.ExecQuery (\"Select * from Win32_ComputerSystem\") \n " +
					 "For Each objComputer in colSettings \n Wscript.Echo objComputer.Name \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			name = result.trim();
		input.close();
		return name;
	}
	
	/**
	 * This function provides information about properties of all pointing devices  
	 * @return Properties of all pointing devices
	 * @throws Exception
	 */
	public static ArrayList<PointingDevice> getPointingDeviceProperties() throws Exception {
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\") \n " +
					 "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_PointingDevice\") \n " +
					 "For Each objItem in colItems \n Wscript.Echo \"Description: \" & objItem.Description \n " +
					 "Wscript.Echo objItem.DeviceID \n Wscript.Echo objItem.DeviceInterface \n " +
					 "Wscript.Echo objItem.DoubleSpeedThreshold \n Wscript.Echo objItem.Handedness \n " +
					 "Wscript.Echo objItem.HardwareType \n Wscript.Echo objItem.InfFileName \n " +
					 "Wscript.Echo objItem.InfSection \n Wscript.Echo objItem.Manufacturer \n " +
					 "Wscript.Echo objItem.Name \n Wscript.Echo objItem.NumberOfButtons \n " +
					 "Wscript.Echo objItem.PNPDeviceID \n Wscript.Echo objItem.PointingType \n " +
					 "Wscript.Echo objItem.QuadSpeedThreshold \n Wscript.Echo objItem.Resolution \n " +
					 "Wscript.Echo objItem.SampleRate \n Wscript.Echo objItem.Synch \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		ArrayList<PointingDevice> list = new ArrayList<PointingDevice>();
		String result = br.readLine();
		while(result != null) {
			PointingDevice device = new PointingDevice();
			device.setDescription(result.trim());
			result = br.readLine();
			device.setDeviceId(result.trim());
			result = br.readLine();
			device.setDeviceInterface(result.trim());
			result = br.readLine();
			device.setDoubleSpeedThreshold(result.trim());
			result = br.readLine();
			device.setHandedness(result.trim());
			result = br.readLine();
			device.setHardwareType(result.trim());
			result = br.readLine();
			device.setInfFileName(result.trim());
			result = br.readLine();
			device.setInfSection(result.trim());
			result = br.readLine();
			device.setManufacturer(result.trim());
			result = br.readLine();
			device.setDeviceName(result.trim());
			result = br.readLine();
			device.setNumberOfButtons(result.trim());
			result = br.readLine();
			device.setPnpDeviceId(result.trim());
			result = br.readLine();
			device.setPointingType(result.trim());
			result = br.readLine();
			device.setQuadSpeedThreshold(result.trim());
			result = br.readLine();
			device.setResolution(result.trim());
			result = br.readLine();
			device.setSampleRate(result.trim());
			result = br.readLine();
			device.setSynch(result.trim());
			list.add(device);
			result = br.readLine();
		}
		br.close();
		return list;
	}
	
	/**
	 * This function provides Speed of the Processor in MHz 
	 * @return Processor Speed in MHz
	 * @throws Exception
	 */
	public static long getProcessorSpeed() throws Exception {
		String speed = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\") \n " +
					 "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\") \n " +
					 "For Each objItem in colItems \n Wscript.Echo objItem.MaxClockSpeed \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			speed = result.trim();
		input.close();
		return Long.parseLong(speed);
	}

	/**
	 * This function provides the Type of Computer i.e. laptop, notebook etc.. 
	 * @return Computer Type
	 * @throws Exception
	 */
	public static String getComputerType() throws Exception {
		String speed = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set Wmi= GetObject(\"winmgmts:\\\\.\\root\\CIMV2\") \n " +
					 "Set ColItems = Wmi.ExecQuery(\"SELECT * FROM Win32_SystemEnclosure\",,48) \n " +
					 "For Each ObjItem in ColItems \n For Each strType in ObjItem.ChassisTypes \n " +
					 "Select Case strType \n Case 1 ComType = \"Other\" \n Case 2 ComType = \"Unknown\" \n " +
					 "Case 3 ComType = \"Desktop\" \n Case 4 ComType = \"Low Profile Desktop\" \n " +
					 "Case 5 ComType = \"Pizza Box\" \n Case 6 ComType = \"Mini Tower\" \n " +
					 "Case 7 ComType = \"Tower\" \n Case 8 ComType = \"Portable\" \n Case 9 ComType = \"Laptop\" \n " +
					 "Case 10 ComType = \"Notebook\" \n Case 11 ComType = \"Handheld\" \n " +
					 "Case 12 ComType = \"Docking Station\" \n Case 13 ComType = \"All-in-One\" \n " +
					 "Case 14 ComType = \"Sub-Notebook\" \n Case 15 ComType = \"Space Saving\" \n " +
					 "Case 16 ComType = \"Lunch Box\" \n Case 17 ComType = \"Main System Chassis\" \n " +
					 "Case 18 ComType = \"Expansion Chassis\" \n Case 19 ComType = \"Sub-Chassis\" \n " +
					 "Case 20 ComType = \"Bus Expansion Chassis\" \n Case 21 ComType = \"Peripheral Chassis\" \n " +
					 "Case 22 ComType = \"Storage Chassis\" \n Case 23 ComType = \"Rack Mount Chassis\" \n " +
					 "Case 24 ComType = \"Sealed-Case PC\" \n Case Else ComType = \"Unknown\" \n End Select \n Next \n" +
					 "Next \n WScript.Echo ComType";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			speed = result.trim();
		input.close();
		return speed;
	}

	
	/**
	 * This function provides information about Plugged USB devices info   
	 * @return Plugged USB devices info
	 * @throws Exception
	 */
	public static ArrayList<PluggedUsbDevice> getPluggedUsbDevicesInfo() throws Exception {
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\") \n " +
					 "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_USBHub\") \n " +
					 "For Each objItem in colItems \n Wscript.Echo objItem.DeviceID \n " +
					 "Wscript.Echo objItem.PNPDeviceID \n Wscript.Echo objItem.Description \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		ArrayList<PluggedUsbDevice> list = new ArrayList<PluggedUsbDevice>();
		String result = br.readLine();
		while(result != null) {
			PluggedUsbDevice device = new PluggedUsbDevice();
			device.setDeviceId(result.trim());
			result = br.readLine();
			device.setPnpDeviceId(result.trim());
			result = br.readLine();
			device.setDescription(result.trim());
			list.add(device);
			result = br.readLine();
		}
		br.close();
		return list;
	}
	
	/**
	 * This function provides No of Tape Drives intalled 
	 * @return No of Tape Drives
	 * @throws Exception
	 */
	public static long getNoofTapeDrives() throws Exception {
		String speed = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\") \n " +
					 "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_TapeDrive\") \n " +
					 "Wscript.Echo colItems.Count";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			speed = result.trim();
		input.close();
		return Long.parseLong(speed);
	}

	/**
	 * This function provides the Domain Name 
	 * @return Domain Name
	 * @throws Exception
	 */
	public static String getDomainName() throws Exception {
		String name = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\") \n " +
					 "Set colSettings = objWMIService.ExecQuery (\"Select * from Win32_ComputerSystem\") \n " +
					 "For Each objComputer in colSettings \n Wscript.Echo objComputer.Domain \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			name = result.trim();
		input.close();
		return name;
	}
	
	/**
	 * This function provides the Domain Role 
	 * @return Domain Role
	 * @throws Exception
	 */
	public static String getDomainRole() throws Exception {
		String name = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\") \n " +
					 "Set colComputers = objWMIService.ExecQuery(\"Select DomainRole from Win32_ComputerSystem\") \n " +
					 "For Each objComputer in colComputers \n Select Case objComputer.DomainRole \n Case 0 \n " +
					 "strComputerRole = \"Standalone Workstation\" \n Case 1 \n strComputerRole = \"Member Workstation\" \n " +
					 "Case 2 \n strComputerRole = \"Standalone Server\" \n Case 3 \n strComputerRole = \"Member Server\" \n " +
					 "Case 4 \n strComputerRole = \"Backup Domain Controller\" \n Case 5 \n " +
					 "strComputerRole = \"Primary Domain Controller\" \n End Select \n Wscript.Echo strComputerRole \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			name = result.trim();
		input.close();
		return name;
	}

	/**
	 * This function provides the Logged In User Name 
	 * @return Logged In User Name
	 * @throws Exception
	 */
	public static String getLoggedInUserName() throws Exception {
		String name = "";
		File file = File.createTempFile("javastorm",".vbs");
		file.deleteOnExit();
		FileWriter fileWriter = new FileWriter(file);
		String str = "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\") \n " +
					 "Set colComputer = objWMIService.ExecQuery(\"Select * from Win32_ComputerSystem\") \n " +
					 "For Each objComputer in colComputer \n WScript.Echo objComputer.UserName \n Next";
		fileWriter.write(str);
		fileWriter.close();
		Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = input.readLine();
		if(result != null) 
			name = result.trim();
		input.close();
		return name;
	}

}
