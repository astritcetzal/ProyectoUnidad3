package exceptions;

public class ApuestaInvalidaRuletaException extends Exception {
    private double minimoMesa;
    private double maximoMesa;

    public ApuestaInvalidaRuletaException(double minimoMesa, double maximoMesa) {
        super("Apuesta inválida. El monto debe estar entre " + minimoMesa + " y " + maximoMesa);
        this.minimoMesa = minimoMesa;
        this.maximoMesa = maximoMesa;
    }

    public double getMinimoMesa() {
        return minimoMesa;
    }

    public double getMaximoMesa() {
        return maximoMesa;
    }

}
