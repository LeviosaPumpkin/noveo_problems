package leviosa.pumpkin.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeSedgvik<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int n; //количество узлов в п// оддереве

        public Node (Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        return root == null ? 0 : root.n;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key) {
        if (root == null) return null;
        int compareResult = key.compareTo(root.key);
        if (compareResult == 0) { 
            return root.value;
        } else if (compareResult < 0) {
            return get(root.left, key);
        } else {
            return get(root.right, key);
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) return new Node(key, value, 1);
        int compareResult = key.compareTo(root.key);
        if (compareResult == 0) {
            root.value = value;
        } else if (compareResult < 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }
        root.n =  size(root.right) + size(root.left) + 1;
        return root;
    }
    
    public void traverseLevelOrder() {
        if (root == null) return;

        Queue<BinarySearchTreeSedgvik.Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinarySearchTreeSedgvik.Node node = queue.remove();
            System.out.println(node.key + " " + node.value);
            if (node.left != null) {
                queue.add(node.left);
            }
    
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void traverserDepthOrderRootLeftRight(Node root) {
        if (root != null) {
            System.out.println(root.key + " " + root.value);
            traverserDepthOrderRootLeftRight(root.left);
            traverserDepthOrderRootLeftRight(root.right);
        }
    }

    public void traverserDepthOrderLeftRootRight(Node root) {
        if (root != null) {
            traverserDepthOrderLeftRootRight(root.left);
            System.out.println(root.key + " " + root.value);
            traverserDepthOrderLeftRootRight(root.right);
        }
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node root, Key key) {
        if (root == null) return null;

        int compareResult = key.compareTo(root.key);
        if (compareResult == 0) {
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            Node tmp = root;
            root = min(tmp.right);
            System.out.println(root.key);
            root.right = deleteMin(tmp.right);
            root.left = tmp.left;
        } else if (compareResult < 0) {
            root.left = delete(root.left, key);
        } else {
            root.right = delete(root.right, key);
        }
        root.n = size(root.left) + size(root.right) + 1;
        return root;
    }

    private Node min(Node root) {
        return root.left == null ? root : min(root.left);
    }

    private Node max(Node root) {
        return root.right == null ? root.right : max(root.right);
    }

    private Node deleteMin(Node root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        root.n = size(root.left) + size(root.right) + 1;
        return root;
    }

    public static void main(String [] args) {
        BinarySearchTreeSedgvik<Integer, String> tree = new BinarySearchTreeSedgvik<>();
        tree.put(10, "Kate");
        tree.put(8, "Ben");
        tree.put(6, "Tony");
        tree.put(2, "Vi");
        tree.put(9, "Pen");
        tree.put(11, "Colin");
        tree.put(65, "Simon");
        tree.put(22, "Sophie");
        tree.put(75, "Ed");
        tree.put(7, "Miles"); 
        
        tree.traverseLevelOrder();
        tree.traverserDepthOrderRootLeftRight(tree.root);
        tree.traverserDepthOrderLeftRootRight(tree.root);
    }
}