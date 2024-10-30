import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String jugador1 = "Tatis";
        String jugador2 = "Juanpa";
        Casilla casillas1 = new Casilla('#');
        Casilla casillas2 = new Casilla('T');
        Tablero tableroJ1 = new Tablero("Tablero_Tatis", casillas1, 10, 10);
        Tablero tableroJ2 = new Tablero("Tablero_Juanpa", casillas2, 10, 10);


        System.out.println("Barcos Jugador");
        //tableroJ1.registrarBarcos();
        tableroJ1.barcosComputador();
        tableroJ1.getTableroJuego().gridPrint();

        System.out.println("Barcos NPC");
        //Para ubicar los barcos de Tatis
        tableroJ2.barcosComputador();
        tableroJ2.getTableroJuego().gridPrint();

        Partida.juego(jugador1, jugador2, tableroJ1, tableroJ2, tableroJ1.getBarcos().toArray(new Barco[0]), tableroJ2.getBarcos().toArray(new Barco[0]));

    }
}