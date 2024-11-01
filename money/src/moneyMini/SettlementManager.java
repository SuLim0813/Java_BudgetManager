package moneyMini;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementManager {
    private final EntryManager entryManager;
    private int totalIncome = 0; // 클래스 인스턴스 변수로 선언
    private int totalExpense = 0; // 클래스 인스턴스 변수로 선언

    public SettlementManager(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    public void displaySettlement() {
        List<Entry> allEntries = entryManager.getEntries();
        if (allEntries.isEmpty()) {
            System.out.println("자산내역이 없습니다."); // 내역이 없을 때
            return;
        }

        // 각 카테고리별 지출 합계를 저장할 맵 생성
        Map<String, Integer> expenseByCategory = new HashMap<>();
        for (Entry entry : allEntries) {
            if ("수입".equals(entry.getType())) {
                totalIncome += entry.getAmount(); // 수입일 경우 합산
            } else if ("지출".equals(entry.getType())) {
                totalExpense += entry.getAmount(); // 지출일 경우 합산
                // 지출 카테고리별로 금액을 누적
                expenseByCategory.put(entry.getCategory(), expenseByCategory.getOrDefault(entry.getCategory(), 0) + entry.getAmount());
            }
        }

        int currentBalance = totalIncome - totalExpense; // 현재 보유 금액 계산

        // NumberFormat을 사용하여 천 단위 구분 기호 추가
        NumberFormat numberFormat = NumberFormat.getInstance();
        
        System.out.println("총 수입 : " + numberFormat.format(totalIncome) + "원");
        System.out.println("총 지출 : " + numberFormat.format(totalExpense) + "원");
        System.out.println("==================================");
        System.out.println("현재 총 자산 : " + numberFormat.format(currentBalance) + "원");

        // 지출 내역이 있을 경우에만 그래프 출력
        if (!expenseByCategory.isEmpty()) {
            System.out.println("\n[카테고리별 지출 그래프]");

            int graphWidth = 30; // 그래프의 최대 너비를 지정하여 비율을 쉽게 조정할 수 있도록 함

            // 각 카테고리의 지출 비율을 계산하고 그래프 표시
            for (Map.Entry<String, Integer> entry : expenseByCategory.entrySet()) {
                String category = entry.getKey();
                int amount = entry.getValue();
                double percentage = (amount / (double) totalExpense) * 100; // 백분율 계산
                int graphLength = (int) (graphWidth * (percentage / 100)); // 그래프 길이 계산

                // 그래프와 퍼센트 사이에 공백을 추가해 일정한 위치에 퍼센트가 표시되도록 함
                String graph = "#".repeat(graphLength);
                String padding = " ".repeat(graphWidth - graphLength + 5); // 그래프 뒤에 고정된 공백 추가

                System.out.printf("%-6s: %s%s%3.1f%%\n", category, graph, padding, percentage);
            }
        } else {
            System.out.println("지출 내역이 없습니다.");
        }
    }
}
