package juegos;

import persona.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.ApuestaMaximaInvalidaException;
import exceptions.ApuestaMinimaInvalidaException;
import exceptions.JuegoInactivoException;
import exceptions.SaldoInsuficienteException;

public class BlackJack extends JuegoMesa {
    private int puntosJugador;
    private int puntosCasa;
    private List<Integer> mazo;

    public BlackJack(String nombre, Jugador jugadorActual, double apuestaMinima, double apuestaMaxima, boolean activo) throws ApuestaMaximaInvalidaException, ApuestaMinimaInvalidaException {
        super(nombre, jugadorActual, apuestaMinima, apuestaMaxima, activo);
        this.mazo = new ArrayList<>();
        prepararMazo();
    }

    private void prepararMazo() {
        mazo.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 10; j++) {
                mazo.add(j);
            }
        }
        Collections.shuffle(mazo);
    }

    private int sacarCarta() {
        if (mazo.isEmpty())
            prepararMazo();
        return mazo.remove(0);
    }

    @Override
    public void iniciar(Jugador jugador) throws SaldoInsuficienteException {
        if (jugador == null) {
            return; 
        }
        
        if (jugador.getSaldo() < getApuestaMinima()) {
            throw new SaldoInsuficienteException(jugador.getSaldo(), getApuestaMinima());
        }

        getJugadorActual();
        this.puntosJugador = 0;
        this.puntosCasa = 0;
        boolean activo = true;
        setActivo(activo);
        prepararMazo();
        System.out.println("¡Mesa lista! Jugador: " + jugador.getNombre());
    }

    public void setPuntosJugador(int puntosJugador) {
        this.puntosJugador = puntosJugador;
    }

    public void setPuntosCasa(int puntosCasa) {
        this.puntosCasa = puntosCasa;
    }

    public void setMazo(List<Integer> mazo) {
        this.mazo = mazo;
    }

    @Override
    public void jugar() {
        try {
            if (!isActivo()) {
                throw new JuegoInactivoException(getNombre());
            }

            if (getJugadorActual() == null) {
                System.out.println("Error: No hay un jugador asignado a la mesa.");
            }

            double montoSimulado = getApuestaMinima(); 
            
            if (!validarApuesta(montoSimulado)) {
                throw new ApuestaMinimaInvalidaException("La apuesta de " + montoSimulado + " no es válida para esta mesa.");
            }

            repartirCartas();

            while (this.puntosJugador < 17) {
                this.puntosJugador += sacarCarta();
            }

            if (this.puntosJugador <= 21) {
                while (this.puntosCasa < 17) {
                    this.puntosCasa += sacarCarta();
                }
            }
            terminar();
            
            } catch (JuegoInactivoException e) {
                System.out.println("Error de estado: " + e.getMessage());
            
            } catch (ApuestaMinimaInvalidaException e) {
                System.out.println("Error de apuesta: " + e.getMessage());
            
            } catch (IllegalStateException e) {
                System.out.println("Error de lógica: " + e.getMessage());
            }
    }
    
    @Override
    public void terminar() {
        isActivo();
        int resultado = calcularPuntos();
        if (resultado == 1) {
            System.out.println("--- ¡GANASTE! ---");
        } else if (resultado == 2) {
            System.out.println("--- GANA LA CASA ---");
        } else {
            System.out.println("--- EMPATE ---");
        }
        System.out.println("Puntos Jugador: " + puntosJugador + " | Puntos Casa: " + puntosCasa);
    }

    public void repartirCartas() {
        this.puntosJugador = sacarCarta() + sacarCarta();
        this.puntosCasa = sacarCarta() + sacarCarta();
    }

    public int calcularPuntos() {
        if (puntosJugador > 21) return 2;
        if (puntosCasa > 21 || puntosJugador > puntosCasa) return 1;
        if (puntosJugador < puntosCasa) return 2;
        return 0;
    }

    public int getPuntosJugador() {
        return puntosJugador; 
    }
    
    public int getPuntosCasa() {
        return puntosCasa; 
    }

    public List<Integer> getMazo(){
        return mazo; 
    }
}