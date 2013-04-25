package br.ufla.risc.instructions;

import br.ufla.risc.registers.RegisterBank;

public abstract class InstructionI implements InstructionBehavior{
	
	//mudar isto aqui.
	protected RegisterBank registerBank;
	
	protected int ra, rb, rc;
	
	protected int const16;	

	
	public InstructionI(int const16, int rc) {
		this.registerBank = RegisterBank.getInstance();
		this.const16 = const16;
		this.rc = rc;
	}
	
	public Integer getDestinyRegister(){
		return rc;
	}
	

}
