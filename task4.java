import java.util.Scanner;

// 1. Шаблони даних (Prototype)
abstract class Data {
    public abstract Data clone();
    public abstract void display();
}

// CSV формат даних
class CSVData extends Data {
    private String data;

    public CSVData(String data) {
        this.data = data;
    }

    @Override
    public Data clone() {
        return new CSVData(this.data);
    }

    @Override
    public void display() {
        System.out.println("CSV Data: " + data);
    }
}

// XML формат даних
class XMLData extends Data {
    private String data;

    public XMLData(String data) {
        this.data = data;
    }

    @Override
    public Data clone() {
        return new XMLData(this.data);
    }

    @Override
    public void display() {
        System.out.println("XML Data: " + data);
    }
}

// JSON формат даних
class JSONData extends Data {
    private String data;

    public JSONData(String data) {
        this.data = data;
    }

    @Override
    public Data clone() {
        return new JSONData(this.data);
    }

    @Override
    public void display() {
        System.out.println("JSON Data: " + data);
    }
}

// 2. Адаптери (Adapter)
interface DataAdapter {
    Data convert(Data data);
}

class CSVToXMLAdapter implements DataAdapter {
    @Override
    public Data convert(Data data) {
        // Проста конвертація для демонстрації
        String newData = "<xml>" + data.toString() + "</xml>";
        return new XMLData(newData);
    }
}

class CSVToJSONAdapter implements DataAdapter {
    @Override
    public Data convert(Data data) {
        // Проста конвертація для демонстрації
        String newData = "{\"data\": \"" + data.toString() + "\"}";
        return new JSONData(newData);
    }
}

class XMLToCSVAdapter implements DataAdapter {
    @Override
    public Data convert(Data data) {
        // Проста конвертація для демонстрації
        String newData = "CSV: " + data.toString();
        return new CSVData(newData);
    }
}

class JSONToCSVAdapter implements DataAdapter {
    @Override
    public Data convert(Data data) {
        // Проста конвертація для демонстрації
        String newData = "CSV: " + data.toString();
        return new CSVData(newData);
    }
}

// 3. Головний клас програми
public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Вибір формату для створення даних
        System.out.println("Виберіть формат даних для створення:");
        System.out.println("1. CSV");
        System.out.println("2. XML");
        System.out.println("3. JSON");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        Data data = null;

        // Створення шаблону даних в залежності від вибору
        switch (choice) {
            case 1:
                System.out.println("Введіть дані для CSV:");
                String csvData = scanner.nextLine();
                data = new CSVData(csvData);
                break;
            case 2:
                System.out.println("Введіть дані для XML:");
                String xmlData = scanner.nextLine();
                data = new XMLData(xmlData);
                break;
            case 3:
                System.out.println("Введіть дані для JSON:");
                String jsonData = scanner.nextLine();
                data = new JSONData(jsonData);
                break;
            default:
                System.out.println("Невірний вибір!");
                return;
        }

        // Клонування даних (Prototype)
        Data clonedData = data.clone();

        // Вибір формату для перетворення
        System.out.println("Виберіть формат для перетворення даних:");
        System.out.println("1. CSV");
        System.out.println("2. XML");
        System.out.println("3. JSON");
        int targetChoice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        DataAdapter adapter = null;

        // Створення відповідного адаптера
        switch (targetChoice) {
            case 1:
                if (data instanceof XMLData) {
                    adapter = new XMLToCSVAdapter();
                } else if (data instanceof JSONData) {
                    adapter = new JSONToCSVAdapter();
                } else {
                    System.out.println("Невірний вибір адаптера!");
                    return;
                }
                break;
            case 2:
                if (data instanceof CSVData) {
                    adapter = new CSVToXMLAdapter();
                } else if (data instanceof JSONData) {
                    adapter = new JSONToCSVAdapter();
                } else {
                    System.out.println("Невірний вибір адаптера!");
                    return;
                }
                break;
            case 3:
                if (data instanceof CSVData) {
                    adapter = new CSVToJSONAdapter();
                } else if (data instanceof XMLData) {
                    adapter = new XMLToCSVAdapter();
                } else {
                    System.out.println("Невірний вибір адаптера!");
                    return;
                }
                break;
            default:
                System.out.println("Невірний вибір!");
                return;
        }

        // Використовуємо адаптер для перетворення даних
        Data convertedData = adapter.convert(clonedData);

        // Виведення перетворених даних
        convertedData.display();

        scanner.close();
    }
}
