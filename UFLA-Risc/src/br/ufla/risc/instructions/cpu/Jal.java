package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.controls.Control;
import br.ufla.risc.instructions.InstructionJ;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 18)
public class Jal extends InstructionJ {	
	
	private final Integer destinyRegister = 31;
	
	@Override
	public Integer execution() {
		jumpTaken = true;
		Integer retorno = Control.pc;
		Control.pc = address - 1;				
		return retorno;
	}
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		super.setFields(binaryInstruction, registerBank);
	}
	
	public Integer getDestinyRegister(){
		return destinyRegister;
	}
}
