package interfaces;
public interface Apostable {
    void apostar(double monto);
    void recibirPago(double monto);
    double getSaldo();
}