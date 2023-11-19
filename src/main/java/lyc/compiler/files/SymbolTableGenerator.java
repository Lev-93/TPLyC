package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SymbolTableGenerator implements FileGenerator{

    private static ArrayList<Simbolo> tablaSimbolos= new ArrayList<Simbolo>();

    @Override
    public void generate(FileWriter fileWriter) throws IOException {

        ArrayList<String> aux = new ArrayList<String>();
        for (Simbolo s:tablaSimbolos) {
            String cadena = s.toString();
            if(!aux.contains(cadena)) {
                System.out.println(cadena); //solo para prueba
                aux.add(cadena);
            }
        }

        fileWriter.write("NOMBRE|TIPODATO|VALOR|LONGITUD\n");
        for (String s:aux) {
            fileWriter.write(s + "\n");
        }
    }



    public ArrayList<Simbolo> getTablaSimbolos() {
        return tablaSimbolos;
    }

}
