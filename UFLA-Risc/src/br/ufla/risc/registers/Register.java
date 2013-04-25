package br.ufla.risc.registers;

public class Register {
	
	private int value;

	protected int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}
}
