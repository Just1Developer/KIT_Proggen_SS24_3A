package edu.kit.kastel.command;

import edu.kit.kastel.Main;

/**
 * This command prints the entire world map, line by line.
 *
 * @author uwwfh
 */
final class PrintCommand implements Command {
    
    private static final int ARGUMENTS_REQUIRED = 0;

    @Override
    public CommandResult execute(String[] commandArguments) {
        System.out.println(Main.getWorld().toString());
        return new CommandResult(CommandResultType.SUCCESS, null);
    }
    
    @Override
    public int getFixedArgumentCount() {
        return ARGUMENTS_REQUIRED;
    }
}
