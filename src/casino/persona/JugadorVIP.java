package persona;


public class JugadorVIP extends Jugador {

    private String nivelVIP;
    private double limiteApuestaEspecial;
    private double porcentajeBonus;

    public JugadorVIP(String nombre, String apellido, int edad, 
                      double saldo, String idJugador, String nivelVIP, 
                      double limiteApuestaEspecial, double porcentajeBonus) {
        super(nombre, apellido, edad, saldo, idJugador);
        if(nivelVIP == null || nivelVIP.isEmpty()){
            throw new IllegalArgumentException("El nivel VIP no puede ser nulo.");
        }
        if(limiteApuestaEspecial <= 0){
            throw new IllegalArgumentException("El límite para la apuesta especial debe ser mayor que 0");
        }
        if(porcentajeBonus > 100 || porcentajeBonus < 0){
            throw new IllegalArgumentException("El porcentaje de bonus debe estar entre 0-100");
        }
        
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
                        " fue aplicado correctamente. Su nuevo saldo es de: $ " + getSaldo());
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

    @Override
    public String toCSV() {
        return new StringBuilder()
            .append(getNombre()).append(",")
            .append(getApellido()).append(",")
            .append(getEdad()).append(",")
            .append(getSaldo()).append(",")
            .append(getIdJugador()).append(",")
            .append(getNivelVIP()).append(",")
            .append(getLimiteApuestaEspecial()).append(",")
            .append(getPorcentajeBonus())
            .toString();
    }

    public static JugadorVIP fromCSV(String linea) {
        try {
            String[] p = linea.split(",");
            if (p.length < 8) throw new IllegalArgumentException("Línea CSV inválida para JugadorVIP: " + linea);
            return new JugadorVIP(
                p[0],                               // nombre
                p[1],                               // apellido
                Integer.parseInt(p[2].trim()),      // edad
                Double.parseDouble(p[3].trim()),    // saldo
                p[4],                               // idJugador
                p[5],                               // nivelVIP
                Double.parseDouble(p[6].trim()),    // limiteApuestaEspecial
                Double.parseDouble(p[7].trim())     // porcentajeBonus
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al parsear JugadorVIP desde CSV: " + e.getMessage());
        }
    }
}
