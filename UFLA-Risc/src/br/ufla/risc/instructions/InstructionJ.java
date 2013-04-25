package br.ufla.risc.instructions;

import br.ufla.risc.registers.RegisterBank;

public abstract class InstructionJ implements InstructionBehavior, Branch{
	
	private static final int MASK = 0x00FFFFFF;

	protected int address;
	
	protected boolean jumpTaken;
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){			
		address = binaryInstruction & MASK;			
	}
		
	public boolean branchTaken(){
		return jumpTaken;
	}
}
