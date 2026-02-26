/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a frota de um jogador no jogo Batalha Naval.
 * A frota é constituída por uma lista de navios que partilham o mesmo tabuleiro. d d d d djr
 * Implementa a interface IFleet para garantir as operações essenciais de gestão da frota.
 */
public class Fleet implements IFleet {
    /**
     * Imprime na consola a informação detalhada de uma lista fornecida de navios.
     *
     * @param ships A lista de navios a ser impressa na consola.
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Devolve a lista completa de navios que compõem a frota atual.
     *
     * @return Uma lista (List) de objetos do tipo IShip.
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um novo navio à frota, garantindo que as regras do jogo são cumpridas.
     * O navio só é adicionado se o tamanho máximo da frota não tiver sido excedido,
     * se o navio estiver dentro das fronteiras do tabuleiro e se não colidir com outros navios.
     *
     * @param s O navio a adicionar à frota.
     * @return true se o navio foi adicionado com sucesso, false caso viole alguma regra.
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Filtra e devolve todos os navios da frota que pertencem a uma categoria específica
     * (e.g., "Galeao", "Fragata", etc.).
     *
     * @param category A categoria do navio em formato de texto.
     * @return Uma lista contendo apenas os navios correspondentes à categoria solicitada.
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Devolve uma lista de todos os navios da frota que ainda não foram totalmente afundados.
     *
     * @return Uma lista de navios (IShip) cujo estado indica que ainda flutuam.
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Verifica e devolve o navio da frota que ocupa uma determinada coordenada (posição) no tabuleiro.
     *
     * @param pos A posição (coordenadas) a inspecionar.
     * @return O objeto IShip que ocupa a posição indicada, ou null se a posição estiver livre (água).
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * Imprime na consola um relatório completo com o estado atual da frota.
     * Inclui a listagem de todos os navios, os navios que ainda flutuam 
     * e o inventário agrupado por categoria de barco do tempo dos Descobrimentos.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * This operation prints all the ships of a fleet belonging to a particular
     * category
     *
     * @param category The category of ships of interest
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * This operation prints all the ships of a fleet but not yet shot
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * This operation prints all the ships of a fleet
     */
    void printAllShips() {
        printShips(ships);
    }

}
