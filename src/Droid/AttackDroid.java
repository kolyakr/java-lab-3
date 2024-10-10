package Droid;

public class AttackDroid extends BaseDroid {
    private double criticalHitChance; // Шанс критичного удару
    private double criticalHitMultiplier; // Множник критичного удару

    public AttackDroid(String name, int health, int damage, double criticalHitChance, double criticalHitMultiplier) {
        super(name, health, damage);
        this.criticalHitChance = validateCriticalHitChance(criticalHitChance);
        this.criticalHitMultiplier = validateCriticalHitMultiplier(criticalHitMultiplier);
    }

    private double validateCriticalHitChance(double chance) {
        if (chance < 0.0 || chance > 1.0) {
            throw new IllegalArgumentException("Шанс критичного удару повинен бути між 0 і 1.");
        }
        return chance;
    }

    private double validateCriticalHitMultiplier(double multiplier) {
        if (multiplier < 1.0 || multiplier > 3.0) {
            throw new IllegalArgumentException("Множник критичного удару повинен бути між 1.0 і 3.0.");
        }
        return multiplier;
    }

    @Override
    public void attack(BaseDroid target) {
        boolean isCriticalHit = Math.random() < criticalHitChance; // Шанс критичного удару
        int damageToDeal = getDamage();

        if (isCriticalHit) {
            damageToDeal *= criticalHitMultiplier; // Збільшення шкоди при критичному ударі
            System.out.println(getName() + " наносить критичний удар!");
        }

        System.out.println(getName() + " атакує " + target.getName() + " і завдає " + damageToDeal + " шкоди.");
        target.takeDamage(damageToDeal);
        System.out.println(target.getName() + " має " + target.getHealth() + " здоров'я після атаки.");
    }

    @Override
    public String toString() {
        return super.toString() +
                " (AttackDroid) " +
                "[Critical Hit Chance: " + criticalHitChance +
                ", Critical Hit Multiplier: " + criticalHitMultiplier + "]";
    }
}
