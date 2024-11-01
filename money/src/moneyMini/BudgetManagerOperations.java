package moneyMini;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BudgetManagerOperations {
    private final EntryManager entryManager;
    private final FileManager fileManager;
    private final CategoryManager categoryManager;

    public BudgetManagerOperations(EntryManager entryManager, FileManager fileManager, CategoryManager categoryManager) {
        this.entryManager = entryManager;
        this.fileManager = fileManager;
        this.categoryManager = categoryManager;
    }

    public void addEntry(Scanner scanner) {
        String date = getValidDate(scanner);
        System.out.println("수입 or 지출 : ");
        String type = scanner.nextLine();

        String category = categoryManager.getCategoryInput(scanner, type);
        System.out.println("금액 : ");

        int amount = 0;
        while (true) {
            try {
                amount = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("유효하지 않은 입력입니다. 숫자를 입력해 주세요.");
                scanner.nextLine(); // 잘못된 입력을 지우기
            }
        }

        System.out.println("메모 : ");
        String note = scanner.nextLine();

        entryManager.addEntry(new Entry(date, type, category, amount, note));
        System.out.println("내역이 추가되었습니다.");
    }

    public void editEntry(Scanner scanner) {
        entryManager.viewEntries();
        int entryNum = getValidEntryNumber(scanner);
        if (entryNum == -1) return;

        String date = getValidDate(scanner);
        System.out.println("수입 or 지출: ");
        String type = scanner.nextLine();
        String category = categoryManager.getCategoryInput(scanner, type);
        
        System.out.println("금액 수정: ");
        int amount = 0;
        while (true) {
            try {
                amount = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("유효하지 않은 입력입니다. 숫자를 입력해 주세요.");
                scanner.nextLine(); // 잘못된 입력을 지우기
            }
        }

        System.out.println("메모 수정: ");
        String note = scanner.nextLine();

        entryManager.editEntry(entryNum - 1, new Entry(date, type, category, amount, note));
    }

    public void deleteEntry(Scanner scanner) {
        entryManager.viewEntries();
        entryManager.deleteEntry(scanner);
    }

    public void clearData(Scanner scanner) {
        System.out.print("데이터를 초기화 하시겠습니까? (네/아니오): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("네")) {
            fileManager.clearData(entryManager);
            System.out.println("데이터가 초기화됩니다.");
        } else if (response.equalsIgnoreCase("아니오")) {
            System.out.println("초기화가 취소되었습니다.");
        } else {
            System.out.println("올바른 입력이 아닙니다. 다시 시도해주세요.");
        }
    }

    public int getUserChoice(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            System.out.println("올바른 번호를 입력해주세요.");
            scanner.next();
            return -1;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void exitProgram(Scanner scanner) {
        fileManager.saveToFile(entryManager.getEntries());
        System.out.println("이용해주셔서 감사합니다. 프로그램을 종료합니다.");
        scanner.close();
    }

    private String getValidDate(Scanner scanner) {
        String date;
        while (true) {
            System.out.println("날짜(yyyy-MM-dd) : ");
            date = scanner.nextLine();

            if (DateValidator.isValidDate(date)) {
                break;
            } else {
                System.out.println("잘못된 날짜 형식입니다. 다시 입력해주세요.");
            }
        }
        return date;
    }

    private int getValidEntryNumber(Scanner scanner) {
        int entryNum = -1;
        while (true) {
            System.out.print("수정할 번호를 입력해주세요 : ");
            try {
                entryNum = scanner.nextInt();
                scanner.nextLine();
                if (entryNum >= 1 && entryNum <= entryManager.getEntries().size()) {
                    break;
                } else {
                    System.out.println("해당 번호는 조회할 수 없습니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("유효하지 않은 입력입니다. 숫자를 입력해 주세요.");
                scanner.nextLine(); // 잘못된 입력을 지우기
            }
        }
        return entryNum;
    }
}
