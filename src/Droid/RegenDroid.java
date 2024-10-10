package Droid;

public class RegenDroid extends BaseDroid {
    private double healthThreshold;
    private int regenAmount;

    private int maxHealth;

    public RegenDroid(String name, int health, int damage, double healthThreshold, int regenAmount) {
        super(name, health, damage);
        this.healthThreshold = validateHealthThreshold(healthThreshold);
        this.regenAmount = validateRegenAmount(regenAmount);
        this.maxHealth = health;
    }

    private double validateHealthThreshold(double threshold) {
        if (threshold < 0.1 || threshold > 0.5) {
            throw new IllegalArgumentException("Поріг регенерації повинен бути між 0.1 і 0.5.");
        }
        return threshold;
    }

    private int validateRegenAmount(int regen) {
        if (regen < 5 || regen > 30) {
            throw new IllegalArgumentException("Кількість регенерації повинна бути між 5 і 30.");
        }
        return regen;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (getHealth() < maxHealth * healthThreshold && getHealth() > 0) {
            regenerate();
        }
    }

    private void regenerate() {
        setHealth(getHealth() + regenAmount);
        System.out.println(getName() + " регенерує " + regenAmount + " здоров'я.");
    }

    @Override
    public String toString() {
        return super.toString() +
                " (RegenDroid) " +
                "[Health Threshold: " + healthThreshold +
                ", Regen Amount: " + regenAmount + "]";
    }
}
