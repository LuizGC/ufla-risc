package br.ufla.risc.stages;

import br.ufla.risc.registers.RegisterBank;
import br.ufla.risc.registers.controls.RegEXWR;

public class WriteRegister implements Stage {
	
	private Integer destinyRegister;
	
	private Integer result;
	
	private RegEXWR regEXWR;
	
	private RegisterBank registerBank;

	private static Stage instance = null;
	
	private WriteRegister(){
		regEXWR = (RegEXWR)RegEXWR.getInstance();
		registerBank = RegisterBank.getInstance();
	}
	
	public static Stage getInstance(){
		if (instance == null){		
			instance = new WriteRegister();
		}
		return instance;
	}

	@Override
	public void upperBorder() {
		
	}

	@Override
	public void lowerBorder() {
		result = regEXWR.getResult();		
		destinyRegister = regEXWR.getDestinyRegister();				
		if (result != null && destinyRegister != null){
			registerBank.write(destinyRegister, result);
		}
	}
	
	public Integer result(){
		return result;
	}
	
	public String toString(){
		return String.format("WriteRegister: [result] = %d - [destinyRegister] = %d", result, destinyRegister);
	}
	
	@Override
	public void clean() {
		
	}
	
}
