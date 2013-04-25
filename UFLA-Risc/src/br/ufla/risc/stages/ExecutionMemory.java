package br.ufla.risc.stages;

import br.ufla.risc.instructions.Branch;
import br.ufla.risc.instructions.InstructionBehavior;
import br.ufla.risc.instructions.InstructionJ;
import br.ufla.risc.instructions.cpu.Beq;
import br.ufla.risc.instructions.cpu.Bne;
import br.ufla.risc.instructions.cpu.Jr;
import br.ufla.risc.registers.controls.RegIDEX;

public class ExecutionMemory implements Stage {	
	
	private InstructionBehavior instruction;
	
	private RegIDEX regIDEX;
	
	private Integer result;

	private static Stage instance = null;
	
	private Integer destinyRegister;
	
	private boolean jumpTaken;
	
	private ExecutionMemory(){
		regIDEX = (RegIDEX)RegIDEX.getInstance();		
	}
	
	public static Stage getInstance(){
		if (instance == null){		
			instance = new ExecutionMemory();
		}
		return instance;
	}

	@Override
	public void upperBorder() {
		instruction = regIDEX.getInstruction();
		jumpTaken = false;
		destinyRegister = regIDEX.getDestinyRegister();
		if (instruction != null){	
			result = instruction.execution();
			if (instructionJump(instruction)){
				if (((Branch)instruction).branchTaken()){
					jumpTaken = true;
				}
			}
		}
	}

	@Override
	public void lowerBorder() {
		
	}

	public Integer getResult() {		
		return result;
	}
	
	public Integer getDestinyRegister() {
		return destinyRegister;
	}
	
	public boolean getJumpTaken(){		
		return jumpTaken;
	}
	
	private boolean instructionJump(InstructionBehavior inst){
		if (inst instanceof Bne){
			return true;
		}
		if (inst instanceof Beq){
			return true;
		}
		if (inst instanceof InstructionJ){
			return true;
		}		
		if (inst instanceof Jr){
			return true;
		}
		return false;
	}

	public String toString(){
		String str = "null";
		if (instruction != null)		
			str = instruction.toString();
		return String.format("ExecutionMemory: [result] = %d - [instruction] = %s", result, str);	
	}
	
	@Override
	public void clean() {
		instruction = null;		
	}
}
