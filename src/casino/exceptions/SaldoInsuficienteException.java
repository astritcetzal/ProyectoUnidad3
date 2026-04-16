package exceptions;

public class SaldoInsuficienteException extends Exception {
    private double saldoActual;
    private double montoRequerido;

    public SaldoInsuficienteException(double saldoActual, double montoRequerido) {
        super("Saldo insuficiente para jugar. Tienes: " + saldoActual + " | Necesitas: " + montoRequerido
                + " como mínimo.");
        this.saldoActual = saldoActual;
        this.montoRequerido = montoRequerido;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public double getMontoRequerido() {
        return montoRequerido;
    }
}
