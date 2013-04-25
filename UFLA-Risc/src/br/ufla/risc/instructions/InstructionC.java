package br.ufla.risc.instructions;

import br.ufla.risc.registers.RegisterBank;

public abstract class InstructionC implements InstructionBehavior{
	
	private static final int MASK = 0x000000FF;
	
	protected int valueRa;
	
	protected Integer ra;
	
	protected Integer const8bits;
	
	private int binaryInstruction;
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		this.binaryInstruction = binaryInstruction;
		ra = (binaryInstruction >> 16) & MASK;
		const8bits = (binaryInstruction >> 8) & MASK;
		valueRa = registerBank.read(ra);
	}
	
	public Integer getDestinyRegister(){
		return binaryInstruction & MASK;
	}

	public Integer getRa() {
		return ra;
	}

	public Integer getConst8Bits() {
		return const8bits;
	}

	public void setValueRa(int valueRa) {
		this.valueRa = valueRa;
	}
	
}
