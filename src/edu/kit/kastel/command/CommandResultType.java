/*
 * Copyright (c) 2023, KASTEL. All rights reserved.
 */

package edu.kit.kastel.command;

/**
 * This enum represents the types that a result of a command can be.
 *
 * @author Programmieren-Team
 * @author uwwfh
 */
enum CommandResultType {
    
    /**
     * The command was executed successfully.
     */
    SUCCESS,
    
    /**
     * An error occurred during processing the command.
     */
    FAILURE
}
