package br.ufla.risc.registers;

public class RegisterBank {
	
	private static final int BANK_REG = 32;
	
	private Register registers[] = null;
	
	private static RegisterBank instance = null;
	
	private RegisterBank(){
		registers = new Register[BANK_REG];
		
		for (int i = 0; i < BANK_REG; i++){
			registers[i] = new Register();
		}		
				
		registers[0].setValue(0);	
	}
	
	public static RegisterBank getInstance(){
		if (instance == null){		
			instance = new RegisterBank();
		}
		return instance;
	}
	
	public int read(int position){		
		return registers[position].getValue();
	}
	
	public void write(int position, int value){
		if (position > 0){
			registers[position].setValue(value); 
		}
	}
	
	public String printRegister(){
		String printRegister = "";
		for (int i = 0; i < registers.length; i++){			
			printRegister += String.format("r%d = %d\n", i, registers[i].getValue());
		}
		return printRegister;
	}
	
	public void clearRegisterBank(){
		registers = new Register[BANK_REG];		
		for (int i = 0; i < BANK_REG; i++){
			registers[i] = new Register();
		}				
		registers[0].setValue(0);	
	}
}
