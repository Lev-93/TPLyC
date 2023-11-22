package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;

public class Simbolo{


    public String nombre;
    public String tipo;
    public String valor;
    public String longitud;



    public boolean uso;

    public Simbolo(String nombre, String tipo, String valor, String longitud) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.uso=false;
        if(longitud.length()>0)
            this.longitud = (longitud.length()-2) + "";
        else
            this.longitud = "";
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public boolean isUso() {
        return uso;
    }

    public void setUso(boolean uso) {
        this.uso = uso;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return nombre +"|"+ tipo +"|"+ valor +"|"+ longitud + "|" + uso;
    }
}