

public class JugadorVIP extends Jugador {

    private String nivelVIP;
    private double limiteApuestaEspecial;
    private double porcentajeBonus;

    public JugadorVIP(String nombre, String apellido, String cedula, int edad, 
                      double saldo, String idJugador, String nivelVIP, 
                      double limiteApuestaEspecial, double porcentajeBonus) {
        super(nombre, apellido, cedula, edad, saldo, idJugador);
        this.nivelVIP = nivelVIP;
        this.limiteApuestaEspecial = limiteApuestaEspecial;
        this.porcentajeBonus = porcentajeBonus;
    }

    public String getNivelVIP() {
        return nivelVIP;
    }

    public void setNivelVIP(String nivelVIP) {
        this.nivelVIP = nivelVIP;
    }

    public double getLimiteApuestaEspecial() {
        return limiteApuestaEspecial;
    }

    public void setLimiteApuestaEspecial(double limite) {
        this.limiteApuestaEspecial = limite;
    }

    public double getPorcentajeBonus() {
        return porcentajeBonus;
        
    }

    public void setPorcentajeBonus(double porcentaje) {
        this.porcentajeBonus = porcentaje;
    }

    public void aplicarBonus(){
        double bonus = getSaldo() * (porcentajeBonus/100);
        setSaldo(getSaldo() + bonus);
        System.out.println("El bonus de: $ " + bonus + 
                        "fue aplicado correctamente. Su nuevo saldo es de: $ " + getSaldo());
    }

    @Override
    public void apostar(double monto){
        if(monto > 0 && monto <= limiteApuestaEspecial && monto <= getSaldo()){
            setSaldo(getSaldo() - monto);
            System.out.println("VIP: La apuesta realizada fue de: $ " + monto + ". El saldo restante es: $ " + getSaldo());
        }else{
            System.out.println("VIP: La apuesta no pudo realizarse porque su saldo es insuficiente o no supera el límite VIP.");
        }
    }

    @Override
    public String toString(){
        return "\n Nombre = " + getNombre() +
               "\n Apellido = " + getApellido() +
               "\n Cédula = " + getCedula() +
               "\n Edad = " + getEdad() +
               "\n Saldo = " + getSaldo() +
               "\n ID del Jugador = " + getIdJugador() +
               "\n Nivel VIP = " + getNivelVIP() +
               "\n Límite de Apuesta Especial = " + getLimiteApuestaEspecial() +
               "\n Porcentaje de Bonus = " + getPorcentajeBonus();
    }

    @Override
    public String getRol(){
        return "Jugador VIP";
    }


    
}
