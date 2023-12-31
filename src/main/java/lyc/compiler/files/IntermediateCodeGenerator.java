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
                auxiliar.setOperador(contenido);
                listaTercetos.set(posicionLista,auxiliar);
                break;

            case 2:
                auxiliar=listaTercetos.get(posicionLista);
                auxiliar.setOperando1(contenido);
                listaTercetos.set(posicionLista,auxiliar);
                break;

            case 3:
                auxiliar=listaTercetos.get(posicionLista);
                auxiliar.setOperando2(contenido);
                listaTercetos.set(posicionLista,auxiliar);
                break;

        }
    }
    public static Stack<String> getPila() {
        return pila;
    }
    public String invertirSalto(Terceto Aux){
        String salto=Aux.getOperador();
        if(salto.equals("JBE")){
            salto="JA";
        } else if (salto.equals("JNE")) {
            salto="JE";
        }else if (salto.equals("JAE")){
            salto="JB";
        } else if (salto.equals("JB")) {
            salto="JAE";
        } else if (salto.equals("JA")) {
            salto="JBE";
        } else if (salto.equals("JE")) {
            salto="JNE";
        }
        return salto;
    }

}
