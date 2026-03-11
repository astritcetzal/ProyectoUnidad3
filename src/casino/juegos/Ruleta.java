package juegos;

import persona.Jugador;

public class Ruleta extends JuegoMesa {

    private int numeroGanador;
    private String colorGanador;

    private int numeroElegido;
    private String colorElegido;
    private double montoApuesta;

    private static final int NUMERO_MAX = 36;
    private static final double PAGO_NUMERO = 35.0; // paga 35 a 1
    private static final double PAGO_COLOR = 2.0; // devuelve el doble

    public Ruleta(String nombre, Jugador jugadorActual, int apuestaMinima, int apuestaMaxima, boolean activo) {
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
        this.numeroElegido = numeroElegido;
        this.colorElegido = colorElegido;
        this.montoApuesta = monto;
    }

    @Override
    public void iniciar(Jugador j) {
        if (j == null) {
            System.out.println("No se puede iniciar la Ruleta sin un jugador.");
            return;
        }
        this.jugadorActual = j;
        this.activo = true;
        System.out.println("══════════════════════════════════════");
        System.out.println("  Bienvenido a la Ruleta, " + j.getNombre() + "!");
        System.out.println("  Apuesta mínima : $" + apuestaMinima);
        System.out.println("  Apuesta máxima : $" + apuestaMaxima);
        System.out.println("  Saldo actual   : $" + j.getSaldo());
        System.out.println("══════════════════════════════════════");
    }

    @Override
    public void jugar() {
        if (!activo || jugadorActual == null) {
            System.out.println("La Ruleta no está activa. Llama a iniciar() primero.");
            return;
        }

        if (montoApuesta <= 0) {
            System.out.println("Debes definir tu apuesta con setApuesta() antes de jugar.");
            return;
        }

        if (!validarApuesta(montoApuesta)) {
            System.out
                    .println("Apuesta inválida. El monto debe estar entre $" + apuestaMinima + " y $" + apuestaMaxima);
            return;
        }
        // Empieza el flujo despues de validar
        jugadorActual.apostar(montoApuesta);

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
            jugadorActual.recibirPago(pago);
        } else if (acertoColor) {
            double pago = montoApuesta * PAGO_COLOR;
            System.out.println("¡Acertaste el color! Ganaste $" + pago);
            jugadorActual.recibirPago(pago);
        } else {
            System.out.println("No acertaste. Perdiste $" + montoApuesta);
        }

        // Resetear para setApuestas para volver a jugar
        montoApuesta = 0;
        numeroElegido = -1;
        colorElegido = "";
    }

    @Override
    public void terminar() {
        if (jugadorActual != null) {
            System.out.println("══════════════════════════════════════");
            System.out.println("  Fin de la partida de Ruleta.");
            System.out.println("  Jugador     : " + jugadorActual.getNombre());
            System.out.println("  Saldo final : $" + jugadorActual.getSaldo());
            System.out.println("══════════════════════════════════════");
        }
        this.activo = false;
        this.jugadorActual = null;
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
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Ruleta{" +
                "nombre='" + nombre + '\'' +
                ", apuestaMin=$" + apuestaMinima +
                ", apuestaMax=$" + apuestaMaxima +
                ", activo=" + activo +
                ", numeroGanador=" + numeroGanador +
                ", colorGanador='" + colorGanador + '\'' +
                '}';
    }

}