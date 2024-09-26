package Droid;

public class AttackDroid extends BaseDroid {
    private static final double CRITICAL_HIT_CHANCE = 0.2; // 20% шанс на критичний удар
    private static final double CRITICAL_HIT_MULTIPLIER = 1.5; // Критичний удар збільшує шкоду на 50%

    public AttackDroid(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void attack(BaseDroid target) {
        boolean isCriticalHit = Math.random() < CRITICAL_HIT_CHANCE; // Шанс критичного удару
        int damageToDeal = getDamage();

        if (isCriticalHit) {
            damageToDeal *= CRITICAL_HIT_MULTIPLIER; // Збільшення шкоди при критичному ударі
            System.out.println(getName() + " наносить критичний удар!");
        }

        System.out.println(getName() + " атакує " + target.getName() + " і завдає " + damageToDeal + " шкоди.");
        target.takeDamage(damageToDeal);
        System.out.println(target.getName() + " має " + target.getHealth() + " здоров'я після атаки.");
    }

    @Override
    public String toString() {
        return super.toString() + " (AttackDroid)";
    }
}
