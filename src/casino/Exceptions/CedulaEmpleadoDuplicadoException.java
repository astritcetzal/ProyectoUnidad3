package exceptions;

public lass CedulaEmpleadoDuplicadoException extends Exception{
private String cedula;
public CedulaEmpleadoDuplicadoException (String cedula){
    super("La cedula del empleado ya está registrada: "  + cedula);
    this.cedula = cedula;
}
    public String getCedula(){
        return cedula;
    }

}
