package Droid;

public class BaseDroid {
    private String name;
    private int health;
    private int damage;

    // Конструктор
    public BaseDroid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    // Методи доступу (геттери)
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    // Методи для зміни значень полів (сеттер)
    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Метод для нанесення шкоди
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    // Метод для атаки
    public void attack(BaseDroid target) {
        System.out.println(this.name + " атакує " + target.getName() + " і завдає " + this.damage + " шкоди.");
        target.takeDamage(this.damage);
        System.out.println(target.getName() + " має " + target.getHealth() + " здоров'я після атаки.");
    }

    // Перевірка стану живого дроїда
    public boolean isAlive() {
        return this.health > 0;
    }

    // Метод для відображення інформації про дроїда
    @Override
    public String toString() {
        return "Droid{name='" + name + "', health=" + health + ", damage=" + damage + "}";
    }
}
