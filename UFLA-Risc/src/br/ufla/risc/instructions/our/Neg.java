package br.ufla.risc.instructions.our;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionR;

@Instruction(opcode = 27)
public class Neg extends InstructionR {

	@Override
	public Integer execution() {
		return -valueRa;
	}

}
