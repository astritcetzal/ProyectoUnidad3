package archivos;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import persona.Empleado;

public class EmpleadoArchivo {
    private static final String NOMBREARCHIVO = "empleado.csv";

   
    public void guardar(List<Empleado> empleados){
        try(PrintWriter pw = new PrintWriter(new FileWriter(NOMBREARCHIVO))){
            pw.println(",,,,,");
            for (Empleado empl: empleados){
                pw.println(empl.toCSV());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }



  
    public List<Empleado> cargar() {
        List<Empleado> empleados = new ArrayList<>();
        File archivo = new File(NOMBREARCHIVO);
        if (!archivo.exists()){
            return empleados;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(NOMBREARCHIVO))){
            String linea;
            boolean primeraLinea = true;
            while ((linea= br.readLine()) != null){
                if (primeraLinea){
                    primeraLinea = false;
                    continue;
                }
                if (!linea.trim().isEmpty()){
                    try {
                        empleados.add(Empleado.fromCSV(linea));
                    } catch(IllegalArgumentException e){
                        System.out.println("Linea ignorada: " + e.getMessage());
                    }
                }
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return empleados;
    }
}
