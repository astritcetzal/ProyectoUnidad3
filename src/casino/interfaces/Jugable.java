package interfaces;
import exceptions.SaldoInsuficienteException;
import persona.Jugador;
public interface Jugable {

    public void iniciar(Jugador j) throws SaldoInsuficienteException;

    public void jugar();

    public void terminar();
    
    public String getNombre();
    
    

    
}