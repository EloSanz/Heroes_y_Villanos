package Excepciones;

public class LigaInexistenteException extends Exception {
    public LigaInexistenteException(String mensaje) {
        super(mensaje);
    }
}