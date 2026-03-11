import java.lang.annotation.Documented;
import juegos.JuegoMesa;
import java.util.ArrayList;
import java.util.List;
public class Casino {
    private String nombre;
    private List<Jugador> jugadores; //agrgacion
    private List<Empleado>empleados; //agregacion
    private List<JuegoMesa> juegos; //composicion
    private double cajaTotal;

    public Casino(String nombre){
        if (nombre == null || nombre.isEmpty())throw new IllegalArgumentException("El nombre no puede estar vacio");
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
        if (nombreRuleta == null || nombreRuleta.isEmpty())throw   new IllegalArgumentException("El nombre de la Ruleta no puede estar vacio");
        Ruleta nuevaRuleta = new Ruleta(nombreRuleta);
        this.juegos.add(nuevaRuleta);
    }

    public void agregarBlackJack  (String nombreBlackJack){
        if (nombreBlackJack == null || nombreBlackJack.isEmpty())throw   new IllegalArgumentException("El nombre del BlackJack no puede estar vacio");
        BlackJack nuevoBlackJack = new BlackJack (nombreBlackJack);
        this.juegos.add(nuevoBlackJack);
    }

    @Override
    public String toString(){
        return "Casino: " + nombre + "- Caja: $" + cajaTotal;
    }

 
}
