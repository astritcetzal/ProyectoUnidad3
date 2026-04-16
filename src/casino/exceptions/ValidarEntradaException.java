package exceptions;

import java.util.Scanner;

public class ValidarEntradaException {
    static int leerOpcionMenu(Scanner scanner) {
        int opcion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                String entrada = scanner.nextLine();
                opcion = Integer.parseInt(entrada);
                if (opcion == 1 || opcion == 2) {
                    entradaValida = true;
                } else {
                    System.out.print("Opción inválida. Ingrese un numero de 1 a 9: ");
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor ingrese un número (1 o 2): ");
            }
        }

        return opcion;
    }
    
}
