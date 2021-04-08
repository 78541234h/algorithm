package utils;

import BasicDataStucture.BinaryTreeNode;
import chapter3.PrintBianryTree;

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

    public static void print(Object object) {
        if(object instanceof BinaryTreeNode) {
            PrintBianryTree.printTree((BinaryTreeNode)object);
        } else if(object instanceof Object[]) {
            PrintUtil.printArray((Object[])object);
        } else if(object instanceof String) {
            PrintUtil.printString((String) object);
        } else {
            PrintUtil.printElement(object);
        }
    }
}
