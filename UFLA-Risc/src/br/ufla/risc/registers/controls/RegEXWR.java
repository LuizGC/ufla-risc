package br.ufla.risc.registers.controls;

import br.ufla.risc.stages.ExecutionMemory;

public class RegEXWR implements RegisterControl {
	
	private ExecutionMemory executionMemory;	
	
	private Integer destinyRegister;
	
	private Integer result;
	
	private static RegisterControl instance = null;
	
	private RegEXWR(){
		executionMemory = (ExecutionMemory)ExecutionMemory.getInstance();		
	}
	
	public static RegisterControl getInstance(){
		if (instance == null){		
			instance = new RegEXWR();
		}
		return instance;
	}

	@Override
	public void read() {
		result = executionMemory.getResult();
		destinyRegister = executionMemory.getDestinyRegister();
	}
	
	public Integer getResult(){		
		return result;
	}

	public Integer getDestinyRegister() {
		return destinyRegister;
	}
	
	public String toString(){
		return String.format("RegEXWR: [destinyRegister] = %d - [result] = %d", destinyRegister, result);
	}
}
