import java.util.ArrayList;
public class Administrador {
    private String nombre;
    private String contrasena;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    public Administrador(String nombre, String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
    public void actualizarJugadores(Jugador jugador, String contrasena) {
        if (this.contrasena.equals(contrasena)) {
            jugadores.add(jugador);
        } else {
            System.out.println("Contraseña incorrecta");
        }
    }
    public void listarJugadores(String contrasena) {
        if (this.contrasena.equals(contrasena)) {
            int i = 0;
            for (Jugador jj : jugadores) {
                jj.imprimirJugador(++i);
            }
        } else {
            System.out.println("Contraseña incorrecta");
        }
    }
}

