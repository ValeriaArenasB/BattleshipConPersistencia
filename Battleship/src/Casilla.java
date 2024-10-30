public class Casilla {
    private char[][] tablero = new char[10][10];

    public void setCasilla(int x, int y, char simbolo){
        this.tablero[x][y] = simbolo;
    }
    public char getCasilla(int x, int y){
        return tablero[x][y];
    }
    public char[][] getTablero(){
        return this.tablero;
    }
    public Casilla(char a) {
        // Inicializar el tablero con espacios en blanco
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                setCasilla(i,j,a);
            }
        }
    }
    // Copiar el contenido de un tablero a otro
    public void fillGrid(char[][] grid) {
        for (int i = 0; i < tablero.length; i++) {
            System.arraycopy(grid[i], 0, tablero[i], 0, tablero[i].length);
        }
    }

    // Imprimir el tablero
    public void gridPrint() {
        char a = 'A';
        // Imprimir letras y números de cuadrícula
        for (int i = 0; i <= 10; i++) {
            if (i == 0) {
                System.out.print("_");
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();
        // Imprimir el tablero
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(a++);
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print("|" + tablero[i][j]);
            }
            System.out.println("|");
        }
    }
}
