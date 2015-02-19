package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class PC implements Register {

	public static String PCvalue= "000000000000";  //PC 12 bits
	public static String PCname = "pc";
	public String getValue() {
		return PCvalue;
	}

	public void setValue(String value) {
		PC.PCvalue = StringUtils.leftPad(value, 12, "0");
	}
	
//	public static void PCplusOne(){
//		int value = Integer.valueOf(PCvalue, 2);
//		value = value + 1;
//		PCvalue = addZero(Integer.toBinaryString(value),16);
//	
//	}
	
	public static String addZero(String binaryString, int length) {
		int l=binaryString.length();
		for(int i=1;i<=(length-l);i++)
		{
			binaryString="0"+binaryString;
		}
		return binaryString;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return PC.PCname;
	}

}
