package com.project.GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang3.StringUtils;

import com.project.init.Init;
import com.project.instruction.Instruction;
import com.project.memory.Memory;
import com.project.process.Process;
import com.project.registerImp.IR;
import com.project.registerImp.MAR;
import com.project.registerImp.MDR;
import com.project.registerImp.PC;
import com.project.registerImp.R0;
import com.project.registerImp.R3;
import com.project.ISA.ISA;

public class Main {
	
	public static Init init;
	public static Vector<Instruction> instructionVec;
	public static Process process;
//	public static Memory memory;
	public static JFrame jFrame;
	public static JPanel jPanel;
	
	public static JLabel ISALabel;
	public static JScrollPane ISAScrollPane;
	public static DefaultListModel ISAListModel;
	public static JList ISAList;
	public static JButton ISAAddBtn;
	public static JButton ISALoadBtn;
	public static JButton ISAProcessBtn;
	public static JLabel singleLabel;
	public static JTextField singleTextField;
	public static JButton singleBtn;
	
	public static JLabel RAMLabel;
	public static JScrollPane RAMScrollPane;
	public static JTable RAMTable;
	public static DefaultTableModel RAMTableModel;
	public static JButton RAMButton;
	
	public static JLabel romLabel;
	public static JScrollPane romScrollPane;
	public static DefaultListModel romListModel;
	public static JList romList;
	
	public static JLabel stepLabel;
	public static JScrollPane stepScrollPane;
	public static DefaultListModel stepListModel;
	public static JList stepList;
	
	public static JLabel regLabel;
	public static JScrollPane regScrollPane;
	public static JTable regTable;
	public static DefaultTableModel regTableModel;
	public static JButton regSetBtn;
	public static JButton regResetBtn;
	
	public static JLabel pcAddressLabel;
	public static JTextField pcContentTextField;
//	public static JLabel singlePCAddrLabel;
	public static JScrollPane singleScrollPane;
	public static DefaultListModel singleListModel;
	public static JList singleList; 
//	public static DefaultListModel RAMListModel;
//	public static JList RAMList;
	
	
	public static void main(String[] args) {
		init = new Init();
		init.reset();
		instructionVec = new Vector<Instruction>();
		process = new Process();
		initializeGUI();
//		Instruction instruction = new Instruction();
		// TODO Auto-generated method stub
//		System.out.println("Hello World");
//		System.out.println(Memory.ram[31]);
		
//		Scanner in = new Scanner(System.in);
//		Instruction.command = in.nextLine();
//		System.out.println(Instruction.command);
		
//		int NumOfOPCODE = Instruction.command.indexOf(" ");
//		Instruction.opcode = Instruction.command.substring(0, NumOfOPCODE);
////		System.out.println(Instruction.opcode);
//		
//		int NumOfRF = Instruction.command.indexOf(",");
//		Instruction.RF = Instruction.command.substring(NumOfOPCODE+1, NumOfRF);
////		System.out.println(Instruction.RF);
//		int NumOfXF = Instruction.command.indexOf(",",NumOfRF+1);
//		Instruction.XF = Instruction.command.substring(NumOfRF+1, NumOfXF);
////		System.out.println(Instruction.XF);
//		int NumOfI = Instruction.command.indexOf(",",NumOfXF+1);
//		Instruction.I = Instruction.command.substring(NumOfXF+1, NumOfI);
////		System.out.println(Instruction.I);
////		int NumOfAddress = Instruction.command.indexOf(",",NumOfI+1);
//		Instruction.ADDR = Instruction.command.substring(NumOfI+1, Instruction.command.length());
////		System.out.println(Instruction.ADDR);
//		
//		
//		
		
		
//		
//		MAR.MARvalue = PC.PCvalue;
//		MDR.MDRvalue = MAR.MARvalue;
//		
//		
		
		
//		IR.IRvalue = translation();
//		System.out.println(IR.IRvalue);
		
//		R3.R3value = Memory.ram[Integer.parseInt(Instruction.ADDR)];
//		System.out.println(R3.R3value);
//		System.out.println(Memory.memoryData[31]);
		
		
		
	}
	
	
	public static void initializeGUI() {

//		memory = new Memory();
		
		jFrame = new JFrame("Simulator");
		jFrame.setBounds(0, 0, 1400, 800);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		
		jPanel = (JPanel)jFrame.getContentPane();
		jPanel.setLayout(null);
		
		ISALabel = new JLabel("Instruction Set");    //setting ISA area
		ISALabel.setBounds(90, 20, 100, 30);
		ISALabel.setFont(new Font("Times New Roman",Font.BOLD, 16));
		jPanel.add(ISALabel);
		ISAListModel = new DefaultListModel();
		ISAList = new JList(ISAListModel);
//		for(int i=0;i<100;i++)
//			ISAListModel.addElement(i+".aaa");
		ISAScrollPane = new JScrollPane(ISAList);
		ISAScrollPane.setPreferredSize(new Dimension(160, 88));
		ISAScrollPane.setBounds(90, 60, 160, 88);
		jPanel.add(ISAScrollPane);
		ISAAddBtn = new JButton("Add");
		ISAAddBtn.setBounds(275, 60, 100, 25);
		jPanel.add(ISAAddBtn);
		ISALoadBtn = new JButton("Load");
		ISALoadBtn.setBounds(275, 120, 100, 25);
		jPanel.add(ISALoadBtn);
		ISAProcessBtn = new JButton("Process");
		ISAProcessBtn.setBounds(275, 180, 100, 25);
//		jPanel.add(ISAProcessBtn);
		singleLabel = new JLabel("Enter PC Address:");
		singleLabel.setBounds(90, 155, 200, 30);
		singleLabel.setFont(new Font("Times New Roman",Font.BOLD, 16));
		jPanel.add(singleLabel);
		singleTextField = new JTextField();
		singleTextField.setBounds(90, 185, 160, 30);
		jPanel.add(singleTextField);
		singleBtn = new JButton("Single Step");
		singleBtn.setBounds(275, 185, 100, 25);
		jPanel.add(singleBtn);
		
		RAMLabel = new JLabel("RAM");
		RAMLabel.setBounds(30, 265, 100, 30);
		RAMLabel.setFont(new Font("Times New Roman",Font.BOLD, 16));
		jPanel.add(RAMLabel);
		RAMTable = new JTable();
		String[] columns = {"Index", "Address", "Words"};
		int[] columnWidth = {10, 100, 70}; // 创建表格数据模型
		RAMTableModel = new DefaultTableModel(columns, init.memo.getMemoryLength());
		RAMTable.setModel(RAMTableModel);// 设置表格数据模型
		TableColumnModel columnModel = RAMTable.getColumnModel();// 获取列模型
		int count = columnModel.getColumnCount();// 获取列数量
		for (int i = 0; i < count; i++) {// 遍历列
			TableColumn column = columnModel.getColumn(i);// 获取列对象
			column.setPreferredWidth(columnWidth[i]);// 以数组元素设置列的宽度
		}
//		RAMTable.setEnabled(false);
//		for(int i=0;i<RAMTable.getRowCount();i++) {
//			for(int j=0;j<RAMTable.getColumnCount();j++) {
//				RAMTable.isCellEditable(i, j);
//			}
//		}
		RAMScrollPane = new JScrollPane(RAMTable);
		RAMScrollPane.setPreferredSize(new Dimension(400, 400));
		RAMScrollPane.setBounds(30, 300, 400, 400);
		updateRAMTable();
		jPanel.add(RAMScrollPane);
		RAMButton = new JButton("Enter Data");
		RAMButton.setBounds(130, 270, 100, 20);
		jPanel.add(RAMButton);
		
		romLabel = new JLabel("ROM");    //setting step area
		romLabel.setBounds(480, 20, 100, 30);
		romLabel.setFont(new Font("Times New Roman",Font.BOLD, 16));
		jPanel.add(romLabel);
		romListModel = new DefaultListModel();
		romList = new JList(romListModel);
		romScrollPane = new JScrollPane(romList);
		romScrollPane.setPreferredSize(new Dimension(260, 160));
		romScrollPane.setBounds(480, 60, 260, 160);
		jPanel.add(romScrollPane);		
		
		stepLabel = new JLabel("Steps");    //setting step area
		stepLabel.setBounds(830, 20, 100, 30);
		stepLabel.setFont(new Font("Times New Roman",Font.BOLD, 16));
		jPanel.add(stepLabel);
		stepListModel = new DefaultListModel();
		stepList = new JList(stepListModel);
		stepScrollPane = new JScrollPane(stepList);
		stepScrollPane.setPreferredSize(new Dimension(160, 160));
		stepScrollPane.setBounds(830, 60, 160, 160);
		jPanel.add(stepScrollPane);		
		
		regLabel = new JLabel("Register Information");    //setting step area
		regLabel.setBounds(480, 265, 200, 30);
		regLabel.setFont(new Font("Times New Roman",Font.BOLD, 16));
		jPanel.add(regLabel);
		regSetBtn = new JButton("Set");
		regSetBtn.setBounds(900, 300, 100, 30);
		jPanel.add(regSetBtn);
		regResetBtn = new JButton("Reset");
		regResetBtn.setBounds(900, 500, 100, 30);
		jPanel.add(regResetBtn);
		regTable = new JTable();
		String[] regColumns = {"Register","Content"};
		int[] regColumnWidth = {50,200}; // 创建表格数据模型
		regTableModel = new DefaultTableModel(regColumns, 12);
		regTable.setModel(regTableModel);// 设置表格数据模型
		TableColumnModel regColumnModel = regTable.getColumnModel();// 获取列模型
		int regCount = columnModel.getColumnCount();// 获取列数量
		for (int i = 0; i < count; i++) {// 遍历列
			TableColumn column = columnModel.getColumn(i);// 获取列对象
			column.setPreferredWidth(columnWidth[i]);// 以数组元素设置列的宽度
		}
		resetRegTable();
		regScrollPane = new JScrollPane(regTable);
		regScrollPane.setPreferredSize(new Dimension(400, 400));
		regScrollPane.setBounds(480, 300, 400, 400);
//		updateRAMTable();
		jPanel.add(regScrollPane);
		
//		singleLabel = new JLabel("Enter Single PC Address:");
//		singleLabel.setBounds(1050, 20, 200, 30);
//		singleLabel.setFont(new Font("Times New Roman",Font.BOLD, 16));
//		jPanel.add(singleLabel);
//		singleTextField = new JTextField();
//		singleTextField.setBounds(1050, 60, 200, 30);
//		jPanel.add(singleTextField);
//		singleBtn = new JButton("Single Step");
//		singleBtn.setBounds(1270, 60, 100, 30);
//		jPanel.add(singleBtn);
//		pcContentLabel = new JLabel("PC Content");
//		pcContentLabel.setBounds(1275, 100, 200, 30);
//		pcContentLabel.setFont(new Font("Times New Roman",Font.BOLD, 16));
//		jPanel.add(pcContentLabel);
//		pcContentTextField = new JTextField();
//		pcContentTextField.setBounds(1050, 100, 200, 30);
//		jPanel.add(pcContentTextField);
//		singleListModel = new DefaultListModel();
//		singleList = new JList(singleListModel);
//		singleScrollPane = new JScrollPane(singleList);
//		singleScrollPane.setPreferredSize(new Dimension(260, 160));
//		singleScrollPane.setBounds(1050, 60, 260, 160);
//		jPanel.add(singleScrollPane);	
		jPanel.revalidate();
		jPanel.repaint();
		
		ISAAddBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				JOptionPane.getRootFrame().setBounds(0, 0, 300, 100);
				String instructionInput=JOptionPane.showInputDialog(null,"Input the new instruction:","New Instruction",JOptionPane.PLAIN_MESSAGE);
				if (instructionInput != null) {
					ISAListModel.addElement(instructionInput);
					Instruction instruction = new Instruction();
					instruction.command = instructionInput;
					instruction.setInstruction();
					instruction.translation();
					instructionVec.add(instruction);
				}
//				for(int i=0;i<instructionVec.size();i++)
//				  System.out.println(i+":"+instructionVec.get(i).bcommand);
//				ISAScrollPane.revalidate();
//				jPanel.revalidate();
				
//				jPanel.repaint();
//				jPanel.updateUI();
//				jPanel.revalidate();    //when input >7 instruction, GUI has some problems
//				jPanel.repaint();
			}
		});
		
		ISALoadBtn.addActionListener(new ActionListener() {   //load all the instructions into rom
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				romListModel.clear();
				init.pc.setValue(Integer.toBinaryString(instructionVec.size()));
//				System.out.println(instructionVec.size());
//				System.out.println(init.pc.getValue());
				for(int i=0;i<instructionVec.size();i++) {
					init.memo.rom.addElement(instructionVec.get(i).bcommand);
//					ISAScrollPane.remove(i);
//					instructionVec.remove(i);
//					System.out.println(instructionVec.get(i).bcommand);
				}
				updateROMTable();
				ISAListModel.clear();
				instructionVec.clear();
				JOptionPane.showMessageDialog(jFrame, "All instructions have been loaded into rom successfully!");
//				jPanel.updateUI();
				jPanel.revalidate();
				jPanel.repaint();
			}
		});
		
		singleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pcAddress = singleTextField.getText();
				int pcIndex = Integer.parseInt(pcAddress);
				if(pcIndex >= init.memo.rom.size())
					JOptionPane.showMessageDialog(jFrame, "The input address is out of boundary!");
				else {
					process.processROMbcommand(init.memo.rom.get(pcIndex));
					updateRAMTable();
					updateRegTable();
				}
				init.memo.rom.clear();
				jPanel.revalidate();
				jPanel.repaint();
			}
		});
		
		ISAProcessBtn.addActionListener(new ActionListener() {  //process all the instructions in rom
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				ISAListModel.clear();
				for(int i=0;i<init.memo.rom.size();i++)
					process.processROMbcommand(init.memo.rom.get(i));
				updateRAMTable();
				updateRegTable();
				jPanel.revalidate();
				jPanel.repaint();
			}
		});
		
		RAMButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog setMemoDialog = new JDialog(jFrame,"Enter Data into RAM");
				setMemoDialog.setLayout(null);
				setMemoDialog.setSize(400, 220);
				setMemoDialog.setLocationRelativeTo(jFrame);
				setMemoDialog.setVisible(true);
				
				JLabel memoName = new JLabel("Input RAM index:");
				memoName.setBounds(20, 20, 150, 30);
				memoName.setFont(new Font("Times New Roman",Font.BOLD, 16));
				setMemoDialog.add(memoName);
				
				JTextField memoNameField = new JTextField();
				memoNameField.setBounds(180, 25, 150, 20);
				setMemoDialog.add(memoNameField);
				
				JLabel memoValue = new JLabel("Set value:");
				memoValue.setBounds(20, 60, 120, 30);
				memoValue.setFont(new Font("Times New Roman",Font.BOLD, 16));
				setMemoDialog.add(memoValue);
				
				JTextField memoValueField = new JTextField();
				memoValueField.setBounds(180, 65, 150, 20);
				setMemoDialog.add(memoValueField);
				
				JButton memoSubmitBtn = new JButton("Submit");
				memoSubmitBtn.setBounds(55, 110, 80, 30);
				setMemoDialog.add(memoSubmitBtn);
				
				JButton memoCancelBtn = new JButton("Cancel");
				memoCancelBtn.setBounds(185, 110, 80, 30);
				setMemoDialog.add(memoCancelBtn);
				
				memoSubmitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String name = memoNameField.getText();
						String content = memoValueField.getText();
//						content = Integer.toBinaryString(Integer.parseInt(content));
						content = StringUtils.leftPad(content, 16, "0");
						for(int i=0;i<RAMTable.getRowCount();i++) {
							if(RAMTable.getValueAt(i, 0).toString().equalsIgnoreCase(name)) {
								init.memo.ram[i] = content;
//								init.getRegByName(name).setValue(content);
								RAMTable.setValueAt(init.memo.ram[i], i, 2);
//								System.out.println(content);
								break;
							}
						}
						updateRegTable();
						jPanel.revalidate();
						jPanel.repaint();
						setMemoDialog.dispose();
					}
				});
				
				memoCancelBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setMemoDialog.dispose();
					}
				});
			}
		});
		
		regSetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog setRegDialog = new JDialog(jFrame,"Set Register");
				setRegDialog.setLayout(null);
				setRegDialog.setSize(350, 220);
				setRegDialog.setLocationRelativeTo(jFrame);
				setRegDialog.setVisible(true);
				
				JLabel regName = new JLabel("Select Register:");
				regName.setBounds(20, 20, 120, 30);
				regName.setFont(new Font("Times New Roman",Font.BOLD, 16));
				setRegDialog.add(regName);
				
				JComboBox<String> regComboBox = new JComboBox<String>();
//				regComboBox.addItem("EA");
//				regComboBox.addItem("IR");
//				regComboBox.addItem("MAR");
//				regComboBox.addItem("MDR");
//				regComboBox.addItem("PC");
				regComboBox.addItem("R0");
				regComboBox.addItem("R1");
				regComboBox.addItem("R2");
				regComboBox.addItem("R3");
//				regComboBox.addItem("X1");
//				regComboBox.addItem("X2");
//				regComboBox.addItem("X3");
				regComboBox.setBounds(150, 25, 150, 20);
				setRegDialog.add(regComboBox);
				
				JLabel regValue = new JLabel("Set value:");
				regValue.setBounds(20, 60, 120, 30);
				regValue.setFont(new Font("Times New Roman",Font.BOLD, 16));
				setRegDialog.add(regValue);
				
				JTextField regValueField = new JTextField();
				regValueField.setBounds(150, 65, 150, 20);
				setRegDialog.add(regValueField);
				
				JButton regSubmitBtn = new JButton("Submit");
				regSubmitBtn.setBounds(55, 110, 80, 30);
				setRegDialog.add(regSubmitBtn);
				
				JButton regCancelBtn = new JButton("Cancel");
				regCancelBtn.setBounds(185, 110, 80, 30);
				setRegDialog.add(regCancelBtn);
//				setRegDiaglog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
				
				regSubmitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String name = init.getRegByName(regComboBox.getSelectedItem().toString()).getName();
						String content = regValueField.getText();
//						content = Integer.toBinaryString(Integer.parseInt(content));
						for(int i=0;i<regTable.getRowCount();i++) {
							if(regTable.getValueAt(i, 0).toString().equalsIgnoreCase(name)) {
								init.getRegByName(name).setValue(content);
								regTable.setValueAt(init.getRegByName(name).getValue(), i, 1);
//								System.out.println(content);
								break;
							}
						}
						updateRegTable();
						setRegDialog.dispose();
					}
				});
				
				regCancelBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setRegDialog.dispose();
					}
				});
			}
		});
		
		regResetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				instructionVec.clear();
				ISAListModel.clear();
				romListModel.clear();
				stepListModel.clear();
				singleTextField.setText("");
				resetRegTable();
				Main.init.memo.resetMemory();
				updateRAMTable();
				jPanel.revalidate();
				jPanel.repaint();
			}
		});
		
	}
	
	public static void updateROMTable() {
		for(int i=0;i<init.memo.rom.size();i++)
			romListModel.addElement(" "+i+" : "+init.memo.rom.get(i));
//		jPanel.revalidate();
//		jPanel.repaint();
	}
	
	public static void updateRAMTable() {
		for(int i=0;i<init.memo.getMemoryLength();i++) {
			RAMTable.setValueAt(i, i, 0);  //insert ram index into RAMTable
			RAMTable.setValueAt(StringUtils.leftPad(Integer.toBinaryString(i), 16, "0"), i, 1); //insert ram address into RAMTable
			RAMTable.setValueAt(init.memo.ram[i], i, 2); //insert ram words into RAMTable
		}
//		jPanel.revalidate();
//		jPanel.repaint();
	}
	
	public static void updateRegTable() {
		regTable.setValueAt("EA", 0, 0);
		regTable.setValueAt(init.ea.getValue(), 0, 1);
		regTable.setValueAt("IR", 1, 0);
		regTable.setValueAt(init.ir.getValue(), 1, 1);
		regTable.setValueAt("MAR", 2, 0);
		regTable.setValueAt(init.mar.getValue(), 2, 1);
		regTable.setValueAt("MDR", 3, 0);
		regTable.setValueAt(init.mdr.getValue(), 3, 1);
		regTable.setValueAt("PC", 4, 0);
		regTable.setValueAt(init.pc.getValue(), 4, 1);
		regTable.setValueAt("R0", 5, 0);
		regTable.setValueAt(init.r0.getValue(), 5, 1);
		regTable.setValueAt("R1", 6, 0);
		regTable.setValueAt(init.r1.getValue(), 6, 1);
		regTable.setValueAt("R2", 7, 0);
		regTable.setValueAt(init.r2.getValue(), 7, 1);
		regTable.setValueAt("R3", 8, 0);
		regTable.setValueAt(init.r3.getValue(), 8, 1);
		regTable.setValueAt("X1", 9, 0);
		regTable.setValueAt(init.x1.getValue(), 9, 1);
		regTable.setValueAt("X2", 10, 0);
		regTable.setValueAt(init.x2.getValue(), 10, 1);
		regTable.setValueAt("X3", 11, 0);
		regTable.setValueAt(init.x3.getValue(), 11, 1);
//		System.out.println(init.r0.getValue());
	}
	
	public static void resetRegTable() {
		init.reset();
		regTable.setValueAt("EA", 0, 0);
		regTable.setValueAt(Main.init.ea.getValue(), 0, 1);
		regTable.setValueAt("IR", 1, 0);
		regTable.setValueAt(Main.init.ir.getValue(), 1, 1);
		regTable.setValueAt("MAR", 2, 0);
		regTable.setValueAt(Main.init.mar.getValue(), 2, 1);
		regTable.setValueAt("MDR", 3, 0);
		regTable.setValueAt(Main.init.mdr.getValue(), 3, 1);
		regTable.setValueAt("PC", 4, 0);
		regTable.setValueAt(Main.init.pc.getValue(), 4, 1);
		regTable.setValueAt("R0", 5, 0);
		regTable.setValueAt(Main.init.r0.getValue(), 5, 1);
		regTable.setValueAt("R1", 6, 0);
		regTable.setValueAt(Main.init.r1.getValue(), 6, 1);
		regTable.setValueAt("R2", 7, 0);
		regTable.setValueAt(Main.init.r2.getValue(), 7, 1);
		regTable.setValueAt("R3", 8, 0);
		regTable.setValueAt(Main.init.r3.getValue(), 8, 1);
		regTable.setValueAt("X1", 9, 0);
		regTable.setValueAt(Main.init.x1.getValue(), 9, 1);
		regTable.setValueAt("X2", 10, 0);
		regTable.setValueAt(Main.init.x2.getValue(), 10, 1);
		regTable.setValueAt("X3", 11, 0);
		regTable.setValueAt(Main.init.x3.getValue(), 11, 1);
	}
//	public static String translation(){
//		
//		String bopcode = null;
//		String	brf,bxf,bi,baddr,bcommand = null;
////		System.out.println(Instruction.opcode);
//		if(Instruction.opcode.equalsIgnoreCase("LDR")){
//			bopcode = "000001";
//		}else if(Instruction.opcode.equalsIgnoreCase("STR")){
//			bopcode = "000010";
//		}
//		
//		brf = Integer.toBinaryString(Integer.parseInt(Instruction.RF));
//		bxf = Integer.toBinaryString(Integer.parseInt(Instruction.XF));
//		bi  = Integer.toBinaryString(Integer.parseInt(Instruction.I));
//		baddr = Integer.toBinaryString(Integer.parseInt(Instruction.ADDR));
//		
//		brf = StringUtils.leftPad(brf, 2, "0");
//		bxf = StringUtils.leftPad(bxf, 2, "0");
//		bi = StringUtils.leftPad(bi, 2, "0");
//		baddr = StringUtils.leftPad(baddr, 5, "0");
//		
//		bcommand = bopcode + brf + bxf + bi + baddr ;
//		
//		return bcommand;
//		
//		
//	}
	

}
