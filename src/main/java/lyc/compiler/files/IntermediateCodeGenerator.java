package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class IntermediateCodeGenerator implements FileGenerator {

    private static HashMap<Integer,Terceto> mapaTercetos= new HashMap<>();

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        fileWriter.write("");
    }

    public static HashMap<Integer, Terceto> getMapaTercetos() {
        return mapaTercetos;
    }

    public int getTercetosActuales(){
        return mapaTercetos.size();
    }
}
