/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.risc.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.ufla.risc.assembler.Assembler;
import br.ufla.risc.controls.CallBack;
import br.ufla.risc.controls.Control;
import br.ufla.risc.controls.WithPipeline;
import br.ufla.risc.controls.WithoutPipeline;
import br.ufla.risc.memory.Memory;
import br.ufla.risc.registers.RegisterBank;

/**
 *
 * @author Jesimar, Luccas, Luiz e Márcio.
 */
public class Panel {    
    
    private final int SIZE_X;
    private final int SIZE_Y;
    
    private JPanel panelCode = new JPanel();
    private JPanel panelCodeBits = new JPanel();
    private JPanel panelControl = new JPanel();
    private JPanel panelRegister = new JPanel();
    private JPanel panelLog = new JPanel();
    private JPanel panelMemory = new JPanel();
    
    private JTextArea textCode = new JTextArea();
    private JTextArea textCodeBits = new JTextArea();
    private JTextArea printRegister = new JTextArea();
    private JTextArea printLog = new JTextArea();
    private JTextArea printMemory = new JTextArea();
    
    private List<Integer> listInstruction = new ArrayList<Integer>();
    
    private Assembler assember;
    
    public Panel(int SIZE_X, int SIZE_Y){
        this.SIZE_X = SIZE_X;
        this.SIZE_Y = SIZE_Y;
        assember = Assembler.getInstance();        
    }
    
    public void build(Window window){
    	JLabel nameCode = new JLabel("Código");
    	panelCode.add(nameCode);
        window.add(createPanel(panelCode, SIZE_X/2 - 240, SIZE_Y - 230));   
        
        JLabel nameCodeBits = new JLabel("Código Binário");
        panelCodeBits.add(nameCodeBits);
        window.add(createPanel(panelCodeBits, SIZE_X/2 - 200, SIZE_Y - 230));
        
        JLabel nameRegister = new JLabel("Registrador");
        panelRegister.add(nameRegister);
        window.add(createPanel(panelRegister, SIZE_X/2 - 400, SIZE_Y - 230));
        
        JLabel nameMemory = new JLabel("Memória");
        panelMemory.add(nameMemory);
        window.add(createPanel(panelMemory, SIZE_X/2 - 370, SIZE_Y - 230));
        
        JLabel nameMenu = new JLabel("Menu");
        panelControl.add(nameMenu);        
        window.add(createPanel(panelControl, SIZE_X/2 - 350, SIZE_Y - 230));
        
        JLabel nameLog = new JLabel("Log Dados");
        panelLog.add(nameLog);
        window.add(createPanel(panelLog, SIZE_X - 36, 180));
        
        addAreaText();
        addButtons();
    }
    
    private JPanel createPanel(JPanel panel, int sizeX, int sizeY){
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));        
        panel.setPreferredSize(new Dimension(sizeX, sizeY));        
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }
    
    private void addAreaText(){
        panelCode.add(createAreaTextScroll(textCode, true, 32, 20));        
        panelCodeBits.add(createAreaTextScroll(textCodeBits, false, 32, 26));
        panelLog.add(createAreaTextScroll(printLog, false, 10, 86));
        panelMemory.add(createAreaTextScroll(printMemory, false, 32, 10));
        
        printRegister.setBackground(Color.white);
        printRegister.setEditable(false);
        printRegister.setColumns(8);
        printRegister.setRows(32);
        panelRegister.add(printRegister);       
    }
    
    private JScrollPane createAreaTextScroll(JTextArea textArea, boolean editable, 
            int rows, int columns){        
        JScrollPane scroll = new JScrollPane();        
        textArea.setBackground(Color.white);
        textArea.setEditable(editable);
        textArea.setColumns(columns);
        textArea.setRows(rows);
        scroll.setViewportView(textArea);
        return scroll;
    }
    
    private void addButtons(){              
        
        JButton btnWithoutPipeline = new JButton("Sem Pipeline");
        btnWithoutPipeline.setPreferredSize(new Dimension(140, 25));
        btnWithoutPipeline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
            	assembly();            	
            	Control control = new WithoutPipeline(listInstruction, new CallBack() {					
					@Override
					public void updatePrint() {
						printRegister.setText("");
						printRegister.append(RegisterBank.getInstance().printRegister());
						
						printMemory.setText("");
						printMemory.append(Memory.getInstance().printMemory());				
					}
					@Override
					public void setLog(String msg){
						printLog.append(msg + "\n");
					}
				});
        		control.execute();
            }
        });
        panelControl.add(btnWithoutPipeline);
        
        JButton btnWithPipeline = new JButton("Com Pipeline");
        btnWithPipeline.setPreferredSize(new Dimension(140, 25));
        btnWithPipeline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	assembly();
            	Control control = new WithPipeline(listInstruction, new CallBack() {					
					@Override
					public void updatePrint() {
						printRegister.setText("");
						printRegister.append(RegisterBank.getInstance().printRegister());
						
						printMemory.setText("");
						printMemory.append(Memory.getInstance().printMemory());				
					}
					@Override
					public void setLog(String msg){
						printLog.append(msg + "\n");
					}
				});
        		control.execute();
            }
        });
        panelControl.add(btnWithPipeline);          
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setPreferredSize(new Dimension(140, 25));
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	clear();
            	listInstruction.clear();
            }
        });
        panelControl.add(btnLimpar);
        
        JButton btnAbout = new JButton("Sobre");
        btnAbout.setPreferredSize(new Dimension(140, 25));
        btnAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, 
                        "Simulador: Processador UFLA - RISC\n\n"
                        + "Trabalho: Arquitetura Computadores II\n\n"
                        + "Professor: Luiz Henrique Andrade Correia\n\n"
                        + "Autores: Luccas Rafael Martins Pinto\n"
                        + "Luiz Augusto Costa\n"
                        + "Jesimar da Silva Arantes\n"                        
                        + "Márcio Camargo", 
                        "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelControl.add(btnAbout);
    }  
    
    private void clear(){
    	textCodeBits.setText("");
    	printLog.setText("");
    	printMemory.setText("");
    	printRegister.setText("");       
    	Memory.getInstance().clearMemory();
    	RegisterBank.getInstance().clearRegisterBank();
    }
    
    private String preProcesserText(String text){
    	String newText = "";
    	String lines[] = text.split("\n");
        for (int i = 0; i < lines.length; i++){
        	if (lines[i].length() == 0){        		
        		continue;
        	}
        	if (lines[i].contains("#")){
        		String codigo[] = lines[i].split("#");
        		int instructionBinary = assember.decoderInstruction(codigo[0]);
        		if (instructionBinary != 0){
          			newText += codigo[0] + "\n";
        		}else{
        			JOptionPane.showMessageDialog(null, "Erro: Linha = " + (i + 1) + 
        					"\nCodigo Gerado Desconsiderando tal Linha.", 
        					"Erro", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}else{
        		int instructionBinary = assember.decoderInstruction(lines[i]);
        		if (instructionBinary != 0){
          			newText += lines[i] + "\n";
        		}else{
        			JOptionPane.showMessageDialog(null, "Erro: Linha = " + (i + 1) +
        					"\nCodigo Gerado Desconsiderando tal Linha.", 
        					"Erro", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}        	
        } 
        return newText;
    }
    
    private void assembly(){
    	clear();
    	listInstruction.clear();         	
        String newText = preProcesserText(textCode.getText());
        String lines[] = newText.split("\n");
        for (int i = 0; i < lines.length; i++){  
        	int instructionBinary = assember.decoderInstruction(lines[i]);
        	listInstruction.add(instructionBinary);
        	textCodeBits.append(extendsInstruction32Bits(instructionBinary) + "\n");
        }
    }
    
    private String extendsInstruction32Bits(int instruction){
        String instruction32Bits = Integer.toBinaryString(instruction);
        if (instruction32Bits.length() < 32){
            int numberZeros = 32 - instruction32Bits.length();
            String zeros = "";
            for (int i = 0; i < numberZeros; i++){
                zeros += "0";
            }
            instruction32Bits = zeros + instruction32Bits;
        }        
        return instruction32Bits;
    }
}
