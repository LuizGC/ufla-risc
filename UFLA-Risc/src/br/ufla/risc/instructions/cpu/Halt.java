package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.controls.Control;
import br.ufla.risc.instructions.InstructionBehavior;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 255)
public class Halt implements InstructionBehavior {	
	@Override
	public Integer execution() {
		Control.end = true;
		return null;
	}	
	
	@Override
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		
	}
	
	public Integer getDestinyRegister(){
		return null;
	}
}
