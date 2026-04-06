package servicio;

import java.util.List;
import persona.Empleado;
import java.io.IOException;
import repositorio.PersonaRepository;
import exceptions.CedulaEmpleadoDuplicadoException;
import juegos.BlackJack;

import java.util.ArrayList;
import java.util.Iterator;

public class EmpleadoService {
    private PersonaRepository repositorio;
    private List<Empleado> empleados = new ArrayList<>();

    public EmpleadoService(PersonaRepository repositorio) throws IOException {
        this.repositorio = repositorio;
        this.empleados = repositorio.cargar();
    }

    public void agregarEmpleado(Empleado empleado) throws CedulaEmpleadoDuplicadoException, IOException {
        if (empleado == null)
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        for (Empleado e : empleados) {
            if (e.getCedula().equals(empleado.getCedula())) {
                throw new CedulaEmpleadoDuplicadoException(
                        "El empleado con la cédula " + empleado.getCedula() + " ya existe");
            }
        }
        empleados.add(empleado);
        repositorio.guardar(empleados);

    }

    public Empleado buscarEmpleado(String cedula) {
        for (Empleado e : empleados)
            if (e.getCedula().equals(cedula)) {
                return e;
            }
        return null;
    }

    public List<Empleado> filtrarPorCargo(String cargo) {
        List<Empleado> resultado = new ArrayList<>();

        for (Empleado e : empleados)
            if (e.getCargo().equalsIgnoreCase(cargo)) {
                resultado.add(e);
            }
        return resultado;
    }

    public void eliminarEmpleado(String cedula) throws IOException {
        Iterator<Empleado> iterator = empleados.iterator();
        while (iterator.hasNext()) {
            Empleado e = iterator.next();
            if (e.getCedula().equals(cedula)) {
                iterator.remove();
                return;
            }
        }
        repositorio.guardar(empleados);
    }
}
