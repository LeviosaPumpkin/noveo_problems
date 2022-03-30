package leviosa.pumpkin.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {
    private class Node {
        int key;
        Node left, right;
        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
        public int getKey() {
            return this.key;
        }
    }
    Node root;

    public void put(int key) {
        root = put(root, key);
    }

    private Node put(Node root, int key) {
        if (root == null) {
            return new Node(key, null, null);
        }
        if (key < root.key) {
            root.left = put(root.left, key);
        } else if (key > root.key) {
            root.right = put(root.right, key);
        }
        return root;
    }

    public Node get(int key) {
        return get(root, key);
    }

    private Node get(Node root, int key) {
        if (key < root.key) {
            return get(root.left, key);
        } else if (key > root.key) {
            return get(root.right, key);
        } else {
            return root;
        }
    }
//10. Отразить бинарное дерево
    public void traverseLeftRootRight(Node root, List<Node> list) {
         if (root != null) {
             traverseLeftRootRight(root.left, list);
             list.add(root);
             traverseLeftRootRight(root.right, list);
         }
    }
// 9. Есть бинарное дерево поиска. Реализовать поиск интервала от минимального до максимального, вернуть в виде отсортированного списка.
    public void getSortedInterval(Node root, int min, int max, List<Integer> list) {
        if (root != null) {
            getSortedInterval(root.left, min, max, list);
            if (min <= root.key && root.key <= max) list.add(root.key);
            getSortedInterval(root.right, min, max, list);
        }
    }

// 12. Есть бинарное дерево поиска с N числами и заданное число.
// Нужно найти в дереве первое число, которое строго больше этого заданного.
// При этом само заданное число может в дереве не присутствовать. Должно работать за O(Log N).
    public void getFirstNodeLarger(Node root, int num, List<Node> list) {
        if (root != null) {
            getFirstNodeLarger(root.left, num, list);
            if (root.key > num) list.add(root);
            getFirstNodeLarger(root.right, num, list);
        }
    }

    public static void main(String[] args) {
        //int [] array = {10, 7, 12, 5, 8, 11, 14};
        int [] array = {30, 10, 40, 5, 20, 35, 45, 25, 42, 50};
        BinarySearchTree tree = new BinarySearchTree();
        for (int i : array) {
            tree.put(i);
        }
        List<Integer> list = new ArrayList<>();
        tree.getSortedInterval(tree.root, 20, 45, list);
        list.stream().forEach(System.out::println);

        //List<Node> list = new LinkedList<>();
        //tree.getFirstNodeLarger(tree.root, 4, list);
        //System.out.println(list.get(0).key);
    }
}
