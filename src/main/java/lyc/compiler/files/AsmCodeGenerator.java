package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import lyc.compiler.files.SymbolTableGenerator;

public class AsmCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        fileWriter.write(".MODEL LARGE"+" \n");
        fileWriter.write(".386"+" \n");
        fileWriter.write(".STACK 200h"+" \n");

        fileWriter.write(".DATA"+" \n");
        escribirListaSimbolos(fileWriter);

        fileWriter.write(".CODE"+" \n");
        fileWriter.write("MOV ax, @DATA"+" \n");
        fileWriter.write("MOV ds,ax"+" \n");
        fileWriter.write("MOV es,ax "+" \n");
        escribirPrograma(fileWriter);

        fileWriter.write("FFREE "+" \n");
        fileWriter.write("MOV ax,4C00h "+" \n");
        fileWriter.write("int 21h "+" \n");
        fileWriter.write("END "+" \n");

    }
private void escribirListaSimbolos(FileWriter fileWriter) throws IOException{
        ArrayList<Simbolo> TS = new ArrayList<Simbolo>();
              TS =  new SymbolTableGenerator().getTablaSimbolos();
        for(Simbolo s: TS){
            fileWriter.write(s.getNombre()+" dd "+s.getValor()+ "\n");
        }
}
private void escribirPrograma(FileWriter fileWriter) throws IOException{
        ArrayList<Terceto> tercetos = new IntermediateCodeGenerator().getListaTercetos();
    ArrayList<Terceto> aux =tercetos;
        for(Terceto t: tercetos){
            fileWriter.write( t.getNroTerceto() + ":" + "\n");
            if(t.getOperador().equals("=")){
                if(t.getOperando2().equals("READ")){
                    fileWriter.write("MOV ah,3fh"+"\n");
					fileWriter.write("MOV bx,00h"+"\n");
				    fileWriter.write("MOV cx,42"+"\n");
					fileWriter.write("MOV dx,offset [input]"+"\n");
				    fileWriter.write("INT 21h"+"\n");
					for(Simbolo s: TS)
					{	if(s.getNombre().equals(t.getOperando1()))
						{
						 if(s.getTipo().equals("int"))
						 {
							fileWriter.write("MOV si,1"+"\n");
						    fileWriter.write("FLD input[0]" + "\n");
							fileWriter.write("FCOM 45"+"\n");
							fileWriter.write("FSTSW AX" + "\n");
							fileWriter.write("SAHF" + "\n");
							fileWriter.write("JNE convertir_a_entero:" + "\n");
							
							fileWriter.write("FLD input[1]" + "\n");
							fileWriter.write("FSUB 30h" + "\n");
							fileWriter.write("INC si" + "\n");
							
							fileWriter.write("convertir_a_entero:" + "\n");
							fileWriter.write("CMP input[si],13"+ "\n");
							fileWriter.write("JE final_convertir:" + "\n");
						    fileWriter.write("FLD 10"+ "\n");
							fileWriter.write("FMUL"+ "\n");
							fileWriter.write("FLD input[si]"+ "\n");
							fileWriter.write("FSUB 30h"+ "\n");
							fileWriter.write("FADD"+ "\n");							
							fileWriter.write("INC si" + "\n");
							fileWriter.write("JMP convertir_a_entero" + "\n");
							fileWriter.write("final_convertir:" + "\n");
							fileWriter.write("FLD input[0]" + "\n");
							fileWriter.write("FCOM 45"+"\n");
							fileWriter.write("FSTSW AX" + "\n");
							fileWriter.write("SAHF" + "\n");
							fileWriter.write("JNE guardar_entero_leido" + "\n");
							fileWriter.write("FMUL -1"+"\n");
							fileWriter.write("guardar_entero_leido:" + "\n");
						 	fileWriter.write("FSTP " + t.getOperando1()+"\n");
						 
						    }else if(s.getTipo().equals("string"))
							{
							  fileWriter.write("MOV si,0"+"\n");
							  fileWriter.write("copiar_cadena:");
						      fileWriter.write("CMP input[si],13"+ "\n");
							  fileWriter.write("JE final_copiado:" + "\n");
							  fileWriter.write("FLD input[si]" + "\n");
							  fileWriter.write("FSTP " +t.getOperando2()+"[si]\n");
							  fileWriter.write("INC si\n");
							  fileWriter.write("JMP copiar_cadena" + "\n");
							  fileWriter.write("final_copiado:"+ "\n");
							}
                    //A IMPLEMENTAR
                    
                             
                   /* fileWriter.write("datos segment\n");
                    fileWriter.write("letrero1 db 10,13,'$'\n");
                    fileWriter.write("datos ends\n");

                    //fileWriter.write("assume ds:datos\n");
                    fileWriter.write("mov ax,datos\n");
                    fileWriter.write("mov ds,ax\n");

                    fileWriter.write("mov ah,09h\n");
                    fileWriter.write("lea dx, letrero1\n");
                    fileWriter.write("int 21h\n");
                    //si en efecto se desea ingresar un numero entero deberíamos restar 30 debido a que dicho digito en ASCII representa otro valor...
                    //if(t.getTipoDatoTerceto().equals("int"))
                        //fileWriter.write("SUB dx,30h\n");
                    String Aux = "mov "+t.getOperando1()+",dx\n";
                    System.out.println(Aux);
                    fileWriter.write(Aux);*/
                }else{
                    fileWriter.write("FSTP " + t.getOperando1() + "\n");
                }

                t.setRecorrido(true);
            } else if(t.getOperador().equals("+")){
                fileWriter.write("FADD " + "\n");
                t.setRecorrido(true);
            }
            else if(t.getOperador().equals("-")){
                fileWriter.write("FSUB " +  "\n");
                t.setRecorrido(true);
            }else if(t.getOperador().equals("/")){
                fileWriter.write("FDIV " + "\n");
                t.setRecorrido(true);
            }else if(t.getOperador().equals("*")){
                fileWriter.write("FMUL " + "\n");
                t.setRecorrido(true);
            } else if (t.getOperador().equals("CMP")) {
                if(t.getOperando1().equals("@aux")){
                    fileWriter.write("FLD "+ t.getOperando1() +"\n");
                    fileWriter.write("FCMP" + "\n");
                    fileWriter.write("FSTSW AX" + "\n");
                    fileWriter.write("SAHF" + "\n");

                }else{
                    fileWriter.write("FCMP" + "\n");
                    fileWriter.write("FXCH" + "\n");
                    fileWriter.write("FSTSW AX" + "\n");
                    fileWriter.write("SAHF" + "\n");

                }
                fileWriter.write("FCMP" + "\n");
                fileWriter.write("FXCH" + "\n");
                fileWriter.write("FSTSW AX" + "\n");
                fileWriter.write("SAHF" + "\n");
                t.setRecorrido(true);
            } else if(esSalto(t.getOperador())){
                fileWriter.write(t.getOperador() + " " + t.getOperando1() + "\n");
                t.setRecorrido(true);
            } else if(t.getOperador().equals("WRITE")){
                fileWriter.write("MOV DX,OFFSET " +  t.getOperando1()+ "\n");
                fileWriter.write("MOV AH,9"+ "\n");
                fileWriter.write("INT 21h"+ "\n");
                t.setRecorrido(true);
            } else {
                fileWriter.write("FLD "+ t.getOperador() +"\n");
            }
        }
}


    private boolean esSalto(String salto){
        if(salto.equals("JB")||salto.equals("JBE")||salto.equals("JA")||salto.equals("JAE")||salto.equals("JE")||salto.equals("JNE")||salto.equals("JMP"))
            return true;
        return false;
}

}
