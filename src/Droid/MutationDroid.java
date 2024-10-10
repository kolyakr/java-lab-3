package Droid;

import java.util.Random;

public class MutationDroid extends BaseDroid {
    private int attackBoost;
    private int healthBoost;
    private int defenseBoost;
    private Random random;

    public MutationDroid(String name, int health, int damage, int attackBoost, int healthBoost, int defenseBoost) {
        super(name, health, damage);
        this.attackBoost = validateBoost(attackBoost, 1, 10);
        this.healthBoost = validateBoost(healthBoost, 5, 20);
        this.defenseBoost = validateBoost(defenseBoost, 1, 5);
        this.random = new Random();
    }

    private int validateBoost(int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException("Значення повинно бути між " + min + " і " + max + ".");
        }
        return value;
    }

    public void mutate() {
        int mutationType = random.nextInt(3); // Випадковий вибір між 3 типами мутації

        switch (mutationType) {
            case 0:
                setDamage(getDamage() + attackBoost);
                System.out.println(getName() + " мутує! Атака збільшується на " + attackBoost + ".");
                break;
            case 1:
                setHealth(getHealth() + healthBoost);
                System.out.println(getName() + " мутує! Здоров'я збільшується на " + healthBoost + ".");
                break;
            case 2:
                System.out.println(getName() + " мутує! Захист підвищується, зменшуючи отриману шкоду.");
                break;
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (random.nextInt(3) == 2) {
            damage -= defenseBoost;
            if (damage < 0) damage = 0;
            System.out.println(getName() + " зменшує шкоду на " + defenseBoost + " через мутацію.");
        }
        super.takeDamage(damage);
    }

    @Override
    public void attack(BaseDroid target) {
        mutate();  // Мутація перед атакою
        super.attack(target);
    }

    @Override
    public String toString() {
        return super.toString() +
                " (MutationDroid) " +
                "[Attack Boost: " + attackBoost +
                ", Health Boost: " + healthBoost +
                ", Defense Boost: " + defenseBoost + "]";
    }
}
