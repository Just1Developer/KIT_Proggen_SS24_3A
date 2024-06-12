package edu.kit.kastel.command;

import edu.kit.kastel.Main;

/**
 * This command prints the position of the ant.
 *
 * @author uwwfh
 */
final class PositionCommand implements Command {
    
    private static final int ARGUMENTS_REQUIRED = 0;

    @Override
    public CommandResult execute(String[] commandArguments) {
        System.out.println(Main.getWorld().getAnt().getLocation().toString());
        return new CommandResult(CommandResultType.SUCCESS, null);
    }
    
    @Override
    public int getFixedArgumentCount() {
        return ARGUMENTS_REQUIRED;
    }
}
