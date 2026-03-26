package repositorio;

import java.io.IOException;
import java.util.List;

import persona.Empleado;

public interface PersonaRepository {
    void guardar(List<Empleado> empleados) throws IOException;

    List<Empleado> cargar() throws IOException;

}
