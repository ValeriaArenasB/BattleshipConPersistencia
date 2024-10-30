
import java.util.ArrayList;
import java.util.Scanner;

public class Tablero {
    private ArrayList<Barco> barcos = new ArrayList<>();
    private String nombreTablero;
    private Casilla tableroJuego;
    private int cols = 10;
    private int filas = 10;

    public Tablero() {
    }

    public Tablero(String nombreTablero, Casilla tableroJuego, int cols, int filas) {
        this.nombreTablero = nombreTablero;
        this.tableroJuego = tableroJuego;
        this.cols = cols;
        this.filas = filas;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(ArrayList<Barco> barcos) {
        this.barcos = barcos;
    }

    // Métodos getter y setter para nombreTablero
    public String getNombreTablero() {
        return nombreTablero;
    }

    public void setNombreTablero(String nombreTablero) {
        this.nombreTablero = nombreTablero;
    }

    // Métodos getter y setter para tableroJuego
    public Casilla getTableroJuego() {
        return tableroJuego;
    }

    public void setTableroJuego(Casilla tableroJuego) {
        this.tableroJuego = tableroJuego;
    }

    // Métodos getter y setter para cols
    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    // Métodos getter y setter para filas
    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void barcosComputador() {
        boolean rev;
        ArrayList<Barco> npcBar = new ArrayList<>();
        npcBar =Barco.barcosNPC();
        for(Barco bb:npcBar) {
            rev = false;
            do {
                bb.setDireccionInicio(Barco.direccionNPC());
                bb.setPosY(Barco.generateRandom(10));
                bb.setPosX(Barco.generateRandom(10));
                rev = ubicarBarco(bb);
            } while (!rev);
        }
        barcosNPC(npcBar);
        //tableroJ2.getTableroJuego().gridPrint();
    }

    public void registrarBarcos(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("____Define tus barcos!____");
        int[] tamanos = new int[]{5, 4, 4, 1, 3, 3, 2, 2, 2, 2};
        String[] tipos= new String[]{"Portaviones", "Acorazado","Acorazado","Crucero","Submarino", "Submarino" ,"Destructores", "Destructores","Destructores", "Destructores"};

        for (int i = 0; i < 10; i++) {
            Barco nuevoBarco = new Barco();
            nuevoBarco.setTipoBarco(tipos[i]);
            nuevoBarco.setTamano(tamanos[i]);
            System.out.println("Colocando " + nuevoBarco.getTipoBarco() + " - Tamaño: " + nuevoBarco.getTamano());

            do {
                System.out.print("Escribe la dirección en la que se coloca el barco (U, D, R, L): ");
                char dir = scanner.next().charAt(0);
                if (dir == 'U' || dir == 'D' || dir == 'R' || dir == 'L') {
                    nuevoBarco.setDireccionInicio(Character.toUpperCase(dir));
                    break;
                } else {
                    System.out.println("Dirección no válida.");
                }
            } while (true);

            do {
                System.out.print("Escribe las coordenadas en formato (letra,número) (A1, B2, C3, ...): ");
                String entry = scanner.next();
                char letter = entry.charAt(0);
                int number = Character.getNumericValue(entry.charAt(1));
                if (entry.length() == 3)
                    if(Character.isDigit(entry.charAt(2))){
                        number = 10;
                }

                if (letter >= 'A' && letter <= 'J' && number >= 1 && number <= 10) {
                    nuevoBarco.setPosX(Partida.atoNum(letter));
                    nuevoBarco.setPosY(number - 1);
                    if (ubicarBarco(nuevoBarco)) {
                        System.out.println("Barco creado con éxito! ");
                        barcos.add(nuevoBarco);
                        this.tableroJuego.gridPrint();
                        break;
                    } else {
                        System.out.println("Error al crear el barco con las coordenadas.");
                    }
                } else {
                    System.out.println("Coordenadas no válidas.");
                }
            } while (true);
        }
    }

    public boolean ubicarBarco(Barco barco) {
        int x = barco.getPosX(), y = barco.getPosY();
        int tamano = barco.getTamano();
        char direccion = Character.toUpperCase(barco.getDireccionInicio());

        if (x >= 0 && x <= filas && y >= 0 && y <= cols) {
            char[][] grid = this.tableroJuego.getTablero();

            switch (direccion) {
                case 'L':
                    if (y - tamano < -1) {
                        return false;
                    } else {
                        int point = y;
                        for (int i = 1; i <= tamano; i++) {
                            if (this.tableroJuego.getCasilla(x, point) == 'B') {
                                return false;
                            } else {
                                point--;
                            }
                        }
                        for (int i = 1; i <= tamano; i++) {
                            tableroJuego.setCasilla(x,y,'B');
                            y--;
                        }
                    }
                    return true;
                case 'R':
                    if (y + tamano > cols - 1) {
                        return false;
                    } else {
                        int point = y;
                        for (int i = 1; i <= tamano; i++) {
                            if (this.tableroJuego.getCasilla(x, point) == 'B') {
                                return false;
                            } else {
                                point++;
                            }
                        }
                        for (int i = 1; i <= tamano; i++) {
                            tableroJuego.setCasilla(x,y, 'B');
                            y++;
                        }
                    }
                    return true;
                case 'D':
                    if (x + tamano > filas - 1) {
                        return false;
                    } else {
                        int point = x;
                        for (int i = 1; i <= tamano; i++) {
                            if (this.tableroJuego.getCasilla( point, y) == 'B') {
                                return false;
                            } else {
                                point++;
                            }
                        }
                        for (int i = 1; i <= tamano; i++) {
                            tableroJuego.setCasilla(x,y,'B');
                            x++;
                        }
                    }
                    return true;
                case 'U':
                    if (x - tamano < -1) {
                        return false;
                    } else {
                        int point = x;
                        for (int i = 1; i <= tamano; i++) {
                            if (this.tableroJuego.getCasilla(point, y) == 'B') {
                                return false;
                            } else {
                                point--;
                            }
                        }
                        for (int i = 1; i <= tamano; i++) {
                            tableroJuego.setCasilla(x,y,'B');
                            x--;
                        }
                    }
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
    public void barcosNPC(ArrayList<Barco> barcos){
        this.barcos.addAll(barcos);
    }
}
