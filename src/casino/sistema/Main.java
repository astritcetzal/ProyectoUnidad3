package sistema;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import exceptions.JuegoInactivoRuletaException;
import exceptions.SaldoInsuficienteException;
import exceptions.ApuestaMinimaInvalidaException;
import exceptions.CedulaEmpleadoDuplicadoException;
import exceptions.IDJugadorDuplicadoException;
import exceptions.JuegoInactivoException;
import exceptions.ApuestaMaximaInvalidaException;
import juegos.BlackJack;
import juegos.Ruleta;
import modelo.Empleado;
import modelo.Jugador;
import modelo.JugadorVIP;
import servicio.EmpleadoService;
import servicio.JugadorService;
import persistencia.EmpleadoArchivo;
import persistencia.JugadorArchivo;

public class Main { 
    public static void main(String[] args) throws IOException, JuegoInactivoException, SaldoInsuficienteException, ApuestaMaximaInvalidaException, JuegoInactivoRuletaException {
        Scanner sc = new Scanner(System.in);
        Casino casino = new Casino("La Cima");

        try{
            EmpleadoService empleadoService = new EmpleadoService(new EmpleadoArchivo("empleados.csv"));
            JugadorService jugadorService = new JugadorService(new JugadorArchivo("jugadores.csv"));

            int opcion;
            do{
                System.out.println("\n~~ SISTEMA CASINO: " + casino.getNombre().toUpperCase() + " ~~\n");
                System.out.println("1. Registrar Jugador");
                System.out.println("2. Registrar Jugador VIP");
                System.out.println("3. Registrar Empleado");
                System.out.println("4. Buscar jugador");
                System.out.println("5. Jugar a la ruleta");
                System.out.println("6. Jugar a BlackJack");
                System.out.println("7. Ver jugadores VIP"); // filtrado
                System.out.println("8. Eliminar jugador");
                System.out.println("9. Eliminar empleado"); //Iterator
                System.out.println("10. Salir y guardar");
                System.out.print("\nSeleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1: //Registrar Jugador
                        boolean registroexitoso = false;
                        while (!registroexitoso) {
                            try {
                                System.out.println("Nombre: "); String nomJ = sc.nextLine();
                        System.out.println("Apellido: "); String apeJ = sc.nextLine();
                        System.out.println("Edad: "); int edadJ = sc.nextInt(); sc.nextLine();
                        System.out.println("ID jugador (ej. JUG-001): "); String id = sc.nextLine();
                        System.out.println("Saldo con el que inicia: "); double saldo = sc.nextDouble(); sc.nextLine();
                        
                        Jugador nuevoJugador = new Jugador(nomJ, apeJ, edadJ, saldo, id);
                        jugadorService.agregarJugador(nuevoJugador);
                        System.out.println("El jugador se registró correctamente!");
                        registroexitoso = true;
                            } catch (IDJugadorDuplicadoException | IllegalArgumentException e) {
                                System.out.println("\n ERROR: " + e.getMessage());
                                System.out.println("Por favor, ingresa los datos correctamente otra vez.");
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("\n ERROR: Debes ingresar un valor numérico válido.");
                                sc.nextLine();
                            }   
                        }
                        break;
                        
                    case 2: //Registrar Jugador VIP
                        boolean registroexitosovip = false;
                        while (!registroexitosovip) {
                            try {
                            System.out.println("Nombre: "); String nomV = sc.nextLine();
                            System.out.println("Apellido: "); String apeV = sc.nextLine();
                            System.out.println("Edad: "); int edadV = sc.nextInt(); sc.nextLine();
                            System.out.println("Nivel VIP (Bronce, Plata, Oro): "); String nivelVIP = sc.nextLine();
                            System.out.println("ID jugador (ej. JUG-VIP1): "); String idV = sc.nextLine();
                            System.out.println("Saldo con el que inicia: "); double saldoV = sc.nextDouble(); sc.nextLine();
                            
                            JugadorVIP nuevoVIP = new JugadorVIP(nomV, apeV, edadV, saldoV, idV, nivelVIP, 1000.0, 10.0);
                            jugadorService.agregarJugador(nuevoVIP);
                            System.out.println("El jugador VIP se registró correctamente!");
                            registroexitosovip = true;
                            } catch (IDJugadorDuplicadoException | IllegalArgumentException e) {
                                System.out.println("\n ERROR: " + e.getMessage());
                                System.out.println("Por favor, ingresa los datos correctamente otra vez.");
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("\n ERROR: Debes ingresar un valor numérico válido.");
                                sc.nextLine();
                            }
                        }
                        break;

                    case 3: //Registrar Empleado
                            boolean registroExitosoEmp = false;
                        while (!registroExitosoEmp) {
                            try {
                            System.out.println("Nombre: "); String nomE = sc.nextLine();
                            System.out.println("Apellido: "); String apeE = sc.nextLine();
                            System.out.println("Edad: "); int edadE = sc.nextInt(); sc.nextLine();
                            System.out.println("Cédula (ej. EMP-001): "); String cedE = sc.nextLine();
                            System.out.println("Cargo: "); String cargo = sc.nextLine();
                            System.out.println("Salario: "); double salario= sc.nextDouble(); sc.nextLine();
                            
                            Empleado nuevoEmpleado = new Empleado(nomE, apeE, cedE, edadE, cargo, salario);
                            empleadoService.agregarEmpleado(nuevoEmpleado);
                            System.out.println("El empleado se registró en el sistema");
                            registroExitosoEmp = true;
                            } catch (CedulaEmpleadoDuplicadoException | IllegalArgumentException e) {
                                System.out.println("\n ERROR: " + e.getMessage());
                                System.out.println("Por favor, ingresa los datos correctamente otra vez.");
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("\n ERROR: Debes ingresar un valor numérico válido.");
                                sc.nextLine();
                            }
                        }
                        break; 

                    case 4: //Buscar jugador
                        System.out.println("ID que desea buscar"); String jugbuscar = sc.nextLine();
                        Jugador encontrado =  jugadorService.buscarJugador(jugbuscar);
                        System.out.println("El jugador fue encotrado: " + encontrado.getNombre());
                        break;

                    case 5: //Jugar a la ruleta
                        System.out.println("\n==== RULETA ====\n");
                        System.out.println("Ingresar el ID del jugador: "); String idruleta = sc.nextLine();

                        try{
                            Jugador jugaRuleta = jugadorService.buscarJugador(idruleta);
                            Ruleta mesaRuleta = casino.agregarRuleta("Ruleta Europea", jugaRuleta, 150, 200, true);
                            mesaRuleta.iniciar(jugaRuleta);
                            System.out.println("Escriba el monto que desea apostar: (mínimo $" + mesaRuleta.getApuestaMinima() + ")"); double montapuesta = sc.nextDouble(); sc.nextLine();
                            System.out.println("Número en el que desea apostar (0-36): "); int numero = sc.nextInt(); sc.nextLine();
                            System.out.println("Color (Rojo, Negro, Verde): "); String color = sc.nextLine();
                            mesaRuleta.setApuesta(numero, color, montapuesta);
                            mesaRuleta.jugar();
                            jugadorService.actualizarJugador();
                        } catch (ApuestaMinimaInvalidaException | ApuestaMaximaInvalidaException | IOException e){ 
                            System.out.println("Algo está fallando en la partida: " + e.getMessage());
                        }
                        break;
                        

                    case 6: // Jugar a BlackJack
                        System.out.println("\n==== BLACKJACK ====\n");
                        System.out.println("Ingresar el ID del jugador: "); String idbj = sc.nextLine();

                        try{
                            Jugador jugBJ = jugadorService.buscarJugador(idbj);
                            BlackJack mesaBJ = casino.agregarBlackJack("BlackJack Clásico", jugBJ, 200.0, 5000.0, true);
                            mesaBJ.iniciar(jugBJ);
                            mesaBJ.jugar();
                            jugadorService.actualizarJugador();
                        } catch ( ApuestaMinimaInvalidaException | SaldoInsuficienteException | IOException e) { 
                            System.out.println("Algo está fallando en la partida: " + e.getMessage());
                        }
                        break;

                    case 7: // Ver jugadores VIP
                        List <Jugador> vips = jugadorService.filtrarVIP();
                        System.out.println("----- Jugadores Vip -----");
                        vips.forEach(v -> System.out.println(v.getNombre()));
                        break;

                    case 8: //Eliminar jugador
                        System.out.println("Ingresa el ID del jugador para eliminar: "); String idbyebye = sc.nextLine();
                        jugadorService.eliminarJugador(idbyebye);
                        System.out.println("El ha sido eliminado exitosamente!!!");
                        break;

                    case 9: // Eliminar empleado
                        System.out.println("Cédula de empleado para elmininar: "); String ceeliminar = sc.nextLine();
                        empleadoService.eliminarEmpleado(ceeliminar);
                        System.out.println("El empleado fue eliminado del sistema.");
                        break;

                    case 10: // Salir y guardar
                        System.out.println("Cerrando el sistema ...");
                        break;

                    default:
                        System.out.println("Opción invalida, intenta otra vez!");
    
                }

            } while (opcion != 10);
        } catch (IOException e){
            System.out.println("\"Error fatal de archivo (no se pudo abrir o crear el CSV): " + e.getMessage());
        }
    }

}