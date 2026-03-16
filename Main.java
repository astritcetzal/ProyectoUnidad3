
import Sistema;
import servicio.CasinoService;
import juegos.BlackJack;
import juegos.Ruleta;
import persona.Jugador;
import persona.JugadorVIP;
import persona.Empleado;
import Sistema.Casino;

public class Main {

    public static void main(String[] args) {

        Casino casino = new Casino("El Royal");

        Jugador juan = new Jugador("Juan", "Pérez", "123456", 30, 5000.0, "J001");
        JugadorVIP maria = new JugadorVIP("María", "Gómez", "789012", 28, 20000.0, "VIP001", "Gold", 15000.0, 10.0);
        Empleado empleado = new Empleado("Carlos", "López", "111222", 40, "Dealer", 3000.0);

        casino.registrarJugador(juan);
        casino.registrarJugador(maria);
        casino.agregarEmpleado(empleado);

        System.out.println(juan);
        System.out.println(maria);
        System.out.println(empleado);
        System.out.println(casino);

        Ruleta ruleta = casino.agregarRuleta("Ruleta VIP", juan, 100.0, 5000.0, false);
        ruleta.iniciar(juan);
        ruleta.setApuesta(7, "Rojo", 500.0);
        ruleta.jugar();
        ruleta.terminar();

        maria.aplicarBonus();
        BlackJack bj = casino.agregarBlackJack("Mesa BlackJack 1", maria, 100.0, 10000.0, false);
        bj.iniciar(maria);
        bj.jugar();

        empleado.supervisarMesa();
    }
}
