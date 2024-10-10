package Droid;

public class AbsorbDroid extends BaseDroid {
    private double absorbPercentage;
    private int attackBoost;

    public AbsorbDroid(String name, int health, int damage, double absorbPercentage, int attackBoost) {
        super(name, health, damage);
        this.absorbPercentage = validateAbsorbPercentage(absorbPercentage);
        this.attackBoost = validateAttackBoost(attackBoost);
    }

    private double validateAbsorbPercentage(double percentage) {
        if (percentage < 0.1 || percentage > 0.5) {
            throw new IllegalArgumentException("Поглинання шкоди повинно бути між 0.1 і 0.5.");
        }
        return percentage;
    }

    private int validateAttackBoost(int boost) {
        if (boost < 1 || boost > 10) {
            throw new IllegalArgumentException("Підвищення атаки повинно бути між 1 і 10.");
        }
        return boost;
    }

    @Override
    public void takeDamage(int damage) {
        int absorbedDamage = (int) (damage * absorbPercentage);
        int reducedDamage = damage - absorbedDamage;
        super.takeDamage(reducedDamage);
        boostAttack(absorbedDamage);
    }

    private void boostAttack(int absorbedDamage) {
        setDamage(getDamage() + attackBoost);
        System.out.println(getName() + " поглинає " + absorbedDamage + " шкоди і збільшує атаку на " + attackBoost + ".");
    }

    @Override
    public String toString() {
        return super.toString() +
                " (AbsorbDroid) " +
                "[Absorb Percentage: " + absorbPercentage +
                ", Attack Boost: " + attackBoost + "]";
    }
}
