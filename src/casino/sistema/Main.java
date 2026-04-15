package sistema;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import exceptions.ApuestaInvalidaRuletaException;
import exceptions.JuegoInactivoRuletaException;
import exceptions.SaldoInsuficienteException;
import exceptions.ApuestaMinimaInvalidaException;
import exceptions.CedulaEmpleadoDuplicadoException;
import exceptions.IDJugadorDuplicadoException;
import exceptions.JuegoInactivoException;
import exceptions.ApuestaMaximaInvalidaException;
import juegos.BlackJack;
import juegos.Ruleta;
import persona.Empleado;
import persona.Jugador;
import persona.JugadorVIP;
import repositorio.JugadorRepository;
import repositorio.PersonaRepository;
import servicio.EmpleadoService;
import servicio.JugadorService;
import persistencia.EmpleadoArchivo;
import persistencia.JugadorArchivo;

public class Main { 
    public static void main(String[] args) throws IOException, JuegoInactivoException {
        Scanner sc = new Scanner(System.in);
        Casino casino = new Casino("La Cima");

        try{
            EmpleadoService empleadoService = new EmpleadoService(new EmpleadoArchivo("empleados.csv"));
            JugadorService jugadorService = new JugadorService(new JugadorArchivo("jugadores.cvs"));

            int opcion;
            do{
                System.out.println("\n~~ SISTEMA CASINO: " + casino.getNombre().toUpperCase() + " ~~\n");
                System.out.println("1.- Registrar Empleado");
                System.out.println("2.- Registrar Jugador");
                System.out.println("3.- Buscar jugador");
                System.out.println("4.- Ver jugadores VIP"); // filtrado
                System.out.println("5.- Eliminar empleado"); //Iterator
                System.out.println("6.- Salir y guarfar");
                System.out.print("\nSeleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:

                    case 2:

                    case 3:

                    case 4:

                    case 5:

                    case 6:

                    default:

                    
                        
                }

        }
    }

}