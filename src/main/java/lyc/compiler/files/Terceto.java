package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;

public class Terceto {
    public String operando;
    public String operador1;
    public String operador2;
    public int nroTerceto;
    public static int cant = 1;

    public Terceto(String operando, String operador1, String operador2) {
        this.operando = operando;
        this.operador1 = operador1;
        this.operador2 = operador2;
        this.nroTerceto = cant;
        cant++;
    }

    public String getOperando() {
        return operando;
    }

    public void setOperando(String operando) {
        this.operando = operando;
    }

    public String getOperador1() {
        return operador1;
    }

    public void setOperador1(String operador1) {
        this.operador1 = operador1;
    }

    public String getOperador2() {
        return operador2;
    }

    public void setOperador2(String operador2) {
        this.operador2 = operador2;
    }

    public int getNroTerceto() {
        return nroTerceto;
    }

    public void setNroTerceto(int nroTerceto) {
        this.nroTerceto = nroTerceto;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return ""+ nroTerceto+"("+operando+";"+operador1+";"+operador2+")";
    }
}