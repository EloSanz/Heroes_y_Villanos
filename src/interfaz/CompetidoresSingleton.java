package interfaz;

import java.util.HashMap;
import java.util.Map;

import heroesVillanos.Competidor;

public class CompetidoresSingleton {

    private static CompetidoresSingleton instance;
    private Map<String, Competidor> competidores;

    private CompetidoresSingleton() {
        competidores = new HashMap<>();
    }

    public static CompetidoresSingleton getInstance() {
        if (instance == null) {
            instance = new CompetidoresSingleton();
        }
        return instance;
    }

    public Map<String, Competidor> getCompetidores() {
        return competidores;
    }
}
