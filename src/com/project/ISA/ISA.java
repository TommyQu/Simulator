package com.project.ISA;

import java.util.HashMap;
import java.util.Map;

public class ISA {
	public static Map<String, String> ISAList = new HashMap<String, String>();

	public static void addISAList(String str, String str1) {
		ISAList.put(str, str1);
	}

	public static String getISAList(String str) {
		
		return ISAList.get(str);
	}
}
