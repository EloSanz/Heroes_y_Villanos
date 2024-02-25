package heroesVillanos;

import Excepciones.CaracteristicaNegativaException;

public class Villano extends Personaje {

	@Override
	public String toString() {
		return String.format("%-15s: \t%-20s %-20s  \t%11.1f\t%11.1f\t%11.1f\t%11.1f",
				"Villano",
				this.getNombre(),
				this.getNombreReal(),
				this.getCaracteristica(Caracteristica.VELOCIDAD),
				this.getCaracteristica(Caracteristica.FUERZA),
				this.getCaracteristica(Caracteristica.DESTREZA),
				this.getCaracteristica(Caracteristica.RESISTENCIA));
	}

	public Villano(String nombreReal, String nombrePersonaje, int velocidad, int fuerza, int resistencia, int destreza)
			throws CaracteristicaNegativaException {
		super(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia, destreza);
		esLiga = false;
	}

	public boolean esHeroe() {
		return false;
	}

	public boolean esVillano() {
		return true;
	}

	@Override
	public boolean contieneA(String string) {
		return this.nombre.equals(string);
	}

	@Override
	public double calcularValorTotalCaracteristica(Caracteristica caracteristica) {
		return this.getCaracteristica(caracteristica);
	}

	@Override
	public int contarCompetidores() {
		return 1;
	}
}
