package br.ufla.risc.instructions.our;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionN;

@Instruction(opcode = 13)
public class Nop extends InstructionN {

	@Override
	public Integer execution() {
		return null;
	}

}
