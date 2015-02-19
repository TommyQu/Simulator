package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class X1 implements Register {

	public static String X1value = "000000000000";  //X1 12 bits
	public static String X1name = "x1";
	public String getValue() {
		return X1value;
	}

	public void setValue(String value) {
		if(value.length() > 12)
			value = value.substring(4, 16);
//		System.out.println(value);
		X1.X1value = StringUtils.leftPad(value, 12, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return X1.X1name;
	}
	
}
// this class implement Register as X1