package juegos;

import persona.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJack extends JuegoMesa {
    private int puntosJugador;
    private int puntosCasa;
    private List<Integer> mazo;

    public BlackJack(String nombre, Jugador jugadorActual, double apuestaMinima, double apuestaMaxima, boolean activo) {
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
    public void iniciar(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("No se puede iniciar el juego sin un jugador válido.");
        }

        if (jugador.getSaldo() < this.apuestaMinima) {
            throw new IllegalStateException("El jugador no tiene saldo suficiente para la apuesta mínima de esta mesa.");
        }

        this.jugadorActual = jugador;
        this.puntosJugador = 0;
        this.puntosCasa = 0;
        this.activo = true;
        prepararMazo();
        System.out.println("¡Mesa lista! Jugador: " + jugador.getNombre());
    }

@Override
    public void jugar() {
        if (!this.activo) {
            System.out.println("El juego no está activo.");
            return;
        }

        if (this.jugadorActual == null) {
            System.out.println("Error: No hay un jugador asignado a la mesa.");
        }

        double montoSimulado = this.apuestaMinima; 
        
        if (!validarApuesta(montoSimulado)) {
            throw new IllegalArgumentException("La apuesta no es válida para los límites de esta mesa.");
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
    }
    @Override
    public void terminar() {
        this.activo = false;
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

    public String getNombre(){
        return this.nombre; 
    }

    public List<Integer>getMazo(){
        return mazo; 
    }
}