package Droid;

public class AbsorbDroid extends BaseDroid {
    private static final double ABSORB_PERCENTAGE = 0.3; // 30% поглинутої шкоди
    private static final int ATTACK_BOOST = 5; // Підвищення атаки при поглинанні шкоди

    public AbsorbDroid(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void takeDamage(int damage) {
        int absorbedDamage = (int) (damage * ABSORB_PERCENTAGE);
        int reducedDamage = damage - absorbedDamage;
        super.takeDamage(reducedDamage);
        boostAttack(absorbedDamage);
    }

    private void boostAttack(int absorbedDamage) {
        setDamage(getDamage() + ATTACK_BOOST);
        System.out.println(getName() + " поглинає " + absorbedDamage + " шкоди і збільшує атаку на " + ATTACK_BOOST + ".");
    }

    @Override
    public String toString() {
        return super.toString() + " (AbsorbDroid)";
    }
}
