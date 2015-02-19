package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class R2 implements Register {

	public static String R2value = "0000000000000000";  //R2 16 bits
	public static String R2name = "r2";
	public String getValue() {
		return R2value;
	}

	public void setValue(String value) {
		R2.R2value = StringUtils.leftPad(value, 16, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return R2.R2name;
	}
	
}
// this class implement Register as R2
