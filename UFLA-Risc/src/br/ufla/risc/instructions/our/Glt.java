package br.ufla.risc.instructions.our;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionR;

/**
 * Retorna o menor numero.
 * GLT - Get Less Than.
 * @author jesimar
 */
@Instruction(opcode = 29)
public class Glt extends InstructionR {

	@Override
	public Integer execution() {
		return (valueRa < valueRb ? valueRa : valueRb);
	}

}
