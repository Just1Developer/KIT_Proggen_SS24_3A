package edu.kit.kastel.command;

import edu.kit.kastel.Main;
import edu.kit.kastel.game.worldbuilder.Coordinate;

import java.util.Optional;

/**
 * This command prints the symbol representation of a given field.
 *
 * @author uwwfh
 */
final class FieldCommand implements Command {
    
    private static final int ARGUMENTS_REQUIRED = 1;
    private static final String ERROR_INVALID_COORD = "the coordinate given was invalid, expected format is \"x,y\", but was \"%s\"";

    @Override
    public CommandResult execute(String[] commandArguments) {
        Optional<Coordinate> coordinate = Coordinate.parseCoordinate(commandArguments[0]);
        if (coordinate.isEmpty()) {
            return new CommandResult(CommandResultType.FAILURE, ERROR_INVALID_COORD.formatted(commandArguments[0]));
        }
        System.out.println(Main.getWorld().asCharacter(coordinate.get()));
        return new CommandResult(CommandResultType.SUCCESS, null);
    }
    
    @Override
    public int getFixedArgumentCount() {
        return ARGUMENTS_REQUIRED;
    }
}
