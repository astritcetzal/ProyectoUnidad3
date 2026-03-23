package persona;

import juegos.JuegoMesa;

public class Empleado extends Persona {
    private String cargo;
    private double salario;
    private JuegoMesa mesaAsignada;

    public Empleado(String nombre, String apellido, String cedula, int edad, String cargo, double salario) {
        super(nombre, apellido, cedula, edad);
        if (cargo == null || cargo.isEmpty()) {
            throw new IllegalArgumentException("El empleado debe tener cargo");
        }
        if (salario < 0) {
            throw new IllegalArgumentException("El salario no puede ser menor a 0");
        }
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public JuegoMesa getMesaAsignada() {
        return mesaAsignada;
    }

    public void setMesaAsignada(JuegoMesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("Debe asignar mesa");
        }
        this.mesaAsignada = mesa;
    }

    public void supervisarMesa() {
        if (this.mesaAsignada != null) {
            System.out.println("El empleado " + this.getNombre() + " (" + this.cargo + ") está supervisando la mesa: "
                    + this.mesaAsignada.getNombre());
        } else {
            System.out.println("El empleado " + this.getNombre() + " actualmente no tienen ninguna mesa asignada");
        }
    }

    @Override
    public String toString() {
        return "Empleado: " + getNombre() + " " + getApellido() + " - Cargo: " + cargo;
    }

    @Override
    public String getRol() {
        return "Empleado";
    }

    public String toCSV() {
        return getCedula() + "," + getNombre() + "," + getApellido() + "," +
           getEdad() + "," + getCargo() + "," + getSalario();
    }

    public static Empleado fromCSV(String linea) {
        String[] p = linea.split(",");
        if (p.length < 6) throw new IllegalArgumentException("Línea CSV inválida: " + linea);
        return new Empleado(p[1], p[2], p[0], Integer.parseInt(p[3].trim()),
                            p[4], Double.parseDouble(p[5].trim()));
    }

}
