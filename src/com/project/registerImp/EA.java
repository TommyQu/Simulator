package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class EA implements Register{

	public static String EAvalue = "0000000000000000";  //EA 16 bits
	public static String EAname = "ea";
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return EAvalue;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		EA.EAvalue = StringUtils.leftPad(value, 16, "0");
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return EA.EAname;
	}

}
