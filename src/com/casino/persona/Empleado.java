

import juegos.JuegoMesa;
public class Empleado extends Persona {
    private String cargo;
    private double salario;
    private JuegoMesa mesaAsignada;

    public Empleado(String nombre, String apellido, String cedula, int edad, String cargo, double salario) {
        super(nombre, apellido, cedula, edad);
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

    /*public String getMesaAsignada(JuegoMesa mesa) {
        return mesaAsignada;
    }*/

    public void setMesaAsignada(JuegoMesa mesa) {
        this.mesaAsignada = mesa;
    }

    public void supervisarMesa() {

    }

    @Override
    public String toString() {
        return "Empleado: " + getNombre() + " " + getApellido() + " - Cargo: " + cargo;
    }

    @Override
    public String getRol() {
        return "Rol: Empleado";
    }

}
