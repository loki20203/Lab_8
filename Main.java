import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Демонстрація використання ConfigurationManager
        ConfigurationManager configManager = ConfigurationManager.getInstance();

        // Діалог з користувачем для зміни налаштувань
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Показати поточні налаштування");
            System.out.println("2. Змінити рівень логування");
            System.out.println("3. Змінити рядок підключення до бази даних");
            System.out.println("4. Вийти");
            System.out.print("Ваш вибір: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    configManager.showCurrentSettings();
                    break;
                case 2:
                    System.out.print("Введіть новий рівень логування: ");
                    String logLevel = scanner.next();
                    configManager.setLogLevel(logLevel);
                    break;
                case 3:
                    System.out.print("Введіть новий рядок підключення до БД: ");
                    String dbConnectionString = scanner.next();
                    configManager.setDbConnectionString(dbConnectionString);
                    break;
                case 4:
                    System.out.println("Вихід з програми.");
                    break;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

class ConfigurationManager {
    private static ConfigurationManager instance;
    private String logLevel;
    private String dbConnectionString;

    // Приватний конструктор для Singleton
    private ConfigurationManager() {
        this.logLevel = "INFO"; // За замовчуванням
        this.dbConnectionString = "Server=myServerAddress;Database=myDataBase;User Id=myUsername;Password=myPassword;"; // За замовчуванням
    }

    // Метод для отримання єдиного екземпляра
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    // Показати поточні налаштування
    public void showCurrentSettings() {
        System.out.println("Поточні налаштування:");
        System.out.println("Рівень логування: " + logLevel);
        System.out.println("Рядок підключення до БД: " + dbConnectionString);
    }

    // Методи для зміни налаштувань
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
        System.out.println("Рівень логування змінено на: " + logLevel);
    }

    public void setDbConnectionString(String dbConnectionString) {
        this.dbConnectionString = dbConnectionString;
        System.out.println("Рядок підключення до БД змінено на: " + dbConnectionString);
    }
}
