package heroesVillanos;

//import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public abstract class Personaje extends Competidor {
    private String nombreReal;
    private Map<Caracteristica, Double> caracteristicas = new HashMap<Caracteristica, Double>();

    public Personaje(String nombreReal, String nombre, double velocidad, double fuerza, double resistencia,
            double destreza) {
        this.nombreReal = nombreReal;
        this.nombre = nombre;
        this.esLiga = false;
        caracteristicas.put(Caracteristica.VELOCIDAD, velocidad);
        caracteristicas.put(Caracteristica.FUERZA, fuerza);
        caracteristicas.put(Caracteristica.RESISTENCIA, resistencia);
        caracteristicas.put(Caracteristica.DESTREZA, destreza);
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombrePersonaje) {
        this.nombre = nombrePersonaje;
    }

    public double getCaracteristica(Caracteristica caracteristica) {
        return caracteristicas.get(caracteristica);
    }
}