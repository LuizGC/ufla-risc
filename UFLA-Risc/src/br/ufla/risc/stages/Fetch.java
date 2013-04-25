package br.ufla.risc.stages;

import br.ufla.risc.controls.Control;
import br.ufla.risc.memory.Memory;

public class Fetch implements Stage{
	
	private static Stage instance = null;
	
	private Memory memory;
	
	private int fetchedBinaryInstruction;		
	
	private Fetch(){
		memory = Memory.getInstance();
	}
	
	public static Stage getInstance(){
		if (instance == null){		
			instance = new Fetch();
		}
		return instance;
	}

	@Override
	public void upperBorder() {
		fetchedBinaryInstruction = memory.readInstruction(Control.pc);
	}
	
	@Override
	public void lowerBorder() {
				
	}	

	public int getBinaryInstruction() {
		return fetchedBinaryInstruction;
	}
	
	public String toString(){
		return String.format("Fetch: [fetchedBinaryInstruction] = %d", fetchedBinaryInstruction);
	}

	@Override
	public void clean() {
		fetchedBinaryInstruction = 0;		
	}
}
