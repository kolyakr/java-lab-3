package Droid;

public class AbsorbDroid extends BaseDroid {
    private static final double ABSORB_PERCENTAGE = 0.3; // 30% поглинутої шкоди
    private static final int ATTACK_BOOST = 5; // Кожні поглинуті 30% дають +5 до атаки

    // Конструктор
    public AbsorbDroid(String name, int health, int damage) {
        super(name, health, damage);
    }

    // Перевизначаємо метод takeDamage, щоб поглинати частину шкоди
    @Override
    public void takeDamage(int damage) {
        int absorbedDamage = (int) (damage * ABSORB_PERCENTAGE); // Поглинаємо частину шкоди
        int reducedDamage = damage - absorbedDamage; // Справжня шкода, яку отримує дроїд
        super.takeDamage(reducedDamage); // Наносимо дроїду зменшену шкоду

        // Використовуємо поглинуту шкоду для збільшення атаки
        boostAttack(absorbedDamage);
    }

    // Метод для збільшення атаки
    private void boostAttack(int absorbedDamage) {
        int additionalDamage = absorbedDamage / (int)(10 / ABSORB_PERCENTAGE); // За кожні поглинуті 10 одиниць додаємо +5 до атаки
        setDamage(getDamage() + additionalDamage);
        System.out.println(getName() + " поглинає " + absorbedDamage + " шкоди і збільшує атаку на " + additionalDamage + ".");
    }

    // Перевизначаємо метод toString для відображення нової атаки
    @Override
    public String toString() {
        return super.toString() + " (збільшена атака)";
    }
}
