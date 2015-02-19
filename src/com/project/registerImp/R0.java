package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class R0 implements Register {

	public static String R0value = "0000000000000000"; //R0 16 bits
	public static String R0name = "r0";
	public String getValue() {
		return R0value;
	}

	public void setValue(String value) {
		R0.R0value = StringUtils.leftPad(value, 16, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return R0.R0name;
	}
	
}
// this class implement Register as R0
