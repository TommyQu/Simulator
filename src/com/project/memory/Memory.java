package com.project.memory;

import java.util.Queue;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

public class Memory {
	private final static int WORD_LENGTH = 16;
	private final static int MEMORY_LENGTH = 2048;
//	private Vector<int[]> memoryData;
	public static String[] ram = new String[MEMORY_LENGTH];
	public static Vector<String> rom = new Vector<String>();
//	public static Queue<String> rom = new 
	
	public Memory() {
		for(int i=0;i<2048;i++) {
			ram[i] = StringUtils.leftPad("", 16, "0");
		}
	}

	public static int getWordLength() {
		return WORD_LENGTH;
	}

	public static int getMemoryLength() {
		return MEMORY_LENGTH;
	}
	
	public static void resetMemory() {
		for(int i=0;i<MEMORY_LENGTH;i++) {
			ram[i] = "0000000000000000";
		}
		rom.clear();
	}
}
