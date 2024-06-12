package edu.kit.kastel.game.worldbuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The coordinate class. Also provides static delta coordinates for movement.
 *
 * @author uwwfh
 */
public class Coordinate {

    /**
     * The default UP coordinate. Adding this to any coordinate will result in the coordinate
     * pointing to the field above its original field.
     */
    public static final Coordinate UP = new Coordinate(0, -1);
    /**
     * The default DOWN coordinate. Adding this to any coordinate will result in the coordinate
     * pointing to the field below its original field.
     */
    public static final Coordinate DOWN = new Coordinate(0, 1);
    /**
     * The default LEFT coordinate. Adding this to any coordinate will result in the coordinate
     * pointing to the field to the left of its original field.
     */
    public static final Coordinate LEFT = new Coordinate(-1, 0);
    /**
     * The default RIGHT coordinate. Adding this to any coordinate will result in the coordinate
     * pointing to the field to the right of its original field.
     */
    public static final Coordinate RIGHT = new Coordinate(1, 0);

    private static final String FORMAT_STRING = "%d,%d";
    private static final Pattern REGEX_PATTERN_PARSE = Pattern.compile("^(\\d+),(\\d+)$");
    private static final int GROUP_INDEX_X = 2;
    private static final int GROUP_INDEX_Y = 1;

    private int x;
    private int y;

    /**
     * Makes a new Coordinate.
     * @param x The x coordinate.
     * @param y The x coordinate.
     */
    public Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a copy of the current coordinate.
     * @return A copy of the current coordinate.
     */
    public Coordinate copy() {
        return copyOf(this);
    }

    /**
     * Gets the x value.
     * @return x value.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y value.
     * @return y value.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x value.
     * @param x The x value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y value.
     * @param y The y value.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Adds the given coordinates position to the current coordinate.
     * @param coord The coordinate to add, for example Coordinate.UP.
     */
    public void add(final Coordinate coord) {
        add(coord.x, coord.y);
    }

    /**
     * Adds the given x and y values to the current coordinate.
     * @param x the x value to add.
     * @param y the y value to add.
     */
    public void add(final int x, final int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public String toString() {
        return FORMAT_STRING.formatted(y, x);
    }

    /**
     * Creates a copy of a given coordinate.
     * @param coordinate the coordinate to create a copy of.
     * @return the copy of the coordinate.
     */
    public static Coordinate copyOf(Coordinate coordinate) {
        return new Coordinate(coordinate.x, coordinate.y);
    }


    /**
     * Parses a Coordinate from a string matching "y,x". If the string is not of that format,
     * or x and y are not non-negative integers, returns empty optional.
     * @param argument The String to parse.
     * @return An optional of the parsed coordinate. May be empty.
     */
    public static Optional<Coordinate> parseCoordinate(String argument) {
        Matcher matcher = REGEX_PATTERN_PARSE.matcher(argument);
        if (!matcher.find()) {
            return Optional.empty();
        }
        int x;
        int y;
        try {
            x = Integer.parseInt(matcher.group(GROUP_INDEX_X));
            y = Integer.parseInt(matcher.group(GROUP_INDEX_Y));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
        return Optional.of(new Coordinate(x, y));
    }
}
