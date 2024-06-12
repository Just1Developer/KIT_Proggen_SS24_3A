/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.kastel.command;

import edu.kit.kastel.Main;

/**
 * This command quits a {@link CommandHandler command handler}.
 *
 * @author Programmieren-Team
 * @author uwwfh
 */
final class QuitCommand implements Command {
    
    private static final int ARGUMENTS_REQUIRED = 0;
    
    /**
     * Executes the command.
     *
     * @param ignored the arguments of the command, ignored
     * @return the result of the command
     */
    @Override
    public CommandResult execute(String[] ignored) {
        Main.getCommandHandler().quit();
        return new CommandResult(CommandResultType.SUCCESS, null);
    }
    
    @Override
    public int getFixedArgumentCount() {
        return ARGUMENTS_REQUIRED;
    }
}
