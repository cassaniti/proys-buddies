package org.example;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;






class Carrera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Participante> participantes = new ArrayList<>();

        MontoRecaudado recaudadoChico = new MontoRecaudado(0);
        MontoRecaudado recaudadoMediano = new MontoRecaudado(0);
        MontoRecaudado recaudadoGrande = new MontoRecaudado(0);


boolean programaIniciado = false;


        while (!programaIniciado) {


            System.out.println("1. Inscribir participante"); // 2
            System.out.println("2. Mostrar lista de participantes"); // 3
            System.out.println("3. Desinscribir participante por ID"); // 4
            System.out.println("4. Calcular montos recaudados");// 5
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: "); // 1

          //  int opcion = scanner.nextInt();
            String opcion = scanner.nextLine();

            if (validarInt(opcion)) {
                System.out.println("La opcion es un numero entero.");

                switch (opcion) {
                    case "1":
                        inscribirParticipante(scanner, participantes, recaudadoChico, recaudadoMediano, recaudadoGrande);
                        scanner.nextLine();
                        break;
                    case "2":
                        mostrarListaParticipantes(participantes);
                        break;
                    case "3":
                        desinscribirParticipantePorId(scanner, participantes, recaudadoChico, recaudadoMediano, recaudadoGrande);
                        scanner.nextLine();
                        break;
                    case "4":
                        calcularMontosRecaudados(participantes, recaudadoChico, recaudadoMediano, recaudadoGrande);
                        break;

                    case "5":
                        System.out.println("Saliendo del programa...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                        break;
                }

                if (!programaIniciado) {
                    // Preguntar si desea realizar otra operación
                    System.out.print("¿Desea realizar otra operación? (y/n): ");
                    String respuesta = scanner.nextLine().toLowerCase();



                    if (!respuesta.equals("y")) {
                        programaIniciado = true;
                        System.out.println("Saliendo del programa...");
                        scanner.close();
                        System.exit(0);
                    }

             
              }

            }

            else if(!validarInt(opcion)){

                System.out.println("Entrada no válida. Ingrese un número entero válido.");
            }


            scanner.nextLine();  // Consumir el salto de línea después del número




        }
    }




    private static void inscribirParticipante(Scanner scanner, ArrayList<Participante> participantes, MontoRecaudado recaudadoChico, MontoRecaudado recaudadoMediano, MontoRecaudado recaudadoGrande) {

        System.out.print("Ingrese el dni del participante: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese el nombre del participante: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del participante: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el celular del participante: ");
        String celular = scanner.nextLine();
        System.out.print("Ingrese el numero de emergencia del participante: ");
        String numeroEmergencia = scanner.nextLine();
        System.out.print("Ingrese el grupo sanguineo del participante: ");
        String grupoSanguineo = scanner.nextLine();

        System.out.print("Ingrese la edad del participante: ");
        int edad = scanner.nextInt();

        System.out.println("Seleccione la categoría:");
        System.out.println("1. Circuito Chico (Costo: $1300 menores de 18 años, $1500 mayores de 18 años)");
        System.out.println("2. Circuito Mediano (Costo: $2000 menores de 18 años, $2300 mayores de 18 años)");
        System.out.println("3. Circuito Grande (Costo: $2800 mayores de 18 años)");
        System.out.print("Seleccione una opción: ");

        int opcionCategoria = scanner.nextInt();
        double costoInscripcion = 0;

        switch (opcionCategoria) {
            case 1:
                if (edad < 18) {
                    costoInscripcion = 1300;
                } else {
                    costoInscripcion = 1500;
                }
                break;
            case 2:
                if (edad < 18) {
                    costoInscripcion = 2000;
                } else {
                    costoInscripcion = 2300;
                }
                break;
            case 3:
                if (edad >= 18) {
                    costoInscripcion = 2800;
                } else {
                    System.out.println("No se permiten inscripciones a menores de 18 años en el Circuito Grande. Inscripción cancelada.");
                    return;
                }
                break;
            default:
                System.out.println("Opción no válida. Inscripción cancelada.");
                return;
        }

        String categoria = obtenerCategoria(opcionCategoria);

        Participante participante = new Participante(dni, nombre, apellido, celular, numeroEmergencia, grupoSanguineo, categoria, costoInscripcion, edad);
        participantes.add(participante);




        // Actualizar monto recaudado por categoría
        switch (opcionCategoria) {
            case 1:
                recaudadoChico.sumarMonto(costoInscripcion);
                break;
            case 2:
                recaudadoMediano.sumarMonto(costoInscripcion);
                break;
            case 3:
                recaudadoGrande.sumarMonto(costoInscripcion);
                break;
        }

        System.out.println("¡Participante inscrito con éxito!");
        System.out.println(participante);
    }





    private static void mostrarListaParticipantes(ArrayList<Participante> participantes) {
        System.out.println("Lista de participantes inscritos :");
        for (Participante participante : participantes) {
            System.out.println(participante);
        }
    }



    private static void desinscribirParticipantePorId(Scanner scanner, ArrayList<Participante> participantes, MontoRecaudado recaudadoChico, MontoRecaudado recaudadoMediano, MontoRecaudado recaudadoGrande) {
        System.out.print("Ingrese el ID del participante a desinscribir: ");
        int idDesinscribir = scanner.nextInt();

        // Buscar el participante en la lista
        Participante participanteDesinscribir = null;

        for (Participante participante : participantes) {
            if (participante.id == idDesinscribir) {
                participanteDesinscribir = participante;
                break;
            }
        }

        // Verificar si se encontró el participante
        if (participanteDesinscribir != null) {
            // Mostrar información del participante antes de desinscribir
            System.out.println("Participante a desinscribir:");
            System.out.println(participanteDesinscribir);

            // Obtener la categoría del participante antes de desinscribirlo
            int opcionCategoria = obtenerOpcionCategoria(participanteDesinscribir.categoria);

            // Desinscribir al participante eliminándolo de la lista
            participantes.remove(participanteDesinscribir);

            switch (opcionCategoria) {
                case 1:
                    recaudadoChico.restarMonto(participanteDesinscribir.costoInscripcion);
                    break;
                case 2:
                    recaudadoMediano.restarMonto(participanteDesinscribir.costoInscripcion);
                    break;
                case 3:
                    recaudadoGrande.restarMonto(participanteDesinscribir.costoInscripcion);
                    break;
            }


        } else {
            System.out.println("Participante no encontrado. No se pudo desinscribir.");
        }
    }

    private static int obtenerOpcionCategoria(String categoria) {
        switch (categoria) {
            case "Circuito Chico":
                return 1;
            case "Circuito Mediano":
                return 2;
            case "Circuito Grande":
                return 3;
            default:
                return 0;
        }
    }






    private static String obtenerCategoria(int opcionCategoria) {
        switch (opcionCategoria) {
            case 1:
                return "Circuito Chico";
            case 2:
                return "Circuito Mediano";
            case 3:
                return "Circuito Grande";
            default:
                return "Desconocida";
        }
    }



    private static void calcularMontosRecaudados(ArrayList<Participante> participantes, MontoRecaudado recaudadoChico, MontoRecaudado recaudadoMediano, MontoRecaudado recaudadoGrande) {
        System.out.println("Monto recaudado por categoría:");
        System.out.println("Circuito Chico: $" + recaudadoChico.getMonto());
        System.out.println("Circuito Mediano: $" + recaudadoMediano.getMonto());
        System.out.println("Circuito Grande: $" + recaudadoGrande.getMonto());

        double montoTotal = recaudadoChico.getMonto() + recaudadoMediano.getMonto() + recaudadoGrande.getMonto();
        System.out.println("Monto total de todas las categorías: $" + montoTotal);
    }


    public static boolean validarInt(String cadena) {
        try {
            // Intentar convertir la cadena a un número entero
            int numero = Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}

