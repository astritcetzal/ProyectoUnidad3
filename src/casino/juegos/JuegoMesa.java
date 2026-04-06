package juegos;

import exceptions.ApuestaInvalidaRuletaException;
import exceptions.ApuestaMaximaInvalidaException;
import exceptions.ApuestaMinimaInvalidaException;
import exceptions.JuegoInactivoRuletaException;
import exceptions.SaldoInsuficienteException;
import interfaces.Jugable;
import persona.Jugador;

public abstract class JuegoMesa implements Jugable {

    private String nombre;
    private Jugador jugadorActual;
    private double apuestaMinima, apuestaMaxima;
    private boolean activo;

    public JuegoMesa(String nombre, Jugador jugadorActual, double apuestaMinima, double apuestaMaxima, boolean activo) throws ApuestaMaximaInvalidaException, ApuestaMinimaInvalidaException {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("El nombre de la Ruleta no puede estar vacio");
        if (jugadorActual == null)
            throw new IllegalArgumentException("Agregar a un jugador");
        if (apuestaMinima < 100.00)
            throw new ApuestaMinimaInvalidaException(String.valueOf(apuestaMinima));
        if (apuestaMaxima > 35000.00)
            throw new ApuestaMaximaInvalidaException(apuestaMaxima);
        if (apuestaMinima >= apuestaMaxima)
            throw new IllegalArgumentException("La apuesta mínima no puede ser mayor o igual a la máxima");

        this.nombre = nombre;
        this.jugadorActual = jugadorActual;
        this.apuestaMinima = apuestaMinima;
        this.apuestaMaxima = apuestaMaxima;
        this.activo = activo;

    }

    // NOMBRE
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El juego no puede no tener nombre");
        }
        this.nombre = nombre;
    }

    // APUESTAMINIMA
    public double getApuestaMinima() {
        return apuestaMinima;
    }

    public void setApuestaMinima(double apuestaMinima) {
        if (apuestaMinima < 0) {
            throw new IllegalArgumentException("Tiene q ser mayor a 0");
        }
        this.apuestaMinima = apuestaMinima;
    }

    // APUESTAMAXIMA
    public double getApuestamaxima() {
        return apuestaMaxima;
    }

    public void setApuestaMaxima(double apuestaMaxima) {
        if (apuestaMaxima <= this.apuestaMinima) {
            throw new IllegalArgumentException("No puede ser menor que la apuesta mínima");
        }
        this.apuestaMaxima = apuestaMaxima;
    }

    // JUGADORACTUAL
    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    // ACTIVO
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // VALIDAR
    public boolean validarApuesta(double monto) {
        return monto >= apuestaMinima && monto <= apuestaMaxima;
    }

    // INTERFACE
    @Override
    public abstract void iniciar(Jugador j) throws SaldoInsuficienteException;

    @Override
    public abstract void jugar() throws JuegoInactivoRuletaException, ApuestaInvalidaRuletaException;

    @Override
    public abstract void terminar();

    public String toString() {
        return "Jugador: " + nombre + "\n Apuesta mínima: " + apuestaMinima + "  &  Apuesta máxima: " + apuestaMaxima
                + "\n Estado de juego: " + activo;
    }

}
