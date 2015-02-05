package wmevo.Programs;

import wmevo.Node;

/**
 * Created by Zoltán on 2014.04.28..
 */
public interface ISpecificLeafNodeGenerator {

    /**
     * GunTurn, Moving, Shooting, Turn Programok Leaf Node-jainak
     * különbözik az értékkészlete, erre vonatkozó infót ad le.
     */
    public Node getSpecificLeafNode();
}
