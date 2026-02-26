package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo Galeão no jogo Battleship.
 * O Galeão é uma embarcação de tamanho 5 que ocupa uma forma em "T",
 * estendendo-se em diferentes direções com base na sua orientação (bearing).
 * * @author [Teu Nome/Equipa]
 * @version 1.0
 */
public class Galleon extends Ship {
    
    /**
     * Tamanho fixo do Galeão (número de posições ocupadas no tabuleiro).
     */
    private static final Integer SIZE = 5;
    
    /**
     * Nome identificador do tipo de navio.
     */
    private static final String NAME = "Galeao";

    /**
     * Constrói um novo Galeão com uma orientação e posição inicial específicas.
     * O método preenche automaticamente as 5 posições do navio no tabuleiro
     * dependendo da direção fornecida.
     *
     * @param bearing A orientação do navio (NORTH, EAST, SOUTH, WEST).
     * @param pos     A posição de referência (âncora) para o posicionamento.
     * @throws NullPointerException     Se o bearing for nulo.
     * @throws IllegalArgumentException Se o bearing for inválido para a construção do Galeão.
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Retorna o tamanho do Galeão.
     * * @return O número de células ocupadas pelo navio (sempre 5).
     * @see iscteiul.ista.battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Preenche as posições do navio quando orientado para Norte.
     * Cria uma forma em "T" onde a barra horizontal está no topo.
     * * @param pos Posição inicial de referência.
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche as posições do navio quando orientado para Sul.
     * * @param pos Posição inicial de referência.
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Preenche as posições do navio quando orientado para Este.
     * * @param pos Posição inicial de referência.
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche as posições do navio quando orientado para Oeste.
     * * @param pos Posição inicial de referência.
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
