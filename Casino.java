import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;
public class Casino {
    private String nombre;
    private List<Jugador> jugadores; //agrgacion
    private List<Empleado>empleados; //agregacion
    private List<JuegoMesa> juegos; //composicion
    private double cajaTotal;

    public Casino(String nombre){
        this.nombre = nombre;
        this.jugadores= new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.juegos=new ArrayList<>();

    }
    
    public String getNombre(){
        return nombre;
    }

    public double getCajaTotal(){
        return cajaTotal;
    }

    public void setCajaTotal(double caja){
        this.cajaTotal = caja;
    }

    public void registrarJugador(Jugador j){
        jugadores.add(j);
    }


    public void agregarEmpleado(Empleado e){
        empleados.add(e);
    }
    
    //composicion 
    public void agregarRuleta (String nombreRuleta){
        Ruleta nuevaRuleta = new Ruleta(nombreRuleta);
        this.juegos.add(nuevaRuleta);
    }

    public void agregarBlackJack  (String nombreBlackJack){
        BlackJack nuevoBlackJack = new BlackJack (nombreBlackJack);
        this.juegos.add(nuevoBlackJack);
    }

    @Override
    public String toString(){
        return "Casino: " + nombre + "- Caja: $" + cajaTotal;
    }

 
}
