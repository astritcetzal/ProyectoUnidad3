package persistencia;

import persona.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JugadorRepository implements Repositorio<Jugador> {

    private static final String ARCHIVO = "jugadores.csv";

    @Override
    public void guardar(List<Jugador> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Jugador j : lista) {
                bw.write(j.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar jugadores: " + e.getMessage());
        }
    }

    @Override
    public List<Jugador> cargar() {
        List<Jugador> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);

        if (archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isEmpty()) {
                    continue;
                }
                try {
                    lista.add(Jugador.fromCSV(linea));
                } catch (Exception e) {
                    System.out.println("Advertencia: línea corrupta ignorada -> " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar jugadores: " + e.getMessage());
        }

        return lista;
    }
}
