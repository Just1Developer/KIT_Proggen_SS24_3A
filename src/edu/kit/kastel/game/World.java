package edu.kit.kastel.game;

import edu.kit.kastel.game.worldbuilder.Coordinate;
import edu.kit.kastel.game.worldbuilder.Direction;
import edu.kit.kastel.game.worldbuilder.TileColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The world map, AKA coordinate grid.
 *
 * @author uwwfh
 */
public class World {

    private static final TileColor DEFAULT_COLOR = TileColor.WHITE;
    private static final Map<TileColor, Character> CHARACTER_REPR = Map.of(
            TileColor.WHITE, '0',
            TileColor.BLACK, '1'
    );
    private static final Map<Character, TileColor> TILE_FROM_CHAR_REPR = Map.of(
            '0', TileColor.WHITE,
            '1', TileColor.BLACK
    );

    private final Map<Coordinate, TileColor> worldMap;
    private final int width;
    private final int height;
    private final Ant ant;

    /**
     * Creates a new world and parses the world contents.
     * @param worldContents The lines that will construct the world. All should be the same length.
     */
    public World(List<String> worldContents) {
        worldMap = new HashMap<>();
        Ant ant = null;

        // Since there is always an ant, width and height are always >= 1.
        // Also, list entries and their length are always >= 1, and we can assume all lengths are the same.
        height = worldContents.size();
        width = worldContents.get(0).length();

        Coordinate currentHead = new Coordinate(0, 0);
        for (String line : worldContents) {
            char character;
            for (int i = 0; i < line.length(); ++i) {
                character = line.charAt(i);

                Optional<Direction> directionOptional = Direction.parseAny(character);
                if (directionOptional.isPresent()) {
                    ant = new Ant(this, currentHead, directionOptional.get());
                    worldMap.put(currentHead.copy(), DEFAULT_COLOR);
                } else {
                    worldMap.put(currentHead.copy(), TILE_FROM_CHAR_REPR.get(character));
                }
                currentHead.add(Coordinate.RIGHT);
            }
            currentHead.setX(0);
            currentHead.add(Coordinate.DOWN);
        }
        // As given per the task, we have exactly one ant. No more, no less.
        this.ant = ant;
    }

    /**
     * Gets the ant of the world. There only ever is exactly one (1) ant.
     * @return The ant that roams this world.
     */
    public Ant getAnt() {
        return ant;
    }

    /**
     * Gets the color of the given Coordinate. Returns the default color if the
     * coordinate is not tracked yet.
     * Note that the worldMap will not begin tracking a tile until it is set with
     * the setColor(Coordinate, TileColor) method.
     * @param coordinate The location.
     * @return The tile color or default color.
     */
    TileColor getColor(final Coordinate coordinate) {
        return worldMap.getOrDefault(coordinate, DEFAULT_COLOR);
    }

    /**
     * Sets (and then tracks) the tile color at the specified coordinate location.
     * Tracking simply means getColor(...) will return the color previously set with setColor(...).
     * @param coordinate The location.
     * @param tileColor The tile color.
     */
    void setColor(final Coordinate coordinate, final TileColor tileColor) {
        worldMap.put(coordinate, tileColor);
    }

    /**
     * If the ant is outside the bounds of the world.
     * @return true if the ant is out of bounds, false if not.
     */
    public boolean isAntOutOfBounds() {
        return isOutOfBounds(ant.getLocation());
    }

    /**
     * If the given coordinate is outside the bounds of the world.
     * @param coordinate The coordinate.
     * @return true if out of bounds, false if not.
     */
    private boolean isOutOfBounds(Coordinate coordinate) {
        // <= because of 0-based indexing
        return coordinate.getX() < 0 || coordinate.getX() >= this.width
                || coordinate.getY() < 0 || coordinate.getY() >= this.height;
    }

    /**
     * Gets the cell at a given coordinate in the context of the world contents.
     * Returns a '0' for a white empty fields in case the coordinates is out of bounds.
     * @param coordinate The coordinate.
     * @return The char value of the coordinate.
     */
    public Character asCharacter(Coordinate coordinate) {
        TileColor color = getColor(coordinate);
        if (ant.getLocation().equals(coordinate)) {
            char character = Direction.toChar(ant.getDirection());
            return color == TileColor.BLACK ? character : Character.toLowerCase(character);
        }
        return CHARACTER_REPR.get(color);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Coordinate writeHead = new Coordinate(0, 0);
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                builder.append(asCharacter(writeHead));
                writeHead.add(Coordinate.RIGHT);
            }
            if (y < height - 1) {
                builder.append(System.lineSeparator());
            }
            writeHead.setX(0);
            writeHead.add(Coordinate.DOWN);
        }
        return builder.toString();
    }
}
