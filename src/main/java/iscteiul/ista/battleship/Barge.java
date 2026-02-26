package iscteiul.ista.battleship;

/**
 * Represents a {@code Barge} ship in the Discoveries Battleship Game.
 * <p>
 * A Barge corresponds to the smallest ship in the fleet and occupies
 * exactly one position on the board (size = 1).
 * </p>
 * 
 * <p>
 * In the Discoveries version of the game, a Barge is equivalent to
 * the traditional submarine in classic Battleship.
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class Barge extends Ship {

    /**
     * The fixed size of a Barge (number of board cells occupied).
     */
    private static final Integer SIZE = 1;

    /**
     * The display name of the ship.
     */
    private static final String NAME = "Barca";

    /**
     * Constructs a Barge with the specified orientation and starting position.
     * <p>
     * Since a Barge occupies only one cell, its bearing does not affect
     * its occupied positions.
     * </p>
     *
     * @param bearing the orientation of the ship (horizontal or vertical)
     * @param pos the upper-left position of the ship on the board
     * @throws NullPointerException if bearing or position is null
     */
    public Barge(Compass bearing, IPosition pos) {
        super(NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the size of the Barge.
     *
     * @return the size of the ship (always 1)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
