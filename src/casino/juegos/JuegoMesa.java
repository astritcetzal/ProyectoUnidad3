package juegos; 

public abstract class JuegoMesa implements Jugable {

    protected String nombre;
    protected Jugador jugadorActual;
    protected double apuestaMinima, apuestaMaxima;
    protected boolean activo;

    public JuegoMesa(String nombre, Jugador jugadorActual, double apuestaMinima, double apuestaMaxima, boolean activo){
        if (nombre==null || nombre.isEmpty()){ throw new IllegalArgumentException("El juego no puede no tener nombre"); }
        if (apuestaMinima < 0){ throw new IllegalArgumentException("La apuesta no puede valer 0"); }
        if (apuestaMaxima <= apuestaMinima){ throw new IllegalArgumentException("La apuesta debe ser mayor a la apuesta mínia"); }

        this.nombre = nombre;
        this.jugadorActual = jugadorActual;
        this.apuestaMinima = apuestaMinima;
        this.apuestaMaxima = apuestaMaxima;
        this.activo = activo;


    }
    //NOMBRE
    public String getNombre(){ return nombre; }

    public void setNombre(String nombre){
        if (nombre==null || nombre.isEmpty()){ throw new IllegalArgumentException("El juego no puede no tener nombre"); }
        this.nombre = nombre;
    }

    //APUESTAMINIMA
    public double getApuestaMinima(){ return apuestaMinima; }

    public void setApuestaMinima(double apuestaMinima){
        if (apuestaMinima < 0){ throw new IllegalArgumentException("Tiene q ser mayor a 0");}
    }

    //APUESTAMAXIMA
    public double getApuestamaxima(){ return apuestaMaxima; }

    public void setApuestaMaxima(double apuestaMaxima){
        if (apuestaMaxima <= this.apuestaMinima){ throw new IllegalArgumentException("No puede ser menor que la apuesta mínima"); }
        this.apuestaMaxima = apuestaMaxima;
    }

    //JUGADORACTUAL
    public Jugador getJugadorActual(){ return jugadorActual; }

    public void setJugadoreActual(Jugador jugadorActual){ this.jugadorActual = jugadorActual; }

    //ACTIVO
    public boolean isActivo(){ return activo; }

    public void setActivo(boolean activo){ this.activo = activo; }

    //VALIDAR
    public boolean validarApuesta(double monto){
        return monto >= apuestaMinima && monto <= apuestaMaxima;
    }

    //INTERFACE
    @Override
    public abstract void iniciar(Jugador j);

    @Override
    public abstract void jugar();

    @Override
    public abstract void terminar();
    
    //@Override
    //public String getNombre(){} //Este siento q está demás
 
    public String toString(){
        return "Jugador: "+nombre+"\n Apuesta mínima: "+apuestaMinima+"  &  Apuesta máxima: "+apuestaMaxima+"\n Estado de juego: "+activo;
    }

}
