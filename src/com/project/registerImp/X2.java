package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class X2 implements Register {

	public static String X2value = "000000000000";  //X2 12 bits
	public static String X2name = "x2";
	public String getValue() {
		return X2value;
	}

	public void setValue(String value) {
		if(value.length() > 12)
			value = value.substring(4, 16);
//		System.out.println(value);
		X2.X2value = StringUtils.leftPad(value, 12, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return X2.X2name;
	}
	
}
// this class implement Register as X2