import java.util.ArrayList;
import java.util.Random;

public class Barco {
    private String tipoBarco;
    private int tamano;
    private boolean hundido= false;
    private int posX;
    private int posY;
    private char direccionInicio;

    public Barco(){
        this.hundido = false;
    }
    public Barco(Barco barqui){
        this.tipoBarco = barqui.getTipoBarco();
        this.direccionInicio = barqui.getDireccionInicio();
        this.posX = barqui.getPosX();
        this.posY = barqui.getPosY();
        this.tamano = barqui.getTamano();
    }
    public Barco(String tipoBarco, int tamano, int posX, int posY, char direccionInicio) {
        this.tipoBarco = tipoBarco;
        this.tamano = tamano;
        this.posX = posX;
        this.posY = posY;
        this.direccionInicio = direccionInicio;
    }
    public String getTipoBarco() {
        return tipoBarco;
    }

    public int getTamano() {
        return tamano;
    }

    public boolean isHundido() {
        return hundido;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public char getDireccionInicio() {
        return direccionInicio;
    }

    // Setters
    public void setTipoBarco(String tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setDireccionInicio(char direccionInicio) {
        this.direccionInicio = direccionInicio;
    }
    public boolean verificarEstado(char[][] grid) {
        int x = posX, y = posY;
        int tam = tamano;
        char dir = Character.toUpperCase(direccionInicio);
        int shot = 0;

        switch (dir) {
            case 'U':
                for (int i = 0; i < tam; i++) {
                    if (y - i >= 0 && grid[y - i][x] == 'X') {
                        shot++;
                    }
                }
                break;

            case 'D':
                for (int i = 0; i < tam; i++) {
                    if (y + i < grid.length && grid[y + i][x] == 'X') {
                        shot++;
                    }
                }
                break;

            case 'R':
                for (int i = 0; i < tam; i++) {
                    if (x + i < grid[0].length && grid[y][x + i] == 'X') {
                        shot++;
                    }
                }
                break;

            case 'L':
                for (int i = 0; i < tam; i++) {
                    if (x - i >= 0 && grid[y][x - i] == 'X') {
                        shot++;
                    }
                }
                break;

            default:
                return false; // Dirección no válida
        }
        if(shot == tam)
        {
            return true;
        }else{
            return false;
        }

    }

    public static char direccionNPC(){
        char[] direcciones = {'U', 'D', 'R', 'L'};
        // Crear una instancia de Random
        Random random = new Random();
        // Generar un índice aleatorio

        int indiceAleatorio = random.nextInt(direcciones.length);
        // Obtener el carácter al azar
        return direcciones[indiceAleatorio];
    }

    public static int generateRandom(int a)
    {
        return new Random().nextInt(a);
    }

    public static ArrayList<Barco> barcosNPC(){

        int[] tamanos = new int[]{5, 4, 4, 1, 3, 3, 2, 2, 2, 2};
        String[] tipos= new String[]{"Portaviones", "Acorazado","Acorazado","Crucero","Submarino", "Submarino" ,"Destructores", "Destructores","Destructores", "Destructores"};
        ArrayList<Barco>barcosNPC = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Barco nuevoBarco = new Barco();
            nuevoBarco.setTipoBarco(tipos[i]);
            nuevoBarco.setTamano(tamanos[i]);
            barcosNPC.add(nuevoBarco);
        }
        return barcosNPC;
    }
}
