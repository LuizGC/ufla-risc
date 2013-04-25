package br.ufla.risc.controls;

import java.util.List;

public class WithoutPipeline extends Control{
	
	private CallBack callBack;
	
	public WithoutPipeline(List<Integer> instructions, CallBack callBack) {
		super(instructions);
		this.callBack = callBack;		
	}

	@Override
	public void execute() {
		for (Control.pc = 0; Control.pc < numberInstructions(); Control.pc++){
			callBack.setLog("<<<<<< PC = " + Control.pc + " >>>>>>>" );
			callBack.setLog("------fetch------");
			fetch();
			regIFID.read();
			callBack.setLog("------decoder------");
			decoder();			
			regIDEX.read();			
			callBack.setLog("------executionMemory------");
			executionMemory();			
			regEXWR.read();			
			callBack.setLog("------writeRegister------");
			writeRegister();
			callBack.updatePrint();
		}		
	}
		
	private void fetch(){
		fetch.upperBorder();
		fetch.lowerBorder();		
	}
	
	private void decoder(){
		decoder.upperBorder();
		decoder.lowerBorder();
	}
	
	private void executionMemory(){
		executionMemory.upperBorder();
		executionMemory.lowerBorder();
	}
	
	private void writeRegister(){
		writeRegister.upperBorder();
		writeRegister.lowerBorder();
	}
	
	private Integer numberInstructions(){
		return memory.sizeInstruction();
	}	
}
