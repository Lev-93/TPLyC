package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;

public class Terceto {
    public String operador;
    public String operando1;
    public String operando2;
    public int nroTerceto;
    public static int cant = 1;

    public Terceto(String operador, String operando1, String operador2) {
        this.operador = operador;
        this.operando1 = operando1;
        this.operando2 = operador2;
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

    @java.lang.Override
    public java.lang.String toString() {
        return ""+ nroTerceto+"("+ operador +";"+ operando1 +";"+ operando2 +")";
    }
}