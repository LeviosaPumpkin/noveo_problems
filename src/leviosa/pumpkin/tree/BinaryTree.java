package leviosa.pumpkin.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private class Node {
        private int key;
        private Node left;
        private Node right;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    public Node create(int[] array) {
        return create(array, root, 0);
    }
    private Node create(int [] array, Node root, int idx) {
        if (idx < array.length) {
            Node tmp = new Node(array[idx]);
            root = tmp;
            root.left = create(array, root.left, 2 * idx + 1);
            root.right = create(array, root.right, 2 * idx + 2);
        }
        return  root;
    }

    public void traverseLevelOrder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node tmp = queue.remove();
            System.out.println(tmp.key);
            if (tmp.left != null) queue.add(tmp.left);
            if (tmp.right != null) queue.add(tmp.right);
        }
    }

    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.key + " ");
            inOrder(root.right);
        }
    }
// 7. Написать функцию, проверяющую, является ли данное бинарное дерево бинарным деревом поиска
// (ВСЕ элементы в левом/правом поддереве меньше/больше текущего узла). Надо реализовать за O(N), одним обходом дерева.
    public boolean isBST(Node root) {
        boolean leftOk = true;
        boolean rightOk = true;
        if (root.left != null) {
            leftOk = root.left.key < root.key;
            if (leftOk) leftOk = isBST(root.left);
        }
        if (root.right != null) {
            rightOk = root.right.key > root.key;
            if (rightOk) rightOk = isBST(root.right);
        }

        return leftOk && rightOk;
    }

    //Есть отсортированный массив. Нужно по нему построить сбалансированное бинарное дерево поиска. Должно работать за O(N).


    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        int arr[] = { 1, 2, 3, 4, 5, 6, 6, 6, 6 };
        int arr2[] = {10, 7, 12, 5, 8, 11, 14};
        tree.root = tree.create(arr2);
        System.out.println("Is BST? " + tree.isBST(tree.root));
    }
}
