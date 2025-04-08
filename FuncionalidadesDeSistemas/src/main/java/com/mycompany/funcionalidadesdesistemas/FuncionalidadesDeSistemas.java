package com.mycompany.funcionalidadesdesistemas;

import java.util.Scanner;

public class FuncionalidadesDeSistemas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Datos base
        String[] menu = {"Comprar Entradas", "Salir"};
        String[] filas = {"A", "B", "C"};
        int numFilas = filas.length;
        int numAsientos = 5;
        boolean[][] reservados = new boolean[numFilas][numAsientos];
        boolean continuar = true;

        System.out.println("¡Hola! Bienvenido al sistema de reservas del Teatro Moro");

        while (continuar) {
            // Mostrar menú
            System.out.println();
            System.out.println("----- Menú -----");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i]);
            }
            System.out.print("¿Qué quieres hacer? (1 o 2): ");

            int opcion;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Esa no es una opción válida. Por favor, escribe 1 o 2.");
                scanner.nextLine();
                continue;
            }

            if (opcion == 1) {
                // Mostrar asientos
                System.out.println();
                System.out.println("----- Plano -----");
                System.out.println("Plano del teatro (X = ocupado):");
                for (int i = 0; i < numFilas; i++) {
                    System.out.print("Fila " + filas[i] + ": ");
                    for (int j = 0; j < numAsientos; j++) {
                        if (reservados[i][j]) {
                            System.out.print("[X] ");
                        } else {
                            System.out.print("[" + filas[i] + (j + 1) + "] ");
                        }
                    }
                    System.out.println();
                }

                // Elegir asiento
                System.out.println();
                System.out.println("----- Reserva tu asiento -----");
                System.out.print("Elige tu asiento (ejemplo: A2): ");
                String asiento = scanner.nextLine().toUpperCase();

                if (!asiento.matches("^[A-C][1-5]$")) {
                    System.out.println("Formato no válido. Usa algo como A1, B3 o C5.");
                    continue;
                }

                char letraFila = asiento.charAt(0);
                int filaNum = letraFila - 'A';
                int numAsiento = Integer.parseInt(asiento.substring(1)) - 1;

                if (!reservados[filaNum][numAsiento]) {
                    reservados[filaNum][numAsiento] = true;

                    // Edad
                    System.out.println();
                    System.out.println("----- Edad -----");

                    int edad = -1;
                    while (edad <= 0) {
                        System.out.print("Indica tu edad: ");
                        if (scanner.hasNextInt()) {
                            edad = scanner.nextInt();
                            scanner.nextLine();
                            if (edad <= 0) {
                                System.out.println("Por favor, ingresa una edad válida.");
                            }
                        } else {
                            System.out.println("Por favor, ingresa una edad válida.");
                            scanner.nextLine();
                        }
                    }

                    // Estudiante
                    System.out.println();
                    System.out.println("----- Estudiante -----");
                    System.out.print("¿Eres estudiante? (sí/no): ");
                    String estudiante = scanner.nextLine().toLowerCase();

                    // Calcular descuento
                    double descuento = 0;
                    if (edad >= 60) {
                        descuento = 0.15;
                    } else if (estudiante.equals("si") || estudiante.equals("s")) {
                        descuento = 0.10;
                    }

                    // Precio
                    double precioBase = 25000;
                    double precioFinal = precioBase * (1 - descuento);

                    // Mostrar resumen
                    System.out.println();
                    System.out.println("----- Resumen de tu compra -----");
                    System.out.println("Asiento reservado: Fila " + filas[filaNum] + ", número " + (numAsiento + 1));
                    System.out.println("Precio normal: $" + precioBase);
                    System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
                    System.out.println("Total a pagar: $" + precioFinal);

                    // Otra compra
                    System.out.println();
                    System.out.print("¿Quieres comprar otro asiento? (sí/no): ");
                    String respuesta = scanner.nextLine().toLowerCase();
                    if (respuesta.equals("no") || respuesta.equals("n")) {
                        continuar = false;
                        System.out.println();
                        System.out.println("Gracias por usar el sistema del Teatro Moro. ¡Hasta pronto!");
                    }
                } else {
                    System.out.println();
                    System.out.println("Ese asiento ya está reservado. Por favor, elige otro.");
                }

            } else if (opcion == 2) {
                continuar = false;
                System.out.println();
                System.out.println("Gracias por visitar el Teatro Moro. ¡Te esperamos otra vez!");
            } else {
                System.out.println();
                System.out.println("Esa opción no existe. Por favor, elige 1 o 2.");
            }
        }

        scanner.close();
    }
}
