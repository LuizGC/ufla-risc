package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionR;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 12)
public class Copy extends InstructionR {

	@Override
	public Integer execution() {
		return valueRa;
	}
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		super.setFields(binaryInstruction, registerBank);		
		valueRb = 0;
		rb = null;
	}

}
