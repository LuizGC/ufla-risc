package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.controls.Control;
import br.ufla.risc.instructions.Branch;
import br.ufla.risc.instructions.InstructionR;

@Instruction(opcode = 21)
public class Bne extends InstructionR implements Branch{

	private boolean jumpTaken;
	
	@Override
	public Integer execution() {
		jumpTaken = false;
		if (valueRa != valueRb){
			jumpTaken = true;
			//PC esta na verdade recebendo o endereco nao registrador destino.
			Control.pc = getDestinyRegister() - 1;
		}
		return null;
	}

	@Override
	public boolean branchTaken() {
		return jumpTaken;
	}

}
