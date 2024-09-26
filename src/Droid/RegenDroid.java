package Droid;

public class RegenDroid extends BaseDroid {
    private static final double HEALTH_THRESHOLD = 0.3; // 30% здоров'я, після якого активується регенерація
    private static final int REGEN_AMOUNT = 10; // Кількість здоров'я для регенерації

    private int maxHealth; // Запам'ятовуємо максимальний рівень здоров'я для регенерації

    public RegenDroid(String name, int health, int damage) {
        super(name, health, damage);
        this.maxHealth = health;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (getHealth() < maxHealth * HEALTH_THRESHOLD && getHealth() > 0) {
            regenerate();
        }
    }

    private void regenerate() {
        setHealth(getHealth() + REGEN_AMOUNT);
        System.out.println(getName() + " регенерує " + REGEN_AMOUNT + " здоров'я.");
    }

    @Override
    public String toString() {
        return super.toString() + " (RegenDroid)";
    }
}
