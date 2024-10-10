package Menu;

import Droid.BaseDroid;
import java.util.ArrayList;

public class BattleSimulator {
    private BattleRecorder battleRecorder;

    public BattleSimulator() {
        this.battleRecorder = new BattleRecorder();
    }

    public void simulateOneOnOne(BaseDroid droid1, BaseDroid droid2) {
        System.out.println("======================================================");
        System.out.println("||                                                  ||");
        System.out.println("||         БІЙ 1 на 1: " + droid1.getName() + " проти " + droid2.getName() + "         ||");
        System.out.println("||                                                  ||");
        System.out.println("======================================================");

        battleRecorder.addAction("Початок бою 1 на 1 між " + droid1.getName() + " і " + droid2.getName());

        int round = 1;
        while (droid1.isAlive() && droid2.isAlive()) {
            printRoundHeader(round);
            battleRecorder.addAction("=== РАУНД " + round + " ===");

            // Атака дроїда 1
            System.out.println(droid1.getName() + " атакує " + droid2.getName());
            droid1.attack(droid2);
            battleRecorder.addAction(droid1.getName() + " атакує " + droid2.getName() + " і завдає " + droid1.getDamage() + " шкоди.");

            if (droid2.isAlive()) {
                // Атака дроїда 2
                System.out.println(droid2.getName() + " атакує " + droid1.getName());
                droid2.attack(droid1);
                battleRecorder.addAction(droid2.getName() + " атакує " + droid1.getName() + " і завдає " + droid2.getDamage() + " шкоди.");
            }

            printHealthStatus(droid1, droid2); // Виведення здоров'я після раунду
            battleRecorder.addAction("Стан після раунду: " + droid1.getName() + " - " + droid1.getHealth() + " здоров'я, " +
                    droid2.getName() + " - " + droid2.getHealth() + " здоров'я.");

            round++;
        }

        printWinner(droid1, droid2);

        if (droid1.isAlive()) {
            battleRecorder.addAction("Переможець: " + droid1.getName());
        } else {
            battleRecorder.addAction("Переможець: " + droid2.getName());
        }
    }

    private void printHealthStatus(BaseDroid droid1, BaseDroid droid2) {
        System.out.println("\n=== СТАН ПІСЛЯ РАУНДУ ===");
        System.out.println(droid1.getName() + ": " + droid1.getHealth() + " здоров'я");
        System.out.println(droid2.getName() + ": " + droid2.getHealth() + " здоров'я");
        System.out.println("=========================\n");
    }

    private void printWinner(BaseDroid droid1, BaseDroid droid2) {
        if (droid1.isAlive()) {
            System.out.println("\n======================================================");
            System.out.println("||                                                  ||");
            System.out.println("||               " + droid1.getName() + " ПЕРЕМІГ!                   ||");
            System.out.println("||                                                  ||");
            System.out.println("======================================================\n");
        } else {
            System.out.println("\n======================================================");
            System.out.println("||                                                  ||");
            System.out.println("||               " + droid2.getName() + " ПЕРЕМІГ!                   ||");
            System.out.println("||                                                  ||");
            System.out.println("======================================================\n");
        }
    }

    public void simulateTeamBattle(ArrayList<BaseDroid> droids) {
        ArrayList<BaseDroid> team1 = new ArrayList<>();
        ArrayList<BaseDroid> team2 = new ArrayList<>();

        if (droids.size() < 4) {
            System.out.println("Для командного бою необхідно мінімум 4 дроїди (2 на 2).");
            return;
        }

        for (int i = 0; i < droids.size(); i++) {
            if (i % 2 == 0) {
                team1.add(droids.get(i));
            } else {
                team2.add(droids.get(i));
            }
        }

        System.out.println("======================================================");
        System.out.println("||                                                  ||");
        System.out.println("||                 ПОЧАТОК КОМАНДНОГО БОЮ           ||");
        System.out.println("||                                                  ||");
        System.out.println("======================================================");

        battleRecorder.addAction("Початок командного бою між Team 1 і Team 2");

        int round = 1;
        while (!team1.isEmpty() && !team2.isEmpty()) {
            printRoundHeader(round);
            battleRecorder.addAction("=== РАУНД " + round + " ===");

            for (BaseDroid droid : team1) {
                if (!team2.isEmpty()) {
                    BaseDroid target = getRandomTarget(team2);
                    System.out.println(droid.getName() + " з команди 1 атакує " + target.getName() + " з команди 2");
                    droid.attack(target);
                    battleRecorder.addAction(droid.getName() + " з команди 1 атакує " + target.getName() + " з команди 2");

                    if (!target.isAlive()) {
                        System.out.println(target.getName() + " загинув!");
                        battleRecorder.addAction(target.getName() + " з команди 2 загинув.");
                        team2.remove(target);
                    }
                }
            }

            for (BaseDroid droid : team2) {
                if (!team1.isEmpty()) {
                    BaseDroid target = getRandomTarget(team1);
                    System.out.println(droid.getName() + " з команди 2 атакує " + target.getName() + " з команди 1");
                    droid.attack(target);
                    battleRecorder.addAction(droid.getName() + " з команди 2 атакує " + target.getName() + " з команди 1");

                    if (!target.isAlive()) {
                        System.out.println(target.getName() + " загинув!");
                        battleRecorder.addAction(target.getName() + " з команди 1 загинув.");
                        team1.remove(target);
                    }
                }
            }

            printTeamStatus(team1, team2);
            round++;
        }

        if (team1.isEmpty()) {
            System.out.println("\n======================================================");
            System.out.println("||                                                  ||");
            System.out.println("||                   ПЕРЕМОГА КОМАНДИ 2            ||");
            System.out.println("||                                                  ||");
            System.out.println("======================================================\n");
            battleRecorder.addAction("Перемога команди 2.");
        } else {
            System.out.println("\n======================================================");
            System.out.println("||                                                  ||");
            System.out.println("||                   ПЕРЕМОГА КОМАНДИ 1            ||");
            System.out.println("||                                                  ||");
            System.out.println("======================================================\n");
            battleRecorder.addAction("Перемога команди 1.");
        }
    }

    private BaseDroid getRandomTarget(ArrayList<BaseDroid> team) {
        return team.get((int) (Math.random() * team.size()));
    }

    private void printRoundHeader(int round) {
        System.out.println("\n======================================================");
        System.out.println("||                     РАУНД " + round + "                      ||");
        System.out.println("======================================================\n");
    }

    private void printTeamStatus(ArrayList<BaseDroid> team1, ArrayList<BaseDroid> team2) {
        System.out.println("\n=== СТАН КОМАНД ===");
        System.out.println("Команда 1:");
        for (BaseDroid droid : team1) {
            System.out.println(droid.getName() + ": " + droid.getHealth() + " здоров'я");
        }
        System.out.println("Команда 2:");
        for (BaseDroid droid : team2) {
            System.out.println(droid.getName() + ": " + droid.getHealth() + " здоров'я");
        }
        System.out.println("===================\n");
    }

    public void saveBattleLog() {
        battleRecorder.saveBattle();
    }

    public void replayBattle() {
        battleRecorder.loadBattle();
        battleRecorder.replayBattle();
    }
}
