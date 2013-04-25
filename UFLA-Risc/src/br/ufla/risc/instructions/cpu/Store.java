package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionM;
import br.ufla.risc.memory.Memory;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 17)
public class Store extends InstructionM {
	
	private Memory memory;
	private RegisterBank registerBank;
	
	public Store(){
		memory = Memory.getInstance();
		registerBank = RegisterBank.getInstance();
	}
	
	@Override
	public Integer execution() {		
		memory.write(registerBank.read(super.getDestinyRegister()), valueRa);
		return null;
	}
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		super.setFields(binaryInstruction, registerBank);		
		valueRb = 0;
	}
}
