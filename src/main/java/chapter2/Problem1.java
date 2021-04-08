package chapter2;

// print the same part of two sorted linked list
public class Problem1 {
    public class Node {
        int val;
        public Node next;
    }

    public void printCommonPart(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.val > head2.val) {
                head2 = head2.next;
            } else if (head2.val > head1.val) {
                head1 = head1.next;
            } else {
                System.out.print(head1.val + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}
