package Menu;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BattleRecorder {
    private List<String> battleLog;
    private static final String FILE_NAME = "battle_log.txt";

    public BattleRecorder() {
        battleLog = new ArrayList<>();
    }

    public void addAction(String action) {
        battleLog.add(action);
    }

    public void saveBattle() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) { // Перезапис файлу
            for (String action : battleLog) {
                writer.write(action + "\n");
            }
            System.out.println("Бій успішно збережено у файл: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Помилка при збереженні у файл: " + e.getMessage());
        }
    }

    public List<String> loadBattle() {
        try {
            List<String> fileContent = Files.readAllLines(Paths.get(FILE_NAME));
            battleLog = new ArrayList<>(fileContent);
            System.out.println("Бій успішно завантажено з файлу.");
            return fileContent;
        } catch (IOException e) {
            System.err.println("Помилка при завантаженні з файлу: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<String> getBattleLog() {
        return battleLog;
    }

    public void replayBattle() {
        if (battleLog.isEmpty()) {
            System.out.println("Бій не знайдено. Завантажте його з файлу.");
            return;
        }

        System.out.println("Відтворення бою:");
        for (String action : battleLog) {
            System.out.println(action);
        }
    }
}
