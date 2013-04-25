package br.ufla.risc.registers.controls;

import br.ufla.risc.instructions.InstructionBehavior;
import br.ufla.risc.instructions.our.Nop;
import br.ufla.risc.stages.Decoder;

public class RegIDEX implements RegisterControl {
	
	private InstructionBehavior instruction;
	
	private Decoder decoder;
	
	private RegIDEX(){
		decoder = (Decoder)Decoder.getInstance();		
	}
	
	private static RegisterControl instance = null;
	
	public static RegisterControl getInstance(){
		if (instance == null){		
			instance = new RegIDEX();
		}
		return instance;
	}

	@Override
	public void read() {		
		instruction = decoder.getInstruction();
	}

	public InstructionBehavior getInstruction() {
		return instruction;
	}
	
	public Integer getDestinyRegister(){
		if(instruction != null)
			return instruction.getDestinyRegister();
		else
			return null;
	}
	
	public void setStall(){
		instruction = new Nop();
	}
	
	public String toString(){
		String str = "null";
		if (instruction != null)	
			str = instruction.toString();
		return String.format("RegDIEX: [instruction] = %s", str);
	}
}
