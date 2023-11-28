package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import lyc.compiler.files.SymbolTableGenerator;

public class AsmCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
		fileWriter.write("INCLUDE macros2.asm"+" \n");
		fileWriter.write("INCLUDE number.asm"+" \n");
        fileWriter.write(".MODEL LARGE"+" \n");
        fileWriter.write(".386"+" \n");
        fileWriter.write(".STACK 200h"+" \n");
		fileWriter.write("MAXTEXTSIZE equ 50"+" \n");


        fileWriter.write(".DATA"+" \n");
        escribirListaSimbolos(fileWriter);

        fileWriter.write(".CODE"+" \n");
		fileWriter.write("START:"+" \n");
        fileWriter.write("MOV ax, @DATA"+" \n");
        fileWriter.write("MOV ds,ax"+" \n");
        fileWriter.write("MOV es,ax "+" \n");
        escribirPrograma(fileWriter);

        fileWriter.write("FFREE "+" \n");
        fileWriter.write("MOV ax,4C00h "+" \n");
        fileWriter.write("int 21h "+" \n");
        fileWriter.write("END START"+" \n");

    }
private void escribirListaSimbolos(FileWriter fileWriter) throws IOException{
        ArrayList<Simbolo> TS = new ArrayList<Simbolo>();
              TS =  new SymbolTableGenerator().getTablaSimbolos();
        for(Simbolo s: TS){
			if(s.getTipo().equals("CTE_STRING"))
				 fileWriter.write(s.getNombre()+" db "+s.getValor()+ ",'$', " +(50-Integer.parseInt(s.getLongitud())) +" dup (?)\n");
            else if (s.getTipo().equals("string"))
				fileWriter.write(s.getNombre()+" db 3 dup (' '), '$'"+"\n");
			else
				fileWriter.write(s.getNombre()+" dd "+s.getValor()+ "\n");
			
        }
		fileWriter.write("input db MAXTEXTSIZE dup (' '), '$'"+"\n");
}
private void escribirPrograma(FileWriter fileWriter) throws IOException{
	  ArrayList<Simbolo> TS = new ArrayList<Simbolo>();
      TS =  new SymbolTableGenerator().getTablaSimbolos();
      ArrayList<Terceto> tercetos = new IntermediateCodeGenerator().getListaTercetos();
      ArrayList<Terceto> aux =tercetos;
        for(Terceto t: tercetos){
            fileWriter.write( "etq"+t.getNroTerceto() + ":" + "\n");
            if(t.getOperador().equals("=")){
                if(t.getOperando2().equals("READ")){
					
					for(Simbolo s: TS)
					{	if(s.getNombre().equals(t.getOperando1()))
						{
						if(s.getTipo().equals("string"))
						 {
							fileWriter.write("GetString " + t.getOperando1()+"\n");
						 }
						 else if(s.getTipo().equals("float"))
						 {
							fileWriter.write("GetFloat " + t.getOperando1()+"\n");
						 }else
						 {
							fileWriter.write("GetInteger " + t.getOperando1()+"\n");
						 }
						 
						}
					}
							
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
                    fileWriter.write("FCOM" + "\n");
                    fileWriter.write("FSTSW AX" + "\n");
                    fileWriter.write("SAHF" + "\n");

                }else{
					fileWriter.write("FXCH" + "\n");
                    fileWriter.write("FCOM" + "\n");
                    fileWriter.write("FSTSW AX" + "\n");
                    fileWriter.write("SAHF" + "\n");
                }
         
                t.setRecorrido(true);
            } else if(esSalto(t.getOperador())){
                fileWriter.write(t.getOperador() + " " + "etq"+t.getOperando1() + "\n");
                t.setRecorrido(true);
            } else if(t.getOperador().equals("WRITE")){
				//fileWriter.write("displayString " +  t.getOperando1()+ "\n");
					for(Simbolo s: TS)
					{	if(s.getNombre().equals(t.getOperando1()))
						{
							if(s.getTipo().equals("string")|| s.getTipo().equals("CTE_STRING"))
							{
								fileWriter.write("MOV DX,OFFSET " +  t.getOperando1()+ "\n");
								fileWriter.write("MOV AH,9"+ "\n");
								fileWriter.write("INT 21h"+ "\n");
							}else if(s.getTipo().equals("int") || s.getTipo().equals("CTE_INT"))
							{
								fileWriter.write("DisplayInteger " + t.getOperando1()+"\n");
								
							}
							else
							{
								fileWriter.write("DisplayFloat " + t.getOperando1() + " , " +6 +"\n");
							}
							fileWriter.write("newLine 1"+ "\n");

						}
					}
				
            ;
                t.setRecorrido(true);
            }else if (t.getOperador().equals("TIMER")) {
					fileWriter.write("MOV DX,OFFSET " +  (tercetos.get(Integer.parseInt(t.getOperando2())-1)).getOperando1()+ "\n");
					fileWriter.write("MOV AH,9"+ "\n");
					fileWriter.write("INT 21h"+ "\n");
					fileWriter.write("newLine 1"+ "\n");
			}
			else{
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
