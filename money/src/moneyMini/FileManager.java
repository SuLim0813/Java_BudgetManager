package moneyMini;

import java.io.*;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "budget.txt"; // 파일 경로

    // 데이터 저장 메서드
    public void saveToFile(List<Entry> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Entry entry : entries) {
                writer.write(formatEntry(entry));
                writer.newLine();
            }
            System.out.println("모든 데이터가 저장되었습니다.");
        } catch (IOException e) {
            handleError("파일을 저장하는데 에러가 발생했습니다.", e);
        }
    }

    // 모든 데이터 및 파일 내용 삭제 메서드
    public void clearData(EntryManager entryManager) {
        entryManager.clearEntries(); // EntryManager의 모든 데이터 삭제
        clearFile(); // 파일 내용 삭제
        System.out.println("모든 데이터가 삭제되었습니다.");
    }

    // 파일 내용을 비우는 메서드
    private void clearFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(""); // 파일을 빈 문자열로 덮어씌워 파일 내용 삭제
        } catch (IOException e) {
            handleError("파일을 초기화하는 동안 오류가 발생했습니다.", e);
        }
    }

    // 파일에서 데이터 불러오는 메서드
    public void loadFromFile(EntryManager entryManager) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("파일을 찾을 수 없습니다.");
            return; // 파일이 존재하지 않으면 메서드를 종료
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean hasData = false; // 데이터가 있는지 여부를 확인하는 변수

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // 비어있는 줄은 무시
                    hasData = true; // 데이터가 있음을 표시
                    Entry entry = parseEntry(line);
                    if (entry != null) {
                        entryManager.addEntry(entry);
                    }
                }
            }

            printLoadMessage(hasData);

        } catch (IOException e) {
            handleError("파일을 불러오던 중 오류가 발생했습니다.", e);
        }
    }

    private String formatEntry(Entry entry) {
        return entry.getDate() + " | " + entry.getType() + " | " + entry.getCategory() + " | " + entry.getAmount() + " | " + entry.getNote();
    }

    private Entry parseEntry(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 5) {
            return null; // 형식이 잘못된 경우 null 반환
        }
        String date = parts[0].trim();
        String type = parts[1].trim();
        String category = parts[2].trim();
        int amount = Integer.parseInt(parts[3].trim());
        String note = parts[4].trim();
        return new Entry(date, type, category, amount, note);
    }

    private void printLoadMessage(boolean hasData) {
        if (hasData) {
            System.out.println("환영합니다. 오늘의 가계부 입니다.");
            System.out.println("데이터를 불러오는 중...");
        } else {
            System.out.println("새로운 내역을 추가해주세요.");
        }
    }

    private void handleError(String message, Exception e) {
        System.out.println(message);
        e.printStackTrace(); // 에러의 스택 트레이스를 출력 (선택적)
    }
}
