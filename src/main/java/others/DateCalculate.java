package others;

public class DateCalculate {
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public static int getMonthDays(int year, int month) {
        if (month == 4 || month == 6 || month == 8 || month == 11) return 30;
        if (month == 2) return isLeapYear(year) ? 29 : 28;
        return 31;
    }

}
