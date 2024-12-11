import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Інтерфейс для графіка
interface Graph {
    void draw();
}

// Лінійний графік
class LineGraph implements Graph {
    private List<Integer> data;

    public LineGraph(List<Integer> data) {
        this.data = data;
    }

    @Override
    public void draw() {
        System.out.println("Лінійний графік:");
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + data.get(i));
        }
    }
}

// Стовпчиковий графік
class BarGraph implements Graph {
    private List<Integer> data;

    public BarGraph(List<Integer> data) {
        this.data = data;
    }

    @Override
    public void draw() {
        System.out.println("Стовпчиковий графік:");
        for (int i = 0; i < data.size(); i++) {
            System.out.print("Точка " + (i + 1) + ": ");
            for (int j = 0; j < data.get(i); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

// Кругова діаграма
class PieChart implements Graph {
    private List<Integer> data;

    public PieChart(List<Integer> data) {
        this.data = data;
    }

    @Override
    public void draw() {
        System.out.println("Кругова діаграма:");
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Сегмент " + (i + 1) + ": " + data.get(i) + "%");
        }
    }
}

// Фабрика для створення графіків
abstract class GraphFactory {
    public abstract Graph createGraph(List<Integer> data);
}

// Фабрика для створення лінійного графіка
class LineGraphFactory extends GraphFactory {
    @Override
    public Graph createGraph(List<Integer> data) {
        return new LineGraph(data);
    }
}

// Фабрика для створення стовпчикового графіка
class BarGraphFactory extends GraphFactory {
    @Override
    public Graph createGraph(List<Integer> data) {
        return new BarGraph(data);
    }
}

// Фабрика для створення кругової діаграми
class PieChartFactory extends GraphFactory {
    @Override
    public Graph createGraph(List<Integer> data) {
        return new PieChart(data);
    }
}

// Головний клас програми
public class lab2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Вибір типу графіка1
        System.out.println("Виберіть тип графіка:");
        System.out.println("1. Лінійний графік");
        System.out.println("2. Стовпчиковий графік");
        System.out.println("3. Кругова діаграма");
        int choice = scanner.nextInt();

        // Введення даних для графіка
        System.out.println("Введіть кількість точок даних:");
        int n = scanner.nextInt();
        List<Integer> data = new ArrayList<>();
        System.out.println("Введіть значення для кожної точки:");
        for (int i = 0; i < n; i++) {
            data.add(scanner.nextInt());
        }

        // Створення відповідної фабрики
        GraphFactory factory = null;
        switch (choice) {
            case 1:
                factory = new LineGraphFactory();
                break;
            case 2:
                factory = new BarGraphFactory();
                break;
            case 3:
                factory = new PieChartFactory();
                break;
            default:
                System.out.println("Невірний вибір.");
                return;
        }

        // Створення графіка за допомогою фабрики
        Graph graph = factory.createGraph(data);

        // Візуалізація графіка
        graph.draw();

        scanner.close();
    }
}
