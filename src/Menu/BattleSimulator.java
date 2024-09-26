package Menu;

import Droid.BaseDroid;
import java.util.ArrayList;
import java.util.Random;

public class BattleSimulator {
    private Random random = new Random();

    public void simulateOneOnOne(BaseDroid droid1, BaseDroid droid2) {
        System.out.println("Бій 1 на 1 між " + droid1.getName() + " і " + droid2.getName());
        while (droid1.isAlive() && droid2.isAlive()) {
            droid1.attack(droid2);
            if (droid2.isAlive()) {
                droid2.attack(droid1);
            }
        }
        if (droid1.isAlive()) {
            System.out.println(droid1.getName() + " переміг!");
        } else {
            System.out.println(droid2.getName() + " переміг!");
        }
    }

    public void simulateTeamBattle(ArrayList<BaseDroid> droids) {
        ArrayList<BaseDroid> team1 = new ArrayList<>();
        ArrayList<BaseDroid> team2 = new ArrayList<>();

        // Розподіляємо дроїдів у дві команди випадково
        for (int i = 0; i < droids.size(); i++) {
            if (i % 2 == 0) {
                team1.add(droids.get(i));
            } else {
                team2.add(droids.get(i));
            }
        }

        System.out.println("Починається командний бій!");
        for (int i = 0; i < Math.min(team1.size(), team2.size()); i++) {
            simulateOneOnOne(team1.get(i), team2.get(i));
        }

        System.out.println("Бій завершено!");
    }
}
