package interfaz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

//import archivos.Archivo;
//import heroesVillanos.Caracteristica;
//import archivos.Archivo;
import heroesVillanos.Competidor;
import heroesVillanos.Liga;

public class InterfazDeUsuario {

    public static void menu() {

        CompetidoresSingleton competidoresSingleton = CompetidoresSingleton.getInstance();
        Map<String, Competidor> competidores = competidoresSingleton.getCompetidores();

        /*
         * Archivo archivo = new Archivo("personajes.in");
         * archivo.cargarPersonajes(competidores);
         * archivo.cargarLigas("ligas.in", competidores);
         */

        System.out.println("Heroes y Villanos: El Videojuego.\n--------------------------------");
        String mensaje = "1) Administracion de Personajes.\n" +
                "2) Administracion de Ligas.\n" +
                "3) Realizacion de Combates.\n" +
                "4) Reportes.\n" +
                "5) Salir.";
        Scanner sc = new Scanner(System.in);
        int opcion = InterfazDeUsuario.obtenerOpcion(mensaje, 1, 5, sc);

        switch (opcion) {
            case 1:
                    AdministracionDePersonajes.administrador(competidores);
                break;
            case 2:
                    AdministracionDeLigas.administrador(competidores);
                break;
            case 3:
                    AdministracionDeCombates.realizacionDeCombates(competidores);
                break;
            case 4:
                    Reportes.reportes(competidores);
                break;
            case 5:
                System.out.println("Gracias por jugar.");
                System.exit(0);
                break;
        }
        sc.close();

    }

    public static int obtenerOpcion(String mensaje, int min, int max, Scanner scanner) {
        int opcion = 0;

        System.out.println(mensaje);

        do {
            System.out.print("Opcion: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
        } while (opcion > max || opcion < min);

        return opcion;
    }

    public static void mostrarCompetidor(Competidor competidor) {
        _mostrarCompetidor(competidor, false);
        System.out.println("");
    }

    public static void _mostrarCompetidor(Competidor competidor, boolean subliga) {
        if (competidor.getEsLiga()) {
            Liga liga = (Liga) competidor;
            Map<String, Competidor> competidores = liga.getCompetidores();

            if (subliga)
                System.out.print("\tSubliga: ");

            System.out.println(liga);

            for (Competidor c : competidores.values()) {
                _mostrarCompetidor(c, true);
            }
        } else {
            System.out.println(competidor);
        }
    }

    public static void mostrarPersonajes(Map<String, Competidor> competidores) {
        System.out.println("Lista personajes:\n");
        System.out.println(
                "Heroe/Villano\t\tNombre Personaje     Nombre Real                      Velocidad       Fuerza          Destreza        Resistencia");
        for (Map.Entry<String, Competidor> competidorAux : competidores.entrySet()) {
            Competidor competidor = competidorAux.getValue();
            if (!competidor.getEsLiga()) {
                System.out.println(competidor);
            }
        }
        System.out.println("");
    }

    public static void mostrarLigas(Map<String, Competidor> competidores) {
        System.out.println("\nLigas:");
        for (Competidor competidor : competidores.values()) {
            if (competidor.getEsLiga()) {
                mostrarCompetidor(competidor);
            }
        }
    }

    public static void guardarEnArchivo(Map<String, Competidor> competidores, boolean personaje) {
        String nombreArchivo = personaje ? "personajesOut.txt" : "ligasNuevas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Map.Entry<String, Competidor> entry : competidores.entrySet()) {
                Competidor competidor = entry.getValue();
                if ((personaje && !competidor.getEsLiga()) || (!personaje && competidor.getEsLiga())) {
                    writer.write(competidor.toString());
                    writer.newLine();
                }
            }
            System.out.println(personaje ? "Personajes guardados en el archivo 'personajesOut.txt'"
                    : "Ligas guardadas en el archivo 'ligasNuevas.txt'");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo: " + e.getMessage());
        }
    }

}