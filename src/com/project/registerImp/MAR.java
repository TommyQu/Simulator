package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class MAR implements Register {

	public static String MARvalue = "0000000000000000"; //MAR 16 bits
	public static String MARname = "mar";
	public String getValue() {
		return MARvalue;
	}

	public void setValue(String value) {
		MAR.MARvalue = StringUtils.leftPad(value, 16, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return MAR.MARname;
	}
	
}
// this class implement Register as MAR