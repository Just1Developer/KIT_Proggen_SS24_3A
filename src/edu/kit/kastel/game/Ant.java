package edu.kit.kastel.game;

import edu.kit.kastel.game.worldbuilder.Coordinate;
import edu.kit.kastel.game.worldbuilder.Direction;
import edu.kit.kastel.game.worldbuilder.TileColor;

/**
 * The ant player class.
 *
 * @author uwwfh
 */
public class Ant {

    private Direction direction;
    private final Coordinate location;
    private final World world;

    /**
     * Creates a new Ant player object. Defaults at direction up.
     * @param world The world the ant is in.
     * @param location The location of the ant. Will be copied.
     * @param direction The direction the ant is looking initially.
     */
    Ant(final World world, Coordinate location, Direction direction) {
        this.direction = Direction.UP;
        this.location = location.copy();
        this.world = world;
    }

    /**
     * Gets the location of the ant in the world.
     * @return The location of the ant.
     */
    public Coordinate getLocation() {
        return location;
    }

    /**
     * Gets the direction of the ant.
     * @return The direction of the ant.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Rotates the Ant clockwise by 90°.
     */
    void rotateClockwise() {
        direction = Direction.clockwiseNext(direction);
    }

    /**
     * Rotates the Ant counter-clockwise by 90°.
     */
    void rotateCounterClockwise() {
        direction = Direction.counterClockwiseNext(direction);
    }

    /**
     * Moves the Ant by the given coordinate.
     * @param delta The movement.
     */
    void move(final Coordinate delta) {
        location.add(delta);
        makePostMoveAction();
    }

    /**
     * Moves the Ant by 1 in the direction it's currently facing.
     */
    public void move() {
        location.add(Direction.asCoordinate(direction));
        makePostMoveAction();
    }

    /**
     * Performs the post-move action. After a move, if the current tile is white,
     * rotates by 90° clockwise, otherwise 90° counter-clockwise. The color of the tile
     * is switched to the opposite.
     */
    private void makePostMoveAction() {
        if (world.getColor(location) == TileColor.WHITE) {
            rotateClockwise();
            world.setColor(location, TileColor.BLACK);
        } else {
            rotateCounterClockwise();
            world.setColor(location, TileColor.WHITE);
        }
    }

}
