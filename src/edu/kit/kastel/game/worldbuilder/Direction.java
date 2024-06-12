package edu.kit.kastel.game.worldbuilder;

import java.util.Optional;

/**
 * The direction for the Ant head. Can be converted to coordinates using asCoordinate(Direction).
 *
 * @author uwwfh
 */
public enum Direction {

    /**
     * The UP direction. Representative of NORTH.
     */
    UP,
    /**
     * The DOWN direction. Representative of SOUTH.
     */
    DOWN,
    /**
     * The LEFT direction. Representative of WEST.
     */
    LEFT,
    /**
     * The RIGHT direction. Representative of EAST.
     */
    RIGHT;

    private static final char UP_CHAR = 'N';
    private static final char DOWN_CHAR = 'S';
    private static final char RIGHT_CHAR = 'E';
    private static final char LEFT_CHAR = 'W';

    /**
     * Converts a direction to a coordinate.
     * @param dir The direction.
     * @return Direction as coordinate delta.
     */
    public static Coordinate asCoordinate(final Direction dir) {
        return switch (dir) {
            case UP -> Coordinate.UP;
            case DOWN -> Coordinate.DOWN;
            case LEFT -> Coordinate.LEFT;
            case RIGHT -> Coordinate.RIGHT;
        };
    }

    /**
     * Attempts to parse a char to a direction. If the char is not a valid direction,
     * returns an empty optional.<br/>Mappings are as follows:<br/>
     *   + 'N' -> UP <br/>
     *   + 'S' -> DOWN<br/>
     *   + 'E' -> RIGHT<br/>
     *   + 'W' -> LEFT
     * @param character The character.
     * @return Optional of the direction or empty.
     */
    public static Optional<Direction> parseAny(final char character) {
        return switch (character) {
            case UP_CHAR -> Optional.of(UP);
            case DOWN_CHAR -> Optional.of(DOWN);
            case LEFT_CHAR -> Optional.of(LEFT);
            case RIGHT_CHAR -> Optional.of(RIGHT);
            default -> Optional.empty();
        };
    }

    /**
     * Gets the char representation of the given direction.<br/>Mappings are as follows:<br/>
     *   + UP -> 'N' <br/>
     *   + DOWN -> 'S'<br/>
     *   + RIGHT -> 'E'<br/>
     *   + LEFT -> 'W'
     * @param direction The character.
     * @return Optional of the direction or empty.
     */
    public static Character toChar(final Direction direction) {
        return switch (direction) {
            case UP -> UP_CHAR;
            case DOWN -> DOWN_CHAR;
            case LEFT -> LEFT_CHAR;
            case RIGHT -> RIGHT_CHAR;
        };
    }

    /**
     * Returns the Direction that would result with a rotation of 90° clockwise.
     * @param dir The direction.
     * @return The rotated direction.
     */
    public static Direction clockwiseNext(final Direction dir) {
        return switch (dir) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

    /**
     * Returns the Direction that would result with a rotation of 90° counter-clockwise.
     * @param dir The direction.
     * @return The rotated direction.
     */
    public static Direction counterClockwiseNext(final Direction dir) {
        return switch (dir) {
            case UP -> LEFT;
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
        };
    }

}
