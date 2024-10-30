public class Jugador {

    private String nombre;
    private String alias;
    private int documento;
    private int puntaje;
    private int partidasGanadas;

    // Constructor
    public Jugador(String nombre, String alias, int documento, int puntaje) {
        this.nombre = nombre;
        this.alias = alias;
        this.documento = documento;
        this.puntaje = puntaje;
        this.partidasGanadas = 0; // Inicializamos partidasGanadas a 0 por defecto
    }

    // Métodos get
    public String getNombre() {
        return nombre;
    }

    public String getAlias() {
        return alias;
    }

    public int getDocumento() {
        return documento;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    // Métodos set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void incrementarPartidasGanadas() {
        this.partidasGanadas += 1;
    }

    public static void acabarJuego(){
        System.exit(0);
    }

    public void imprimirJugador(int numero){
        System.out.println("Información del Jugador #" + numero);
        System.out.println("Nombre: " + nombre);
        System.out.println("Alias: " + alias);
        System.out.println("Documento: " + documento);
        System.out.println("Puntaje: " + puntaje);
        System.out.println("Partidas Ganadas: " + partidasGanadas);
    }
    public void imprimirJugador(){
        System.out.println("Información del Jugador:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Alias: " + alias);
        System.out.println("Documento: " + documento);
        System.out.println("Puntaje: " + puntaje);
        System.out.println("Partidas Ganadas: " + partidasGanadas);
    }
}
