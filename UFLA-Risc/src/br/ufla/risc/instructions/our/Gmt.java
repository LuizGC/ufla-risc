package br.ufla.risc.instructions.our;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionR;

/**
 * Retorna o maior numero.
 * GLT - Get More Than.
 * @author jesimar
 */
@Instruction(opcode = 28)
public class Gmt extends InstructionR {

	@Override
	public Integer execution() {
		return (valueRa > valueRb ? valueRa : valueRb);
	}

}
