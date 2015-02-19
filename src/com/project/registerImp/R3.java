package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class R3 implements Register {

	public static String R3value = "0000000000000000";  //R3 16 bits
	public static String R3name = "r3";
	public String getValue() {
		return R3value;
	}

	public void setValue(String value) {
		R3.R3value = StringUtils.leftPad(value, 16, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return R3.R3name;
	}
	
}
// this class implement Register as R3