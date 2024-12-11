import java.util.Scanner;

// Компоненти для продуктів
interface Screen {
    void display();
}

interface Processor {
    void process();
}

interface Camera {
    void capture();
}

// Конкретні компоненти для смартфона
class SmartphoneScreen implements Screen {
    @Override
    public void display() {
        System.out.println("Смартфон: 6.5-дюймовий OLED екран");
    }
}

class SmartphoneProcessor implements Processor {
    @Override
    public void process() {
        System.out.println("Смартфон: Процесор Snapdragon 888");
    }
}

class SmartphoneCamera implements Camera {
    @Override
    public void capture() {
        System.out.println("Смартфон: Камера 12 МП");
    }
}

// Конкретні компоненти для ноутбука
class LaptopScreen implements Screen {
    @Override
    public void display() {
        System.out.println("Ноутбук: 15.6-дюймовий Full HD екран");
    }
}

class LaptopProcessor implements Processor {
    @Override
    public void process() {
        System.out.println("Ноутбук: Процесор Intel Core i7");
    }
}

class LaptopCamera implements Camera {
    @Override
    public void capture() {
        System.out.println("Ноутбук: Камера 720p");
    }
}

// Конкретні компоненти для планшета
class TabletScreen implements Screen {
    @Override
    public void display() {
        System.out.println("Планшет: 10-дюймовий Retina екран");
    }
}

class TabletProcessor implements Processor {
    @Override
    public void process() {
        System.out.println("Планшет: Процесор A14 Bionic");
    }
}

class TabletCamera implements Camera {
    @Override
    public void capture() {
        System.out.println("Планшет: Камера 8 МП");
    }
}

// Абстрактна фабрика для створення продуктів
interface ProductFactory {
    Screen createScreen();
    Processor createProcessor();
    Camera createCamera();
}

// Фабрики для створення компонентів різних продуктів
class SmartphoneFactory implements ProductFactory {
    @Override
    public Screen createScreen() {
        return new SmartphoneScreen();
    }

    @Override
    public Processor createProcessor() {
        return new SmartphoneProcessor();
    }

    @Override
    public Camera createCamera() {
        return new SmartphoneCamera();
    }
}

class LaptopFactory implements ProductFactory {
    @Override
    public Screen createScreen() {
        return new LaptopScreen();
    }

    @Override
    public Processor createProcessor() {
        return new LaptopProcessor();
    }

    @Override
    public Camera createCamera() {
        return new LaptopCamera();
    }
}

class TabletFactory implements ProductFactory {
    @Override
    public Screen createScreen() {
        return new TabletScreen();
    }

    @Override
    public Processor createProcessor() {
        return new TabletProcessor();
    }

    @Override
    public Camera createCamera() {
        return new TabletCamera();
    }
}

// Клас для збірки та демонстрації продукту
class Product {
    private Screen screen;
    private Processor processor;
    private Camera camera;

    public Product(Screen screen, Processor processor, Camera camera) {
        this.screen = screen;
        this.processor = processor;
        this.camera = camera;
    }

    public void assemble() {
        screen.display();
        processor.process();
        camera.capture();
    }
}

// Головний клас програми
public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Вибір типу продукту
        System.out.println("Виберіть тип продукту:");
        System.out.println("1. Смартфон");
        System.out.println("2. Ноутбук");
        System.out.println("3. Планшет");
        int choice = scanner.nextInt();

        // Створення відповідної фабрики
        ProductFactory factory = null;
        switch (choice) {
            case 1:
                factory = new SmartphoneFactory();
                break;
            case 2:
                factory = new LaptopFactory();
                break;
            case 3:
                factory = new TabletFactory();
                break;
            default:
                System.out.println("Невірний вибір.");
                return;
        }

        // Створення компонентів за допомогою фабрики
        Screen screen = factory.createScreen();
        Processor processor = factory.createProcessor();
        Camera camera = factory.createCamera();

        // Збірка продукту
        Product product = new Product(screen, processor, camera);
        product.assemble();

        scanner.close();
    }
}
