package juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import exceptions.ApuestaMaximaInvalidaException;
import exceptions.ApuestaMinimaInvalidaException;
import exceptions.JuegoInactivoException;
import exceptions.SaldoInsuficienteException;
import modelo.Jugador;

public class BlackJack extends JuegoMesa {
    private int puntosJugador;
    private int puntosCasa;
    private List<Integer> mazo;
    private double montoApuesta;

    public BlackJack(String nombre, Jugador jugadorActual, double apuestaMinima, double apuestaMaxima, boolean activo)
            throws ApuestaMaximaInvalidaException, ApuestaMinimaInvalidaException {
        super(nombre, jugadorActual, apuestaMinima, apuestaMaxima, activo);
        this.mazo = new ArrayList<>();
        this.montoApuesta = 0;
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

    public void prepararApuesta(double monto) {
        if (monto < getApuestaMinima() || monto > getApuestamaxima()) {
            throw new IllegalArgumentException("La apuesta de $" + monto + " está fuera de los límites.");
        }
        this.montoApuesta = monto;
    }

    @Override
    public void iniciar(Jugador jugador) throws SaldoInsuficienteException {
        if (jugador == null)
            return;

        if (jugador.getSaldo() < getApuestaMinima()) {
            throw new SaldoInsuficienteException(jugador.getSaldo(), getApuestaMinima());
        }

        setJugadorActual(jugador);
        this.puntosJugador = 0;
        this.puntosCasa = 0;
        setActivo(true);
        prepararMazo();

        System.out.println("══════════════════════════════════════");
        System.out.println("  Bienvenido al BlackJack, " + jugador.getNombre() + "!");
        System.out.println("  Apuesta mínima : $" + getApuestaMinima());
        System.out.println("  Apuesta máxima : $" + getApuestamaxima());
        System.out.println("  Saldo actual   : $" + jugador.getSaldo());
        System.out.println("══════════════════════════════════════");
    }

    @Override
    public void jugar() {
        try {
            if (!isActivo() || getJugadorActual() == null) {
                throw new JuegoInactivoException(getNombre());
            }

            getJugadorActual().apostar(montoApuesta);
            repartirCartas();

            Scanner sc = new Scanner(System.in);
            System.out.println("Tus puntos: " + puntosJugador);
            System.out.println("La casa tiene: [?]");

            String resp = "s";
            while (puntosJugador <= 21 && resp.equalsIgnoreCase("s")) {
                System.out.print("¿Pedir carta? (s/n): ");
                resp = sc.nextLine();
                if (resp.equalsIgnoreCase("s")) {
                    puntosJugador += sacarCarta();
                    System.out.println("Tus puntos: " + puntosJugador);
                }
            }

            if (puntosJugador > 21) {
                System.out.println("Te pasaste. La casa gana.");
            } else {
                while (puntosCasa < 17) {
                    puntosCasa += sacarCarta();
                }
                System.out.println("La casa tiene: " + puntosCasa);
            }

            int resultado = calcularPuntos();
            if (resultado == 1) {
                double premio = montoApuesta * 2;
                System.out.println("Ganaste! Recibes: $" + premio);
                getJugadorActual().recibirPago(premio);
            } else if (resultado == 0) {
                System.out.println("Empate. Se te devuelve: $" + montoApuesta);
                getJugadorActual().recibirPago(montoApuesta);
            } else {
                System.out.println("Gana la casa. Perdiste $" + montoApuesta);
            }

            terminar();

        } catch (JuegoInactivoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void terminar() {
        if (getJugadorActual() != null) {
            System.out.println("══════════════════════════════════════");
            int resultado = calcularPuntos();
            if (resultado == 1) {
                System.out.println("  RESULTADO: ¡GANASTE!");
            } else if (resultado == 2) {
                System.out.println("  RESULTADO: GANA LA CASA");
            } else {
                System.out.println("  RESULTADO: EMPATE");
            }
            System.out.println("  Puntos Jugador  : " + puntosJugador);
            System.out.println("  Puntos Casa     : " + puntosCasa);
            System.out.println("  Saldo final     : $" + getJugadorActual().getSaldo());
            System.out.println("══════════════════════════════════════");
        }
        this.montoApuesta = 0;
        setActivo(false);
        setJugadorActual(null);
    }

    public void repartirCartas() {
        this.puntosJugador = sacarCarta() + sacarCarta();
        this.puntosCasa = sacarCarta() + sacarCarta();
    }

    public int calcularPuntos() {
        // Si el jugador se pasa de 21, pierde siempre (Gana casa = 2)
        if (puntosJugador > 21)
            return 2;
        // Si la casa se pasa o el jugador tiene más puntos, gana jugador (1)
        if (puntosCasa > 21 || puntosJugador > puntosCasa)
            return 1;
        // Si tienen lo mismo, empate (0)
        if (puntosJugador == puntosCasa)
            return 0;
        // En cualquier otro caso, gana casa (2)
        return 2;
    }

    public int getPuntosJugador() {
        return puntosJugador;
    }

    public int getPuntosCasa() {
        return puntosCasa;
    }

    public void jugar(Scanner sc) {
        try {
            if (!isActivo() || getJugadorActual() == null) {
                throw new JuegoInactivoException(getNombre());
            }

            getJugadorActual().apostar(montoApuesta);

            repartirCartas();
            System.out.println("══════════════════════════════════════");
            System.out.println("  Tus cartas suman : " + puntosJugador);
            System.out.println("  La casa tiene    : " + puntosCasa);
            System.out.println("══════════════════════════════════════");

            String decision = "s";
            while (decision.equalsIgnoreCase("s") && puntosJugador <= 21) {
                System.out.println("¿Pedir carta? (s = sí / n = plantarse): ");
                decision = sc.nextLine();
                if (decision.equalsIgnoreCase("s")) {
                    puntosJugador += sacarCarta();
                    System.out.println("  Nueva suma: " + puntosJugador);
                    if (puntosJugador > 21) {
                        System.out.println("  ¡Te pasaste de 21!");
                    }
                }
            }

            if (puntosJugador <= 21) {
                while (puntosCasa < 17) {
                    puntosCasa += sacarCarta();
                }
            }

            int resultado = calcularPuntos();
            System.out.println("Apuesta realizada. Monto apostado: $" + montoApuesta);

            if (resultado == 1) {
                double premio = montoApuesta * 2;
                System.out.println("¡GANASTE! Pago recibido de: $" + premio);
                getJugadorActual().recibirPago(premio);
            } else if (resultado == 0) {
                System.out.println("EMPATE. Se devuelve tu apuesta de: $" + montoApuesta);
                getJugadorActual().recibirPago(montoApuesta);
            } else {
                System.out.println("La casa gana. Perdiste $" + montoApuesta);
            }

            terminar();

        } catch (JuegoInactivoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}