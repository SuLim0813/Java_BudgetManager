package moneyMini;

import java.util.Scanner;

public class BudgetManagerEx {
    private static final EntryManager entryManager = new EntryManager();
    private static final FileManager fileManager = new FileManager();
    private static final CategoryManager categoryManager = new CategoryManager();
    private static final SettlementManager settlementManager = new SettlementManager(entryManager);
    private static final BudgetManagerOperations operations = new BudgetManagerOperations(entryManager, fileManager, categoryManager);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        fileManager.loadFromFile(entryManager);

        while (true) {
            displayMenu();
            int choice = operations.getUserChoice(scanner);
            if (choice == -1) continue;

            if (!executeMenuChoice(choice, scanner)) {
                operations.exitProgram(scanner);
                break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n메뉴 [ 1 : 가계부 작성  |  2 : 내역 조회  |  3 : 자산 현황  |  4 : 내역 수정  |  5 : 내역 삭제  |  6 : 통합 저장  |  7 : 내역 초기화  |  0 : 종료 ]");
    }

    private static boolean executeMenuChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1 -> operations.addEntry(scanner);
            case 2 -> entryManager.viewEntries();
            case 3 -> settlementManager.displaySettlement();
            case 4 -> operations.editEntry(scanner);
            case 5 -> operations.deleteEntry(scanner);
            case 6 -> fileManager.saveToFile(entryManager.getEntries());
            case 7 -> operations.clearData(scanner);
            case 0 -> { return false; }
            default -> System.out.println("올바른 번호가 아닙니다. 번호를 다시 입력해주세요.");
        }
        return true;
    }
}