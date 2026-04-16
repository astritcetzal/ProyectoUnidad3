package modelo;
import interfaces.Apostable;


public class Jugador extends Persona implements Apostable {
    
    private double saldo;
    private int puntosLealtad;
    private String idJugador;

    public Jugador(String nombre, String apellido, int edad, double saldo, String idJugador){
        super(nombre, apellido, edad);
        if(saldo < 0){
            throw new IllegalArgumentException("El saldo debe ser mayor que 0. No hay saldos negativos");
        }
        if(idJugador == null || idJugador.isEmpty()){
            throw new IllegalArgumentException("El id del jugador no puede ser nulo");
        }
        
        this.saldo = saldo;
        this.puntosLealtad = 0;
        this.idJugador = idJugador.toUpperCase();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getPuntosLealtad() {
        return puntosLealtad;
    }

    public void setPuntosLealtad(int puntosLealtad) {
        this.puntosLealtad = puntosLealtad;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    @Override
    public void apostar(double monto){
        if(monto > 0 && monto <= saldo){
            saldo -= monto;
            System.out.println("Apuesta realizada. Monto apostado: $ " + monto + ". Saldo restante: " + saldo );
        }else{
            System.out.println("La apuesta no se pudo realizar porque el saldo es insuficiente.");
        }
    }

    public void recibirPago(double monto){
        if(monto > 0){
            saldo += monto;
            System.out.println("Pago recibido. Su saldo actual es de: $ " + saldo);
        }
    }

    @Override

    public String toString(){
        return "Nombre = " + getNombre() +
               "\n Apellido = " + getApellido() +
               "\n Edad = " + getEdad() +
               "\n Saldo = " + saldo +
               "\n Puntos de Lealtad = " + puntosLealtad +
               "\n ID del Jugador = " + idJugador;
    }

    @Override

    public String getRol(){
        return "Jugador";
    }

    public String toCSV() {
    return new StringBuilder()
        .append(getNombre()).append(",")
        .append(getApellido()).append(",")
        .append(getEdad()).append(",")
        .append(getSaldo()).append(",")
        .append(getIdJugador())
        .toString();
    }

    public static Jugador fromCSV(String linea) {
        try{
            String[] p = linea.split(",");
            if (p.length < 5) throw new IllegalArgumentException("Línea CSV inválida: " + linea);
            return new Jugador(p[0], p[1], Integer.parseInt(p[2].trim()),
                                Double.parseDouble(p[3].trim()), p[4]);
        }catch(NumberFormatException e){return null;}
    }


}
