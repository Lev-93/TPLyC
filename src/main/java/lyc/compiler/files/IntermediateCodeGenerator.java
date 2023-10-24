package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class IntermediateCodeGenerator implements FileGenerator {

    private static final ArrayList<Terceto> listaTercetos= new ArrayList<Terceto>();
    private static final Stack<Integer> pila = new Stack<Integer>();
    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        for (Terceto t:listaTercetos) {
            fileWriter.write(t.toString() + "\n");
        }
    }
    public ArrayList<Terceto> getListaTercetos() {
        return listaTercetos;
    }
}
