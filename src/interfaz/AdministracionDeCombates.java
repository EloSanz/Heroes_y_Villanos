package interfaz;

import java.util.Map;
import java.util.Scanner;

import Excepciones.CaracteristicaInexistenteException;
import heroesVillanos.Caracteristica;
import heroesVillanos.Competidor;
import heroesVillanos.Liga;
import heroesVillanos.Personaje;

public class AdministracionDeCombates {
    public static void realizacionDeCombates(Map<String, Competidor> competidores) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nRealizacion de Combates.\n--------------------------------");

        String mensaje = "1) Combate entre personajes.\n" +
                "2) Combate entre ligas.\n" +
                "3) Combate entre personaje y liga.\n";

        int opcion = InterfazDeUsuario.obtenerOpcion(mensaje, 1, 4, scanner);

        switch (opcion) {
            case 1:
                combatePersonajeXPersonaje(scanner, competidores);
                break;
            case 2:
                combateLigaXLiga(scanner, competidores);
                break;
            case 3:
                combateLigaXPersonaje(scanner, competidores);
                break;
            case 4:
                System.out.println("Volviendo al menú principal...");
                InterfazDeUsuario.menu();
                break;
        }
        InterfazDeUsuario.menu();
    }

    private static void combatePersonajeXPersonaje(Scanner scanner, Map<String, Competidor> competidores) {
        System.out.println("Combate entre Personajes");
        Personaje personaje1, personaje2 = null;

        personaje1 = getPersonaje(scanner, competidores, 1);
        System.out.println(personaje1.getNombre() + " Seleccionado. Pulse para avanzar");
        do {
            personaje2 = getPersonaje(scanner, competidores, 2);
            if (personaje1 == personaje2) {
                System.out
                        .println("No se puede combatir contra si mismo. Ingrese otro personaje...\nPulse para avanzar");

            }
        } while (personaje1 == personaje2);

        int op = InterfazDeUsuario.obtenerOpcion("Ingrese categoría a competir:\n" +
                "Velocidad: 1\n" +
                "Fuerza: 2\n" +
                "Destreza: 3\n" +
                "Resistencia: 4\n", 1, 4, scanner);
        System.out.println(
                "Heroe/Villano\t\tNombre Personaje     Nombre Real                      Velocidad       Fuerza          Destreza        Resistencia");
        System.out.println(personaje1 + "\n\t\t\t********contra********\n" + personaje2);
        try {
            switch (op) {
                case 1:
                    if (personaje1.esGanador(personaje2, Caracteristica.VELOCIDAD, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 2:
                    if (personaje1.esGanador(personaje2, Caracteristica.FUERZA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 3:
                    if (personaje1.esGanador(personaje2, Caracteristica.DESTREZA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 4:
                    if (personaje1.esGanador(personaje2, Caracteristica.RESISTENCIA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
            }
        } catch (CaracteristicaInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Personaje getPersonaje(Scanner scanner, Map<String, Competidor> competidores, int numeroPersonaje) {
        scanner.nextLine();// limpio el buffer
        while (true) {
            System.out.print("Seleccione el personaje " + numeroPersonaje + ": ");
            String nombrePersonaje = scanner.nextLine().trim(); // Leer y limpiar la línea de entrada
            Competidor competidor = competidores.get(nombrePersonaje);
            if (competidor == null) {
                System.out.println("No existe un personaje con ese nombre. Por favor, ingrese otro nombre.");
            } else if (competidor.getEsLiga()) {
                System.out.println(nombrePersonaje + " es una liga. Ingrese otro nombre.");
            } else {
                return (Personaje) competidor;
            }
        }
    }

    private static void combateLigaXPersonaje(Scanner scanner, Map<String, Competidor> competidores) {
        System.out.println("Combate entre Liga y Personaje");
        Liga liga1;
        Personaje personaje1;

        liga1 = getLiga(scanner, competidores);
        System.out.println(liga1.getNombre() + " Seleccionada. Pulse para avanzar");
        do {
            personaje1 = getPersonaje(scanner, competidores, 1);
            if (liga1.contieneA(personaje1)) {
                System.out.println(personaje1.getNombre() + " ya es miembro de la liga. Ingrese otro personaje...");
            }
        } while (liga1.contieneA(personaje1));

        int op = InterfazDeUsuario.obtenerOpcion("Ingrese categoría a competir:\n" +
                "Velocidad: 1\n" +
                "Fuerza: 2\n" +
                "Destreza: 3\n" +
                "Resistencia: 4\n", 1, 4, scanner);

        System.out.println(
                "Heroe/Villano\t\tNombre Personaje     Nombre Real                      Velocidad       Fuerza          Destreza        Resistencia");
        InterfazDeUsuario.mostrarCompetidor(liga1);
        System.out.println("\t********contra********\n" + personaje1);

        try {
            switch (op) {
                case 1:
                    if (personaje1.esGanador(liga1, Caracteristica.VELOCIDAD, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 2:
                    if (personaje1.esGanador(liga1, Caracteristica.FUERZA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 3:
                    if (personaje1.esGanador(liga1, Caracteristica.DESTREZA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 4:
                    if (personaje1.esGanador(liga1, Caracteristica.RESISTENCIA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
            }
        } catch (CaracteristicaInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Liga getLiga(Scanner scanner, Map<String, Competidor> competidores) {
        scanner.nextLine(); // Limpiar el buffer
        while (true) {
            System.out.print("Seleccione la liga ");
            String nombreLiga = scanner.nextLine().trim(); // Leer y limpiar la línea de entrada
            Competidor competidor = competidores.get(nombreLiga);
            if (competidor == null) {
                System.out.println("No existe una liga con ese nombre. Por favor, ingrese otro nombre.");
            } else if (!competidor.getEsLiga()) {
                System.out.println(nombreLiga + " no es una liga. Ingrese otro nombre.");
            } else {
                return (Liga) competidor;
            }
        }
    }

    private static void combateLigaXLiga(Scanner scanner, Map<String, Competidor> competidores) {
        System.out.println("Combate entre Ligas");
        Liga liga1, liga2;

        liga1 = getLiga(scanner, competidores, 1);
        System.out.println(liga1.getNombre() + " Seleccionada. Pulse para avanzar");
        do {
            liga2 = getLiga(scanner, competidores, 2);
            if (liga1 == liga2) {
                System.out.println("No puede competir una liga contra si misma.\nPulse para avanzar");
            } else if (liga1.contieneA(liga2)) {
                System.out.println(liga1.getNombre() + " contiene a " + liga2.getNombre()
                        + " No pueden competir.\nPulse para avanzar");
            }
        } while (liga1 == liga2 || liga1.contieneA(liga2) || liga2.contieneA(liga1));

        System.out.println(liga2.getNombre() + " Seleccionada. Pulse para avanzar");

        int op = InterfazDeUsuario.obtenerOpcion("Ingrese categoría a competir:\n" +
                "Velocidad: 1\n" +
                "Fuerza: 2\n" +
                "Destreza: 3\n" +
                "Resistencia: 4\n", 1, 4, scanner);

        // System.out.println("Heroe/Villano\t\tNombre Personaje Nombre Real Velocidad
        // Fuerza Destreza Resistencia");
        System.out.println(liga1 + "\n\t\t\t********contra********\n" + liga2);

        try {
            switch (op) {
                case 1:
                    if (liga1.esGanador(liga2, Caracteristica.VELOCIDAD, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 2:
                    if (liga1.esGanador(liga2, Caracteristica.FUERZA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 3:
                    if (liga1.esGanador(liga2, Caracteristica.DESTREZA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
                case 4:
                    if (liga1.esGanador(liga2, Caracteristica.RESISTENCIA, true) == 0) {
                        System.out.println("Los competidores EMPATAN TOTALMENTE.");
                    }
                    break;
            }
        } catch (CaracteristicaInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Liga getLiga(Scanner scanner, Map<String, Competidor> competidores, int numeroLiga) {
        scanner.nextLine(); // Limpiar el buffer
        while (true) {
            System.out.print("Seleccione la liga " + numeroLiga + ": ");
            String nombreLiga = scanner.nextLine().trim(); // Leer y limpiar la línea de entrada
            Competidor competidor = competidores.get(nombreLiga);
            if (competidor == null) {
                System.out.println("No existe una liga con ese nombre. Por favor, ingrese otro nombre.");
            } else if (!competidor.getEsLiga()) {
                System.out.println(nombreLiga + " no es una liga. Ingrese otro nombre.");
            } else {
                return (Liga) competidor;
            }
        }
    }

}
