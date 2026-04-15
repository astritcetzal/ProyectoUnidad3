package servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import exceptions.IDJugadorDuplicadoException;
import persona.Jugador;
import repositorio.JugadorRepository;

public class JugadorService {

    private JugadorRepository repositorio;
    private List<Jugador> jugadores = new ArrayList<>();

    public JugadorService(JugadorRepository repositorio) throws IOException {
        this.repositorio = repositorio;
        this.jugadores = repositorio.cargar();
    }

    public void agregarJugador(Jugador j) throws IDJugadorDuplicadoException, IOException {
        if (j == null)
            throw new IllegalArgumentException("El jugador no puede ser nulo");

        if (j.getEdad() < 18)
            throw new IllegalArgumentException("El jugador debe ser mayor de edad");

        for (Jugador jg  : jugadores) {
            if (jg.getIdJugador().equals(j.getIdJugador())) {
                throw new IDJugadorDuplicadoException(j.getIdJugador());
            }
        }
        jugadores.add(j);
        repositorio.guardar(jugadores);
    }

    public Jugador buscarJugador(String id) {
        for (Jugador j : jugadores)
            if (j.getIdJugador().equals(id)) {
                return j;
            }
        throw new IllegalArgumentException("No se encontró un jugador con ID: " + id);
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

    public void eliminarJugador(String id) throws IOException {
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador j = iterator.next();
            if (j.getIdJugador().equals(id)) {
                iterator.remove();
                repositorio.guardar(jugadores);
                return;
            }
        }
        throw new IllegalArgumentException("No se encontró un jugador con ID: " + id);
    }

}
