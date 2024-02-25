package Excepciones;

public class PersonajeExistenteException extends Exception {
    public PersonajeExistenteException(String mensaje) {
        super(mensaje);
    }
}