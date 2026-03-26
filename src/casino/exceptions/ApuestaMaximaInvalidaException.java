package exceptions;

public class ApuestaMaximaInvalidaException extends Exception {
    private String apuestaMaxima;


    public ApuestaMaximaInvalidaException(String apuestaMaxima) {
        super("La apuesta mínima no puede ser menor que 100.00. Tú apuesta:"+apuestaMaxima);
        this.apuestaMaxima = apuestaMaxima;
    }
    
}
