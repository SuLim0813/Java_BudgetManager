package moneyMini;

import java.util.Calendar;

public class DateValidator {

    public static boolean isValidDate(String date) {
        return isParseable(date) && isDateInRange(date) && isDayAndMonthValid(date);
    }

    private static boolean isParseable(String date) {
        String[] parts = date.split("-");
        if (parts.length != 3) return false;

        try {
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1; // Calendar는 0부터 11까지의 월 사용
            int day = Integer.parseInt(parts[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false); // 엄격 모드 설정
            calendar.set(year, month, day);

            // set()을 호출했을 때 유효하지 않은 날짜라면 에러 발생
            calendar.getTime();
            return true;

        } catch (Exception e) {
            return false; // 유효하지 않은 날짜 형식
        }
    }

    private static boolean isDateInRange(String date) {
        int year = extractYear(date);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year >= 2000 && year <= currentYear + 10;
    }

    private static boolean isDayAndMonthValid(String date) {
        int month = extractMonth(date);
        int day = extractDay(date);
        return month >= 1 && month <= 12 && day >= 1 && day <= 31;
    }

    private static int extractYear(String date) {
        return Integer.parseInt(date.split("-")[0]);
    }

    private static int extractMonth(String date) {
        return Integer.parseInt(date.split("-")[1]);
    }

    private static int extractDay(String date) {
        return Integer.parseInt(date.split("-")[2]);
    }
}
