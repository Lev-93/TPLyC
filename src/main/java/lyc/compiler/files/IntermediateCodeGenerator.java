package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class IntermediateCodeGenerator implements FileGenerator {

    private static ArrayList<Terceto> listaTercetos= new ArrayList<Terceto>();
    private static Stack<String> pila = new Stack<String>();
    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        for (Terceto t:listaTercetos) {
            fileWriter.write(t.toString() + "\n");
        }
    }
    public ArrayList<Terceto> getListaTercetos() {
        return listaTercetos;
    }

    public void modificarTerceto(int posicionLista,String contenido,int posicionTerceto){
        Terceto auxiliar;
        switch (posicionTerceto) {
            case 1:
                auxiliar=listaTercetos.get(posicionLista);
                auxiliar.setOperando(contenido);
                listaTercetos.set(posicionLista,auxiliar);
                break;

            case 2:
                auxiliar=listaTercetos.get(posicionLista);
                auxiliar.setOperador1(contenido);
                listaTercetos.set(posicionLista,auxiliar);
                break;

            case 3:
                auxiliar=listaTercetos.get(posicionLista);
                auxiliar.setOperador2(contenido);
                listaTercetos.set(posicionLista,auxiliar);
                break;

        }
    }
    public static Stack<String> getPila() {
        return pila;
    }
    public String invertirSalto(Terceto Aux){
        String salto=Aux.getOperando();
        if(salto.equals("BLE")){
            salto="BGT";
        } else if (salto.equals("BNE")) {
            salto="BEQ";
        }else if (salto.equals("BGE")){
            salto="BLT";
        } else if (salto.equals("BLT")) {
            salto="BGE";
        } else if (salto.equals("BGT")) {
            salto="BLE";
        } else if (salto.equals("BEQ")) {
            salto="BNE";
        }
        return salto;
    }
}
