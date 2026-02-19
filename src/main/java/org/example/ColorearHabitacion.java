package org.example;

import java.util.Arrays;

public class ColorearHabitacion {
    public static final String[] COLORES = {"\u001B[41m", "\u001B[42m", "\u001B[43m", "\u001B[44m", "\u001B[45m", "\u001B[46m"};
    public static final String RESETEAR = "\u001B[0m";

    public void procesarYMostrar(String planoAscii) {
        char[][] matriz = transformarAMatriz(planoAscii);
        // matriz de enteros para guardar el id de la habitacion (-1 es sin color)
        int[][] mapaColores = new int[matriz.length][matriz[0].length];
        for (int[] fila : mapaColores) Arrays.fill(fila, -1);

        colorear(matriz, mapaColores);
        imprimirPlano(matriz, mapaColores);
    }

    private void colorear(char[][] plano, int[][] mapaColores) {
        int colorIndex = 0;
        for (int f = 0; f < plano.length; f++) {
            for (int c = 0; c < plano[0].length; c++) {
                // Si es un espacio vacío, no tiene color asignado y no es puerta se rellenaa
                if (plano[f][c] == ' ' && mapaColores[f][c] == -1 && !esPuerta(plano, f, c)) {
                    rellenar(plano, mapaColores, f, c, colorIndex);
                    colorIndex++;
                }
            }
        }
    }

    private void rellenar(char[][] plano, int[][] mapaColores, int f, int c, int idColor) {
        // validamos si es pared, si ya tiene color o si es puerta
        if (f < 0 || f >= plano.length || c < 0 || c >= plano[0].length ||
                plano[f][c] == '#' || mapaColores[f][c] != -1 || esPuerta(plano, f, c)) {
            return;
        }

        mapaColores[f][c] = idColor;

        // Recursión para los 4 vecinos
        rellenar(plano, mapaColores, f + 1, c, idColor);
        rellenar(plano, mapaColores, f - 1, c, idColor);
        rellenar(plano, mapaColores, f, c + 1, idColor);
        rellenar(plano, mapaColores, f, c - 1, idColor);
    }

    private boolean esPuerta(char[][] plano, int f, int c) {
        try {
            // Una puerta divide dos paredes colineales , horizontal o vertical
            boolean horizontal = (plano[f][c-1] == '#' && plano[f][c+1] == '#');
            boolean vertical = (plano[f-1][c] == '#' && plano[f+1][c] == '#');
            return horizontal || vertical;
        } catch (Exception e) { return false; }
    }

    private char[][] transformarAMatriz(String ascii) {
        String[] lineas = ascii.split("\n");
        int maxCol = 0;
        for (String l : lineas) maxCol = Math.max(maxCol, l.length());

        char[][] matriz = new char[lineas.length][maxCol];
        for (int i = 0; i < lineas.length; i++) {
            for (int j = 0; j < maxCol; j++) {
                matriz[i][j] = (j < lineas[i].length()) ? lineas[i].charAt(j) : ' ';
            }
        }
        return matriz;
    }

    private void imprimirPlano(char[][] matriz, int[][] mapaColores) {
        for (int f = 0; f < matriz.length; f++) {
            for (int c = 0; c < matriz[0].length; c++) {
                if (mapaColores[f][c] != -1) {
                    String colorString = COLORES[mapaColores[f][c] % COLORES.length];
                    System.out.print(colorString + " " + RESETEAR);
                } else {
                    System.out.print(matriz[f][c]);
                }
            }
            System.out.println();
        }
    }
}