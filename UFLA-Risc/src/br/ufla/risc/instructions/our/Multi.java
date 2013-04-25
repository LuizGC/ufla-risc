package br.ufla.risc.instructions.our;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionC;

@Instruction(opcode = 26)
public class Multi extends InstructionC {

	@Override
	public Integer execution() {
		return valueRa * const8bits;
	}

}
