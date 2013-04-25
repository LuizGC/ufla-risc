package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.controls.Control;
import br.ufla.risc.instructions.Branch;
import br.ufla.risc.instructions.InstructionR;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 19)
public class Jr extends InstructionR implements Branch{		
	
	private RegisterBank registerBank;
	
	private boolean jumpTaken;
	
	@Override
	public Integer execution() {	
		jumpTaken = true;
		Control.pc = registerBank.read(super.getDestinyRegister()) - 1;
		return null;
	}
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		super.setFields(binaryInstruction, registerBank);
		this.registerBank = RegisterBank.getInstance();
		valueRa = 0;
		valueRb = 0;
		ra = null;
		rb = null;
	}

	@Override
	public boolean branchTaken() {		
		return jumpTaken;
	}
}
