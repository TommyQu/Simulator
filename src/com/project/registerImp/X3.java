package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class X3 implements Register {

	public static String X3value = "000000000000";  //X3 12 bits
	public static String X3name = "x3";
	public String getValue() {
		return X3value;
	}

	public void setValue(String value) {
		if(value.length() > 12)
			value = value.substring(4, 16);
//		System.out.println(value);
		X3.X3value = StringUtils.leftPad(value, 12, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return X3.X3name;
	}
	
}
// this class implement Register as X3