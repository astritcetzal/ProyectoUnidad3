package persona;

public abstract class Persona {

    private String nombre, apellido, cedula;
    private int edad;

    public Persona(String nombre, String apellido, String cedula, int edad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos como Nombre no puede estar vacío o nulo");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos como Apellido no puede estar vacío o nulo");
        }
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos como Cédula no puede estar vacío o nulo");
        }
        if (edad < 18) {
            throw new IllegalArgumentException("Debes ser mayor de edad para entrar a un Casino");
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.edad = edad;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos como Nombre no puede estar vacío o nulo");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos como Apellido no puede estar vacío o nulo");
        }
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos como Cédula no puede estar vacío o nulo");
        }
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 18) {
            throw new IllegalArgumentException("Debes ser mayor de edad para entrar a un Casino");
        }
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona: nombre = " + nombre + ", apellido: " + apellido + ", cedula: " + cedula + ", edad: " + edad;
    }

    public abstract String getRol();

}
