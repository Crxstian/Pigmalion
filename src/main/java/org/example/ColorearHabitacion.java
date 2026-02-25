package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ColorearHabitacion {
    private final String[] COLORES = {"\u001B[41m", "\u001B[42m", "\u001B[43m", "\u001B[44m", "\u001B[45m", "\u001B[46m"};
    private char[] SIMBOLOS = {'A','B','C','D','E','F'};
    public void procesarYMostrar(String planoAscii) {
        int superficieMayor=0;
        //convertimos el string a una matriz
        char [][] matriz = convertirAMatriz(planoAscii);
        Map<Character, Integer> estadisticas = new HashMap<>();
        int colorIndex = 0;
        int cantidadHabitaciones = 0;

        for (int r = 0; r < matriz.length; r++){
            for (int c =0; c< matriz[0].length; c++){
                //si encontramos un espacio que No es puerta y no ha sido visitado
                if (matriz[r][c] == ' ' && !esPuerta(r,c,matriz)){
                    char simboloActual = SIMBOLOS[colorIndex % SIMBOLOS.length];
                    //lanzamos el explorador para que pinte toda la habitacion
                    int tamanio = explorarHabitacionIterativo(r,c,matriz,simboloActual);
                    estadisticas.put(simboloActual,tamanio);
                    //luego de terminar esto la habitacion esta pintada y termina al encontrar una puerta
                    //cambiamos el colorIndex para la siguiente habitacion que hara en la proxima iteracion
                    colorIndex ++;
                    cantidadHabitaciones ++;
                }
            }
        }

        imprimirPlano(matriz);
        mostrarReporte(cantidadHabitaciones,estadisticas);

    }
    private int explorarHabitacion(int r, int c, char[][] matriz,char marcador) {
        //Establecemos los limites de corte o limites del grafo

        //Si salgo de la matriz
        if (r<0 || r>=matriz.length || c < 0 || c >=matriz[0].length) return 0;
        //Si chocamos con una pared # o si ya fue recorrido esa coordenada
        if (matriz[r][c] == '#' || matriz[r][c] !=' ') return 0;
        //Si es puerta frenamos no pintamos y no pasamos por ella
        if (esPuerta(r,c,matriz))return 0;
        //Maracamos la celda como vistada o pintada
        matriz[r][c] = marcador;
        int tamanio =1;

        //exploramos los vecinos de forma recursiva
        tamanio+= explorarHabitacion(r-1,c,matriz,marcador);   //abajo
        tamanio+= explorarHabitacion(r,c+1,matriz,marcador);   //derecha
        tamanio+= explorarHabitacion(r,c-1,matriz,marcador);   //izquierda
        tamanio+= explorarHabitacion(r+1,c,matriz,marcador);   //arriba

        return tamanio;
    }

    private boolean esPuerta(int f, int c,char[][] plano ) {
        // primero validamos que no estemos en un extremo de la matriz para no romper el codigo
        if (f <=0 || f>=plano.length -1 || c<=0 || c >=plano[0].length -1) return false;
        //Pared arriba y abajo o izquierda y derecha
        boolean paredArribaAbajo =(plano[f-1][c] == '#' && plano[f+1][c] == '#');
        boolean paredIzquierdaDerecha = (plano[f][c-1] == '#' && plano[f][c+1]== '#');

        return paredArribaAbajo || paredIzquierdaDerecha;
    }

    private char[][] convertirAMatriz(String plano) {
        String[] lineas = plano.split("\n");
        int filas = lineas.length;
        int columnas = 0;
        for (String l:lineas)columnas=Math.max(columnas,l.length());

        char[][] matriz = new char[filas][columnas];
        // Solo recorremos la fila
        for (int i=0;i<filas;i++) {
            for (int j = 0; j < columnas; j++) {
                // toCharArray devuelve toda la fila , entonces queda llena de una sola vez
                matriz[i][j] = (j < lineas[i].length()) ? lineas[i].charAt(j) : ' ';
            }
        }
        return matriz;
    }
    private void imprimirPlano(char[][] matriz) {
        for (int f = 0; f < matriz.length; f++) {
            for (int c = 0; c < matriz[0].length; c++) {
                // el caracter "\u001B[0m" es para dejar el color original luego de una escritura
                char simbolo = matriz[f][c];
                String colorFondo = getColor(simbolo);
                if (!colorFondo.isEmpty()) {
                    // tiene color (A, B, C...), imprimimos el fondo con un espacio
                    System.out.print(colorFondo + " " + "\u001B[0m");
                } else {
                    // imprimimos el carácter original
                    System.out.print(simbolo);
                }
            }
            System.out.println();
        }
    }
    private String getColor(char simbolo) {
        for (int i = 0; i < SIMBOLOS.length; i++) {
            if (SIMBOLOS[i] == simbolo) {
                return COLORES[i];
            }
        }
        return ""; // vacio sin color
    }
    private void mostrarReporte(int total, Map<Character, Integer> stats) {
        System.out.println("\n--- RESULTADOS DEL ANALISIS ---");
        System.out.println("Habitaciones encontradas: " + total);
        int max = 0;
        for (int tam : stats.values()) if (tam > max) max = tam;
        System.out.println("Superficie de la mayor: " + max);
        stats.forEach((id, tam) -> System.out.println("Habitación " + id + ": " + tam + " celdas."));
    }
    private int explorarHabitacionIterativo(int filaInicio, int colInicio, char[][] matriz, char marcador) {
        // pila manual para guardar las coordenadas
        Deque<int[]> pila = new ArrayDeque<>();
        pila.push(new int[]{filaInicio, colInicio});

        int tamanio = 0;

        while (!pila.isEmpty()) {
            int[] actual = pila.pop();
            int r = actual[0];
            int c = actual[1];

            // mismas validaciones que en el recursivo)
            if (r < 0 || r >= matriz.length || c < 0 || c >= matriz[0].length) continue;
            if (matriz[r][c] != ' ' || esPuerta(r, c, matriz)) continue;
            // pintamos y contamos
            matriz[r][c] = marcador;
            tamanio++;
            // Agregamos vecinos a la pila (Norte, Sur, Este, Oeste)
            pila.push(new int[]{r + 1, c});
            pila.push(new int[]{r - 1, c});
            pila.push(new int[]{r, c + 1});
            pila.push(new int[]{r, c - 1});
        }
        return tamanio;
        //Para usar colas o usar un BFS para encontrar caminos cortos reemplzamos pila.push por cola.add y
        //pila.pop por cola.poll funcioanria igual solo cambia el orden de visita
    }
}