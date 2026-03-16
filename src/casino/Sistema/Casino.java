package Sistema;

import juegos.BlackJack;
import juegos.JuegoMesa;
import juegos.Ruleta;
import persona.Jugador;
import persona.Empleado;
import java.util.ArrayList;
import java.util.List;

public class Casino {
    private String nombre;
    private List<Jugador> jugadores; // agrgacion
    private List<Empleado> empleados; // agregacion
    private List<JuegoMesa> juegos; // composicion
    private double cajaTotal;

    public Casino(String nombre) {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.juegos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getCajaTotal() {
        return cajaTotal;
    }

    public void setCajaTotal(double caja) {
        this.cajaTotal = caja;
    }

    public void registrarJugador(Jugador j) {
        jugadores.add(j);
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    // composicion
    public Ruleta agregarRuleta(String nombreRuleta, Jugador jugadorActual, double apuestaMinima, double apuestaMaxima,
            boolean activo) {
        Ruleta nuevaRuleta = new Ruleta(nombreRuleta, jugadorActual, apuestaMinima, apuestaMaxima, activo);

        this.juegos.add(nuevaRuleta);
        return nuevaRuleta;

    }

    public BlackJack agregarBlackJack(String nombreBlackJack, Jugador jugadorActual, double apuestaMinima,
            double apuestaMaxima, boolean activo) {
        BlackJack nuevoBlackJack = new BlackJack(nombreBlackJack, jugadorActual, apuestaMinima, apuestaMaxima, activo);
        this.juegos.add(nuevoBlackJack);

        return nuevoBlackJack;
    }

    @Override
    public String toString() {
        return "Casino: " + nombre + "- Caja: $" + cajaTotal;
    }

}
