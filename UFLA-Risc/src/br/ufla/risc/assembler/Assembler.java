package br.ufla.risc.assembler;

public class Assembler {

    private static Assembler instance = null;
    
    private Assembler(){
        
    }
    
    public static Assembler getInstance(){
        if (instance == null){
            instance = new Assembler();
        }
        return instance;
    }
    
    public int decoderInstruction(String lineInstruction){
        String fields[] = lineInstruction.split(" ");
        int numberInstruction;
        int formatBinaryInstruction = 0;
        String fieldsRegister[];        
        if (fields[0].equals("add")){
            numberInstruction = 1;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);
        }else if (fields[0].equals("sub")){
            numberInstruction = 2;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        }else if (fields[0].equals("zeros")){
            numberInstruction = 3;           
            formatBinaryInstruction = processInstructionTypeZ(numberInstruction, fields[1]);            
        }else if (fields[0].equals("xor")){
            numberInstruction = 4;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("or")){
            numberInstruction = 5;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("not")){
            numberInstruction = 6;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeI(numberInstruction, fieldsRegister);                
        } else if (fields[0].equals("and")){
            numberInstruction = 7;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("asl")){
            numberInstruction = 8;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("asr")){
            numberInstruction = 9;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        }  else if (fields[0].equals("lsl")){
            numberInstruction = 10;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("lsr")){
            numberInstruction = 11;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("copy")){
            numberInstruction = 12;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeI(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("nop")){
            numberInstruction = 13;            
            formatBinaryInstruction = processInstructionTypeY(numberInstruction);
        } else if (fields[0].equals("lch")){
            numberInstruction = 14;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeX(numberInstruction, fieldsRegister);            
        }else if (fields[0].equals("lcl")){
            numberInstruction = 15;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeX(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("load")){
            numberInstruction = 16;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeI(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("store")){
            numberInstruction = 17;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeI(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("jal")){
            numberInstruction = 18;            
            formatBinaryInstruction = processInstructionTypeJ(numberInstruction, fields[1]);            
        } else if (fields[0].equals("jr")){
            numberInstruction = 19;            
            formatBinaryInstruction = processInstructionTypeZ(numberInstruction, fields[1]);            
        } else if (fields[0].equals("beq")){
            numberInstruction = 20;         
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeW(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("bne")){
            numberInstruction = 21;         
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeW(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("j")){
            numberInstruction = 22;            
            formatBinaryInstruction = processInstructionTypeJ(numberInstruction, fields[1]);            
        } else if (fields[0].equals("addi")){
            numberInstruction = 23;  
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeA(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("subi")){
            numberInstruction = 24;  
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeA(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("mult")){
            numberInstruction = 25;  
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);            
        } else if (fields[0].equals("multi")){
            numberInstruction = 26;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeA(numberInstruction, fieldsRegister);
        } else if (fields[0].equals("neg")){
            numberInstruction = 27;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeI(numberInstruction, fieldsRegister);
        } else if (fields[0].equals("gmt")){
            numberInstruction = 28;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);
        } else if (fields[0].equals("glt")){
            numberInstruction = 29;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);
        } else if (fields[0].equals("slt")){
            numberInstruction = 30;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeR(numberInstruction, fieldsRegister);
        } else if (fields[0].equals("slti")){
            numberInstruction = 31;
            fieldsRegister = fields[1].split(",");
            formatBinaryInstruction = processInstructionTypeA(numberInstruction, fieldsRegister);
        } else if (fields[0].equals("halt")){            
            formatBinaryInstruction = -1;
        }
        
        return formatBinaryInstruction;
    }
    
    /**
     * Instrucoes do tipo R [add, sub, xor, or, and, asl, asr, lsl, lsr]: 
     * Exemplo: add r1, r2, r3
     * Notação: add ra, rb, rc
     * @param numberInstruction - numero da instrução correspondente (add).
     * @param fieldsRegister - campos dos registradores (r1, r2, r3).
     * @return formanto equivalente da instrução em binario.
     */
    private int processInstructionTypeR(int numberInstruction, 
            String fieldsRegister[]){        
        
        int formatBinaryInstruction;
        int size = fieldsRegister.length;
        int numberRegister[] = new int[size];
        for (int i = 0; i < size; i++){
            numberRegister[i] = Integer.parseInt(fieldsRegister[i].substring(1));
        }
        formatBinaryInstruction = numberInstruction << 24;
        formatBinaryInstruction += numberRegister[1] << 16;
        formatBinaryInstruction += numberRegister[2] << 8;
        formatBinaryInstruction += numberRegister[0];
        return formatBinaryInstruction;
    }
    
    /**
     * Instrucoes do tipo I [not, copy, load, store]: 
     * Exemplo: not r1, r2 
     * Notação: not rc, ra
     * @param numberInstruction - numero da instrução correspondente (not).
     * @param fieldsRegister - campos dos registradores (r1, r2).
     * @return formanto equivalente da instrução em binario.
     */
    private int processInstructionTypeI(int numberInstruction, 
            String fieldsRegister[]){        
        
        int formatBinaryInstruction;
        int size = fieldsRegister.length;
        int numberRegister[] = new int[size];
        for (int i = 0; i < size; i++){
            numberRegister[i] = Integer.parseInt(fieldsRegister[i].substring(1));
        }
        formatBinaryInstruction = numberInstruction << 24;
        formatBinaryInstruction += numberRegister[1] << 16;        
        formatBinaryInstruction += numberRegister[0];
        return formatBinaryInstruction;
    }
    
    /**
     * Instrucoes do tipo X [lch, lcl]: 
     * Exemplo: lch r1, 2000
     * Notação: lch rc, const16Bits
     * @param numberInstruction - numero da instrução correspondente (lch).
     * @param fields - campos [registrador, endereço] (r1, 2000).
     * @return formanto equivalente da instrução em binario.
     */
    private int processInstructionTypeX(int numberInstruction, String fields[]){        
        int formatBinaryInstruction;       
        int numberRegister = Integer.parseInt(fields[0].substring(1));
        int const16Bits = Integer.parseInt(fields[1]);
        formatBinaryInstruction = numberInstruction << 24;
        formatBinaryInstruction += const16Bits << 8;        
        formatBinaryInstruction += numberRegister;
        return formatBinaryInstruction;
    }
    
    /**
     * Instrucoes do tipo J [jal, j]: 
     * Exemplo: jal 2000
     * Notação: jal endereço
     * @param numberInstruction - numero da instrução correspondente (jal).
     * @param address - endereço a ser saltado (endereço).
     * @return formanto equivalente da instrução em binario.
     */
    private int processInstructionTypeJ(int numberInstruction, String address){                
        int formatBinaryInstruction;       
        int numberAddress = Integer.parseInt(address);        
        formatBinaryInstruction = numberInstruction << 24;        
        formatBinaryInstruction += numberAddress;
        return formatBinaryInstruction;
    }
        
    /**
     * Instrucoes do tipo Z [not, jr]: 
     * Exemplo: jr r1
     * Notação: jr rc
     * @param numberInstruction - numero da instrução correspondente (jr).
     * @param fieldRegister - campos do registrador (r1).
     * @return formanto equivalente da instrução em binario.
     */
    private int processInstructionTypeZ(int numberInstruction, String fieldRegister){        
        int formatBinaryInstruction;        
        int numberRegister = Integer.parseInt(fieldRegister.substring(1));        
        formatBinaryInstruction = numberInstruction << 24;
        formatBinaryInstruction += numberRegister;
        return formatBinaryInstruction;
    }
    
    /**
     * Instrucoes do tipo W [beq, bne]: 
     * Exemplo: beq r1, r2, endereço
     * Notação: beq ra, rb, endereço
     * @param numberInstruction - numero da instrução correspondente (beq).
     * @param fields - campos necessarios (r1, r2, endereço).
     * @return formanto equivalente da instrução em binario.
     */
    private int processInstructionTypeW(int numberInstruction, String fields[]){
        int formatBinaryInstruction;
        int size = fields.length;
        int numberRegister[] = new int[size - 1];
        for (int i = 0; i < size - 1; i++){
            numberRegister[i] = Integer.parseInt(fields[i].substring(1));
        }
        int address = Integer.parseInt(fields[2]);
        formatBinaryInstruction = numberInstruction << 24;
        formatBinaryInstruction += numberRegister[0] << 16;
        formatBinaryInstruction += numberRegister[1] << 8;
        formatBinaryInstruction += address;
        return formatBinaryInstruction;
    }
    
    /**
     * Instrucoes do tipo W [addi, subi, multi]: 
     * Exemplo: addi r1, r2, const8bits 
     * Notação: addi rc, ra, const8bits
     * @param numberInstruction - numero da instrução correspondente (addi).
     * @param fields - campos necessarios (r1, r2, const8bits).
     * @return formanto equivalente da instrução em binario.
     */
    private int processInstructionTypeA(int numberInstruction, String fields[]){
        int formatBinaryInstruction;
        int size = fields.length;
        int numberRegister[] = new int[size - 1];
        for (int i = 0; i < size - 1; i++){
            numberRegister[i] = Integer.parseInt(fields[i].substring(1));
        }
        int const8bits = Integer.parseInt(fields[2]);
        formatBinaryInstruction = numberInstruction << 24;
        formatBinaryInstruction += numberRegister[1] << 16;
        formatBinaryInstruction += const8bits << 8;
        formatBinaryInstruction += numberRegister[0];
        return formatBinaryInstruction;
    }
    
    /**
     * Instrucoes do tipo Y [nop]: 
     * Exemplo: nop
     * Notação: nop
     * @param numberInstruction - numero da instrução correspondente (nop).
     * @param fields - campos necessarios.
     * @return formanto equivalente da instrução em binario.
     */
    private int processInstructionTypeY(int numberInstruction){
        int formatBinaryInstruction;                        
        formatBinaryInstruction = numberInstruction << 24;
        return formatBinaryInstruction;
    }
        
}
