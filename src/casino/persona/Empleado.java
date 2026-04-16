package persona;

public class Empleado extends Persona {
    private String cargo;
    private double salario;
    private String cedula;

    public Empleado(String nombre, String apellido, String cedula, int edad, String cargo, double salario) {
        super(nombre, apellido, edad);
        if (cedula == null || cedula.isEmpty())
        {throw new IllegalArgumentException("Datos como cédula no puede estar vacío o nulo");}
        if (cargo == null || cargo.isEmpty()) { throw new IllegalArgumentException("El empleado debe tener cargo");}
        if (salario < 0) {
            throw new IllegalArgumentException("El salario no puede ser menor a 0");
        }
        this.cedula = cedula;
        this.cargo = cargo;
        this.salario = salario;
    }


    public String toCSV(){
        //(String nombre, String apellido, String cedula, int edad, String cargo, double salario) 
        StringBuilder sb = new StringBuilder();
        sb.append(getNombre()).append(",").append(getApellido()).append(",").append(getCedula()).append(",").append(getEdad()).append(",").append(getCargo()).append(",").append(getSalario());
        return sb.toString();
    }
    
    public static Empleado fromCSV(String linea){
        String[] partes = linea.split(",");

        if (partes.length!=6){throw new IllegalArgumentException("Linea invalida, se esperan al menos 2 campos: " + linea);}
   
        try {
            int edadParseada = Integer.parseInt(partes[3]);
            double salarioedadParseada = Double.parseDouble(partes[5]);
            return new Empleado(partes[0].trim(), partes[1].trim(), partes[2].trim(), edadParseada, partes[4].trim(), salarioedadParseada);
        } catch (NumberFormatException e){
            System.out.println("Error al leer los datos del empleado " + e.getMessage());
            throw new IllegalArgumentException("Dato numerico invalido");
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getCedula(){
        return cedula;
    }
    public void setCedula(String cedula){
        if (cedula == null || cedula.isEmpty()){
            throw new IllegalArgumentException("Datos como cédula no pueden estar vacios o nulo");
        }
        this.cedula= cedula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    


    @Override
    public String toString() {
        return "Empleado: " + getNombre() + " " + getApellido() + " - Cargo: " + getCargo() +" - Edad: " + getEdad();
    }

    @Override
    public String getRol() {
        return "Empleado";
    }
}
