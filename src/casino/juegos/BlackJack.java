package juegos;
import interfaces.Jugable;
import juegos.JuegoMesa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJack extends JuegoMesa implements Jugable {
    private int puntosJugador;
    private int puntosCasa;
    private List<Integer> mazo;

    public BlackJack(String nombre){
        super(nombre);
        this.mazo = new ArrayList<>();
        prepararMazo();
    }

    private void prepararMazo(){
        mazo.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 10; j++) {
                mazo.add(j);
            }
        }
        Collections.shuffle(mazo);
    }

    private int sacarCarta(){
        if (mazo.isEmpty()) prepararMazo();
        return mazo.remove(0);
    }

    @Override
    public void iniciar(Jugador jugador){
        if (jugador.getSaldo() < this.apuestaMinima){
            throw new IllegalArgumentException("Saldo insuficiente para iniciar apuesta minima");
        }
        this.puntosJugador = 0;
        this.puntosCasa = 0;
        this.activo = true;
        prepararMazo();
    }

    @Override
    public void jugar(){
        repartirCartas();
        Scanner sc = new Scanner(System.in);
        
        try {
            while (this.puntosJugador < 21){
                System.out.println("Puntos: " + puntosJugador + ". ¿Pedir? (s/n)");
                String res = sc.nextLine();
                if (res.equalsIgnoreCase("s")) {
                    this.puntosJugador += sacarCarta();
                } else break;
            }

            while (this.puntosCasa < 17 && this.puntosJugador <= 21) {
                this.puntosCasa += sacarCarta();
            }
            terminar();
        } catch (Exception e) {
            System.out.println("Error durante el flujo del juego: " + e.getMessage());
        }
    }

    @Override
    public void terminar(){
        this.activo = false;
        int resultado = calcularPuntos();
        System.out.println("Fin del juego. Resultado: " + (resultado == 1 ? "Gano Jugador" : "Gano Casa"));
    }

    public void repartirCartas(){
        this.puntosJugador = sacarCarta() + sacarCarta();
        this.puntosCasa = sacarCarta() + sacarCarta();
    }

    public int calcularPuntos(){
        if (puntosJugador > 21) 
            return 2;
        if (puntosCasa > 21 || puntosJugador > puntosCasa) 
            return 1;
        if (puntosJugador < puntosCasa) 
            return 2;
        return 0;
    }

    public int getPuntosJugador(){ 
        return puntosJugador; 
    }

    public int getPuntosCasa(){ 
        return puntosCasa; 
    }

    public List<Integer> getMazo(){ 
        return mazo; 
    }
    
    @Override
    public String getNombre(){ 
        return this.nombre; 
    }
}