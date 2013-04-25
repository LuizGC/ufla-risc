package br.ufla.risc.instructions;

import br.ufla.risc.registers.RegisterBank;

public interface InstructionBehavior {

	public Integer execution();
	
	public void setFields(int binaryInstruction, RegisterBank registerBank);

	public Integer getDestinyRegister();
}
