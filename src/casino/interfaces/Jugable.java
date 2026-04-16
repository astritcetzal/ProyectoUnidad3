package interfaces;

import exceptions.ApuestaInvalidaRuletaException;
import exceptions.JuegoInactivoRuletaException;
import exceptions.SaldoInsuficienteException;
import modelo.Jugador;

public interface Jugable {

    public void iniciar(Jugador j) throws SaldoInsuficienteException;

    public void jugar() throws JuegoInactivoRuletaException, ApuestaInvalidaRuletaException;

    public void terminar();

    public String getNombre();

}