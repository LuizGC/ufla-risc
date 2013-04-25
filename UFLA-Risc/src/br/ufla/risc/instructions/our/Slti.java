package br.ufla.risc.instructions.our;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionC;

/**
 * Seta rc se o numero for menor que o imediato.
 * SLT - Set less than Immediate.
 * @author jesimar
 */
@Instruction(opcode = 31)
public class Slti extends InstructionC {

	@Override
	public Integer execution() {
		return (valueRa < const8bits ? 1 : 0);
	}

}
