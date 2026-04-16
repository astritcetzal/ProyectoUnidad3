package modelo;

public abstract class Persona {

    private String nombre, apellido;
    private int edad;

    public Persona(String nombre, String apellido, int edad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos como Nombre no puede estar vacío o nulo");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos como Apellido no puede estar vacío o nulo");
        }

        if (edad < 18) {
            throw new IllegalArgumentException("Debes ser mayor de edad para entrar a un Casino");
        }
        this.nombre = nombre;
        this.apellido = apellido;
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
        return "Persona: nombre = " + nombre + ", apellido: " + apellido + ", edad: " + edad;
    }

    public abstract String getRol();

}
