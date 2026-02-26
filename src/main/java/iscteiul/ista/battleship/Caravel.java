/**
 *
 */
package iscteiul.ista.battleship;
/**
 * Representa uma Caravela no jogo Discoveries Battleship.
 * A Caravela é um navio de pequena dimensão com tamanho 2.
 * * @author Alexandre Mira
 * @version 1.0
 */

public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    /**
     * Dimensão fixa da caravela (número de quadrados que ocupa). string estática
     */
    private static final String NAME = "Caravela";
    /**
     * Nome tradicional da embarcação.
     */

    /**
     * @param bearing the bearing where the Caravel heads to
     * @param pos     initial point for positioning the Caravel
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    /**
     * Devolve o tamanho da caravela.
     * * @return O número de células ocupadas pelo navio (sempre 2).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
