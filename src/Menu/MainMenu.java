package Menu;

import Droid.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private ArrayList<BaseDroid> droids = new ArrayList<>();
    private BattleSimulator battleSimulator = new BattleSimulator();
    private BattleRecorder battleRecorder = new BattleRecorder();
    private Scanner scanner = new Scanner(System.in);
    private static final int MIN_TEAM_SIZE = 2;
    private boolean isBattleFinished = false;

    public void start() {
        boolean exit = false;
        while (!exit) {
            printMenuHeader();
            System.out.println("|| 1. Створити дроїда                                 ||");
            System.out.println("|| 2. Показати список дроїдів                         ||");
            System.out.println("|| 3. Запустити бій 1 на 1                            ||");
            System.out.println("|| 4. Запустити бій команда на команду                ||");
            if (isBattleFinished) {
                System.out.println("|| 5. Записати проведений бій у файл               ||");
            }
            System.out.println("|| 6. Відтворити бій з файлу                          ||");
            System.out.println("|| 7. Вийти                                           ||");
            printMenuFooter();

            System.out.print("Оберіть дію: ");
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
                    if (isBattleFinished) {
                        saveBattle();
                    } else {
                        System.out.println("Спершу потрібно провести бій.");
                    }
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

    private void printMenuHeader() {
        System.out.println("=======================================================");
        System.out.println("||                                                    ||");
        System.out.println("||                МЕНЮ УПРАВЛІННЯ ДРОЇДАМИ            ||");
        System.out.println("||                                                    ||");
        System.out.println("=======================================================");
        System.out.println("||                                                    ||");
    }

    private void printMenuFooter() {
        System.out.println("||                                                    ||");
        System.out.println("=======================================================");
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

        System.out.print("Введіть здоров'я дроїда (від 50 до 200): ");
        int health = getInputInRange(50, 200);

        System.out.print("Введіть атаку дроїда (від 10 до 50): ");
        int damage = getInputInRange(10, 50);

        BaseDroid droid;
        switch (choice) {
            case 1:
                // Введення параметрів для AttackDroid
                System.out.print("Введіть шанс критичного удару (0.0 - 1.0): ");
                double criticalHitChance = getInputInRangeDouble(0.0, 1.0);
                System.out.print("Введіть множник критичного удару (1.0 - 3.0): ");
                double criticalHitMultiplier = getInputInRangeDouble(1.0, 3.0);
                droid = new AttackDroid(name, health, damage, criticalHitChance, criticalHitMultiplier);
                break;
            case 2:
                // Введення параметрів для RegenDroid
                System.out.print("Введіть поріг здоров'я для регенерації (0.1 - 0.5): ");
                double healthThreshold = getInputInRangeDouble(0.1, 0.5);
                System.out.print("Введіть кількість регенерації (5 - 30): ");
                int regenAmount = getInputInRange(5, 30);
                droid = new RegenDroid(name, health, damage, healthThreshold, regenAmount);
                break;
            case 3:
                // Введення параметрів для AbsorbDroid
                System.out.print("Введіть відсоток поглинання шкоди (0.1 - 0.5): ");
                double absorbPercentage = getInputInRangeDouble(0.1, 0.5);
                System.out.print("Введіть підвищення атаки при поглинанні (1 - 10): ");
                int attackBoost = getInputInRange(1, 10);
                droid = new AbsorbDroid(name, health, damage, absorbPercentage, attackBoost);
                break;
            case 4:
                // Введення параметрів для MutationDroid
                System.out.print("Введіть підвищення атаки при мутації (1 - 10): ");
                int mutationAttackBoost = getInputInRange(1, 10);
                System.out.print("Введіть підвищення здоров'я при мутації (5 - 20): ");
                int mutationHealthBoost = getInputInRange(5, 20);
                System.out.print("Введіть зменшення шкоди при мутації (1 - 5): ");
                int mutationDefenseBoost = getInputInRange(1, 5);
                droid = new MutationDroid(name, health, damage, mutationAttackBoost, mutationHealthBoost, mutationDefenseBoost);
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

    private double getInputInRangeDouble(double min, double max) {
        double value;
        do {
            String input = scanner.next();
            input = input.replace(',', '.');
            try {
                value = Double.parseDouble(input);
                if (value < min || value > max) {
                    System.out.println("Невірне значення. Введіть число в межах від " + min + " до " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат. Введіть число.");
                value = min - 1;
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
            isBattleFinished = true;
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
        isBattleFinished = true;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < droids.size();
    }

    private void saveBattle() {
        if (isBattleFinished) {
            battleSimulator.saveBattleLog();
            isBattleFinished = false;
        } else {
            System.out.println("Немає бою для збереження.");
        }
    }

    private void loadBattle() {
        battleSimulator.replayBattle();
    }
}
