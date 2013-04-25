package br.ufla.risc.memory;

import java.util.List;

public class Memory {
	
	private static final int MEMORY_SIZE = 65536;
	
	private int dataMemorySize;
	
	private int words[];
	
	private static Memory instance = null;
	
	private Memory() {
		words = new int[MEMORY_SIZE];
	}
	
	public static Memory getInstance(){
		if (instance == null){		
			instance = new Memory();
		}
		return instance;
	}
	
	public Integer readInstruction(int position){
		return words[MEMORY_SIZE - position - 1];
	}
	
	public Integer readData(int position){		
		if (position < dataMemorySize){
			return words[position];
		}else{
			return null;
		}
	}
	
	public void write(int position, int value){
		if (position < dataMemorySize){			
			words[position] = value;
		}
	}
	
	public void loader(List<Integer> instructions){
		if (instructions.size() <= MEMORY_SIZE){
			dataMemorySize = MEMORY_SIZE - instructions.size();
			int i = 0;
			for (Integer instruction : instructions){
				words[MEMORY_SIZE - i - 1] = instruction;
				i++;
			}
		}
	}
	
	public int sizeInstruction(){
		return MEMORY_SIZE - dataMemorySize;
	}
	
	public String printMemory(){
		String printMemory = "";
		for (int i = 0; i < dataMemorySize; i++){
			if (words[i] != 0){
				printMemory += String.format("M%d = %d\n", i, words[i]);
			}
		}
		return printMemory;
	}
	
	public void clearMemory(){
		words = new int[MEMORY_SIZE];
	}
}
