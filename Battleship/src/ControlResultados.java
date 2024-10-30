
import java.util.Comparator;
class ControlResultados implements Comparator<Jugador> {
    @Override
    public int compare(Jugador jugador1, Jugador jugador2) {
        return Integer.compare(jugador2.getPartidasGanadas(), jugador1.getPartidasGanadas());
    }
}