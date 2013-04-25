package br.ufla.risc.instructions.cpu;

import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionM;
import br.ufla.risc.memory.Memory;
import br.ufla.risc.registers.RegisterBank;

@Instruction(opcode = 16)
public class Load extends InstructionM {
	
	private Memory memory;
	
	public Load(){
		memory = Memory.getInstance();
	}
	
	@Override
	public Integer execution() {
		return memory.readData(valueRa);
	}
	
	public void setFields(int binaryInstruction, RegisterBank registerBank){
		super.setFields(binaryInstruction, registerBank);		
		valueRb = 0;
	}
}
