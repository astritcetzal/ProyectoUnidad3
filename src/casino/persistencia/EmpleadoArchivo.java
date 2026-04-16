package persistencia;

import java.util.ArrayList;
import java.util.List;

import modelo.Empleado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import repositorio.PersonaRepository;

public class EmpleadoArchivo implements PersonaRepository {
    private String rutaArchivo;

    public EmpleadoArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void guardar(List<Empleado> empleados) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            pw.println("Nombre,Apellido,Cedula,Edad,Cargo,Salario");
            for (Empleado empl : empleados) {
                pw.println(empl.toCSV());
            }
        }
    }

    @Override
    public List<Empleado> cargar() throws IOException {
        List<Empleado> empleados = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) { return empleados; }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                if (!linea.trim().isEmpty()) {
                    try {
                        empleados.add(Empleado.fromCSV(linea));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Linea ignorada: " + e.getMessage());
                    }
                }
            }
        }
        return empleados;
    }
}
