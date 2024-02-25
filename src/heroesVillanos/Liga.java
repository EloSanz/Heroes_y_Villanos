package heroesVillanos;

import java.util.HashMap;
import java.util.Map;

public class Liga extends Competidor {
    private Map<String, Competidor> competidores; // tanto Personajes como Ligas
    private boolean esHomogenea = true;
    private boolean esLigaDeHeroes = true;

    //
    public Liga(String nombre) {
        this.esLiga = true;
        this.setNombre(nombre);
        this.competidores = new HashMap<>();// composite implementado
    }

    @Override
    public String toString() {
        return "Liga: " + this.nombre;
    }

    public void agregarMiembro(Competidor miembroNuevo) {
        competidores.put(miembroNuevo.getNombre(), miembroNuevo); // Agregar el miembro al HashMap
        // Actualizar los indicadores de la liga
        if (competidores.isEmpty()) {
            esLigaDeHeroes = !miembroNuevo.esVillano();
        } else {
            if (esLigaDeHeroes && miembroNuevo.esVillano()) {
                esLigaDeHeroes = false;
                esHomogenea = false;
            } else if (!esLigaDeHeroes && esHomogenea && miembroNuevo.esHeroe()) {
                esHomogenea = false;
            }
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public double getCaracteristica(Caracteristica caracteristica) {
        // Este método sigue dividiendo el valor total por el número de competidores
        // para obtener el promedio de la característica solicitada.
        double total = calcularValorTotalCaracteristica(caracteristica);
        int cantidad = contarCompetidores();
        return cantidad > 0 ? total / cantidad : 0;
    }

    @Override
    public double calcularValorTotalCaracteristica(Caracteristica caracteristica) {
        double suma = 0;
        for (Competidor competidor : competidores.values()) {
            suma += competidor.calcularValorTotalCaracteristica(caracteristica);
        }
        return suma;
    }

    @Override
    public int contarCompetidores() {
        int contador = 0;
        for (Competidor competidor : competidores.values()) {
            contador += competidor.contarCompetidores();
        }
        return contador;
    }

    @Override
    public boolean esHeroe() {
        return this.esLigaDeHeroes;
    }

    @Override
    public boolean esVillano() {
        return (!esLigaDeHeroes && esHomogenea);
    }

    public boolean contieneA(String nombre) {

        return competidores.containsKey(nombre); // Utilizar containsKey para verificar si la clave existe en el HashMap
    }

    public boolean contieneA(Competidor other) {
        if (competidores.containsKey(other.getNombre())) {
            return true;
        } else {
            for (Competidor competidor : competidores.values()) {
                if (competidor.getEsLiga()) {
                    Liga liga = (Liga) competidor;
                    if (liga.contieneA(other)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Map<String, Competidor> getCompetidores() {
        return this.competidores;
    }

    public void quitarMiembro(Competidor miembro) {
        this.competidores.remove(miembro.getNombre());
    }

    public boolean esHomogenea() {
        return this.esHomogenea;
    }

    public void setEsHomogenea(boolean b) {
        this.esHomogenea = b;
    }
}
