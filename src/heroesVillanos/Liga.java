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
        competidores.put(miembroNuevo.getNombre(), miembroNuevo); 
        
        if (miembroNuevo.getEsLiga()) {
            Liga ligaNueva = (Liga) miembroNuevo; 
            if (esLigaDeHeroes && ligaNueva.tieneVillano()) { // Si la liga principal es de heroes y la nueva liga tiene al menos un villano
                esLigaDeHeroes = false;
                esHomogenea = false;
            } else if (!esLigaDeHeroes && esHomogenea && ligaNueva.tieneHeroe()) {  // Si la liga principal no es de heroes,
                esHomogenea = false;                                     // pero sigue siendo homogrnea y la nueva liga tiene al menos un heroe 
            }
        } else {
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
    }
    public boolean tieneHeroe() {
        for (Competidor miembro : this.competidores.values()) {
            if (miembro.esHeroe() || miembro.getEsLiga() && ((Liga)miembro).tieneHeroe() ) {
                return true;
            }
        }
        return false;
    }

    public boolean tieneVillano() {
        for (Competidor miembro : this.competidores.values()) {
            if (miembro.esVillano() || miembro.getEsLiga() && ((Liga)miembro).tieneVillano() ) {
                return true;
            }
        }
        return false;
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
        if(cantidad > 0)
        {
            if(esHomogenea)
                return (total/cantidad) * 1.1;
            else
                return total/cantidad;
        }else
            return 0;
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
