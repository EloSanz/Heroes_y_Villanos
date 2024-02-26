package heroesVillanos;

public class Heroe extends Personaje {

	@Override
	public String toString() {
		return String.format("%-15s: \t%-20s %-20s  \t%11.1f\t%11.1f\t%11.1f\t%11.1f",
				"Héroe",
				this.getNombre(),
				this.getNombreReal(),
				this.getCaracteristica(Caracteristica.VELOCIDAD),
				this.getCaracteristica(Caracteristica.FUERZA),
				this.getCaracteristica(Caracteristica.DESTREZA),
				this.getCaracteristica(Caracteristica.RESISTENCIA));
	}

	public void mostrar() {
		System.out.println(this);
	}

	public Heroe(String nombreReal, String nombrePersonaje, double velocidad, double fuerza, double resistencia,
			double destreza) {
		super(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia, destreza);
		esLiga = false;
	}

	public boolean esHeroe() {
		return true;
	}

	public boolean esVillano() {
		return false;
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
