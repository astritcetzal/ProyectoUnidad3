package sistema;

import exceptions.ApuestaInvalidaRuletaException;
import exceptions.IDJugadorDuplicadoException;
import exceptions.JuegoInactivoRuletaException;
import exceptions.SaldoInsuficienteException;
import juegos.BlackJack;
import juegos.Ruleta;
import persona.Empleado;
import persona.Jugador;
import persona.JugadorVIP;
import persona.Persona;
import servicio.CasinoService;

public class Main {
    public static void main(String[] args) throws JuegoInactivoRuletaException, ApuestaInvalidaRuletaException {
        Casino casino = new Casino("La Cima");
        CasinoService servicio = new CasinoService(casino);
        Persona p1 = new Jugador("Gem", "Martin", "CED-001", 18, 300.0, "JUG-001");
        Persona p2 = new Jugador("Pucca", "Gaston", "CED-001", 20, 100.000, "JUG-001");
        Persona pVIP = new JugadorVIP("Blair", "Waldorf", "CED-002", 22, 5000.0, "JUG-VIP1", "Oro", 2000.0, 15.0);
        Persona pEmpleado = new Empleado("Carlos", "Gomez", "EMP-001", 35, "Crupier", 1500.0);

        System.out.println("Personas creadas exitosamente en memoria.");
        System.out.println(p1.getNombre() + " es: " + p1.getRol());
        System.out.println(pVIP.getNombre() + " es: " + pVIP.getRol());
        System.out.println(pEmpleado.getNombre() + " es: " + pEmpleado.getRol());

        try {
            servicio.agregarJugador((Jugador) p1);
            System.out.println("ÉXITO: Jugador " + p1.getNombre() + " agregado al servicio.");

            servicio.agregarJugador((Jugador) pVIP);
            System.out.println("ÉXITO: Jugador " + pVIP.getNombre() + " agregado al servicio.");

            servicio.agregarJugador((Jugador) p2);
            System.out.println("ÉXITO: Jugador " + p1.getNombre() + " agregado al servicio.");

            System.out.println("\nIntentando agregar un jugador con ID duplicado (JUG-001)...");
            Jugador jDuplicado = new Jugador("Clon", "Falso", "CED-003", 25, 500.0, "JUG-001");

            servicio.agregarJugador(jDuplicado);

            System.out.println("El sistema dejó pasar un ID duplicado.");

        } catch (IDJugadorDuplicadoException e) {
            System.out.println("El id esta duplicado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        }

        try {
            // Creando juegos a través del servicio (Apuesta mínima de 150 y 200 para pasar
            // la validación de >100)
            Ruleta mesaRuleta = servicio.agregarRuleta("Ruleta Europea", "JUG-001", 150.0, 2000.0);
            BlackJack mesaBJ = servicio.agregarBlackJack("BlackJack Clásico", "JUG-VIP1", 200.0, 5000.0);

            // --- JUGANDO RULETA ---
            System.out.println("\n>>> SIMULANDO PARTIDA DE RULETA <<<");
            mesaRuleta.iniciar((Jugador) p1);

            double montoApostar = 200.0;
            System.out.println(p1.getNombre() + " apuesta $" + montoApostar + " al Rojo, número 15.");
            mesaRuleta.setApuesta(15, "Rojo", montoApostar);

            mesaRuleta.jugar();
            mesaRuleta.terminar();

            // --- JUGANDO BLACKJACK ---
            System.out.println("\n>>> SIMULANDO PARTIDA DE BLACKJACK <<<");
            mesaBJ.iniciar((Jugador) pVIP);
            mesaBJ.jugar();

        } catch (IllegalArgumentException | IllegalStateException | SaldoInsuficienteException e) {
            System.out.println("Error en la configuración o ejecución de las mesas: " + e.getMessage());
        }

        JugadorVIP vipReal = (JugadorVIP) pVIP;
        System.out.println("Saldo de " + vipReal.getNombre() + " antes del bonus: $" + vipReal.getSaldo());
        vipReal.aplicarBonus();

    }
}