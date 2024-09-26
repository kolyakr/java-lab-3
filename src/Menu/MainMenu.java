package Menu;

import Droid.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private ArrayList<BaseDroid> droids = new ArrayList<>();
    private BattleSimulator battleSimulator = new BattleSimulator();
    private BattleRecorder battleRecorder = new BattleRecorder();
    private Scanner scanner = new Scanner(System.in);
    private static final int MIN_TEAM_SIZE = 2; // Мінімальна кількість дроїдів для кожної команди

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Меню:");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Запустити бій 1 на 1");
            System.out.println("4. Запустити бій команда на команду (мінімум по " + MIN_TEAM_SIZE + " дроїди в кожній команді)");
            System.out.println("5. Записати бій у файл");
            System.out.println("6. Відтворити бій з файлу");
            System.out.println("7. Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startOneOnOneBattle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    saveBattle();
                    break;
                case 6:
                    loadBattle();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Вихід з програми.");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private void createDroid() {
        System.out.println("Оберіть тип дроїда:");
        System.out.println("1. AttackDroid");
        System.out.println("2. RegenDroid");
        System.out.println("3. AbsorbDroid");
        System.out.println("4. MutationDroid");

        int choice = scanner.nextInt();
        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.next();

        // Задання характеристик у певному діапазоні
        System.out.print("Введіть здоров'я дроїда (від 50 до 200): ");
        int health = getInputInRange(50, 200);

        System.out.print("Введіть атаку дроїда (від 10 до 50): ");
        int damage = getInputInRange(10, 50);

        BaseDroid droid;
        switch (choice) {
            case 1:
                droid = new AttackDroid(name, health, damage);
                break;
            case 2:
                droid = new RegenDroid(name, health, damage);
                break;
            case 3:
                droid = new AbsorbDroid(name, health, damage);
                break;
            case 4:
                droid = new MutationDroid(name, health, damage);
                break;
            default:
                System.out.println("Невірний вибір.");
                return;
        }

        droids.add(droid);
        System.out.println("Дроїд " + droid.getName() + " створений.");
    }

    private int getInputInRange(int min, int max) {
        int value;
        do {
            value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.println("Невірне значення. Введіть число в межах від " + min + " до " + max + ": ");
            }
        } while (value < min || value > max);
        return value;
    }

    private void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("Список дроїдів порожній.");
        } else {
            System.out.println("Список створених дроїдів:");
            for (int i = 0; i < droids.size(); i++) {
                System.out.println((i + 1) + ". " + droids.get(i));
            }
        }
    }

    private void startOneOnOneBattle() {
        if (droids.size() < 2) {
            System.out.println("Недостатньо дроїдів для бою 1 на 1.");
            return;
        }
        System.out.println("Виберіть дроїдів для бою 1 на 1:");
        showDroids();
        System.out.print("Виберіть дроїда 1: ");
        int droid1Index = scanner.nextInt() - 1;
        System.out.print("Виберіть дроїда 2: ");
        int droid2Index = scanner.nextInt() - 1;

        if (isValidIndex(droid1Index) && isValidIndex(droid2Index)) {
            battleSimulator.simulateOneOnOne(droids.get(droid1Index), droids.get(droid2Index));
        } else {
            System.out.println("Невірний вибір дроїдів.");
        }
    }

    private void startTeamBattle() {
        if (droids.size() < MIN_TEAM_SIZE * 2) {
            System.out.println("Недостатньо дроїдів для командного бою. Необхідно хоча б " + (MIN_TEAM_SIZE * 2) + " дроїдів.");
            return;
        }

        System.out.println("Формуємо команди для бою:");
        battleSimulator.simulateTeamBattle(droids);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < droids.size();
    }

    private void saveBattle() {
        battleRecorder.saveBattle();
    }

    private void loadBattle() {
        battleRecorder.loadBattle();
    }
}
