import java.util.Collections;
import java.util.ArrayList;

public class Competencia {
    ArrayList<Jugador> jugadoresCompetencia = new ArrayList<>();
    ArrayList<Jugador> ganadores = new ArrayList<>();

    public void mostrarRanking() {
        Collections.sort(ganadores, new ControlResultados());
        System.out.println("\nLista de ganadores:");
        for (Jugador jugador : ganadores) {
            jugador.imprimirJugador();
        }
    }

    public void añadirJugadores(ArrayList<Jugador> jugadoresAñadir){
         jugadoresCompetencia.addAll(jugadoresAñadir);
    }
}
