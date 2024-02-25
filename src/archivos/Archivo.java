package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.nio.charset.StandardCharsets; // para que lea caracteres especiales

import Excepciones.CaracteristicaNegativaException;
import heroesVillanos.Competidor;
import heroesVillanos.Heroe;
import heroesVillanos.Liga;
import heroesVillanos.Personaje;
import heroesVillanos.Villano;

public class Archivo {
    private String nombreArchivo;

    public Archivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean cargarPersonajes(Map<String, Competidor> competidores) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(", "); // lee linea por linea
                if (partes.length == 7) { // si la linea es correcta
                    String tipo = partes[0];
                    String nombreReal = partes[1];
                    String nombrePersonaje = partes[2];
                    int velocidad = Integer.parseInt(partes[3]);
                    int fuerza = Integer.parseInt(partes[4]);
                    int resistencia = Integer.parseInt(partes[5]);
                    int destreza = Integer.parseInt(partes[6]);
                    try {
                        Competidor competidor;
                        if (tipo.equals("Héroe")) {
                            competidor = new Heroe(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia,
                                    destreza);
                        } else if (tipo.equals("Villano")) {
                            competidor = new Villano(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia,
                                    destreza);
                        } else {
                            // Manejar el caso donde el tipo no es válido (podría guardar en un archivo de
                            // errores)
                            continue;
                        }
                        competidores.put(nombrePersonaje, competidor); // Agregar el competidor al HashMap usando el
                                                                       // nombre como clave

                    } catch (CaracteristicaNegativaException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Guardar la línea incorrecta en un archivo de errores si es necesario
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void cargarLigas(String nombreArchivo, Map<String, Competidor> competidores) {
        try (BufferedReader bf = new BufferedReader(new FileReader(nombreArchivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] partes = linea.split(", ");
                Liga liga = new Liga(partes[0]);// el primero es el nombre de la liga en estos registros.
                ArrayList<Personaje> personajesCargados = new ArrayList<>();
                for (int i = 1; i < partes.length; i++)// cargo miembros.
                {
                    String miembroBuscado = partes[i];
                    for (Competidor competidor : competidores.values()) {
                        if (miembroBuscado.equals(competidor.getNombre())) {
                            // si es un personaje y aun no fue cargado en el ArrayList ->
                            if (!competidor.getEsLiga() && !personajesCargados.contains((Personaje) competidor)) {
                                liga.agregarMiembro(competidor);
                                personajesCargados.add((Personaje) competidor);
                            }
                            if (competidor.getEsLiga()) {
                                Liga subliga = (Liga) competidor;
                                if (!subliga.esHomogenea())
                                    liga.setEsHomogenea(false);

                                liga.agregarMiembro(competidor);
                                Map<String, Competidor> miembrosSubliga = subliga.getCompetidores();

                                for (Competidor miembro : miembrosSubliga.values()) {
                                    if (personajesCargados.contains(miembro)) {
                                        liga.quitarMiembro(miembro);
                                    }
                                }
                            }
                            break; // no hay competidores con el mismo nombre.
                        }
                    }
                }
                competidores.put(liga.getNombre(), liga);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

}