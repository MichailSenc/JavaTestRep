package com.company;

public class LinkedList implements MyArray {
    private class Node {
        public int value;
        public Node next;
    }

    int size = 0;
    Node firstNode = new Node();
    Node lastNode = firstNode;

    @Override
    public int get(int i) {
        Node tempNode = firstNode;
        for (int j = 0; j < i; j++) {
            tempNode = tempNode.next;
        }
        return tempNode.value;
    }

    @Override
    public int add(int value) {
        lastNode.value = value;
        lastNode.next = new Node();
        lastNode = lastNode.next;
        size++;
        return size - 1;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void printKonsole() {
        System.out.println("LinkedList");
        for(Node cur = firstNode; cur!=lastNode; cur = cur.next)
            System.out.println(cur.value);
    }
}

