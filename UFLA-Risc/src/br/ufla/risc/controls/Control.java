package br.ufla.risc.controls;

import java.util.List;

import br.ufla.risc.memory.Memory;
import br.ufla.risc.registers.controls.RegIDEX;
import br.ufla.risc.registers.controls.RegEXWR;
import br.ufla.risc.registers.controls.RegIFID;
import br.ufla.risc.stages.Decoder;
import br.ufla.risc.stages.ExecutionMemory;
import br.ufla.risc.stages.Fetch;
import br.ufla.risc.stages.WriteRegister;

public abstract class Control {
	
	protected Fetch fetch;
	protected Decoder decoder;
	protected ExecutionMemory executionMemory;
	protected WriteRegister writeRegister;
	
	protected RegIFID regIFID;
	protected RegIDEX regIDEX;
	protected RegEXWR regEXWR;
	
	protected Memory memory;
	
	public static Integer pc = 0;
	public static Boolean end = false;
		
	public Control(List<Integer> instructions){
		pc = 0;
		end = false;
		
		fetch = (Fetch)Fetch.getInstance();
		decoder = (Decoder)Decoder.getInstance();
		executionMemory = (ExecutionMemory)ExecutionMemory.getInstance();
		writeRegister = (WriteRegister)WriteRegister.getInstance();
		
		regIFID = (RegIFID)RegIFID.getInstance();		
		regIDEX = (RegIDEX)RegIDEX.getInstance();	
		regEXWR = (RegEXWR)RegEXWR.getInstance();
		
		memory = Memory.getInstance();
		memory.loader(instructions);
	}
	
	public abstract void execute();
	
	public void setPC(Integer pc){
		Control.pc = pc;
	}
	
	public Integer getPC(){
		return pc;
	}
}
