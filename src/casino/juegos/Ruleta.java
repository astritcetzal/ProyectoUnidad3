package juegos;

import exceptions.ApuestaInvalidaRuletaException;
import exceptions.ApuestaMaximaInvalidaException;
import exceptions.ApuestaMinimaInvalidaException;
import exceptions.JuegoInactivoRuletaException;
import modelo.Jugador;

public class Ruleta extends JuegoMesa {

    private int numeroGanador;
    private String colorGanador;

    private int numeroElegido;
    private String colorElegido;
    private double montoApuesta;

    private static final int NUMERO_MAX = 36;
    private static final double PAGO_NUMERO = 35.0; // paga 35 a 1
    private static final double PAGO_COLOR = 2.0; // devuelve el doble

    public Ruleta(String nombre, Jugador jugadorActual, double apuestaMinima, double apuestaMaxima, boolean activo)
            throws ApuestaMaximaInvalidaException, ApuestaMinimaInvalidaException {
        super(nombre, jugadorActual, apuestaMinima, apuestaMaxima, activo); // apuesta mínima $5, máxima $1000
        this.numeroGanador = 0;
        this.colorGanador = "";
        this.numeroElegido = -1;
        this.colorElegido = "";
        this.montoApuesta = 0;

    }

    public int getNumeroGanador() {
        return numeroGanador;
    }

    public String getColorGanador() {
        return colorGanador;
    }

    public void setApuesta(int numeroElegido, String colorElegido, double monto) {
        if (numeroElegido < 0 || numeroElegido >= 36) {
            throw new IllegalArgumentException("El número elegido debe estar entre 0 y 36");
        }
        if (!colorElegido.equalsIgnoreCase("Rojo") && !colorElegido.equalsIgnoreCase("Negro")
                && !colorElegido.equalsIgnoreCase("Verde")) {
            throw new IllegalArgumentException("El color elegido debe ser Rojo, Negro o Verde");
        }

        this.numeroElegido = numeroElegido;
        this.colorElegido = colorElegido;
        this.montoApuesta = monto;
    }

    @Override
    public void iniciar(Jugador j) {
        if (j == null) {
            throw new IllegalArgumentException("No se puede iniciar la Ruleta sin un jugador");
        }
        setJugadorActual(j);
        boolean activo = true;
        setActivo(activo);
        System.out.println("══════════════════════════════════════");
        System.out.println("  Bienvenido a la Ruleta, " + j.getNombre() + "!");
        System.out.println("  Apuesta mínima : $" + getApuestaMinima());
        System.out.println("  Apuesta máxima : $" + getApuestamaxima());
        System.out.println("  Saldo actual   : $" + j.getSaldo());
        System.out.println("══════════════════════════════════════");
    }

    @Override
    public void jugar() {
        try {
            if (!isActivo() || getJugadorActual() == null) {
                throw new JuegoInactivoRuletaException(getNombre());
            }

            if (!validarApuesta(montoApuesta)) {
                throw new ApuestaInvalidaRuletaException(getApuestaMinima(), getApuestamaxima());
            }

            // Empieza el flujo después de validar
            getJugadorActual().apostar(montoApuesta);

            girarRuleta();
            System.out.println("══════════════════════════════════════");
            System.out.println("  Número ganador : " + numeroGanador);
            System.out.println("  Color ganador  : " + colorGanador);
            System.out.println("══════════════════════════════════════");

            // Lógica
            boolean acertoNumero = (numeroElegido == numeroGanador);
            boolean acertoColor = colorElegido.equalsIgnoreCase(colorGanador);

            if (acertoNumero) {
                double pago = montoApuesta * PAGO_NUMERO;
                System.out.println("¡Acertaste el número! Ganaste $" + pago);
                getJugadorActual().recibirPago(pago);
            } else if (acertoColor) {
                double pago = montoApuesta * PAGO_COLOR;
                System.out.println("¡Acertaste el color! Ganaste $" + pago);
                getJugadorActual().recibirPago(pago);
            } else {
                System.out.println("No acertaste. Perdiste $" + montoApuesta);
            }

            // Resetear para setApuestas para volver a jugar
            montoApuesta = 0;
            numeroElegido = -1;
            colorElegido = "";
            terminar();

        } catch (ApuestaInvalidaRuletaException e) {
            System.out.println("Error de estado: " + e.getMessage());
        } catch (JuegoInactivoRuletaException e) {
            System.out.println("Error de apuesta: " + e.getMessage());
        }
    }

    @Override
    public void terminar() {
        if (getJugadorActual() != null) {
            System.out.println("══════════════════════════════════════");
            System.out.println("  Fin de la partida de Ruleta.");
            System.out.println("  Jugador     : " + getJugadorActual().getNombre());
            System.out.println("  Saldo final : $" + getJugadorActual().getSaldo());
            System.out.println("══════════════════════════════════════");
        }
        setActivo(false);
        setJugadorActual(null);
    }

    public int girarRuleta() {
        numeroGanador = (int) (Math.random() * (NUMERO_MAX + 1)); // 0 a 36

        if (numeroGanador == 0) {
            colorGanador = "Verde";
        } else if (numeroGanador % 2 == 0) {
            colorGanador = "Negro";
        } else {
            colorGanador = "Rojo";
        }
        return numeroGanador;
    }

    @Override
    public String toString() {
        return "Ruleta{" +
                "nombre='" + getNombre() + '\'' +
                ", apuestaMin: $" + getApuestaMinima() +
                ", apuestaMax: $" + getApuestamaxima() +
                ", activo: " + isActivo() +
                ", numeroGanador: " + numeroGanador +
                ", colorGanador: " + colorGanador + '\'' +
                '}';
    }

}
