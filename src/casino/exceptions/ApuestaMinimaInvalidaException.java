package exceptions;
public class ApuestaMinimaInvalidaException extends Exception {
    private String apuestaMinima;
    public ApuestaMinimaInvalidaException(String apuestaMinima) {
        super("La apuesta mínima no puede ser menor que 100.00. Tú apuesta:"+apuestaMinima);
        this.apuestaMinima = apuestaMinima;
    }
}