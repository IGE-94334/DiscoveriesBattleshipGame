package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void buildShip() {
        //Ship s = Ship.buildShip("Galeao", null, null);
        //(s);
        Compass c = Compass.values().length > 0 ? Compass.values()[0] : null;
        Position p = new Position(0, 0);
        Ship s = Ship.buildShip("N", c, p);
        assertNull(s);
        s = Ship.buildShip("Galeao", c, p);
        assertNotNull(s);
        assertEquals("Galeao", s.getCategory());
        assertEquals(p, s.getPosition());
        assertEquals(c, s.getBearing());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void getCategory(Compass c) {
        Ship s = Ship.buildShip("Barca", c, new Position(1,1));
        assertEquals("Barca", s.getCategory());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void getPositions(Compass c) {
        Ship s = Ship.buildShip("Caravela", c, new Position(2, 3));
        assertEquals(2, s.getPositions().size());
        if(c==Compass.NORTH || c==Compass.SOUTH) {
            assertTrue(s.getPositions().contains(new Position(2, 3)));
            assertTrue(s.getPositions().contains(new Position(3, 3)));
        }
        else {
            assertTrue(s.getPositions().contains(new Position(2, 3)));
            assertTrue(s.getPositions().contains(new Position(2, 4)));
        }
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void getPosition(Compass c) {
        Position p = new Position(5, 5);
        Ship s = Ship.buildShip("Barca", c, p);
        assertEquals(p, s.getPosition());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void getBearing(Compass c) {
        Ship s = Ship.buildShip("Barca", c, new Position(0,0));
        assertEquals(c, s.getBearing());
    }

    @Test
    void stillFloating() {
        Ship s = Ship.buildShip("Caravela", Compass.charToCompass('e') , new Position(1, 1));
        assertTrue(s.stillFloating());
        s.getPositions().get(0).shoot();
        assertTrue(s.stillFloating());
        s.getPositions().get(1).shoot();
        assertFalse(s.stillFloating());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void getTopMostPos(Compass c) {
        Ship s = Ship.buildShip("Caravela", c, new Position(5, 5));
        assertEquals(5, s.getTopMostPos());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void getBottomMostPos(Compass c) {
        Ship s = Ship.buildShip("Caravela", c, new Position(5, 5));
        if(c==Compass.NORTH || c==Compass.SOUTH)
            assertEquals(6, s.getBottomMostPos());
        else
            assertEquals(5, s.getBottomMostPos());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void getLeftMostPos(Compass c) {
        Ship s = Ship.buildShip("Caravela", c, new Position(5, 5));
        assertEquals(5, s.getLeftMostPos());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void getRightMostPos(Compass c) {
        Ship s = Ship.buildShip("Caravela", c, new Position(5, 5));
        if(c==Compass.NORTH || c==Compass.SOUTH)
            assertEquals(5, s.getRightMostPos());
        else
            assertEquals(6, s.getRightMostPos());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void occupies(Compass c) {
        Position p1 = new Position(3, 3);
        Position p2 = new Position(4, 4);
        Ship s = Ship.buildShip("Caravela", c, p1);
        assertTrue(s.occupies(p1));
        assertFalse(s.occupies(p2));
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void tooCloseTo(Compass c) {
        Position p1 = new Position(3, 3);
        Position p2 = new Position(4, 4); // adjacent
        Position p3 = new Position(6, 6);  // not adjacent
        Ship s = Ship.buildShip("Nau", c, p1);
        assertTrue(s.tooCloseTo(p2));
        assertFalse(s.tooCloseTo(p3));
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void testTooCloseTo(Compass c) {
        Position p1 = new Position(1, 1);
        Position p2 = new Position(1, 2);
        Ship s1 = Ship.buildShip("Barca", c, p1);
        Ship s2 = Ship.buildShip("Barca", c, p2);
        assertTrue(s1.tooCloseTo(s2));

        Position p3 = new Position(5, 5);
        Ship s3 = Ship.buildShip("Barca", c, p3);
        assertFalse(s1.tooCloseTo(s3));
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void shoot(Compass c) {
        Ship s = Ship.buildShip("Fragata", c, new Position(2, 2));
        assertFalse(s.getPositions().get(0).isHit());
        s.shoot(s.getPositions().get(0));
        assertTrue(s.getPositions().get(0).isHit());
    }

    @ParameterizedTest
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    void testToString(Compass c) {
        Ship s = Ship.buildShip("Barca", c, new Position(1, 1));
        String str = s.toString();
        if(c==Compass.NORTH)
            assertTrue(str.contains("[Barca n Linha = " + s.getPosition().getRow() + " Coluna = " + s.getPosition().getColumn() + "]"));
        else if (c==Compass.SOUTH)
            assertTrue(str.contains("[Barca s Linha = " + s.getPosition().getRow() + " Coluna = " + s.getPosition().getColumn() + "]"));
        else if (c==Compass.EAST)
            assertTrue(str.contains("[Barca e Linha = " + s.getPosition().getRow() + " Coluna = " + s.getPosition().getColumn() + "]"));
        else if (c==Compass.WEST)
            assertTrue(str.contains("[Barca o Linha = " + s.getPosition().getRow() + " Coluna = " + s.getPosition().getColumn() + "]"));
    }
}