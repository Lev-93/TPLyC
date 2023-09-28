package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SymbolTableGenerator implements FileGenerator{

    public ArrayList<Simbolo> TablaSimbolos= new ArrayList<Simbolo>();

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        fileWriter.write("NOMBRE|TIPODATO|VALOR|LONGITUD\n");
        for (Simbolo s:TablaSimbolos) {
            s.toString();
        }
    }


}
