package heroesVillanos;

import Excepciones.CaracteristicaInexistenteException;

public abstract class Competidor {
    protected String nombre;
    protected boolean esLiga;

    @Override
    public abstract String toString();

    public abstract double getCaracteristica(Caracteristica caracteristica);

    public abstract double calcularValorTotalCaracteristica(Caracteristica caracteristica);

    public abstract int contarCompetidores();

    public String getNombre() {
        return this.nombre;
    }

    public boolean getEsLiga() {
        return this.esLiga;
    }

    public int venceA(Competidor competidor, Caracteristica caracteristica, int contador, boolean imprimirPorPantalla)
            throws CaracteristicaInexistenteException {
        if (contador > 3) {
            return 0;
        } else {
            switch (caracteristica) {
                case VELOCIDAD:
                    if (this.getCaracteristica(Caracteristica.VELOCIDAD) == competidor
                            .getCaracteristica(Caracteristica.VELOCIDAD)) {
                        if (imprimirPorPantalla)
                            System.out.println("Los competidores EMPATAN en VELOCIDAD");
                        return venceA(competidor, Caracteristica.FUERZA, contador + 1, imprimirPorPantalla);
                    } else if (this.getCaracteristica(Caracteristica.VELOCIDAD) > competidor
                            .getCaracteristica(Caracteristica.VELOCIDAD)) {
                        if (imprimirPorPantalla)
                            System.out.println("El competidor " + this.getNombre() + " GANA en VELOCIDAD a "
                                    + competidor.getNombre());
                        return 1;
                    } else {
                        if (imprimirPorPantalla)
                            System.out.println("El competidor " + competidor.getNombre() + " GANA en VELOCIDAD a "
                                    + this.getNombre());
                        return 2;
                    }
                case FUERZA:
                    if (this.getCaracteristica(Caracteristica.FUERZA) == competidor
                            .getCaracteristica(Caracteristica.FUERZA)) {
                        if (imprimirPorPantalla)
                            System.out.println("Los competidores EMPATAN en FUERZA");
                        return venceA(competidor, Caracteristica.RESISTENCIA, contador + 1, imprimirPorPantalla);
                    } else if (this.getCaracteristica(Caracteristica.FUERZA) > competidor
                            .getCaracteristica(Caracteristica.FUERZA)) {
                        if (imprimirPorPantalla)
                            System.out.println("El competidor " + this.getNombre() + " GANA en FUERZA a "
                                    + competidor.getNombre());
                        return 1;
                    } else {
                        if (imprimirPorPantalla)
                            System.out.println("El competidor " + competidor.getNombre() + " GANA en FUERZA a "
                                    + this.getNombre());
                        return 2;
                    }
                case RESISTENCIA:
                    if (this.getCaracteristica(Caracteristica.RESISTENCIA) == competidor
                            .getCaracteristica(Caracteristica.RESISTENCIA)) {
                        if (imprimirPorPantalla)
                            System.out.println("Los competidores EMPATAN en RESISTENCIA");
                        return venceA(competidor, Caracteristica.DESTREZA, contador + 1, imprimirPorPantalla);
                    } else if (this.getCaracteristica(Caracteristica.RESISTENCIA) > competidor
                            .getCaracteristica(Caracteristica.RESISTENCIA)) {
                        if (imprimirPorPantalla)
                            System.out.println("El competidor " + this.getNombre() + " GANA en RESISTENCIA a "
                                    + competidor.getNombre());
                        return 1;
                    } else {
                        if (imprimirPorPantalla)
                            System.out.println("El competidor " + competidor.getNombre() + " GANA en RESISTENCIA a "
                                    + this.getNombre());
                        return 2;
                    }
                case DESTREZA:
                    if (this.getCaracteristica(Caracteristica.DESTREZA) == competidor
                            .getCaracteristica(Caracteristica.DESTREZA)) {
                        if (imprimirPorPantalla)
                            System.out.println("Los competidores EMPATAN en DESTREZA");
                        return venceA(competidor, Caracteristica.VELOCIDAD, contador + 1, imprimirPorPantalla);
                    } else if (this.getCaracteristica(Caracteristica.DESTREZA) > competidor
                            .getCaracteristica(Caracteristica.DESTREZA)) {
                        if (imprimirPorPantalla)
                            System.out.println("El competidor " + this.getNombre() + " GANA en DESTREZA a "
                                    + competidor.getNombre());
                        return 1;
                    } else {
                        if (imprimirPorPantalla)
                            System.out.println("El competidor " + competidor.getNombre() + " GANA en DESTREZA a "
                                    + this.getNombre());
                        return 2;
                    }
                default:
                    throw new CaracteristicaInexistenteException("Característica inexistente: " + caracteristica);
            }
        }
    }

    public int esGanador(Competidor competidor, Caracteristica caracteristica, boolean imprimirPorPantalla)
            throws CaracteristicaInexistenteException { // FUNCION ENVOLTORIO
        int contador = 0;
        return venceA(competidor, caracteristica, contador, imprimirPorPantalla);
    }

    public abstract boolean esHeroe();

    public abstract boolean esVillano();

    public abstract boolean contieneA(String string);
}