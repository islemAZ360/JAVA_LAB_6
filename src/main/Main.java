package main;

import main.objects.Const;
import main.objects.HumanBeing;
import main.utils.*;
import main.utils.commands.*;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Главный класс приложения (Main).
 * Точка входа в программу. Здесь происходит инициализация и запуск интерактивного режима.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        // 2. Инициализация менеджеров (Initialization)
        InputManager inputManager = new InputManager(scanner);
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        String filePath = "";
        String username = "";

        //  1. Определение файла с данными (Data File Setup)
        // Логика: Если путь передан через аргументы командной строки, используем его.
        // Иначе используем файл по умолчанию (как делал Person 1)

        if (args.length == 1) {
            filePath = args[0];
        } else if (args.length == 2) {
            username = args[1];
        } else {
            filePath = Const.FILEPATH;
            username = "";
            System.err.println("Внимание: Путь к файлу не передан через аргументы. Используется файл по умолчанию: " + filePath);
        }
        try {
            HumanBeingFileManager humanBeingFileManager;

            try {
                // Tạo FileManager duy nhất
                humanBeingFileManager = new HumanBeingFileManager(filePath);
                humanBeingFileManager.readFileAndLoadHumanBeing(collectionManager);
                System.out.println("Коллекция успешно загружена. Количество элементов: " + collectionManager.size());

            } catch (Exception e) {
                System.out.println("Ошибка при чтении файла (или файл не найден). Запуск с пустой коллекцией.");
                // Vẫn tạo FileManager để có thể save sau này
                humanBeingFileManager = new HumanBeingFileManager(filePath);
            }

//            CommandFileManager commandFileManager = new CommandFileManager(Const.SCRIPTFILEPATH);

            commandManager.registerCommand("help", new HelpCommand(commandManager));
            commandManager.registerCommand("info", new InfoCommand(collectionManager));
            commandManager.registerCommand("show", new ShowCommand(collectionManager));

            commandManager.registerCommand("add", new AddCommand(collectionManager,inputManager));
            commandManager.registerCommand("update", new UpdateCommand(collectionManager,inputManager));
            commandManager.registerCommand("remove_by_id", new RemoveByIdCommand(collectionManager));
            commandManager.registerCommand("clear", new ClearCommand(collectionManager));

            commandManager.registerCommand("save", new SaveCommand(collectionManager, humanBeingFileManager));

            commandManager.registerCommand("add_if_max", new AddIfMaxCommand(collectionManager, inputManager));
            commandManager.registerCommand("add_if_min", new AddIfMinCommand(collectionManager, inputManager));

            commandManager.registerCommand("remove_greater", new RemoveGreaterCommand(collectionManager));

            commandManager.registerCommand("filter_contains_name", new FilterContainsNameCommand(collectionManager));
            commandManager.registerCommand("filter_less_than_minutes_of_waiting", new FilterLessThanMinutesOfWaitingCommand(collectionManager));
            commandManager.registerCommand("filter_greater_than_car", new FilterGreaterThanCarCommand(collectionManager));

            commandManager.registerCommand("run_script_file", new ExecuteScriptCommand(collectionManager, commandManager, humanBeingFileManager));
//            commandManager.registerCommand("exit", new ExitCommand());


            //  5. Интерактивный цикл (Interactive Loop - Logic from Person 2)
            System.out.println("\nПрограмма готова к работе! Введите 'help' для подержки || 'exit' для выхода.");

            while (true) {
                // Вывод приглашения к вводу
                System.out.print(">>> ");

                // Проверка на конец ввода (Ctrl+D)
                if (!scanner.hasNextLine()) break;

                String input = scanner.nextLine().trim();

                // Пропуск пустых строк
                if (input.isEmpty()) continue;

                // Разделение ввода на команду и аргументы
                String[] commandArgs = input.split("\\s+");

                String command = commandArgs[0].toLowerCase();

                if (command.equals("exit")) {
                    System.out.println("Завершение работы программы...");
                    break;
                }

                // Выполнение команды через менеджер
                commandManager.executeCommand(commandArgs);
            }

            scanner.close();

        } catch (Exception E) {
            System.err.println("Error: " + E.getMessage());
        }
    }
}

// run_script_file scripts/scripts.txt