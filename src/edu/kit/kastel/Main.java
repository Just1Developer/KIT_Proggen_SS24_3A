package edu.kit.kastel;

import edu.kit.kastel.command.CommandHandler;
import edu.kit.kastel.game.World;

import java.util.List;

/**
 * The main entry point of the program.
 *
 * @author uwwfh
 */
public final class Main {

    private static CommandHandler commandHandler;
    private static World world;

    private Main() { }

    /**
     * The main entry point.
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        // Given: args = {"<path>"} with |args| = 1
        final String filepath = args[0];
        final List<String> fileContents = FileHelper.readAllLines(filepath);
        world = new World(fileContents);

        CommandHandler.initialize();
        commandHandler = new CommandHandler();
        commandHandler.handleUserInput();
    }

    /**
     * Gets the current CommandHandler.
     * @return The current CommandHandler.
     */
    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    /**
     * Gets the current World.
     * @return The current World.
     */
    public static World getWorld() {
        return world;
    }

}
