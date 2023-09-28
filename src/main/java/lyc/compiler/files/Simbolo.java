package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;

public class Simbolo{


    String nombre;
    String tipo;
    String valor;
    int longitud;

    public Simbolo(String nombre, String tipo, String valor, int longitud) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.longitud = longitud;
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

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return nombre +"|"+ tipo +"|"+ valor +"|"+ longitud;
    }
}