package com.linkedin.algo;

public class LinkedList {
    public Node head;
    public int listCount;

    public LinkedList() {
        head = new Node(0);
        listCount = 0;
    }

    public void show() {
        Node current = head;
        while (current.next != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println(current.data);
    }

    public boolean add(int d) {
        Node end = new Node(d);
        Node current = head;

        while (current.next != null) {
            current = current.next;
        }
        current.next = end;
        listCount++;
        System.out.println(d + " appended to tail !");
        return true;
    }

    public boolean add(int d, int index) {
        Node end = new Node(d);
        Node current = head;
        int jump;

        if (index > listCount || index < 1) {
            System.out.println("Add Failed: index out of bounds of size of linked list!!");
            return false;
        } else {
            jump = 0;
            while (jump < index - 1) {
                current = current.next;
                jump++;
            }
            end.next = current.next;
            current.next = end;
            listCount++;
            System.out.println("Success! " + d + " added at index " + index);
            return true;
        }
    }


    public static void main(String[] args) {
        LinkedList myLinkedList = new LinkedList();
        myLinkedList.add(1);
        myLinkedList.add(3,1);
        myLinkedList.add(4,2);
        myLinkedList.add(2);
        myLinkedList.show();
    }
}
