import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Partida {
    private final ArrayList<String> jugadas = new ArrayList<>();
    private final Barco[][] controlBarcos = new Barco[2][10]; // el indice 0, tendra los barcos del jugador1, el indice 1, tendra los barcos del NPC

    public static void juego(String jugador1, String jugador2, Tablero tablero1, Tablero tablero2,
                             Barco[] barcosP1, Barco[] barcosP2) {

        String entry = null;
        String nombreArchivo = "Partidas.txt";

        int jugadorAtacar = 2;
        boolean ganador = false;
        char rangoXsup = 'A';
        char rangoXinf = 'J';
        int rangoYinf = 1;
        int rangoYsup = 10;
        int detecto = 10;
        char letter;
        int number = 0;
        Casilla[] tableros = new Casilla[2];
        tableros[0] = new Casilla('#');
        tableros[1] = new Casilla('T');

        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Formatea la fecha y hora según tus preferencias
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formato);


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            bw.write("\n Partida de:" + jugador1 + "\n Jugada el: " + fechaHoraFormateada + "\n");
            int turnos = 0;
            do {

                // Imprimir el turno del jugador
                System.out.println("------------Turno de: " + ((jugadorAtacar == 1) ? "NPC" : jugador1) + " -------------");
                bw.write("Turno #" + ++turnos +"\n Turno de: " + ((jugadorAtacar == 1) ? "NPC" : jugador1) + "\n");

                // Jugador 1 y Jugador 2 es NPC(Computador)
                // Mostrar el tablero del oponente
                System.out.println("Escriba \"exit\" para salir del juego");
                if (jugadorAtacar == 1) {
                    System.out.println("Estado de tu tablero:");
                    tableros[0].gridPrint();
                    tablero1.getTableroJuego().gridPrint();
                }
                // Preguntar por las coordenadas del ataque
                do {

                    if (jugadorAtacar == 2) {
                        System.out.println("________________Tablero enemigo________________");
                        tableros[1].gridPrint();
                        tablero2.getTableroJuego().gridPrint();
                        System.out.println("Escribe las coordenadas en formato (letra,número) (A1, B2, C3, ...): ");
                        Scanner scanner = new Scanner(System.in);
                        entry = scanner.next();

                        if (entry.equalsIgnoreCase("exit")) {
                            bw.write("_______Se detuvo el programa_______");
                            bw.close();
                            Jugador.acabarJuego();
                        }
                        bw.write("Disparo en:" + entry + "\n");
                        letter = entry.charAt(0);
                        if (entry.length() == 3) {
                            System.out.println("Entro al 10");
                            if (Character.isDigit(entry.charAt(2))) {
                                number = 9;
                            }
                        } else {
                            number = Character.getNumericValue(entry.charAt(1)) - 1;
                        }
                    } else {
                        do {
                            //El NPC prepara su siguiente jugada
                            System.out.println("NPC pensando....");
                            do {
                                letter = Partida.randomChar(rangoXsup, detecto);
                            } while (letter < rangoXsup || letter > rangoXinf);
                            do {
                                number = Partida.generateRandomNumber(rangoYsup);
                            } while (number < rangoYinf || number > rangoYsup);
                        } while (Lanzamiento.shoottablero(number, letter, tablero1, barcosP1).equals("false"));
                        System.out.println("NPC dispara en: " +letter + number);
                        bw.write("Disparo en: " + letter+number + "\n");
                        //En caso de que tarde en elegir una jugada valida, se muestra un letrero
                        System.out.println("Dejo de pensar!");
                    }

                    if (letter >= 'A' && letter <= 'J' && number >= 0 && number <= 10) {
                        //Convierte las coordenadas a numeros de arreglo para interactuar con el tablero de Casilla
                        int y = Partida.atoNum(letter);
                        int x = number;
                        String verificar;

                        if (jugadorAtacar == 1) {
                            System.out.println("Ataca NPC");
                            verificar = Lanzamiento.shoottablero(x, letter, tablero1, barcosP1);
                            //Mostrarle al jugador, como interactuo el NPC con su tablero
                            if (verificar.equals("Impacto")) {
                                System.out.println("Impacto en tu barco");
                                tablero1.getTableroJuego().setCasilla(x, Partida.atoNum(letter), 'X');
                                tableros[0].setCasilla(x, Partida.atoNum(letter), 'X');
                            } else if (verificar.equals("Hundido")) {
                                System.out.println("Barco tuyo hundido");
                                tablero1.getTableroJuego().setCasilla(x, Partida.atoNum(letter), 'X');
                                tableros[0].setCasilla(x, Partida.atoNum(letter), 'X');
                            } else {
                                System.out.println("Fallo el enemigo");
                                tablero1.getTableroJuego().setCasilla(x, Partida.atoNum(letter), 'V');
                                tableros[0].setCasilla(x, Partida.atoNum(letter), 'V');
                            }
                            bw.write("Estado disparo: "+ verificar +"\n");

                            //Muestra al jugador el estado de su tablero
                            System.out.println("Tu tablero: :0");
                            tablero1.getTableroJuego().gridPrint();

                        } else {
                            System.out.println("Atacas TU!");
                            verificar = Lanzamiento.shoottablero(x, letter, tablero2, barcosP2);
                            //Si le dio al enemigo, ubicar la X
                            if (verificar.equals("Impacto") || verificar.equals("Hundido")) {
                                tablero2.getTableroJuego().setCasilla(Partida.atoNum(letter), x, 'X');
                                tableros[1].setCasilla(Partida.atoNum(letter), x, 'X');
                            } else {
                                tablero2.getTableroJuego().setCasilla(Partida.atoNum(letter), x, 'V');
                                tableros[1].setCasilla(Partida.atoNum(letter), x, 'V');
                            }
                            //Mostrarle al jugador si le dio o no, y mostrar como quedo el tablero enemigo
                            System.out.println("-------------" + verificar + "----------------");
                            bw.write("Estado disparo: "+verificar + "\n");
                            System.out.println("_____________Estado Tablero Enemigo___________");
                            tableros[1].gridPrint();
                        }
                        // Cambiar de jugador si el ataque fue un fallo
                        if (verificar.equals("Missed")) {
                            jugadorAtacar = (jugadorAtacar == 1) ? 2 : 1;
                        }

                        // Verificar si hay un ganador
                        if (verificarGanador(barcosP1)) {
                            ganador = true;
                            System.out.println("¡" + jugador2 + " es el ganador!");
                            bw.write("¡" + jugador2 + " es el ganador! \n");
                        } else if (verificarGanador(barcosP2)) {
                            ganador = true;
                            System.out.println("¡" + jugador1 + " es el ganador!");
                            bw.write("¡" + jugador1 + " es el ganador!\n");
                        } else {
                            ganador = false;
                        }

                    } else {
                        System.out.println("Coordenadas no válidas.");
                    }
                } while (!entry.equalsIgnoreCase("exit"));
                bw.close();

            } while (!ganador);
            bw.close();
        }catch (IOException e){
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static boolean verificarGanador(Barco[] barcos) {
        for (Barco barco : barcos) {
            if (!barco.isHundido()) {
                return false; // Todavía hay barcos no hundidos
            }
        }
        return true; // Todos los barcos han sido hundidos
    }

    public static int atoNum(char x) {
        char a = 'A';
        int i = 0;
        while (a <= 'Z') {
            if (x == a) {
                return i;
            } else {
                i++;
                a++;
            }
        }
        return -1; // No encontró la letra
    }

    private static char randomChar(char casillaActual, int tam) {
        Random r = new Random();
        return (char) (r.nextInt(tam) + casillaActual);
    }

    public static int generateRandomNumber(int a) {
        // generate a random number between `0` and `n`
        return new Random().nextInt(a);
    }
}
