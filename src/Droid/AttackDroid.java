package Droid;

import java.util.Random;

public class AttackDroid extends BaseDroid {
    private static final double CRITICAL_HIT_CHANCE = 0.2; // 20% шанс на критичний удар
    private static final double CRITICAL_HIT_MULTIPLIER = 2.0; // Критичний удар завдає вдвічі більше шкоди
    private Random random;

    // Конструктор
    public AttackDroid(String name, int health, int damage) {
        super(name, health, damage);
        this.random = new Random();
    }

    // Метод атаки з можливістю критичного удару
    @Override
    public void attack(BaseDroid target) {
        boolean isCriticalHit = random.nextDouble() < CRITICAL_HIT_CHANCE; // Визначаємо, чи буде критичний удар
        int damageToDeal = getDamage();

        if (isCriticalHit) {
            damageToDeal *= CRITICAL_HIT_MULTIPLIER; // Збільшуємо шкоду при критичному ударі
            System.out.println(getName() + " наносить критичний удар!");
        }

        System.out.println(getName() + " атакує " + target.getName() + " і завдає " + damageToDeal + " шкоди.");
        target.takeDamage(damageToDeal);
    }
}
