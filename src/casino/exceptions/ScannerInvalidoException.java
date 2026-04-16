package exceptions;

public class ScannerInvalidoException extends Exception {

    int numero;

    public ScannerInvalidoException(int numero) {
        super("Apuesta inválida. El dato ingresado debe ser un numero");
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

}
