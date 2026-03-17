package Exceptions;
public class IDJugadorDuplicadoException extends Exception{
private String id;
    public IDJugadorDuplicadoException (String id){
    super("Un jugdor ya tiene registrado este id: "  + id);
    this.id = id;
}
    public String getIdJugador(){
        return id;
    }

}
