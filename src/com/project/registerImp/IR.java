package com.project.registerImp;

import org.apache.commons.lang3.StringUtils;

import com.project.register.Register;

public class IR implements Register{
	public static String IRvalue = "0000000000000000"; //IR 16 bits
	public static String IRname = "ir";
	public String getValue(){
		return IRvalue;
	}
	public void setValue(String value){
		IR.IRvalue = StringUtils.leftPad(value, 16, "0");;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return IR.IRname;
	}
	
	// this class implement Register as IR.
}
