package br.ufla.risc.instructions;

import br.ufla.risc.registers.RegisterBank;

public abstract class InstructionM implements InstructionBehavior{
	
	private static final int MASK = 0x000000FF;
	
	protected int valueRa, valueRb;
	
	protected Integer ra, rb;
	
	private int binaryInstruction;
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		this.binaryInstruction = binaryInstruction;
		ra = (binaryInstruction >> 16) & MASK;
		rb = (binaryInstruction >> 8) & MASK;
		valueRa = registerBank.read(ra);
		valueRb = registerBank.read(rb);		
	}
	
	public Integer getDestinyRegister(){
		return binaryInstruction & MASK;
	}

	public Integer getRa() {
		return ra;
	}

	public Integer getRb() {
		return rb;
	}

	public void setValueRa(int valueRa) {
		this.valueRa = valueRa;
	}

	public void setValueRb(int valueRb) {
		this.valueRb = valueRb;
	}
	
}
