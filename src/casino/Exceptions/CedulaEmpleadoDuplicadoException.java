package exceptions;

public class CedulaEmpleadoDuplicadoException extends Exception{
private String cedula;
public CedulaEmpleadoDuplicadoException (String cedula){
    super("La cédula del empleado ya está registrada: "  + cedula);
    this.cedula = cedula;
}
    public String getCedula(){
        return cedula;
    }

}
