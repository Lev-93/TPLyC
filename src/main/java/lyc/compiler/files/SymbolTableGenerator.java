package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SymbolTableGenerator implements FileGenerator{

    private static ArrayList<Simbolo> tablaSimbolos= new ArrayList<Simbolo>();

    @Override
    public void generate(FileWriter fileWriter) throws IOException {

        LinkedHashSet<Simbolo> set = new LinkedHashSet<Simbolo>(tablaSimbolos);
        tablaSimbolos.clear();
        tablaSimbolos.addAll(set);
        fileWriter.write("NOMBRE|TIPODATO|VALOR|LONGITUD\n");
        for (Simbolo s:tablaSimbolos) {
            System.out.println(s);
            fileWriter.write(s.toString() + "\n");
        }
    }

    public ArrayList<Simbolo> getTablaSimbolos() {
        return tablaSimbolos;
    }
}
