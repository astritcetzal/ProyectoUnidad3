package repositorio;

import java.io.IOException;
import java.util.List;

import modelo.Jugador;

public interface JugadorRepository {
    void guardar(List<Jugador> jugadores) throws IOException;

    List<Jugador> cargar() throws IOException;

}
