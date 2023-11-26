package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;

public class Terceto {
    public String operador;
    public String operando1;
    public String operando2;
    public int nroTerceto;
    public static int cant = 1;

    public String tipoDatoTerceto;
    private static ArrayList<Simbolo> tablaSimbolos = new SymbolTableGenerator().getTablaSimbolos();
    public boolean isRecorrido() {
        return recorrido;
    }

    public void setRecorrido(boolean recorrido) {
        this.recorrido = recorrido;
    }

    boolean recorrido;

    public Terceto(String operador, String operando1, String operando2) throws TiposException {
        String tipo1 = "";
        String tipo2 = "";
        if(operando1.isEmpty() && operando2.isEmpty()){
            this.tipoDatoTerceto = buscarTipoDato(operador);
        }else{
            if(operando2.isEmpty()){
                this.tipoDatoTerceto = "SALTO";
            }
            else{
                if(esNumero(operando1))
                    tipo1 = traerTipo(operando1);
                else{
                    for (Simbolo S:tablaSimbolos) {
                        if(S.getNombre().equals(operando1)){
                            tipo1 = S.getTipo();
                            break;
                        }
                    }
                }
                System.out.println(operando1);
                System.out.println(operando2);
                if(!operando2.equals("READ")) {
                    if (esNumero(operando2))
                        tipo2 = traerTipo(operando2);
                    else {
                        for (Simbolo S : tablaSimbolos) {
                            if (S.getNombre().equals(operando2)) {
                                tipo2 = S.getTipo();
                                break;
                            }
                        }
                    }
                    tipo1 = tipo1.toLowerCase();
                    tipo2 = tipo2.toLowerCase();
                    System.out.println(tipo1);
                    System.out.println(tipo2);
                    if (tipo1.contains(tipo2) || tipo2.contains(tipo1) || tipo1.equals("salto") || tipo2.equals("salto")) {
                        if(tipo1.equals("salto") || tipo2.equals("salto")){
                            if(tipo1.equals("salto") && !tipo2.equals("salto")){
                                this.tipoDatoTerceto = tipo2;
                            }else{
                                if(tipo2.equals("salto") && !tipo1.equals("salto")){
                                    this.tipoDatoTerceto = tipo2;
                                }else
                                    throw new TiposException("No deben existir 2 tipos de dato SALTO");
                            }
                        }else{
                            int long1 = tipo1.length();
                            int long2 = tipo2.length();
                            if (long1 <= long2)
                                this.tipoDatoTerceto = tipo1;
                            else
                                this.tipoDatoTerceto = tipo2;
                        }

                    } else
                        throw new TiposException("los tipos de operadores no son compatibles.");
                }else{
                    this.tipoDatoTerceto = tipo1;
                }
            }
        }
        recorrido=false;
        this.operador = operador;
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.nroTerceto = cant;
        cant++;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getOperando1() {
        return operando1;
    }

    public void setOperando1(String operando1) {
        this.operando1 = operando1;
    }

    public String getOperando2() {
        return operando2;
    }

    public void setOperando2(String operando2) {
        this.operando2 = operando2;
    }

    public int getNroTerceto() {
        return nroTerceto;
    }

    public void setNroTerceto(int nroTerceto) {
        this.nroTerceto = nroTerceto;
    }

    public String getTipoDatoTerceto() {
        return tipoDatoTerceto;
    }

    public void setTipoDatoTerceto(String tipoDatoTerceto) {
        this.tipoDatoTerceto = tipoDatoTerceto;
    }

    public boolean esNumero(String Numero){
        try{
            Integer.parseInt(Numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public String traerTipo(String NumeroTerceto){
        int Num = Integer.parseInt(NumeroTerceto);
        ArrayList<Terceto> listaTercetos = new IntermediateCodeGenerator().getListaTercetos();
        for (Terceto T: listaTercetos) {
            if(T.getNroTerceto() == Num)
                return T.getTipoDatoTerceto();
        }
        return "";
    }

    public String buscarTipoDato(String op){
        for (Simbolo S:tablaSimbolos) {
            if (S.getNombre().equals(op))
                return S.getTipo();
        }
        return "";
    }

    @java.lang.Override
    public java.lang.String toString() {
        return ""+ nroTerceto+"("+ operador +";"+ operando1 +";"+ operando2 +")" + " "+ tipoDatoTerceto;
    }
}