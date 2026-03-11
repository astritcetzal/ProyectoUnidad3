public class Jugador extends Persona {
    
    private double saldo;
    private int puntosLealtad;
    private String idJugador;

    public Jugador(String nombre, String apellido, String cedula, int edad, double saldo, String idJugador){
        super(nombre, apellido, cedula, edad);
        this.saldo = saldo;
        this.puntosLealtad = 0;
        this.idJugador = idJugador;
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
               "\n Cédula = " + getCedula() +
               "\n Edad = " + getEdad() +
               "\n Saldo = " + saldo +
               "\n Puntos de Lealtad = " + puntosLealtad +
               "\n ID del Jugador = " + idJugador;
    }

    @Override

    public String getRol(){
        return "Jugador";
    }
}
