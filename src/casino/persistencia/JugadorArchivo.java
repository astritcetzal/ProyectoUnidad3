package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import persona.Jugador;
import persona.JugadorVIP;
import repositorio.JugadorRepository;

public class JugadorArchivo implements JugadorRepository {
    private String rutaArchivo;

    public JugadorArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void guardar(List<Jugador> jugadores) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            pw.println("Nombre,Apellido,Cedula,Edad,Saldo,idJugador,NivelVIP,LimiteApuestaEspecial,PorcentajeBonus");
            for (Jugador j : jugadores) {
                pw.println(j.toCSV());
            }
        }
    }

    @Override
    public List<Jugador> cargar() throws IOException {
        List<Jugador> jugadores = new ArrayList<>();
        File archivoJugador = new File(rutaArchivo);
        if (!archivoJugador.exists()) {
            return jugadores;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                if (!linea.trim().isEmpty()) {
                    try {
                        // Detectar si es VIP o normal según la cantidad de columnas
                        String[] campos = linea.split(",");
                        if (campos.length >= 9) {
                            // Es un JugadorVIP (tiene 9 columnas)
                            jugadores.add(JugadorVIP.fromCSV(linea));
                        } else {
                            // Es un Jugador normal (tiene 6 columnas)
                            jugadores.add(Jugador.fromCSV(linea));
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Linea ignorada: " + e.getMessage());
                    }
                }
            }
        }
        return jugadores;
    }
}
