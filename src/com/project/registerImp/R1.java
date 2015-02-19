package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class R1 implements Register {

	public static String R1value = "0000000000000000";  //R1 16 bits
	public static String R1name = "r1";
	public String getValue() {
		return R1value;
	}

	public void setValue(String value) {
		R1.R1value = StringUtils.leftPad(value, 16, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return R1.R1name;
	}
	
}
// this class implement Register as R1
