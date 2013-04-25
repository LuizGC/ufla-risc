package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionR;

@Instruction(opcode = 7)
public class And extends InstructionR {

	@Override
	public Integer execution() {
		return valueRa & valueRb;
	}

}
