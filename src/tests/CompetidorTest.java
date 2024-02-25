package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import heroesVillanos.Caracteristica;
import heroesVillanos.Competidor;
import heroesVillanos.Heroe;
import heroesVillanos.Villano;
import Excepciones.CaracteristicaInexistenteException;

public class CompetidorTest {
    @Test
    public void esGanadorTestVelocidad() {
        try {
            Competidor p1 = new Villano("Arthur Fleck", "El Guasón", 200, 210, 220, 205);
            Competidor p2 = new Heroe("Barry Allen", "Flash", 500, 250, 240, 400);
            assertEquals(2, p1.esGanador(p2, Caracteristica.VELOCIDAD, false));
        } catch (CaracteristicaInexistenteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void esGanadorTestFuerza() {
        try {
            Competidor p1 = new Heroe("Steve Rogers", "Captain America", 300, 350, 400, 350);
            Competidor p2 = new Heroe("Tony Stark", "Iron Man", 400, 400, 400, 350);
            assertEquals(2, p1.esGanador(p2, Caracteristica.FUERZA, false));
        } catch (CaracteristicaInexistenteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void esGanadorTestResistencia() {
        try {
            Competidor p1 = new Villano("Victor Fries", "Mr. Freeze", 320, 280, 350, 300);
            Competidor p2 = new Villano("Selina Kyle", "Catwoman", 280, 300, 250, 350);
            assertEquals(1, p1.esGanador(p2, Caracteristica.RESISTENCIA, false));
        } catch (CaracteristicaInexistenteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void esGanadorTestDestreza() {
        try {
            Competidor p1 = new Heroe("Norman Osborn", "Green Goblin", 300, 250, 300, 550);
            Competidor p2 = new Villano("Peter Parker", "Spider-Man", 350, 300, 300, 350);
            assertEquals(1, p1.esGanador(p2, Caracteristica.DESTREZA, false));
        } catch (CaracteristicaInexistenteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void esGanadorTestEmpate() {
        try {
            Competidor p1 = new Heroe("Lucas Diaz", "Pepe patea traseros", 300, 250, 100, 500);
            Competidor p2 = new Villano("Alejandro Perez", "Pana Rabbit", 300, 250, 100, 500);
            assertEquals(0, p1.esGanador(p2, Caracteristica.VELOCIDAD, false));
            assertEquals(0, p2.esGanador(p1, Caracteristica.VELOCIDAD, false));
        } catch (CaracteristicaInexistenteException e) {
            e.printStackTrace();
        }

    }
}
