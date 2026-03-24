package servicio;

import juegos.BlackJack;
import juegos.JuegoMesa;
import juegos.Ruleta;
import persona.Empleado;
import persona.Jugador;
import sistema.Casino;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import archivos.JugadorRepository;
import archivos.Repositorio;
import exceptions.CedulaEmpleadoDuplicadoException;
import exceptions.IDJugadorDuplicadoException;

public class CasinoService {
    private List<Jugador> jugadores = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();
    private List<JuegoMesa> juegos = new ArrayList<>();
    private Casino casino;
    private Repositorio<Jugador> jugadorRepo;

    public CasinoService(Casino casino) {
        if (casino == null)
            throw new IllegalArgumentException("El casino no puede ser nulo");
        this.casino = casino;
        this.jugadorRepo = new JugadorRepository();
        this.jugadores = jugadorRepo.cargar();
        for (Jugador j : jugadores)
            casino.registrarJugador(j);
    }

    public void agregarJugador(Jugador j) throws IDJugadorDuplicadoException {
        if (j == null)
            throw new IllegalArgumentException("El jugador no puede ser nulo");

        if (j.getEdad() < 18)
            throw new IllegalArgumentException("El jugador debe ser mayor de edad");

        for (Jugador jg : jugadores) {
            if (jg.getIdJugador().equals(j.getIdJugador())) {
                throw new IDJugadorDuplicadoException(j.getIdJugador());
            }
        }
        jugadores.add(j);
        casino.registrarJugador(j);
        jugadorRepo.guardar(jugadores);
    }

    public Jugador buscarJugador(String id) {
        for (Jugador j : jugadores)
            if (j.getIdJugador().equals(id)) {
                return j;
            }
        throw new IllegalArgumentException("Mo se encontró un jugador con ID: " + id);
    }

    public List<Jugador> filtrarPorSaldo(double minimo) {
        List<Jugador> resultado = new ArrayList<>();
        for (Jugador j : jugadores)
            if (j.getSaldo() >= minimo) {
                resultado.add(j);
            }
        return resultado;
    }

    public List<Jugador> filtrarVIP() {
        List<Jugador> resultado = new ArrayList<>();
        for (Jugador j : jugadores)
            if (j.getRol().equals("Jugador VIP")) {
                resultado.add(j);
            }
        return resultado;
    }

    public void eliminarJugador(String id) {
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador j = iterator.next();
            if (j.getIdJugador().equals(id)) {
                iterator.remove();
                jugadorRepo.guardar(jugadores);
                return;
            }
        }
        throw new IllegalArgumentException("No se encontró un jugador con ID: " + id);
    }

    public void agregarEmpleado(Empleado empleado) throws CedulaEmpleadoDuplicadoException {
        if (empleado == null)
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        for (Empleado e : empleados) {
            if (e.getCedula().equals(empleado.getCedula())) {
                throw new CedulaEmpleadoDuplicadoException(empleado.getCedula());
            }
        }
        empleados.add(empleado);
        casino.agregarEmpleado(empleado);

    }

    public Empleado buscarEmpleado(String cedula) {
        for (Empleado e : empleados)
            if (e.getCedula().equals(cedula)) {
                return e;
            }
        throw new IllegalArgumentException("No se encontró el empleado con la cédula: " + cedula);
    }

    public List<Empleado> filtrarPorCargo(String cargo) {
        List<Empleado> resultado = new ArrayList<>();

        for (Empleado e : empleados)
            if (e.getCargo().equalsIgnoreCase(cargo)) {
                resultado.add(e);
            }
        return resultado;
    }

    public void eliminarEmpleado(String cedula) {
        Iterator<Empleado> iterator = empleados.iterator();
        while (iterator.hasNext()) {
            Empleado e = iterator.next();
            if (e.getCedula().equals(cedula)) {
                iterator.remove();
                return;
            }
        }
        throw new IllegalArgumentException("No se encontró el empleado con la cédula: " + cedula);
    }

    public Ruleta agregarRuleta(String nombre, String idJugador, double min, double max) {
        if (max <= min) {
            throw new IllegalArgumentException("La apuesta máxima debe ser mayor que la mínima");
        }
        Ruleta ruleta = casino.agregarRuleta(nombre, buscarJugador(idJugador), min, max, false);
        juegos.add(ruleta);
        return ruleta;
    }

    public BlackJack agregarBlackJack(String nombre, String idJugador, double min, double max) {
        if (max <= min) {
            throw new IllegalArgumentException("La apuesta máxima debe ser mayor que la mínima");
        }
        BlackJack bj = casino.agregarBlackJack(nombre, buscarJugador(idJugador), min, max, false);
        juegos.add(bj);
        return bj;
    }

    public JuegoMesa buscarJuego(String nombre) {
        for (JuegoMesa j : juegos)
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                return j;
            }
        return null;
    }

    public List<JuegoMesa> filtrarActivos() {
        List<JuegoMesa> resultado = new ArrayList<>();

        for (JuegoMesa j : juegos)
            if (j.isActivo()) {
                resultado.add(j);
            }
        return resultado;
    }

    public void eliminarJuego(String nombre) {
        Iterator<JuegoMesa> iterator = juegos.iterator();
        while (iterator.hasNext()) {
            JuegoMesa j = iterator.next();
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                iterator.remove();
                return;
            }
        }
        throw new IllegalArgumentException("No se encontró el juego: " + nombre);
    }
}
