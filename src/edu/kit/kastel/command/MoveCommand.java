package edu.kit.kastel.command;

import edu.kit.kastel.Main;
import edu.kit.kastel.game.worldbuilder.Coordinate;

/**
 * This command performs a given number of moves on the current playing field.
 * If the ant is outside the playing area after any move, the game ends immediately.
 *
 * @author uwwfh
 */
final class MoveCommand implements Command {
    
    private static final int ARGUMENTS_REQUIRED = 1;
    private static final int ARG_INDEX_MOVES = 0;

    private static final String ERROR_INVALID_ARG = "the amount of moves must be a non-negative integer.";

    @Override
    public CommandResult execute(String[] commandArguments) {
        int moves;
        try {
            moves = Integer.parseInt(commandArguments[ARG_INDEX_MOVES]);
        } catch (NumberFormatException e) {
            return new CommandResult(CommandResultType.FAILURE, ERROR_INVALID_ARG);
        }
        if (moves < 0) {
            return new CommandResult(CommandResultType.FAILURE, ERROR_INVALID_ARG);
        }

        for (int i = 0; i < moves; ++i) {
            Coordinate loc = Main.getWorld().getAnt().getLocation();
            Main.getWorld().getAnt().move();
            if (Main.getWorld().isAntOutOfBounds()) {
                System.out.println(Main.getWorld().asCharacter(loc));
                Main.getCommandHandler().quit();
                break;
            }
        }
        return new CommandResult(CommandResultType.SUCCESS, null);
    }
    
    @Override
    public int getFixedArgumentCount() {
        return ARGUMENTS_REQUIRED;
    }
}
