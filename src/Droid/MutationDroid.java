package Droid;

import java.util.Random;

public class MutationDroid extends BaseDroid {
    private static final int ATTACK_BOOST = 10;   // Підвищення атаки при мутації
    private static final int HEALTH_BOOST = 20;   // Підвищення здоров'я при мутації
    private static final int DEFENSE_BOOST = 5;   // Зменшення отриманої шкоди при мутації

    private Random random;

    // Конструктор
    public MutationDroid(String name, int health, int damage) {
        super(name, health, damage);
        this.random = new Random();
    }

    // Метод мутації: змінює характеристики випадковим чином
    public void mutate() {
        int mutationType = random.nextInt(3); // Випадковий вибір між 3 типами мутації

        switch (mutationType) {
            case 0:
                // Мутація: збільшення атаки
                setDamage(getDamage() + ATTACK_BOOST);
                System.out.println(getName() + " мутує! Атака збільшується на " + ATTACK_BOOST + ".");
                break;
            case 1:
                // Мутація: збільшення здоров'я
                setHealth(getHealth() + HEALTH_BOOST);
                System.out.println(getName() + " мутує! Здоров'я збільшується на " + HEALTH_BOOST + ".");
                break;
            case 2:
                // Мутація: зменшення шкоди (підвищення захисту)
                System.out.println(getName() + " мутує! Захист підвищується, зменшуючи отриману шкоду.");
                break;
            default:
                break;
        }
    }

    // Перевизначаємо метод takeDamage, щоб врахувати захисну мутацію
    @Override
    public void takeDamage(int damage) {
        // Перевірка на захисну мутацію
        if (random.nextInt(3) == 2) {  // Якщо активна мутація захисту
            damage -= DEFENSE_BOOST;
            if (damage < 0) damage = 0; // Уникаємо негативної шкоди
            System.out.println(getName() + " поглинає частину шкоди через мутацію захисту!");
        }
        super.takeDamage(damage); // Наносимо зменшену шкоду
    }

    // Метод атаки, який включає випадкову мутацію
    @Override
    public void attack(BaseDroid target) {
        mutate();  // Мутація перед атакою
        super.attack(target);  // Викликаємо стандартну атаку
    }

    // Перевизначаємо метод toString для додаткової інформації
    @Override
    public String toString() {
        return super.toString() + " (може мутувати)";
    }
}
