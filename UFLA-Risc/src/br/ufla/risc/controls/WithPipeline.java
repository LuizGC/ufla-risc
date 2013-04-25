package br.ufla.risc.controls;

import java.util.List;
import br.ufla.risc.instructions.InstructionBehavior;
import br.ufla.risc.instructions.InstructionC;
import br.ufla.risc.instructions.InstructionR;
import br.ufla.risc.instructions.InstructionM;
import br.ufla.risc.instructions.cpu.Load;
import br.ufla.risc.instructions.cpu.Store;

public class WithPipeline extends Control{

	private CallBack callBack;
	
	public WithPipeline(List<Integer> instructions, CallBack callBack) {
		super(instructions);
		this.callBack = callBack;
	}

	@Override
	public void execute() {		
		cleanHalt();
		while(!Control.end){
			callBack.setLog("<<<<<< PC = " + Control.pc + " >>>>>>>" );
			callBack.setLog("------UpperBorder------");
			upperBorder();
			callBack.setLog("------ReadRegister------");
			readIntermediateRegisters();
			callBack.setLog("------LowerBorder------");
			lowerBorder();
			Control.pc++;
			callBack.setLog("\n");
			callBack.updatePrint();
		}
	}

	private void readIntermediateRegisters() {	
		
		regIFID.read();	
		callBack.setLog(regIFID.toString());
		InstructionBehavior instructionBefore = regIDEX.getInstruction();
		regIDEX.read();
		callBack.setLog(regIDEX.toString());		
		InstructionBehavior instructionActual = regIDEX.getInstruction();			
		regEXWR.read();				
		callBack.setLog(regEXWR.toString());
				
		forward(instructionBefore, instructionActual);		
	}	
	
	private void forward(InstructionBehavior instructionBefore, InstructionBehavior instructionActual){		
		if (instructionActual instanceof Store){
			if (instructionBefore instanceof InstructionR){
				//Caso RC == RA
				if (((InstructionR)instructionBefore).getDestinyRegister() == ((InstructionM)instructionActual).getRa()){
					((InstructionM)instructionActual).setValueRa(regEXWR.getResult());					
				}
			}
			if (instructionBefore instanceof InstructionC){
				//Caso RC == RA
				if (((InstructionC)instructionBefore).getDestinyRegister() == ((InstructionM)instructionActual).getRa()){
					((InstructionM)instructionActual).setValueRa(regEXWR.getResult());
				}
			}
		}
		
		if (instructionBefore instanceof Load){
			if (instructionActual instanceof InstructionR){
				//Caso RC == RA
				if (((InstructionM)instructionBefore).getDestinyRegister() == ((InstructionR)instructionActual).getRa()){
					((InstructionR)instructionActual).setValueRa(regEXWR.getResult());					
				}
				//Caso RC == RB
				if (((InstructionM)instructionBefore).getDestinyRegister() == ((InstructionR)instructionActual).getRb()){
					((InstructionR)instructionActual).setValueRb(regEXWR.getResult());
				}
			}		
			if (instructionActual instanceof InstructionC){
				//Caso RC == RA
				if (((InstructionM)instructionBefore).getDestinyRegister() == ((InstructionC)instructionActual).getRa()){
					((InstructionC)instructionActual).setValueRa(regEXWR.getResult());
				}				
			}
		}			
		
		if (instructionBefore instanceof InstructionR){
			if (instructionActual instanceof InstructionR){				
				//Caso RC == RA
				if (((InstructionR)instructionBefore).getDestinyRegister() == ((InstructionR)instructionActual).getRa()){
					((InstructionR)instructionActual).setValueRa(regEXWR.getResult());
				}
				//Caso RC == RB
				if (((InstructionR)instructionBefore).getDestinyRegister() == ((InstructionR)instructionActual).getRb()){
					((InstructionR)instructionActual).setValueRb(regEXWR.getResult());
				}
			}
		}
		
		if (instructionBefore instanceof InstructionC){
			if (instructionActual instanceof InstructionC){				
				//Caso RC == RA
				if (((InstructionC)instructionBefore).getDestinyRegister() == ((InstructionC)instructionActual).getRa()){
					((InstructionC)instructionActual).setValueRa(regEXWR.getResult());
				}				
			}
		}
		
		if (instructionBefore instanceof InstructionR){
			if (instructionActual instanceof InstructionC){				
				//Caso RC == RA
				if (((InstructionR)instructionBefore).getDestinyRegister() == ((InstructionC)instructionActual).getRa()){
					((InstructionC)instructionActual).setValueRa(regEXWR.getResult());
				}				
			}
		}
		
		if (instructionBefore instanceof InstructionC){
			if (instructionActual instanceof InstructionR){				
				//Caso RC == RA
				if (((InstructionC)instructionBefore).getDestinyRegister() == ((InstructionR)instructionActual).getRa()){
					((InstructionR)instructionActual).setValueRa(regEXWR.getResult());
				}
				//Caso RC == RB
				if (((InstructionC)instructionBefore).getDestinyRegister() == ((InstructionR)instructionActual).getRb()){
					((InstructionR)instructionActual).setValueRb(regEXWR.getResult());
				}
			}
		}			
	}
	
	private void upperBorder(){
		fetch.upperBorder();
		callBack.setLog(fetch.toString());
		decoder.upperBorder();
		callBack.setLog(decoder.toString());
		executionMemory.upperBorder();
		callBack.setLog(executionMemory.toString());
		writeRegister.upperBorder();
		callBack.setLog(writeRegister.toString());
	}
	
	private void lowerBorder(){
		fetch.lowerBorder();
		callBack.setLog(fetch.toString());
		decoder.lowerBorder();
		callBack.setLog(decoder.toString());
		
		if (executionMemory.getJumpTaken()){
			callBack.setLog("\n==========moni==========\n");
			regIFID.setStall();
			regIDEX.setStall();
		}
		
		executionMemory.lowerBorder();
		callBack.setLog(executionMemory.toString());
		writeRegister.lowerBorder();
		callBack.setLog(writeRegister.toString());
	}	
	
	private void cleanHalt(){
		fetch.clean();
		decoder.clean();
		executionMemory.clean();
		writeRegister.clean();
		regIFID.setStall();
		regIDEX.setStall();
	}
}
