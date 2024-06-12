package edu.kit.kastel.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class handles the user input and executes the commands.
 *
 * @author Programmieren-Team
 * @author uwwfh
 */
public final class CommandHandler {
    
    /**
     * The prefix for any error messages.
     */
    private static final String ERROR_PREFIX = "Error, ";
    private static final String COMMAND_HANDLER_NOT_INITIALIZED =
            "%sthe CommandHandler has not yet been initialized. Call CommandHandler.initialize() first.".formatted(ERROR_PREFIX);
    private static final String COMMAND_SEPARATOR_REGEX = " +";
    private static final String COMMAND_NOT_FOUND_FORMAT = "Command '%s' not found!";
    private static final String WRONG_ARGUMENTS_COUNT_FORMAT = "Wrong number of arguments for command '%s'! Expected %d but got %d.";
    private static final String MOVE_COMMAND_NAME = "move";
    private static final String PRINT_COMMAND_NAME = "print";
    private static final String POSITION_COMMAND_NAME = "position";
    private static final String FIELD_COMMAND_NAME = "field";
    private static final String QUIT_COMMAND_NAME = "quit";
    private static final String INVALID_RESULT_TYPE_FORMAT = "Unexpected value: %s";
    
    private static Map<String, Command> commands;
    private static boolean running = false;

    /**
     * Creates a new CommandHandler.
     */
    public CommandHandler() {
        // This is a precaution, this should not be needed.
        if (commands == null) {
            initialize();
        }
    }
    
    /**
     * Initializes the CommandHandler. Must be executed before any calls can be made.
     */
    public static void initialize() {
        commands = new HashMap<>();
        initCommands();
    }
    
    /**
     * Starts the interaction with the user.
     * CommandHandler must be initialized.
     */
    public void handleUserInput() {
        if (commands == null) {
            System.err.println(COMMAND_HANDLER_NOT_INITIALIZED);
            return;
        }
        
        running = true;
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (running && scanner.hasNextLine()) {
                executeCommand(scanner.nextLine());
            }
        }
    }
    
    /**
     * Quits the interaction with the user.
     */
    void quit() {
        running = false;
    }
    
    private void executeCommand(String commandWithArguments) {
        // For debugging purposes, todo remove
        //System.out.println("> " + commandWithArguments);
        String[] splitCommand = commandWithArguments.trim().split(COMMAND_SEPARATOR_REGEX);
        String commandName = splitCommand[0];
        String[] commandArguments = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
        
        executeCommand(commandName, commandArguments);
    }
    
    private void executeCommand(String commandName, String[] commandArguments) {
        if (!commands.containsKey(commandName)) {
            System.err.println(ERROR_PREFIX + COMMAND_NOT_FOUND_FORMAT.formatted(commandName));
            return;
        }
        
        Command command = commands.get(commandName);
        
        if (command.getFixedArgumentCount() != commandArguments.length) {
            System.err.println(ERROR_PREFIX + WRONG_ARGUMENTS_COUNT_FORMAT.formatted(commandName,
                    command.getFixedArgumentCount(), commandArguments.length));
            return;
        }
        
        CommandResult result = command.execute(commandArguments);
        String output = switch (result.getType()) {
            case SUCCESS -> result.getMessage();
            case FAILURE -> ERROR_PREFIX + result.getMessage();
        };
        if (output != null) {
            switch (result.getType()) {
                case SUCCESS -> System.out.println(output);
                case FAILURE -> System.err.println(output);
                default -> throw new IllegalStateException(INVALID_RESULT_TYPE_FORMAT.formatted(result.getType()));
            }
        }
    }
    
    /**
     * Initializes the commands.
     */
    private static void initCommands() {
        addCommand(MOVE_COMMAND_NAME, new MoveCommand());
        addCommand(PRINT_COMMAND_NAME, new PrintCommand());
        addCommand(POSITION_COMMAND_NAME, new PositionCommand());
        addCommand(FIELD_COMMAND_NAME, new FieldCommand());
        addCommand(QUIT_COMMAND_NAME, new QuitCommand());
    }
    
    /**
     * Adds a command to the known commands to handle.
     *
     * @param commandName The command name
     * @param command The executioner
     */
    private static void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }
    
}
