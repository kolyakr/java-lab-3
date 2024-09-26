package Droid;

import java.util.Random;

public class MutationDroid extends BaseDroid {
    private static final int ATTACK_BOOST = 5;   // Підвищення атаки при мутації
    private static final int HEALTH_BOOST = 10;   // Підвищення здоров'я при мутації
    private static final int DEFENSE_BOOST = 3;   // Зменшення отриманої шкоди при мутації

    private Random random;

    public MutationDroid(String name, int health, int damage) {
        super(name, health, damage);
        this.random = new Random();
    }

    public void mutate() {
        int mutationType = random.nextInt(3); // Випадковий вибір між 3 типами мутації

        switch (mutationType) {
            case 0:
                setDamage(getDamage() + ATTACK_BOOST);
                System.out.println(getName() + " мутує! Атака збільшується на " + ATTACK_BOOST + ".");
                break;
            case 1:
                setHealth(getHealth() + HEALTH_BOOST);
                System.out.println(getName() + " мутує! Здоров'я збільшується на " + HEALTH_BOOST + ".");
                break;
            case 2:
                System.out.println(getName() + " мутує! Захист підвищується, зменшуючи отриману шкоду.");
                break;
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (random.nextInt(3) == 2) {
            damage -= DEFENSE_BOOST;
            if (damage < 0) damage = 0;
            System.out.println(getName() + " зменшує шкоду на " + DEFENSE_BOOST + " через мутацію.");
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
        return super.toString() + " (MutationDroid)";
    }
}
