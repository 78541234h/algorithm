package utils;

public class PrintUtil {
    public static void printSepreateLine() {
        System.out.println("======================================");
    }

    public static void newLine() {
        System.out.println();
    }

    public static <T> void printElement(T element) {
        System.out.print(element.toString() + " ");
    }

    public static void printString(String str) {
        System.out.print(str);
    }
}
