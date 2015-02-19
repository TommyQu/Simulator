package com.project.instruction;

import org.apache.commons.lang3.StringUtils;

public class Instruction {
	public String command;
	public String bcommand;
	public String opcode;
	public String RF;
	public String XF;
	public String I;
	public String ADDR;
	public String Immediate;  //6 bits, signal bit
//	public static String immed;
//	public static String cc;
//	public static String DevID;
//	public static String rx;
//	public static String ry;
//	public static String FRF;
	// this class make all instructions static for other classes.
	public void setInstruction() {
		int NumOfOPCODE = this.command.indexOf(" ");
		this.opcode = this.command.substring(0, NumOfOPCODE);

		int NumOfRF = this.command.indexOf(",");
		this.RF = this.command.substring(NumOfOPCODE + 1, NumOfRF);

		if (this.opcode.equalsIgnoreCase("LDR")||this.opcode.equalsIgnoreCase("STR")||
				this.opcode.equalsIgnoreCase("LDA")||this.opcode.equalsIgnoreCase("LDX")||
				this.opcode.equalsIgnoreCase("STX")||this.opcode.equalsIgnoreCase("AMR")||
				this.opcode.equalsIgnoreCase("SMR")) {
			int NumOfXF = this.command.indexOf(",", NumOfRF + 1);
			this.XF = this.command.substring(NumOfRF + 1, NumOfXF);

			int NumOfI = this.command.indexOf(",", NumOfXF + 1);
			this.I = this.command.substring(NumOfXF + 1, NumOfI);

			this.ADDR = this.command.substring(NumOfI + 1,
					this.command.length());
		}
		else if(this.opcode.equalsIgnoreCase("AIR")||this.opcode.equalsIgnoreCase("SIR")) {
//			int NumOfImmediate = this.command.indexOf(",", NumOfRF + 1);
			this.Immediate = this.command.substring(NumOfRF + 1, this.command.length());
		}
	}
	
	public void translation() {

		String bopcode = "";
		String brf, bxf, bi, baddr, bimmediate, bcommand = null;
		// System.out.println(Instruction.opcode);
		if (this.opcode.equalsIgnoreCase("LDR")
				|| this.opcode.equalsIgnoreCase("STR")
				|| this.opcode.equalsIgnoreCase("LDA")
				|| this.opcode.equalsIgnoreCase("LDX")
				|| this.opcode.equalsIgnoreCase("STX")
				|| this.opcode.equalsIgnoreCase("AMR")
				|| this.opcode.equalsIgnoreCase("SMR")) {
			if (this.opcode.equalsIgnoreCase("LDR")) {
				bopcode = "000001";
			} else if (this.opcode.equalsIgnoreCase("STR")) {
				bopcode = "000010";
			} else if (this.opcode.equalsIgnoreCase("LDA")) {
				bopcode = "000011";
			} else if (this.opcode.equalsIgnoreCase("LDX")) {
				bopcode = "101001";
			} else if (this.opcode.equalsIgnoreCase("STX")) {
				bopcode = "101010";
			} else if (this.opcode.equalsIgnoreCase("AMR")) {
				bopcode = "000100";
			} else if (this.opcode.equalsIgnoreCase("SMR")) {
				bopcode = "000101";
			}
			brf = Integer.toBinaryString(Integer.parseInt(this.RF));
			bxf = Integer.toBinaryString(Integer.parseInt(this.XF));
			bi = Integer.toBinaryString(Integer.parseInt(this.I));
			baddr = Integer.toBinaryString(Integer.parseInt(this.ADDR));

			brf = StringUtils.leftPad(brf, 2, "0");
			bxf = StringUtils.leftPad(bxf, 2, "0");
			bi = StringUtils.leftPad(bi, 1, "0");
			baddr = StringUtils.leftPad(baddr, 5, "0");
			bcommand = bopcode + brf + bxf + bi + baddr;
		}
		else if (this.opcode.equalsIgnoreCase("AIR")
				||(this.opcode.equalsIgnoreCase("SIR"))) {
			if(this.opcode.equalsIgnoreCase("AIR"))
			   bopcode = "000110";
			else if(this.opcode.equalsIgnoreCase("SIR"))
				bopcode = "000111";
			brf = Integer.toBinaryString(Integer.parseInt(this.RF));
			brf = StringUtils.leftPad(brf, 2, "0");
//			System.out.println(this.Immediate);
			int iImmediate = Integer.parseInt(this.Immediate);
			if(iImmediate < 0) {
				iImmediate = -iImmediate;
				bimmediate = Integer.toBinaryString(iImmediate);
				bimmediate = StringUtils.leftPad(bimmediate, 6, "0");
				bimmediate = bimmediate.replaceFirst(bimmediate.substring(0, 1), "1");
			}
			else {
				bimmediate = Integer.toBinaryString(iImmediate);
				bimmediate = StringUtils.leftPad(bimmediate, 6, "0");
			}
			bcommand = bopcode + brf + bimmediate;
//			bimmediate = Integer.toBinaryString(Integer.parseInt(this.Immediate));
			
//			bimmediate = StringUtils.leftPad(bimmediate, 6, "0");
//			bcommand = bopcode + brf + bimmediate;
		}
//		System.out.println(bcommand);
		this.bcommand = bcommand;

	}
}
