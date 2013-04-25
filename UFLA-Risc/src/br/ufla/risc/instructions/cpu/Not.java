package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionR;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 6)
public class Not extends InstructionR {

	private static final int MASK = 0xFFFFFFFF;
	
	@Override
	public Integer execution() {
		return (valueRa ^ MASK) + 1;
	}
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		super.setFields(binaryInstruction, registerBank);		
		valueRb = 0;
		rb = null;
	}
}
