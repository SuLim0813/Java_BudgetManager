package moneyMini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EntryManager {
    private final List<Entry> entries = new ArrayList<>();

    public void addEntry(Entry entry) {
        entries.add(entry);
        sortEntries();
    }

    public List<Entry> getEntries() {
        return new ArrayList<>(entries); // 불변 리스트 반환
    }

    public void clearEntries() {
        entries.clear(); // 모든 항목 삭제
    }

    public void viewEntries() {
        System.out.println("모든 내역 조회 (총 " + entries.size() + "개):");
        printAllEntries();
    }

    private void printAllEntries() {
        int index = 1;
        for (Entry entry : entries) {
            System.out.println(index + " : " + entry);
            index++;
        }
    }

    public void editEntry(int index, Entry entry) {
        if (isValidIndex(index)) {
            entries.set(index, entry);
            sortEntries(); // 항목 수정 후 정렬
            System.out.println("내역이 수정되었습니다.");
        } else {
            System.out.println("해당 번호는 조회할 수 없습니다.");
        }
    }

    public void deleteEntry(Scanner scanner) {
        while (true) {
            System.out.print("삭제할 항목의 번호를 입력하세요: ");
            int index = scanner.nextInt(); // 번호 입력 받기
            scanner.nextLine(); // 개행 문자 제거

            int adjustedIndex = index - 1; // 1부터 시작하는 번호를 0부터 시작하는 인덱스로 변환

            if (isValidIndex(adjustedIndex)) {
                entries.remove(adjustedIndex);
                System.out.println("해당 내역이 삭제되었습니다.");
                break; // 삭제 후 반복 종료
            } else {
                System.out.println("해당 번호는 조회할 수 없습니다.");
            }
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < entries.size();
    }

    private void sortEntries() {
        // 임시 리스트로 수입과 지출을 분리하여 각 카테고리 순서대로 추가
        List<Entry> incomeEntries = new ArrayList<>();
        List<Entry> expenseEntries = new ArrayList<>();
        
        for (Entry entry : entries) {
            if (entry.getType().equals("수입")) {
                incomeEntries.add(entry);
            } else {
                expenseEntries.add(entry);
            }
        }
        
        // 수입과 지출을 합쳐서 입력 순서대로 정렬하도록 새로운 리스트에 다시 추가
        entries.clear();
        entries.addAll(incomeEntries);
        entries.addAll(expenseEntries);
    }

    // 수입 카테고리 목록을 반환
    public static List<String> getIncomeCategories() {
        return Arrays.asList("급여", "상여급", "용돈", "장학금", "사업수입");
    }

    // 지출 카테고리 목록을 반환
    public static List<String> getExpenseCategories() {
        return Arrays.asList("식비", "카페/간식", "술/유흥", "생활비", "패션/쇼핑", "뷰티/미용", "교통", "의료/건강", "주거/통신", "문화/여가", "여행/숙박", "자기개발", "반려동물", "선물/경조사");
    }
}
