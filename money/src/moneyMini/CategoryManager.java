package moneyMini;

import java.util.List;
import java.util.Scanner;

public class CategoryManager {

    public String getCategoryInput(Scanner scanner, String type) {
        List<String> categories = getCategoriesByType(type);
        displayCategories(categories);
        return promptForValidCategory(scanner, categories);
    }

    private List<String> getCategoriesByType(String type) {
        switch (type.toLowerCase()) {
            case "수입":
                return EntryManager.getIncomeCategories();
            case "지출":
                return EntryManager.getExpenseCategories();
            default:
                throw new IllegalArgumentException("유효하지 않은 타입입니다.");
        }
    }

    private void displayCategories(List<String> categories) {
        System.out.println("카테고리 [ " + String.join(", ", categories) + " ]");
    }

    private String promptForValidCategory(Scanner scanner, List<String> categories) {
        String category;
        while (true) {
            System.out.print("카테고리를 입력하세요: ");
            category = scanner.nextLine();

            if (categories.contains(category)) {
                return category; // 유효한 카테고리 반환
            }
            System.out.println("유효한 카테고리가 아닙니다. 다시 입력해주세요.");
            displayCategories(categories); // 유효한 카테고리 다시 표시
        }
    }
}
