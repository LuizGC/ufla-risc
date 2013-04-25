package br.ufla.risc.instructions.our;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionC;

@Instruction(opcode = 23)
public class Addi extends InstructionC {

	@Override
	public Integer execution() {
		return valueRa + const8bits;
	}

}
