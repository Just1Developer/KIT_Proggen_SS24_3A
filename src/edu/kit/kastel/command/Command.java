/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.kastel.command;

/**
 * This interface represents an executable command.
 *
 * @author Programmieren-Team
 * @author uwwfh
 */
interface Command {
    
    /**
     * Executes the command.
     *
     * @param commandArguments the arguments of the command
     * @return the result of the command
     */
    CommandResult execute(String[] commandArguments);
    
    /**
     * Returns the number of required arguments that the command expects.
     *
     * @return the number of required arguments.
     */
    int getFixedArgumentCount();
}
