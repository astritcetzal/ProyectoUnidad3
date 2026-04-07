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
    public String toCSV(){
        //(String nombre, String apellido, String cedula, int edad, String cargo, double salario) 
        StringBuilder sb = new StringBuilder();
        sb.append(getNombre()).append(",").append(getApellido()).append(",").append(getCedula()).append(",").append(getEdad()).append(",").append(getCargo()).append(",").append(getSalario());
        return sb.toString();
    }
    public static Empleado fromCSV(String linea){
        String[] partes = linea.split(",");
        if (partes.length!=6){
            throw new IllegalArgumentException("Linea invalida, se esperan al menos 2 campos: " + linea);
        }
   
        try {
        int edadParseada = Integer.parseInt(partes[3]);
        double salarioedadParseada = Double.parseDouble(partes[5]);
        Empleado empl = new Empleado(partes[0], partes[1], partes[2], edadParseada, partes[4], salarioedadParseada );
        return empl;
        } catch (NumberFormatException e){
            System.out.println("Error al leer los datos del empleado " + e.getMessage());
            throw new IllegalArgumentException();
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
}
