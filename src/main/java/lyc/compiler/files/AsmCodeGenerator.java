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
        ArrayList<Terceto> Tercetos = new IntermediateCodeGenerator().getListaTercetos();
        for(Terceto t: Tercetos){
            fileWriter.write( t.getNroTerceto() + ":" + "\n");
            if(t.getOperador().equals("=")){
                fileWriter.write("FSTP " + t.getOperando1() + "\n");
            } else if(t.getOperador().equals("+")){
                fileWriter.write("FADD " + "\n");
            }
            else if(t.getOperador().equals("-")){
                fileWriter.write("FSUB " +  "\n");
            }else if(t.getOperador().equals("/")){
                fileWriter.write("FDIV " + "\n");
            }else if(t.getOperador().equals("*")){
                fileWriter.write("FMUL " + "\n");
            } else if (t.getOperador().equals("CMP")) {
                fileWriter.write("FCMP" + "\n");
                fileWriter.write("FXCH" + "\n");
                fileWriter.write("FSTSW AX" + "\n");
                fileWriter.write("SAHF" + "\n");
            } else if(esSalto(t.getOperador())){
                fileWriter.write(t.getOperador() + " " + t.getOperando1() + "\n");
            } else if(t.getOperador().equals("WRITE")){
                fileWriter.write("MOV DX,OFFSET " +  t.getOperando1()+ "\n");
                fileWriter.write("MOV AH,9"+ "\n");
                fileWriter.write("INT 21h"+ "\n");
            }
        }
}

private boolean esSalto(String salto){
        if(salto.equals("JB")||salto.equals("JBE")||salto.equals("JA")||salto.equals("JAE")||salto.equals("JE")||salto.equals("JNE")||salto.equals("JMP"))
            return true;
        return false;
}

}
