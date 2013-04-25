package br.ufla.risc.registers.controls;

import br.ufla.risc.stages.Fetch;

public class RegIFID implements RegisterControl {
	
	private int fetchedBinaryInstruction;

	private static RegisterControl instance = null;
	
	private Fetch fetch;
	
	private RegIFID(){
		fetch = (Fetch)Fetch.getInstance();		
	}
	
	public static RegisterControl getInstance(){
		if (instance == null){		
			instance = new RegIFID();
		}
		return instance;
	}
	
	public void read(){
		fetchedBinaryInstruction = fetch.getBinaryInstruction();
	}
	
	public int getBinaryInstruction(){
		return fetchedBinaryInstruction;
	}
	
	public void setStall(){
		fetchedBinaryInstruction = 13 << 24;
	}
	
	public String toString(){
		return String.format("RegIFDI: [fetchedBinaryInstruction] = %d", fetchedBinaryInstruction);
	}
}
