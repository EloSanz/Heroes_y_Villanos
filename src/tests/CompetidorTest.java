package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import heroesVillanos.*;
import Excepciones.*;

public class CompetidorTest {
    @Test
    public void testEsHeroe() {
        Competidor p1 = new Villano("Arthur Fleck", "El Guasón", 200, 210, 220, 205);
        Competidor p2 = new Heroe("Barry Allen", "Flash", 500, 250, 240, 400);
        assertTrue(p2.esHeroe());
        assertFalse(p1.esHeroe());
    }

    @Test
    public void testEsVillano() {
        Competidor p1 = new Villano("Arthur Fleck", "El Guasón", 200, 210, 220, 205);
        Competidor p2 = new Heroe("Barry Allen", "Flash", 500, 250, 240, 400);
        assertTrue(p1.esVillano());
        assertFalse(p2.esVillano());
    }

    @Test
    public void testEsLiga() {
        Competidor l1 = new Liga("Testing");
        assertTrue(l1.getEsLiga());
    }

    @Test
    public void testLigaXLiga() {
        Liga liga1 = new Liga("Liga 1");
        Liga liga2 = new Liga("Liga 2");

        Competidor p1 = new Villano("Arthur Fleck", "El Guasón", 200, 210, 220, 205);
        Competidor p2 = new Heroe("Barry Allen", "Flash", 500, 250, 240, 400);

        Competidor p3 = new Heroe("Steve Rogers", "Captain America", 300, 350, 400, 350);
        Competidor p4 = new Villano("Tony Stark", "Iron Man", 400, 400, 400, 350);

        liga1.agregarMiembro(p1);
        liga1.agregarMiembro(p2);
        liga2.agregarMiembro(p3);
        liga2.agregarMiembro(p4);

        try {
            assertEquals(2, liga1.esGanador(liga2, Caracteristica.VELOCIDAD, false));
        } catch (CaracteristicaInexistenteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLigaXLigaHomogeneas() {
        Liga liga1 = new Liga("Liga 1");
        Liga liga2 = new Liga("Liga 2"); // HOMOGENEA

        Competidor p1 = new Villano("Arthur Fleck", "El Guasón", 200, 210, 220, 205);
        Competidor p2 = new Heroe("Barry Allen", "Flash", 500, 250, 240, 400);

        Competidor p3 = new Heroe("Steve Rogers", "Captain America", 300, 350, 400, 350);
        Competidor p4 = new Heroe("Tony Stark", "Iron Man", 400, 400, 400, 350);

        liga1.agregarMiembro(p1);
        liga1.agregarMiembro(p2);
        liga2.agregarMiembro(p3);
        liga2.agregarMiembro(p4);

        try {
            assertEquals(2, liga1.esGanador(liga2, Caracteristica.VELOCIDAD, false));
        } catch (CaracteristicaInexistenteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPersonajeXLiga() {
        Competidor p = new Heroe("Tony Stark", "Iron Man", 400, 400, 400, 350);

        Liga liga1 = new Liga("Liga 1");

        Competidor p1 = new Villano("Arthur Fleck", "El Guasón", 200, 210, 220, 205);
        Competidor p2 = new Heroe("Barry Allen", "Flash", 500, 250, 240, 400);

        liga1.agregarMiembro(p1);
        liga1.agregarMiembro(p2);

        try {
            assertEquals(1, p.esGanador(liga1, Caracteristica.VELOCIDAD, false));
        } catch (CaracteristicaInexistenteException e) {
            e.printStackTrace();
        }
    }

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
