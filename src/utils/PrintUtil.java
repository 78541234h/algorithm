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

    public static <T> void printArray(T[] arr) {
        for(T item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
