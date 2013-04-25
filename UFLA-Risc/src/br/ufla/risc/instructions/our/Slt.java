package br.ufla.risc.instructions.our;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionR;

/**
 * Seta o maior numero.
 * SLT - Set More Than.
 * @author jesimar
 */
@Instruction(opcode = 30)
public class Slt extends InstructionR {

	@Override
	public Integer execution() {
		return (valueRa < valueRb ? 1 : 0);
	}

}
