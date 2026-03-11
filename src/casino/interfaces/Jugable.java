package interfaces;
import persona.Jugador;

public interface Jugable {

    public void iniciar(Jugador j);

    public void jugar();

    public void terminar();
    
    public String getNombre();
    
    
}
