package heroesVillanos;
import java.util.Comparator;
import java.util.Map;

public class CompetidorComparator implements Comparator<Map.Entry<String, Competidor>> {

    private Caracteristica[] caracteristicas;

    public CompetidorComparator(Caracteristica[] caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public int compare(Map.Entry<String, Competidor> entry1, Map.Entry<String, Competidor> entry2) {
        Competidor c1 = entry1.getValue();
        Competidor c2 = entry2.getValue();
        for (Caracteristica caracteristica : caracteristicas) {
            double valorC1 = c1.getCaracteristica(caracteristica);
            double valorC2 = c2.getCaracteristica(caracteristica);
            if (valorC1 != valorC2) {
                return Double.compare(valorC1, valorC2);
            }
        }
        // Si los personajes tienen las mismas características, se ordenan por nombre
        return entry1.getKey().compareTo(entry2.getKey());
    }
}
