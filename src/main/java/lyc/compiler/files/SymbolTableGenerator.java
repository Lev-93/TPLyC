package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SymbolTableGenerator implements FileGenerator{

    private final static ArrayList<Simbolo> tablaSimbolos= new ArrayList<Simbolo>();

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        fileWriter.write("NOMBRE|TIPODATO|VALOR|LONGITUD\n");
        for (Simbolo s:tablaSimbolos) {
            fileWriter.write(s.toString() + "\n");
        }
    }
    public ArrayList<Simbolo> getTablaSimbolos() {
        return tablaSimbolos;
    }
}
