package lyc.compiler.files;

public class Punteros {
    static int indiceElemento=0;
    static int indiceLista=0;
    static int indiceFirstIndex=0;
    static int indiceTimer=0;
    static int indiceWrite=0;
    static int indiceRead=0;
    static int indiceFactor=0;
    static int indiceTermino=0;
    static int indiceExpresion=0;
    static int indiceAsignacion=0;
    static int indiceComparador=0;
    static int indiceComparacion=0;
    static int indiceCondicion=0;
    static int indiceSeleccion=0;
    static int indiceIteracion=0;
    static int indiceSentencia=0;
    static int indicePrograma=1;
    static int indiceProgramaSentencia=0;
    static int indiceListaCadena=0;
    static int indiceListaFlotante=0;
    static int indiceListaEntero=0;
    static int indiceVariableIniciada=0;
    static int indiceDeclaracion=0;
    static int indicePrincipio=0;

    static int auxiliar1=0;
    static int auxiliar2=0;
    static int auxiliarAnd=0;
    static int auxiliarWhile=0;
    static int auxiliarOr=0;
    public static int getAuxiliarOr() {
        return auxiliarOr;
    }

    public static void setAuxiliarOr(int auxiliarOr) {
        Punteros.auxiliarOr = auxiliarOr;
    }


    public static int getAuxiliarAnd() {
        return auxiliarAnd;
    }

    public static void setAuxiliarAnd(int auxiliarAnd) {
        Punteros.auxiliarAnd = auxiliarAnd;
    }

    public static int getAuxiliarWhile() {
        return auxiliarWhile;
    }

    public static void setAuxiliarWhile(int auxiliarWhile) {
        Punteros.auxiliarWhile = auxiliarWhile;
    }

    public static int getIndiceProgramaSentencia() {return indiceProgramaSentencia;}

    public static void setIndiceProgramaSentencia(int indiceProgramaSentencia) {Punteros.indiceProgramaSentencia = indiceProgramaSentencia;}

    public static int getIndiceFirstIndex() {return indiceFirstIndex;}

    public static void setIndiceFirstIndex(int indiceFirstIndex) {Punteros.indiceFirstIndex = indiceFirstIndex;}

    public int getAuxiliar1() {
        return auxiliar1;
    }

    public void setAuxiliar1(int auxiliar) {
        this.auxiliar1 = auxiliar;
    }

    public int getAuxiliar2() {
        return auxiliar2;
    }

    public void setAuxiliar2(int auxiliar) {
        this.auxiliar2 = auxiliar;
    }

    public int getIndiceElemento() {
        return indiceElemento;
    }

    public void setIndiceElemento(int indiceElemento) {
        this.indiceElemento = indiceElemento;
    }

    public int getIndiceLista() {
        return indiceLista;
    }

    public void setIndiceLista(int indiceLista) {
        this.indiceLista = indiceLista;
    }

    public int getIndiceTimer() {
        return indiceTimer;
    }

    public void setIndiceTimer(int indiceTimer) {
        this.indiceTimer = indiceTimer;
    }

    public int getIndiceWrite() {
        return indiceWrite;
    }

    public void setIndiceWrite(int indiceWrite) {
        this.indiceWrite = indiceWrite;
    }

    public int getIndiceRead() {
        return indiceRead;
    }

    public void setIndiceRead(int indiceRead) {
        this.indiceRead = indiceRead;
    }

    public int getIndiceFactor() {
        return indiceFactor;
    }

    public void setIndiceFactor(int indiceFactor) {
        this.indiceFactor = indiceFactor;
    }

    public int getIndiceTermino() {
        return indiceTermino;
    }

    public void setIndiceTermino(int indiceTermino) {
        this.indiceTermino = indiceTermino;
    }

    public int getIndiceExpresion() {
        return indiceExpresion;
    }

    public void setIndiceExpresion(int indiceExpresion) {
        this.indiceExpresion = indiceExpresion;
    }

    public int getIndiceAsignacion() {
        return indiceAsignacion;
    }

    public void setIndiceAsignacion(int indiceAsignacion) {
        this.indiceAsignacion = indiceAsignacion;
    }

    public int getIndiceComparador() {
        return indiceComparador;
    }

    public void setIndiceComparador(int indiceComparador) {
        this.indiceComparador = indiceComparador;
    }

    public int getIndiceComparacion() {
        return indiceComparacion;
    }

    public void setIndiceComparacion(int indiceComparacion) {
        this.indiceComparacion = indiceComparacion;
    }

    public int getIndiceCondicion() {
        return indiceCondicion;
    }

    public void setIndiceCondicion(int indiceCondicion) {
        this.indiceCondicion = indiceCondicion;
    }

    public int getIndiceSeleccion() {
        return indiceSeleccion;
    }

    public void setIndiceSeleccion(int indiceSeleccion) {
        this.indiceSeleccion = indiceSeleccion;
    }

    public int getIndiceIteracion() {
        return indiceIteracion;
    }

    public void setIndiceIteracion(int indiceIteracion) {
        this.indiceIteracion = indiceIteracion;
    }

    public int getIndiceSentencia() {
        return indiceSentencia;
    }

    public void setIndiceSentencia(int indiceSentencia) {
        this.indiceSentencia = indiceSentencia;
    }

    public int getIndicePrograma() {
        return indicePrograma;
    }

    public void setIndicePrograma(int indicePrograma) {
        this.indicePrograma = indicePrograma;
    }

    public int getIndiceListaCadena() {
        return indiceListaCadena;
    }

    public void setIndiceListaCadena(int indiceListaCadena) {
        this.indiceListaCadena = indiceListaCadena;
    }

    public int getIndiceListaFlotante() {
        return indiceListaFlotante;
    }

    public void setIndiceListaFlotante(int indiceListaFlotante) {
        this.indiceListaFlotante = indiceListaFlotante;
    }

    public int getIndiceListaEntero() {
        return indiceListaEntero;
    }

    public void setIndiceListaEntero(int indiceListaEntero) {
        this.indiceListaEntero = indiceListaEntero;
    }

    public int getIndiceVariableIniciada() {
        return indiceVariableIniciada;
    }

    public void setIndiceVariableIniciada(int indiceVariableIniciada) {
        this.indiceVariableIniciada = indiceVariableIniciada;
    }

    public int getIndiceDeclaracion() {
        return indiceDeclaracion;
    }

    public void setIndiceDeclaracion(int indiceDeclaracion) {
        this.indiceDeclaracion = indiceDeclaracion;
    }

    public int getIndicePrincipio() {
        return indicePrincipio;
    }

    public void setIndicePrincipio(int indicePrincipio) {
        this.indicePrincipio = indicePrincipio;
    }
}
