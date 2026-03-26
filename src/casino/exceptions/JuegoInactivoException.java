package exceptions;

public class JuegoInactivoException extends Exception {
    public JuegoInactivoException(String nombreJuego) {
        super("El juego '" + nombreJuego + "' no está activo o no ha sido iniciado correctamente.");
    }
}