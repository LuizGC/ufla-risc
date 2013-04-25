package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.controls.Control;
import br.ufla.risc.instructions.InstructionJ;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 22)
public class J extends InstructionJ {	
	
	private final Integer destinyRegister = 31;
	
	@Override
	public Integer execution() {
		jumpTaken = true;
		Control.pc = address - 1;				
		return null;
	}
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		super.setFields(binaryInstruction, registerBank);
	}
	
	public Integer getDestinyRegister(){
		return destinyRegister;
	}
}
