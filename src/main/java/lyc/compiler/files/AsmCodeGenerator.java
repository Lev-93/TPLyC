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

        //escribirPrograma();

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


}
