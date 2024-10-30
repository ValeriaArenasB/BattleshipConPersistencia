import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;

public class Lanzamiento {
    public static String shoottablero(int x, char letter, Tablero tablero, Barco[] ships) {
        int yIndex = Partida.atoNum(letter); // Ajustar y para que sea un índice válido
        int xIndex = x; // Convertir la letra a un índice

        System.out.println("Shooting... in x:" + xIndex + ", y:" + yIndex);
        System.out.println("La casilla contiene: " + tablero.getTableroJuego().getCasilla(xIndex, yIndex) + " ;");
        ArrayList<Barco> noHundidos = new ArrayList<>();
        int[] index = new int[10];
        int isHundido = 0;
        int i = 0;
        int j = 0;
        for (Barco ss : ships) {
            if (!ss.isHundido()) {
                noHundidos.add(ss);
                index[i++] = j;
            }
            j++;
        }
        if (xIndex >= 0 && xIndex <= 9 && yIndex >= 0 && yIndex <= 9) {
            System.out.println("---------------Tablero a disparar---------------");
            tablero.getTableroJuego().gridPrint();
            if (tablero.getTableroJuego().getCasilla(yIndex, xIndex) == 'B') {
                tablero.getTableroJuego().setCasilla(yIndex, xIndex, 'X');
                i = 0;
                for (Barco ss : noHundidos) {
                    if (ss.verificarEstado(tablero.getTableroJuego().getTablero())) {
                        boolean v = true;
                        ships[index[i]].setHundido(v);
                        System.out.println("Hundido");
                        System.out.println("---------------Tablero Disparado---------------");
                        tablero.getTableroJuego().gridPrint();
                        return "Hundido";
                    }
                    i++;
                }
                System.out.println("Impacto");
                System.out.println("---------------Tablero Disparado---------------");
                tablero.getTableroJuego().gridPrint();
                return "Impacto";
            } else {

                if (tablero.getTableroJuego().getCasilla(yIndex, xIndex) != 'X') {
                    tablero.getTableroJuego().setCasilla(yIndex, xIndex, 'M');
                    System.out.println("---------------Tablero Fallo---------------");
                    tablero.getTableroJuego().gridPrint();
                    System.out.println("Missed");
                    return "Missed";
                }
                System.out.println("---------------Tablero Fallo---------------");
                tablero.getTableroJuego().gridPrint();
                System.out.println("Missed");
                return "Missed";
            }
        } else {
            System.out.println("Invalid Coordinates...");
            return "false";
        }
    }
}
