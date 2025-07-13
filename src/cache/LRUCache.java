package cache;

import java.util.HashMap;

public class LRUCache {
    private class Node {
        String key;
        Node prev, next;

        Node(String key) {
            this.key = key;
        }
    }

    private int capacity;
    private HashMap<String, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(""); // dummy head
        tail = new Node(""); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public void put(String key) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        } else if (map.size() == capacity) {
            map.remove(tail.prev.key);
            remove(tail.prev);
        }
        Node node = new Node(key);
        addToFront(node);
        map.put(key, node);
    }

    public void display() {
        Node curr = head.next;
        System.out.println("Recent Searches:");
        while (curr != tail) {
            System.out.println("- " + curr.key);
            curr = curr.next;
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
}
