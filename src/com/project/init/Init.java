package com.project.init;

import com.project.memory.Memory;
import com.project.register.Register;
import com.project.registerImp.*;
import com.project.GUI.Main;
import com.project.ISA.ISA;


public class Init {
	public Register ea = new EA();
	public Register ir = new IR();
	public Register mar = new MAR();
	public Register mdr = new MDR();
	public Register pc = new PC();
	public Register r0 = new R0();
	public Register r1 = new R1();
	public Register r2 = new R2();
	public Register r3 = new R3();
	public Register x1 = new X1();
	public Register x2 = new X2();
	public Register x3 = new X3();
	
	public ISA isa = new ISA();
	public Memory memo = new Memory();
	
	public Register getRegByName(String regName) {
		if(regName.equalsIgnoreCase("ea"))
			return ea;
		else if(regName.equalsIgnoreCase("ir"))
			return ir;
		else if(regName.equalsIgnoreCase("mar"))
			return mar;
		else if(regName.equalsIgnoreCase("mdr"))
			return mdr;
		else if(regName.equalsIgnoreCase("pc"))
			return pc;
		else if(regName.equalsIgnoreCase("ir"))
			return ir;
		else if(regName.equalsIgnoreCase("r0"))
			return r0;
		else if(regName.equalsIgnoreCase("r1"))
			return r1;
		else if(regName.equalsIgnoreCase("r2"))
			return r2;
		else if(regName.equalsIgnoreCase("r3"))
			return r3;
		else if(regName.equalsIgnoreCase("x1"))
			return x1;
		else if(regName.equalsIgnoreCase("x2"))
			return x2;
		else if(regName.equalsIgnoreCase("x3"))
			return x3;
		else
			return null;
	}
	
	public void reset(){
		Main.init.ea.setValue("0000000000000000");
		Main.init.ir.setValue("0000000000000000");
		Main.init.mar.setValue("0000000000000000");
		Main.init.mdr.setValue("0000000000000000");
		Main.init.pc.setValue("000000000000");
		Main.init.r0.setValue("0000000000000000");
		Main.init.r1.setValue("1000000000000100");
		Main.init.r2.setValue("0000000000000000");
		Main.init.r3.setValue("0000000000000000");
		Main.init.x1.setValue("000000000000");
		Main.init.x2.setValue("000000000000");
		Main.init.x3.setValue("000000000000");
//		IR.IRvalue = "0000000000000000";
//		MAR.MARvalue  = "0000000000000000";
//		MDR.MDRvalue = "0000000000000000";
//		PC.PCvalue = "0000000000000000";
//		R0.R0value = "0000000000010000";
//		R1.R1value = "0000000000000000";
//		R2.R2value = "0000000000000000";
//		R3.R3value = "0000000000000000";
//		X1.X1value = "000000000100";
//		X2.X2value = "000000000000";
//		X3.X3value = "000000000000";
		
		ISA.addISAList("LDR", "000001");
		ISA.addISAList("STR", "000010");
		ISA.addISAList("LDA", "000011");
		ISA.addISAList("LDX", "101001");
		ISA.addISAList("STX", "101010");
		Memory.ram[12] = new String("1000000000000001");
	}
	
	
}
