package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SymbolTableGenerator implements FileGenerator{

    private static ArrayList<Simbolo> tablaSimbolos= new ArrayList<Simbolo>();

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        ArrayList<Simbolo> aux = getListaSimbolos();

        fileWriter.write("NOMBRE|TIPODATO|VALOR|LONGITUD|USO\n");
        for (Simbolo s:aux) {
            fileWriter.write(s.toString() + "\n");
        }
    }

    private static ArrayList<Simbolo> getListaSimbolos() {
        ArrayList<Simbolo> aux = new ArrayList<Simbolo>();
        ArrayList<String>  aux2 = new ArrayList<String>();
        for (Simbolo s:tablaSimbolos) {
            String cadena = s.getNombre();
            if(!aux2.contains(cadena) && validarID(aux2,cadena) ) {
                System.out.println(cadena); //solo para prueba
                aux.add(s);
                aux2.add(cadena);
            }
        }
        tablaSimbolos=aux;
        return aux;
    }

        private static boolean validarID(ArrayList<String>  aux,String cadena){
            if(cadena.charAt(0)=='_') //Es cte no verifico
                return true;
            if(aux.contains(cadena))
                throw new IllegalArgumentException("ID " + cadena +" duplicado ");
            return true;
        }


    public ArrayList<Simbolo> getTablaSimbolos() {
        return tablaSimbolos;
    }

}
