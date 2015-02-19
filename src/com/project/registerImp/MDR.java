package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class MDR implements Register {

	public static String MDRvalue = "0000000000000000";  //MDR 16 bits
	public static String MDRname = "mdr";
	public String getValue() {
		return MDRvalue;
	}

	public void setValue(String value) {
		MDR.MDRvalue = StringUtils.leftPad(value, 16, "0");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return MDR.MDRname;
	}
	
}
// this class implement Register as R2