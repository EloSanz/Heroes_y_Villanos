package interfaz;

import java.util.Map;
import java.util.Scanner;
import Excepciones.CaracteristicaNegativaException;
import archivos.Archivo;
import heroesVillanos.*;

public class AdministracionDePersonajes {
    public static void administrador(Map<String, Competidor> competidores) {
        Scanner scanner = new Scanner(System.in);
        String mensaje = "1) Carga desde archivo.\n" +
                "2) Creación de personaje.\n" +
                "3) Listado de personajes.\n" +
                "4) Guardar en archivo todos los personajes.\n" +
                "5) Volver al menú principal.";

        int opcion;
        do {
            System.out.println("\nAdministracion de Personajes.\n--------------------------------");
            opcion = InterfazDeUsuario.obtenerOpcion(mensaje, 1, 5, scanner);

            switch (opcion) {
                case 1:
                    cargarDesdeArchivo(scanner, competidores);
                    break;
                case 2:
                    crearPersonaje(scanner, competidores);
                    break;
                case 3:
                    InterfazDeUsuario.mostrarPersonajes(competidores);
                    break;
                case 4:
                    InterfazDeUsuario.guardarEnArchivo(competidores, true);
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    InterfazDeUsuario.menu();
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }

    public static void cargarDesdeArchivo(Scanner scanner, Map<String, Competidor> competidores) {
        System.out.println("Ingrese el nombre del archivo: ");
        String path = scanner.next();
        Archivo archivo = new Archivo(path);
        if (archivo.cargarPersonajes(competidores))
            System.out.println("Cargado: " + path);
        else
            System.out.println("Error al cargar el archivo " + path);
    }

    public static void crearPersonaje(Scanner scanner, Map<String, Competidor> competidores) {
        int tipo = 0;
        System.out.println("Creación de Personaje");
        do {
            System.out.print("Tipo (1: Héroe, 2: Villano): ");
            tipo = validarEntero(scanner);
        } while (tipo != 1 && tipo != 2);

        System.out.print("Nombre Real: ");
        String nombreReal = scanner.nextLine();

        System.out.print("Nombre del Personaje: ");
        String nombrePersonaje = scanner.nextLine();

        System.out.print("Velocidad: ");
        int velocidad = validarEntero(scanner);

        System.out.print("Fuerza: ");
        int fuerza = validarEntero(scanner);

        System.out.print("Destreza: ");
        int destreza = validarEntero(scanner);

        System.out.print("Resistencia: ");
        int resistencia = validarEntero(scanner);

        if (tipo == 1) {
            try {
                competidores.put(nombrePersonaje,
                        new Heroe(nombreReal, nombrePersonaje, velocidad, fuerza, destreza, resistencia));
            } catch (CaracteristicaNegativaException e) {
                System.out.println("Error al crear el héroe: " + e.getMessage());
            }
        } else if (tipo == 2) {
            try {
                competidores.put(nombrePersonaje,
                        new Villano(nombreReal, nombrePersonaje, velocidad, fuerza, destreza, resistencia));
            } catch (CaracteristicaNegativaException e) {
                System.out.println("Error al crear el villano: " + e.getMessage());
            }
        }
    }

    public static int validarEntero(Scanner scanner) {
        int dato = 0;
        boolean datoValido = false;
        do {
            if (scanner.hasNextInt()) {
                dato = scanner.nextInt();
                if (dato >= 0) {
                    datoValido = true;
                } else {
                    System.out.print("Ingrese un entero no negativo: ");
                }
            } else {
                System.out.print("Ingrese un entero: ");
                scanner.next();
            }
        } while (datoValido != true || dato < 0);
        scanner.nextLine(); // Limpiar el buffer del scanner
        return dato;
    }

}
