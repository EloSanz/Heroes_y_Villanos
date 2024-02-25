package interfaz;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import archivos.Archivo;
import heroesVillanos.Competidor;
import heroesVillanos.Liga;

public class AdministracionDeLigas {

    public static void administrador(Map<String, Competidor> competidores) {
        Scanner scanner = new Scanner(System.in);
        String mensaje = "1) Carga desde archivo.\n" +
                "2) Creación de liga.\n" +
                "3) Listado de ligas.\n" +
                "4) Guardar en archivo todas las ligas.\n" +
                "5) Volver al menu principal\n";
        int opcion;
        do {
            System.out.println("\nAdministracion de Ligas.\n--------------------------------");
            opcion = InterfazDeUsuario.obtenerOpcion(mensaje, 1, 5, scanner);

            switch (opcion) {
                case 1:
                    System.out.println("Cargando Ligas.\nIngrese el nombre del archivo: ");
                    scanner.nextLine(); // Consumir el carácter de nueva línea pendiente
                    String path = scanner.nextLine();
                    System.out.println(path);
                    Archivo archivo = new Archivo(path);
                    archivo.cargarLigas(path, competidores);

                    System.out.println("Cargando Ligas.\nIngrese el nombre del archivo: ");

                    System.out.println("Cargado: " + path);
                    break;
                case 2:
                    crearLiga(scanner, competidores);
                    break;
                case 3:
                    InterfazDeUsuario.mostrarLigas(competidores);
                    break;
                case 4:
                    InterfazDeUsuario.guardarEnArchivo(competidores, false);
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    InterfazDeUsuario.menu();
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static void crearLiga(Scanner scanner, Map<String, Competidor> competidores) {
        System.out.println("Creación de Liga");
        String nombreLiga;
        boolean nombreValido = true;
        do {
            System.out.print("Ingrese Nombre de la Liga: ");
            scanner.nextLine();
            nombreLiga = scanner.nextLine();
            if (competidores.containsKey(nombreLiga)) {
                System.out.println("Ya existe un competidor con ese nombre. Por favor, ingrese otro nombre.");
                nombreValido = false;
            }
        } while (!nombreValido);
        Liga liga = new Liga(nombreLiga);
        agregarMiembrosALiga(scanner, competidores, liga);
        competidores.put(nombreLiga, liga); // Agregar la liga al mapa usando su nombre como clave
        System.out.println("Liga creada exitosamente.");
    }
    private static void agregarMiembrosALiga(Scanner scanner, Map<String, Competidor> competidores, Liga liga) {
        String nombreBuscado;
        List<String> miembrosCargados = new ArrayList<>(); // Lista para rastrear los miembros ya cargados
        do {
            System.out.print("Ingrese el nombre de un competidor para agregar a la liga (o 'fin' para terminar): ");
            nombreBuscado = scanner.nextLine();
            Competidor competidor = competidores.get(nombreBuscado); // Obtener el competidor por su nombre
            if (competidor != null) {
                if (competidor.getEsLiga()) {
                    Liga subliga = (Liga) competidor;
                    if (liga.contieneA(subliga)) {
                        System.out.println("La subliga " + subliga.getNombre() + " ya está presente en la liga principal.");
                    } else {
                        liga.agregarMiembro(subliga);
                        for (Competidor miembro : subliga.getCompetidores().values()) {
                            if (miembrosCargados.contains(miembro.getNombre())) {
                                liga.quitarMiembro(miembro);
                                miembrosCargados.add(miembro.getNombre());
                            }
                        }
                    }
                } else {//es personaje
                    if (!miembrosCargados.contains(competidor.getNombre()) && !liga.contieneA(competidor)) {
                        liga.agregarMiembro(competidor);
                        miembrosCargados.add(competidor.getNombre());
                    } else {
                        System.out.println("El competidor " + competidor.getNombre() + " ya está presente en la liga principal.");
                    }
                }
            } else if (!nombreBuscado.equals("fin")) {
                System.out.println("No se encontró ningún competidor con ese nombre.");
            }
        } while (!nombreBuscado.equals("fin"));
    }

    
}
