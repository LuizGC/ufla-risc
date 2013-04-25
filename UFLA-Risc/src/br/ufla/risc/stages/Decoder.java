package br.ufla.risc.stages;

import static org.atatec.trugger.scan.ClassScan.findClasses;

import java.lang.annotation.Annotation;
import java.util.Set;
import br.ufla.risc.annotation.Instruction;
import br.ufla.risc.instructions.InstructionBehavior;
import br.ufla.risc.registers.RegisterBank;
import br.ufla.risc.registers.controls.RegIFID;

public class Decoder implements Stage{
	
	private InstructionBehavior instruction;
	
	private int binaryInstruction;
	
	private RegisterBank registerBank;
	
	private RegIFID regIFID;
	
	private static Stage instance = null;
	
	private static final int MASK = 0x000000FF;
	
	private Decoder(){
		regIFID = (RegIFID)RegIFID.getInstance();
		registerBank = RegisterBank.getInstance();
	}
	
	public static Stage getInstance(){
		if (instance == null){		
			instance = new Decoder();
		}
		return instance;
	}

	@Override
	public void upperBorder() {				
		binaryInstruction = regIFID.getBinaryInstruction();
		instruction = decoderInstruction();		
		if(instruction != null)
			instruction.setFields(binaryInstruction, registerBank);	
	}
	
	@Override
	public void lowerBorder() {
		
	}

	public InstructionBehavior getInstruction(){
		return instruction;
	}	
	
	@SuppressWarnings("rawtypes")
	private InstructionBehavior decoderInstruction() {
		//Pega todos os pacotes no Classloader
		Package[] ps = Package.getPackages();

		//Percorre todos os pacotes
		for (Package p : ps) {

			//Busca se existe classes anotadas como InstructionAnn dentro dos pacotes
			Set<Class> classes = findClasses().recursively()
					.annotatedWith(Instruction.class).in(p.getName());
			
			for (Class aClass : classes) {
				
				//busca todas as anotacoes das classes
				Annotation[] annotations = aClass.getAnnotations();

				
				for (Annotation annotation : annotations) {
					
					//verifica anotacao por anotacao 
					//se encontrar um InstructionAnn 
					if (annotation instanceof Instruction) {
						Instruction myAnnotation = (Instruction) annotation;
						//verifica se o opcode da anotacao e igual ao do opcode
						if (myAnnotation.opcode() == getOpcode()) {
							try {
								//cria uma instancia da classe por reflection
								//System.out.println(aClass.getCanonicalName());
								return (InstructionBehavior) Class.forName(
										aClass.getCanonicalName()).newInstance();
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

		return null;
	}
	
	private int getOpcode(){
		int opcode = binaryInstruction >> 24;
		opcode = opcode & MASK;
		return opcode;
	}	
	
	public String toString(){
		String str = "null";
		if (instruction != null)	
			str = instruction.toString();
		return String.format("Decoder: [binaryInstruction] = %d - [instruction] = %s", binaryInstruction, str);
	}
	
	@Override
	public void clean() {
		instruction = null;
		
	}
}
