package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionR;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 3)
public class Zeros extends InstructionR {

	@Override
	public Integer execution() {
		return 0;
	}
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		super.setFields(binaryInstruction, registerBank);
		valueRa = 0;
		valueRb = 0;
		ra = null;
		rb = null;
	}

}
