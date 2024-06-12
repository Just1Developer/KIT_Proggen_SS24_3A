/*
 * Copyright (c) 2023, KASTEL. All rights reserved.
 */

package edu.kit.kastel.command;

/**
 * This class represents the result of a command.
 * It contains a message and a type.
 * The type indicates whether the command was successful or not.
 * The message contains additional information about the result.
 *
 * @see CommandResultType
 * @see CommandHandler
 *
 * @author Programmieren-Team
 * @author uwwfh
 */
final class CommandResult {
    
    private final String message;
    private final CommandResultType type;
    
    /**
     * Constructs a new CommandResult.
     *
     * @param type the type of the result
     * @param resultMessage the message of the result. May be {@code null} to indicate that there is no message.
     */
    CommandResult(CommandResultType type, String resultMessage) {
        this.message = resultMessage;
        this.type = type;
    }
    
    /**
     * Returns the message of the result. Might be {@code null} indicating that there was no result message.
     * @return the message of the result.
     */
    String getMessage() {
        return message;
    }
    
    /**
     * Returns the type of the result.
     * @return the type of the result.
     */
    CommandResultType getType() {
        return type;
    }
}
