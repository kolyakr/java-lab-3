package Droid;

public class RegenDroid extends BaseDroid {
    private static final double HEALTH_THRESHOLD = 0.3; // 30% від максимального здоров'я
    private static final int REGEN_AMOUNT = 10; // Кількість очок здоров'я для регенерації

    private int maxHealth; // Запам'ятовуємо максимальне здоров'я для розрахунків

    // Конструктор
    public RegenDroid(String name, int health, int damage) {
        super(name, health, damage);
        this.maxHealth = health; // Зберігаємо максимальний рівень здоров'я
    }

    // Перевизначаємо метод takeDamage, щоб додати регенерацію
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage); // Викликаємо стандартне нанесення шкоди
        if (getHealth() < maxHealth * HEALTH_THRESHOLD) {
            regenerate();
        }
    }

    // Метод регенерації
    private void regenerate() {
        setHealth(getHealth() + REGEN_AMOUNT); // Додаємо очки здоров'я
        System.out.println(getName() + " регенерує " + REGEN_AMOUNT + " здоров'я.");
    }

    // Перевизначаємо метод toString для додаткової інформації
    @Override
    public String toString() {
        return super.toString() + " (максимальне здоров'я = " + maxHealth + ")";
    }
}
