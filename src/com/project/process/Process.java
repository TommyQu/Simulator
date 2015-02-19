package com.project.process;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import com.project.GUI.Main;

public class Process {
	public static String bcommand;
	public static String opcode;
	public static String RF;
	public static String XF;
	public static String I;
	public static String ADDR;
	public static String Immediate;
	
	public Process() {
		
	}
	
	public void processROMbcommand(String bcommand) {
		this.bcommand = bcommand;
		this.opcode = bcommand.substring(0, 6);
//		Main.stepListModel.addElement(this.b);  
		Main.init.mar.setValue(Main.init.pc.getValue()); //MAR <- PC
		Main.stepListModel.addElement("MAR <- PC");
		int romIndex = Integer.parseInt(Main.init.mar.getValue(),2)-1;
		romIndex = Integer.parseInt(Integer.toOctalString(romIndex));
		String mdrStr = StringUtils.rightPad(Main.init.memo.rom.get(romIndex), 16, "0");
		Main.init.mdr.setValue(mdrStr); // MDR <- M[MAR]
//		System.out.println(Main.init.memo.rom.get(romIndex));
		Main.stepListModel.addElement("MDR <- ROM[MAR]");
//		System.out.println(Main.init.ir.getValue());
		Main.init.ir.setValue(Main.init.mdr.getValue());//IR <- MDR
		Main.stepListModel.addElement("IR <- MDR");
		if(Main.init.ir.getValue().substring(0, 6).equals("000001"))  // LDR bcommand
			processLDR();
		else if(Main.init.ir.getValue().substring(0, 6).equals("000010"))  // STR bcommand
			processSTR();
		else if(Main.init.ir.getValue().substring(0, 6).equals("000011"))  // LDA bcommand
			processLDA();
		else if(Main.init.ir.getValue().substring(0, 6).equals("101001"))  // LDX bcommand
			processLDX();
		else if(Main.init.ir.getValue().substring(0, 6).equals("101010"))  // STX bcommand
			processSTX();
		else if(Main.init.ir.getValue().substring(0, 6).equals("000100"))  // AMR bcommand
			processAMR();
		else if(Main.init.ir.getValue().substring(0, 6).equals("000101"))  // SMR bcommand
			processSMR();
		else if(Main.init.ir.getValue().substring(0, 6).equals("000110"))  // AIR bcommand
			processAIR();
		else if(Main.init.ir.getValue().substring(0, 6).equals("000111"))  // SIR bcommand
			processSIR();
	}
	
	private void processLDR() {
		this.RF = bcommand.substring(6, 8);
		this.XF = bcommand.substring(8, 10);
		this.I = bcommand.substring(10, 11);
		this.ADDR = bcommand.substring(11, 16);
		Main.init.ea.setValue(this.ADDR);   //EA <- Address
		Main.stepListModel.addElement("EA <- Address");
		Main.init.mar.setValue(Main.init.ea.getValue());   //MAR <- EA
		Main.stepListModel.addElement("MAR <- EA");
//		System.out.println(this.ADDR);
		int ramIndex = Integer.parseInt(Main.init.mar.getValue(),2);  //get the index in ram
//		System.out.println(ramIndex);
		Main.init.mdr.setValue(Main.init.memo.ram[ramIndex]);  //MDR <- M(MAR)
		Main.stepListModel.addElement("MDR <- M[MAR]");
		int rIndex = Integer.parseInt(this.RF,2);
//		System.out.println(rIndex);
		if(rIndex == 0) {                                        //store in R
			Main.init.r0.setValue(Main.init.mdr.getValue());
			Main.stepListModel.addElement("R0 <- MDR");
		}
		else if(rIndex == 1) {
			Main.init.r1.setValue(Main.init.mdr.getValue());
			Main.stepListModel.addElement("R1 <- MDR");
		}
		else if(rIndex == 2) {
			Main.init.r2.setValue(Main.init.mdr.getValue());
			Main.stepListModel.addElement("R2 <- MDR");
		}
		else if(rIndex == 3) {
			Main.init.r3.setValue(Main.init.mdr.getValue());
			Main.stepListModel.addElement("R3 <- MDR");
		}
		else
			JOptionPane.showMessageDialog(Main.jFrame, "RF Wrong!");
//		System.out.println(Main.init.r3.getValue());
		int pcNum = Integer.parseInt(Main.init.pc.getValue(),2);  //pc <- pc+1
		Main.init.pc.setValue(Integer.toBinaryString(pcNum));
		Main.stepListModel.addElement("PC <- PC+1");
		Main.stepListModel.addElement("----------------");
	}
	
	private void processSTR() {
//		System.out.println("aa");
		this.RF = bcommand.substring(6, 8);
		this.XF = bcommand.substring(8, 10);
		this.I = bcommand.substring(10, 11);
		this.ADDR = bcommand.substring(11, 16);
		Main.init.ea.setValue(this.ADDR);
		Main.stepListModel.addElement("EA <- Address");
		Main.init.mar.setValue(Main.init.ea.getValue());
		Main.stepListModel.addElement("MAR <- EA");
		if(this.RF.equals("00")) {
			Main.init.mdr.setValue(Main.init.r0.getValue());
			Main.stepListModel.addElement("MDR <- C[R0]");
		}
		else if(this.RF.equals("01")) {
			Main.init.mdr.setValue(Main.init.r1.getValue());
			Main.stepListModel.addElement("MDR <- C[R1]");
		}
		else if(this.RF.equals("10")) {
			Main.init.mdr.setValue(Main.init.r2.getValue());
			Main.stepListModel.addElement("MDR <- C[R2]");
		}
		else if(this.RF.equals("11")) {
			Main.init.mdr.setValue(Main.init.r3.getValue());
			Main.stepListModel.addElement("MDR <- C[R3]");
		}
		int ramIndex = Integer.parseInt(Main.init.mar.getValue(),2);
		Main.init.memo.ram[ramIndex] = Main.init.mdr.getValue();
		Main.stepListModel.addElement("M[MAR] <- MDR");
		int pcNum = Integer.parseInt(Main.init.pc.getValue(),2);  //pc <- pc+1
		Main.init.pc.setValue(Integer.toBinaryString(pcNum));
		Main.stepListModel.addElement("PC <- PC+1");
		Main.stepListModel.addElement("----------------");
	}
	
	private void processLDA() {
//		System.out.println("aa");
		this.RF = bcommand.substring(6, 8);
		this.XF = bcommand.substring(8, 10);
		this.I = bcommand.substring(10, 11);
		this.ADDR = bcommand.substring(11, 16);
		Main.init.ea.setValue(this.ADDR);
		Main.stepListModel.addElement("EA <- Address");
		Main.init.mar.setValue(Main.init.ea.getValue());
		Main.stepListModel.addElement("MAR <- EA");
		Main.init.mdr.setValue(Main.init.mar.getValue());
		Main.stepListModel.addElement("MDR <- MAR");
		if(this.RF.equals("00")) {
			Main.init.r0.setValue(Main.init.mdr.getValue());
			Main.stepListModel.addElement("R0 <- MDR");
		}
		else if(this.RF.equals("01")) {
			Main.init.r1.setValue(Main.init.mdr.getValue());
			Main.stepListModel.addElement("R1 <- MDR");
		}
		else if(this.RF.equals("10")) {
			Main.init.r2.setValue(Main.init.mdr.getValue());
			Main.stepListModel.addElement("R2 <- MDR");
		}
		else if(this.RF.equals("11")) {
			Main.init.r3.setValue(Main.init.mdr.getValue());
			Main.stepListModel.addElement("R3 <- MDR");
		}
		int pcNum = Integer.parseInt(Main.init.pc.getValue(),2);  //pc <- pc+1
		Main.init.pc.setValue(Integer.toBinaryString(pcNum));
		Main.stepListModel.addElement("PC <- PC+1");
		Main.stepListModel.addElement("----------------");
	}
	
	private void processLDX() {  //LDX X1,X2,I,Address
		this.RF = bcommand.substring(6, 8);
		this.XF = bcommand.substring(8, 10);
		this.I = bcommand.substring(10, 11);
		this.ADDR = bcommand.substring(11, 16);
		Main.init.ea.setValue(this.ADDR);
		Main.stepListModel.addElement("EA <- Address");
		Main.init.mar.setValue(Main.init.ea.getValue());
		Main.stepListModel.addElement("MAR <- EA");
		int ramIndex = Integer.parseInt(Main.init.mar.getValue(),2);
		Main.init.mdr.setValue(Main.init.memo.ram[ramIndex]);
		Main.stepListModel.addElement("MDR <- M[MAR]");
		int ramContent = Integer.parseInt(Main.init.mdr.getValue(),2);
		if(ramContent >= 4096)  // Because words in memory has 16 bits and Index Register only has 12 bits
			JOptionPane.showMessageDialog(Main.jFrame, "The content in ram is too big to be loaded into Index Register!");
		else {
//			Main.init.mdr.setValue(Main.init.mdr.getValue().substring(4, 16));
//			System.out.println(Main.init.mdr.getValue());
			if(this.RF.equals("01")) {
				Main.init.x1.setValue(Main.init.mdr.getValue());
				Main.stepListModel.addElement("X1 <- MDR");
			}
			else if(this.RF.equals("10")) {
				Main.init.x2.setValue(Main.init.mdr.getValue());
				Main.stepListModel.addElement("X2 <- MDR");
			}
			else if(this.RF.equals("11")) {
				Main.init.x3.setValue(Main.init.mdr.getValue());
				Main.stepListModel.addElement("X3 <- MDR");
			}
			int pcNum = Integer.parseInt(Main.init.pc.getValue(),2);  //pc <- pc+1
			Main.init.pc.setValue(Integer.toBinaryString(pcNum));
			Main.stepListModel.addElement("PC <- PC+1");
			Main.stepListModel.addElement("----------------");
		}
	}
	
	private void processSTX() {
//		System.out.println("aa");
		this.RF = bcommand.substring(6, 8);
		this.XF = bcommand.substring(8, 10);
		this.I = bcommand.substring(10, 11);
		this.ADDR = bcommand.substring(11, 16);
		Main.init.ea.setValue(this.ADDR);
		Main.stepListModel.addElement("EA <- Address");
		Main.init.mar.setValue(Main.init.ea.getValue());
		Main.stepListModel.addElement("MAR <- EA");
        if(this.RF.equals("01")) {
			Main.init.mdr.setValue(Main.init.x1.getValue());
			Main.stepListModel.addElement("MDR <- C[X1]");
		}
		else if(this.RF.equals("10")) {
			Main.init.mdr.setValue(Main.init.x2.getValue());
			Main.stepListModel.addElement("MDR <- C[X2]");
		}
		else if(this.RF.equals("11")) {
			Main.init.mdr.setValue(Main.init.x3.getValue());
			Main.stepListModel.addElement("MDR <- C[X3]");
		}
		int ramIndex = Integer.parseInt(Main.init.mar.getValue(),2);
		Main.init.memo.ram[ramIndex] = Main.init.mdr.getValue();
		Main.stepListModel.addElement("M[MAR] <- MDR");
		int pcNum = Integer.parseInt(Main.init.pc.getValue(),2);  //pc <- pc+1
		Main.init.pc.setValue(Integer.toBinaryString(pcNum));
		Main.stepListModel.addElement("PC <- PC+1");
		Main.stepListModel.addElement("----------------");
	}
	
	private void processAMR() {
		this.RF = bcommand.substring(6, 8);
		this.XF = bcommand.substring(8, 10);
		this.I = bcommand.substring(10, 11);
		this.ADDR = bcommand.substring(11, 16);
		int XI = Integer.parseInt(this.XF);
		if( XI > 0) {
			if(XI == 1) {
//				Main.init.ea.setValue(Main.init.x1.getValue());
//				Main.init.ea.setValue(Main.init.ea.getValue()+this.ADDR);
				Main.init.ea.setValue(Main.init.x1.getValue());
				Main.stepListModel.addElement("EA <- X1");
			}
			else if(XI == 2) {
				Main.init.ea.setValue(Main.init.x2.getValue());
				Main.stepListModel.addElement("EA <- X2");
			}
			else if(XI == 3) {
				Main.init.ea.setValue(Main.init.x3.getValue());
				Main.stepListModel.addElement("EA <- X3");
			}
			int eaIvalue = Integer.parseInt(Main.init.ea.getValue(), 2)+Integer.parseInt(this.ADDR, 2);
			Main.init.ea.setValue(Integer.toBinaryString(eaIvalue));
			Main.stepListModel.addElement("EA <- EA + c(ADDRESS)");
			Main.init.mar.setValue(Main.init.ea.getValue());
			Main.stepListModel.addElement("MAR <- EA");
			int ramIndex = Integer.parseInt(Main.init.mar.getValue(),2);
			Main.init.mdr.setValue(Main.init.memo.ram[ramIndex]);
			Main.stepListModel.addElement("MDR <- M["+ramIndex+"]");
			Main.stepListModel.addElement("MAR <- ADDER");
			int dataR, dataEA, result;
			int flagR, flagEA;
			String replaceStr;
			if(this.RF.equals("00")) {
				flagR = Main.init.r0.getValue().charAt(0);
				flagEA = Main.init.mdr.getValue().charAt(0);
				if(flagR == '1') {
					replaceStr = Main.init.r0.getValue().replaceFirst(Main.init.r0.getValue().substring(0, 1), "0");
					Main.init.r0.setValue(replaceStr);
					flagR = -1;
//					System.out.println(Main.init.r0.getValue());
				}
				if(flagEA == '1') {
					replaceStr = Main.init.mdr.getValue().replaceFirst(Main.init.mdr.getValue().substring(0, 1), "0");
					Main.init.mdr.setValue(replaceStr);
					flagEA = -1;
//					System.out.println(Main.init.mdr.getValue());
				}
				dataR = Integer.parseInt(Main.init.r0.getValue(),2);
				dataEA = Integer.parseInt(Main.init.mdr.getValue(),2);
				result = dataR*flagR + dataEA*flagEA;
				System.out.println(result);
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result <0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
						Main.init.r0.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r0.setValue(resultStr);
					}
					Main.stepListModel.addElement("R0 <- ARR");
				}
			}
			else if(this.RF.equals("01")) {
				flagR = Main.init.r1.getValue().charAt(0);
				flagEA = Main.init.mdr.getValue().charAt(0);
				if(flagR == '1') {
					replaceStr = Main.init.r1.getValue().replaceFirst(Main.init.r1.getValue().substring(0, 1), "0");
					Main.init.r1.setValue(replaceStr);
					flagR = -1;
//					System.out.println(Main.init.r0.getValue());
				}
				if(flagEA == '1') {
					replaceStr = Main.init.mdr.getValue().replaceFirst(Main.init.mdr.getValue().substring(0, 1), "0");
					Main.init.mdr.setValue(replaceStr);
					flagEA = -1;
//					System.out.println(Main.init.mdr.getValue());
				}
				dataR = Integer.parseInt(Main.init.r1.getValue(),2);
				dataEA = Integer.parseInt(Main.init.mdr.getValue(),2);
				result = dataR*flagR + dataEA*flagEA;
				System.out.println(result);
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result <0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
						Main.init.r1.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r1.setValue(resultStr);
					}
					Main.stepListModel.addElement("R1 <- ARR");
				}
			}
			else if(this.RF.equals("10")) {
				flagR = Main.init.r2.getValue().charAt(0);
				flagEA = Main.init.mdr.getValue().charAt(0);
				if(flagR == '1') {
					replaceStr = Main.init.r2.getValue().replaceFirst(Main.init.r2.getValue().substring(0, 1), "0");
					Main.init.r2.setValue(replaceStr);
					flagR = -1;
//					System.out.println(Main.init.r0.getValue());
				}
				if(flagEA == '1') {
					replaceStr = Main.init.mdr.getValue().replaceFirst(Main.init.mdr.getValue().substring(0, 1), "0");
					Main.init.mdr.setValue(replaceStr);
					flagEA = -1;
//					System.out.println(Main.init.mdr.getValue());
				}
				dataR = Integer.parseInt(Main.init.r2.getValue(),2);
				dataEA = Integer.parseInt(Main.init.mdr.getValue(),2);
				result = dataR*flagR + dataEA*flagEA;
				System.out.println(result);
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result <0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
						Main.init.r2.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r2.setValue(resultStr);
					}
					Main.stepListModel.addElement("R2 <- ARR");
				}
			}
			else if(this.RF.equals("11")) {
				flagR = Main.init.r3.getValue().charAt(0);
				flagEA = Main.init.mdr.getValue().charAt(0);
				if(flagR == '1') {
					replaceStr = Main.init.r3.getValue().replaceFirst(Main.init.r3.getValue().substring(0, 1), "0");
					Main.init.r3.setValue(replaceStr);
					flagR = -1;
//					System.out.println(Main.init.r0.getValue());
				}
				if(flagEA == '1') {
					replaceStr = Main.init.mdr.getValue().replaceFirst(Main.init.mdr.getValue().substring(0, 1), "0");
					Main.init.mdr.setValue(replaceStr);
					flagEA = -1;
//					System.out.println(Main.init.mdr.getValue());
				}
				dataR = Integer.parseInt(Main.init.r3.getValue(),2);
				dataEA = Integer.parseInt(Main.init.mdr.getValue(),2);
				result = dataR*flagR + dataEA*flagEA;
				System.out.println(result);
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result <0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
						Main.init.r3.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r3.setValue(resultStr);
					}
					Main.stepListModel.addElement("R3 <- ARR");
				}
			}
			Main.stepListModel.addElement("PC <- PC+1");
			Main.stepListModel.addElement("----------------");
//			System.out.println(Main.init.mdr.getValue());
		}
	}
	
	private void processSMR() {
		this.RF = bcommand.substring(6, 8);
		this.XF = bcommand.substring(8, 10);
		this.I = bcommand.substring(10, 11);
		this.ADDR = bcommand.substring(11, 16);
		int XI = Integer.parseInt(this.XF);
		
		if( XI > 0) {
			if(XI == 1) {
//				Main.init.ea.setValue(Main.init.x1.getValue());
//				Main.init.ea.setValue(Main.init.ea.getValue()+this.ADDR);
				Main.init.ea.setValue(Main.init.x1.getValue());
				Main.stepListModel.addElement("EA <- X1");
			}
			else if(XI == 2) {
				Main.init.ea.setValue(Main.init.x2.getValue());
				Main.stepListModel.addElement("EA <- X2");
			}
			else if(XI == 3) {
				Main.init.ea.setValue(Main.init.x3.getValue());
				Main.stepListModel.addElement("EA <- X3");
			}
			int eaIvalue = Integer.parseInt(Main.init.ea.getValue(), 2)+Integer.parseInt(this.ADDR, 2);
			Main.init.ea.setValue(Integer.toBinaryString(eaIvalue));
			Main.stepListModel.addElement("EA <- EA - c(ADDRESS)");
			Main.init.mar.setValue(Main.init.ea.getValue());
			Main.stepListModel.addElement("MAR <- EA");
			int ramIndex = Integer.parseInt(Main.init.mar.getValue(),2);
			Main.init.mdr.setValue(Main.init.memo.ram[ramIndex]);
			Main.stepListModel.addElement("MDR <- M["+ramIndex+"]");
			Main.stepListModel.addElement("MAR <- ADDER");
			int dataR, dataEA, result;
			int flagR, flagEA;
			String replaceStr;
			if(this.RF.equals("00")) {
				flagR = Main.init.r0.getValue().charAt(0);
				flagEA = Main.init.mdr.getValue().charAt(0);
				if(flagR == '1') {
					replaceStr = Main.init.r0.getValue().replaceFirst(Main.init.r0.getValue().substring(0, 1), "0");
					Main.init.r0.setValue(replaceStr);
					flagR = -1;
//					System.out.println(Main.init.r0.getValue());
				}
				if(flagEA == '1') {
					replaceStr = Main.init.mdr.getValue().replaceFirst(Main.init.mdr.getValue().substring(0, 1), "0");
					Main.init.mdr.setValue(replaceStr);
					flagEA = -1;
//					System.out.println(Main.init.mdr.getValue());
				}
				dataR = Integer.parseInt(Main.init.r0.getValue(),2);
				dataEA = Integer.parseInt(Main.init.mdr.getValue(),2);
				result = dataR*flagR - dataEA*flagEA;
				System.out.println(result);
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The result is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The result is too big!");
				else {
					String resultStr;
					if(result <0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
						Main.init.r0.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r0.setValue(resultStr);
					}
					Main.stepListModel.addElement("R0 <- ARR");
				}
			}
			else if(this.RF.equals("01")) {
				flagR = Main.init.r1.getValue().charAt(0);
				flagEA = Main.init.mdr.getValue().charAt(0);
				if(flagR == '1') {
					replaceStr = Main.init.r1.getValue().replaceFirst(Main.init.r1.getValue().substring(0, 1), "0");
					Main.init.r1.setValue(replaceStr);
					flagR = -1;
//					System.out.println(Main.init.r0.getValue());
				}
				if(flagEA == '1') {
					replaceStr = Main.init.mdr.getValue().replaceFirst(Main.init.mdr.getValue().substring(0, 1), "0");
					Main.init.mdr.setValue(replaceStr);
					flagEA = -1;
//					System.out.println(Main.init.mdr.getValue());
				}
				dataR = Integer.parseInt(Main.init.r1.getValue(),2);
				dataEA = Integer.parseInt(Main.init.mdr.getValue(),2);
				result = dataR*flagR - dataEA*flagEA;
				System.out.println(result);
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result <0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
						Main.init.r1.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r1.setValue(resultStr);
					}
					Main.stepListModel.addElement("R1 <- ARR");
				}
			}
			else if(this.RF.equals("10")) {
				flagR = Main.init.r2.getValue().charAt(0);
				flagEA = Main.init.mdr.getValue().charAt(0);
				if(flagR == '1') {
					replaceStr = Main.init.r2.getValue().replaceFirst(Main.init.r2.getValue().substring(0, 1), "0");
					Main.init.r2.setValue(replaceStr);
					flagR = -1;
//					System.out.println(Main.init.r0.getValue());
				}
				if(flagEA == '1') {
					replaceStr = Main.init.mdr.getValue().replaceFirst(Main.init.mdr.getValue().substring(0, 1), "0");
					Main.init.mdr.setValue(replaceStr);
					flagEA = -1;
//					System.out.println(Main.init.mdr.getValue());
				}
				dataR = Integer.parseInt(Main.init.r2.getValue(),2);
				dataEA = Integer.parseInt(Main.init.mdr.getValue(),2);
				result = dataR*flagR - dataEA*flagEA;
				System.out.println(result);
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result <0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
						Main.init.r2.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r2.setValue(resultStr);
					}
					Main.stepListModel.addElement("R2 <- ARR");
				}
			}
			else if(this.RF.equals("11")) {
				flagR = Main.init.r3.getValue().charAt(0);
				flagEA = Main.init.mdr.getValue().charAt(0);
				if(flagR == '1') {
					replaceStr = Main.init.r3.getValue().replaceFirst(Main.init.r3.getValue().substring(0, 1), "0");
					Main.init.r3.setValue(replaceStr);
					flagR = -1;
//					System.out.println(Main.init.r0.getValue());
				}
				if(flagEA == '1') {
					replaceStr = Main.init.mdr.getValue().replaceFirst(Main.init.mdr.getValue().substring(0, 1), "0");
					Main.init.mdr.setValue(replaceStr);
					flagEA = -1;
//					System.out.println(Main.init.mdr.getValue());
				}
				dataR = Integer.parseInt(Main.init.r3.getValue(),2);
				dataEA = Integer.parseInt(Main.init.mdr.getValue(),2);
				result = dataR*flagR - dataEA*flagEA;
//				System.out.println(result);
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result <0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
						Main.init.r3.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r3.setValue(resultStr);
					}
					Main.stepListModel.addElement("R3 <- ARR");
				}
			}
			Main.stepListModel.addElement("PC <- PC+1");
			Main.stepListModel.addElement("----------------");
//			System.out.println(Main.init.mdr.getValue());
		}
	}
	
	private void processAIR() {
		this.RF = bcommand.substring(6, 8); 
		this.Immediate = bcommand.substring(8, 14);
//		System.out.println(bcommand);
		if(Integer.parseInt(this.Immediate) == 0)
			System.out.println("Does nothing");
		else {
			int flagImmediate = 1, flagR = 1;
			int dataImmediate, dataR, result;
			String strImmediate = this.Immediate;
			String strR;
			if(this.RF.equals("00")) {
				strR = Main.init.r0.getValue();
				if(strR.startsWith("1")) {
					flagR = -1;
					strR = strR.replaceFirst(strR.substring(0,1), "0");
				}
				if(strImmediate.startsWith("1")) {
					flagImmediate = -1;
					strImmediate = strImmediate.replaceFirst(strImmediate.substring(0,1), "0");
				}
				dataR = Integer.parseInt(strR, 2);
				dataImmediate = Integer.parseInt(strImmediate,2);
				result = dataR*flagR + dataImmediate*flagImmediate;
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result < 0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
//						System.out.println(resultStr);
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
//						System.out.println(resultStr);
						Main.init.r0.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r0.setValue(resultStr);
					}
					Main.stepListModel.addElement("R0 <- c(R0)+Immed");
				}
			}
			else if(this.RF.equals("01")) {
				strR = Main.init.r1.getValue();
				if(strR.startsWith("1")) {
					flagR = -1;
					strR = strR.replaceFirst(strR.substring(0,1), "0");
				}
				if(strImmediate.startsWith("1")) {
					flagImmediate = -1;
					strImmediate = strImmediate.replaceFirst(strImmediate.substring(0,1), "0");
				}
				dataR = Integer.parseInt(strR, 2);
				dataImmediate = Integer.parseInt(strImmediate,2);
				result = dataR*flagR + dataImmediate*flagImmediate;
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result < 0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
//						System.out.println(resultStr);
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
//						System.out.println(resultStr);
						Main.init.r1.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r1.setValue(resultStr);
					}
					Main.stepListModel.addElement("R1 <- c(R1)+Immed");
				}
			}
			else if(this.RF.equals("10")) {
				strR = Main.init.r2.getValue();
				if(strR.startsWith("1")) {
					flagR = -1;
					strR = strR.replaceFirst(strR.substring(0,1), "0");
				}
				if(strImmediate.startsWith("1")) {
					flagImmediate = -1;
					strImmediate = strImmediate.replaceFirst(strImmediate.substring(0,1), "0");
				}
				dataR = Integer.parseInt(strR, 2);
				dataImmediate = Integer.parseInt(strImmediate,2);
				result = dataR*flagR + dataImmediate*flagImmediate;
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result < 0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
//						System.out.println(resultStr);
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
//						System.out.println(resultStr);
						Main.init.r2.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r2.setValue(resultStr);
					}
					Main.stepListModel.addElement("R2 <- c(R2)+Immed");
				}
			}
			else if(this.RF.equals("11")) {
				strR = Main.init.r3.getValue();
				if(strR.startsWith("1")) {
					flagR = -1;
					strR = strR.replaceFirst(strR.substring(0,1), "0");
				}
				if(strImmediate.startsWith("1")) {
					flagImmediate = -1;
					strImmediate = strImmediate.replaceFirst(strImmediate.substring(0,1), "0");
				}
				dataR = Integer.parseInt(strR, 2);
				dataImmediate = Integer.parseInt(strImmediate,2);
				result = dataR*flagR + dataImmediate*flagImmediate;
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result < 0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
//						System.out.println(resultStr);
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
//						System.out.println(resultStr);
						Main.init.r3.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r3.setValue(resultStr);
					}
					Main.stepListModel.addElement("R3 <- c(R3)+Immed");
				}
			}
		}
		Main.stepListModel.addElement("PC <- PC+1");
		Main.stepListModel.addElement("----------------");
	}
	
	private void processSIR() {
		this.RF = bcommand.substring(6, 8); 
		this.Immediate = bcommand.substring(8, 14);
//		System.out.println(bcommand);
		if(Integer.parseInt(this.Immediate) == 0)
			System.out.println("Does nothing");
		else {
			int flagImmediate = 1, flagR = 1;
			int dataImmediate, dataR, result;
			String strImmediate = this.Immediate;
			String strR;
			if(this.RF.equals("00")) {
				strR = Main.init.r0.getValue();
				if(strR.startsWith("1")) {
					flagR = -1;
					strR = strR.replaceFirst(strR.substring(0,1), "0");
				}
				if(strImmediate.startsWith("1")) {
					flagImmediate = -1;
					strImmediate = strImmediate.replaceFirst(strImmediate.substring(0,1), "0");
				}
				dataR = Integer.parseInt(strR, 2);
				dataImmediate = Integer.parseInt(strImmediate,2);
				result = dataR*flagR - dataImmediate*flagImmediate;
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result < 0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
//						System.out.println(resultStr);
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
//						System.out.println(resultStr);
						Main.init.r0.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r0.setValue(resultStr);
					}
					Main.stepListModel.addElement("R0 <- c(R0)-Immed");
				}
			}
			else if(this.RF.equals("01")) {
				strR = Main.init.r1.getValue();
				if(strR.startsWith("1")) {
					flagR = -1;
					strR = strR.replaceFirst(strR.substring(0,1), "0");
				}
				if(strImmediate.startsWith("1")) {
					flagImmediate = -1;
					strImmediate = strImmediate.replaceFirst(strImmediate.substring(0,1), "0");
				}
				dataR = Integer.parseInt(strR, 2);
				dataImmediate = Integer.parseInt(strImmediate,2);
				result = dataR*flagR - dataImmediate*flagImmediate;
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result < 0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
//						System.out.println(resultStr);
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
//						System.out.println(resultStr);
						Main.init.r1.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r1.setValue(resultStr);
					}
					Main.stepListModel.addElement("R1 <- c(R1)-Immed");
				}
			}
			else if(this.RF.equals("10")) {
				strR = Main.init.r2.getValue();
				if(strR.startsWith("1")) {
					flagR = -1;
					strR = strR.replaceFirst(strR.substring(0,1), "0");
				}
				if(strImmediate.startsWith("1")) {
					flagImmediate = -1;
					strImmediate = strImmediate.replaceFirst(strImmediate.substring(0,1), "0");
				}
				dataR = Integer.parseInt(strR, 2);
				dataImmediate = Integer.parseInt(strImmediate,2);
				result = dataR*flagR - dataImmediate*flagImmediate;
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result < 0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
//						System.out.println(resultStr);
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
//						System.out.println(resultStr);
						Main.init.r2.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r2.setValue(resultStr);
					}
					Main.stepListModel.addElement("R2 <- c(R2)-Immed");
				}
			}
			else if(this.RF.equals("11")) {
				strR = Main.init.r3.getValue();
				if(strR.startsWith("1")) {
					flagR = -1;
					strR = strR.replaceFirst(strR.substring(0,1), "0");
				}
				if(strImmediate.startsWith("1")) {
					flagImmediate = -1;
					strImmediate = strImmediate.replaceFirst(strImmediate.substring(0,1), "0");
				}
				dataR = Integer.parseInt(strR, 2);
				dataImmediate = Integer.parseInt(strImmediate,2);
				result = dataR*flagR - dataImmediate*flagImmediate;
				if(result < -32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too small!");
				else if(result > 32767)
					JOptionPane.showMessageDialog(Main.jFrame, "The sum number is too big!");
				else {
					String resultStr;
					if(result < 0) {
						result = 0-result;
						resultStr = Integer.toBinaryString(result);
						resultStr = StringUtils.leftPad(resultStr, 16, "0");
//						System.out.println(resultStr);
						resultStr = resultStr.replaceFirst(resultStr.substring(0, 1), "1");
//						System.out.println(resultStr);
						Main.init.r3.setValue(resultStr);
					}
					else {
						resultStr = StringUtils.leftPad(Integer.toBinaryString(result), 16, "0");
						Main.init.r3.setValue(resultStr);
					}
					Main.stepListModel.addElement("R3 <- c(R3)-Immed");
				}
			}
		}
		Main.stepListModel.addElement("PC <- PC+1");
		Main.stepListModel.addElement("----------------");
	}
}
