/**
 * {@author Caio Albuquerque}
 *
 * the BinarySearchTree <T> class which has methods in reference to how
 * Binary Search Trees work
 */
public class BinarySearchTree <T> {
    /*
    private field that sets the value of root
    private field that will count to find which value is the smallest
     */
    private Node<T> root = null;
    private int count = 0;
    /**
     * insert(int key) method which inserts a given key into the Binary Search Tree
     * @param key --> key to be inserted into the BST
     */
    public void insert(int key) {
        /*
        Node that will store the value of the parent
        Node that will store the value of the element to be traversed
        These two Node values are used to find where the node should be inserted and its parent
         */
        Node parent = null;
        Node traversal = root;
        /*
        while loop that will parse through the BST until the current spot is found
         */
        while (traversal != null) {
            /*
            sets the current value of the BST as the value to be checked (starts at the root)
             */
            parent = traversal;
            /*
            if the passed key is less than the current key, then it goes to the left
            if the passed key is greater than or equal to the current key, then it goes to the right
             */
            if (key < traversal.getKey()) {
                traversal = traversal.getLeft();
            }
            else {
                traversal = traversal.getRight();
            }
        }
        /*
        once the spot of insertion is found, it checks if the parent value is null (to check if the tree is empty)
         */
        if (parent == null) {
            root = new Node(key);
        }
        /*
        if the tree is not empty, and the current key is less than the parent key, then a new Node
        is created to the left of the current key
         */
        else if (key < parent.key) {
            parent.left = new Node(key);
        }
        /*
        if the tree is not empty, and the current key is greater than or equal to the parent key, then a new Node
        is created to the right of the current key
         */
        else {
            parent.right = new Node(key);
        }
    }

    /**
     * createTree(int[] values) method that creates a new BST with the given values
     * @param values --> array to be implemented as a new BST
     */
    public void createTree(int[] values) {
        /*
        for each loop that traverses through the entirety of the passed array and inserts each value
         */
        for (int value : values) {
            insert(value);
        }
    }

    /**
     * search() method that checks if a given key is in the BST or not
     * @param key --> key to be checked if in or not in the BST
     * @return boolean --> true if it is found, false if it is not found
     */
    public boolean search(int key) {
        /*
        creates a Node that will start at the root
         */
        Node<T> traversal = root;
        /*
        while loop that will parse through the BST
        if the key is found, return true
        if the key is less than the root, it goes to the left child
        if the key is greater than or equal to the root, it goes to the right child
         */
        while (traversal != null) {
            if (key == traversal.getKey()) {
                return true;
            }
            else if (key < root.getKey()) {
                traversal = traversal.getLeft();
            }
            else {
                traversal = traversal.getRight();
            }
        }
        /*
        returns false if the key is not found in the BST
        */
        return false;
    }

    /**
     * delete(int key) method that deletes a node from BST and returns null if the key is not in BST
     * @param key --> key to be deleted from BST
     * @return Node of the key is found and null if key is not found
     */
    public Node delete(int key) {
        /*
        Creates the parent node of type Node
        Creates the traversal node of type Node
         */
        Node<T> parent = null;
        Node<T> traversal = root;
        /*
        Parses through the entirety of the tree checking for the node
         */
        while (traversal != null && traversal.getKey() != key) {
            /*
            Sets the parent = to the traversal to go down each level in bst starting at the root
             */
            parent = traversal;
            /*
            If the key is less than the current node, the traversal Node goes left
             */
            if (key < traversal.getKey()) {
                traversal = traversal.getLeft();
            }
            /*
            If the key is greater than or equal to the current node, the traversal Node goes right
             */
            else {
                traversal = traversal.getRight();
            }
        }
        /*
        If it reaches the end of the tree without finding the value to be deleted, null is returned
         */
        if (traversal == null) {
            return null;
        }
        /*
        Recursively calls the private helper method deleteNode with the @param of traversal and parent
        Returns the deleted node
         */
        deleteNode(traversal, parent);
        return traversal;
    }

    /**
     * Inorder traversal of BST --> calls the private helper method inOrderTraversal
     * Prints out all values using In-Order Traversal
     */
    public void inorderRec() {
        inOrderTraversal(this.root);
    }

    /**
     * Preorder traversal of BST --> calls the private helper method preOrderTraversal
     * Prints out all values using Pre-Order Traversal
     */
    public void preorderRec() {
        preOrderTraversal(this.root);
    }

    /**
     * Postorder traversal of BST --> calls the private helper method postOrderTraversal
     * Prints out all values using Post-Order Traversal
     */
    public void postorderRec() {
        postOrderTraversal(this.root);
    }

    /**
     * kthSmallest(int k) method which finds the kth smallest element in BST
     * Calls the getKthSmallest method
     * @param k --> the value to be checked in BST
     * @return root --> The Node of the kth smallest element in BST
     */
    public Node kthSmallest(int k) {
        Node<T> foundNode = getKthSmallest(root, k);
        return root;
    }

    /**
     * creates the private static class called 'Node' which will be implemented into the Stack
     * @param <T> --> generic type 'T' which can be used for any Data Type for the node
     */
    private static class Node<T> {
        /*
        creates key of value int
        creates data of value T
        creates the Node for the left subtree
        creates the Node for the right subtree
         */
        private int key;
        private T data;
        private Node<T> left;
        private Node<T> right;

        /**
         * Node constructor which creates the instances of key, left, and right
         * @param key --> the key of type int --> tells us where to put which values
         */
        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

        /**
         * getter method for the int key
         * @return key
         */
        public int getKey() {
            return this.key;
        }

        /**
         * setter method for the int key
         * @param key --> key to be set
         */
        public void setKey(int key) {
            this.key = key;
        }

        /**
         * getter method for the int data
         * @return data
         */
        public T getData() {
            return this.data;
        }

        /**
         * setter method for T data
         * @param data --> data to be set
         */
        public void setData(T data) {
            this.data = data;
        }

        /**
         * getter method for the left Node of the key
         * @return left
         */
        public Node<T> getLeft() {
            return this.left;
        }

        /**
         * setter method for the left Node of the key
         * @param left --> sets the left Node to a new value
         */
        public void setLeft(Node<T> left) {
            this.left = left;
        }

        /**
         * getter method for the right Node of the key
         * @return right
         */
        public Node<T> getRight() {
            return this.right;
        }

        /**
         * setter method for the right Node of the key
         * @param right --> sets the right Node to a new value
         */
        public void setRight(Node<T> right) {
            this.right = right;
        }

        /**
         * Returns the value of the key
         * @return key
         */
        @Override
        public String toString() {
            return "The key is: " + key;
        }
    }

    /**
     * Private helper method implemented in inorderRec()
     * @param node --> node to be traversed (starts at root)
     */
    private void inOrderTraversal(Node<T> node) {
        /*
        Base case such that when the root is null the traversal is done
         */
        if (node == null) {
            return;
        }
        /*
        Recursively calls the left child of the root,
        then prints the value of the root,
        then recursively calls the right value of the root
         */
        inOrderTraversal(node.getLeft());
        System.out.print(node.getKey() + " ");
        inOrderTraversal(node.getRight());
    }

    /**
     * Private helper method implemented in preorderRec()
     * @param node --> node to be traversed (starts at root)
     */
    private void preOrderTraversal(Node<T> node) {
        /*
        Base case such that when the root is null the traversal is done
         */
        if (node == null) {
            return;
        }
        /*
        Prints the value of the root
        then recursively calls the left child of the root
        then recursively calls the right child of the root
         */
        System.out.print(node.getKey() + " ");
        preOrderTraversal(node.getLeft());
        preOrderTraversal(node.getRight());
    }

    /**
     * Private helper method implemented in postorderRec()
     * @param node --> node to be traversed (starts at root)
     */
    private void postOrderTraversal(Node<T> node) {
        /*
        Base case such that when the root is null the traversal is done
         */
        if (node == null) {
            return;
        }
        /*
        Recursively calls the left child of the root
        then recursively calls the right child of the root
        then prints the value of the root
         */
        postOrderTraversal(node.getLeft());
        postOrderTraversal(node.getRight());
        System.out.print(node.getKey() + " ");
    }

    /**
     * Private helper method for delete(int key)
     * Takes into account three different cases:
     * Case 1 --> 0 children
     * Case 2 --> 1 child
     * Case 3 --> 2 children
     * @param current --> current node
     * @param parent --> parent of the current node
     */
    private void deleteNode(Node<T> current, Node<T> parent) {
        /*
        Case 1 which sets the child value as null if there is just 1 kid
         */
        if (current.getLeft() == null || current.getRight() == null) {
            Node<T> child = null;
            /*
            If the left node is not null, it is stored
             */
            if (current.getLeft() != null) {
                child = current.getLeft();
            }
            /*
            If the right node is not null, it is stores
             */
            else if (current.getRight() != null) {
                child = current.getRight();
            }
            /*
            If the node on the left is needed to be deleted, the new child will be the left node
             */
            if (current.getKey() < parent.getKey()) {
                parent.setLeft(child);
            }
            /*
            If the node on the right is needed to be deleted, the new child will be the right node
             */
            else {
                parent.setRight(child);
            }
        }
        /*
        Case 3
         */
        else {
            /*
            local Nodes that will:
            replace the parentNode
            left node of the right child which will be moved
             */
            Node<T> newParent = current;
            Node<T> newValue = current.getRight();
            /*
            While loop that parses through the tree until the main node is found
             */
            while (newValue.getLeft() != null) {
                /*
                Sets the newParent node to the left of newValue node
                 */
                newParent = newValue;
                newValue = newValue.getLeft();
            }
            /*
            Sets the data of the current node the newValue data
            Sets the key of the current node to the newValue key
            recursively calls the deleteNode function in order to change Case 3 into a Case 1 or Case 2
             */
            current.setData(newValue.getData());
            current.setKey(newValue.getKey());
            deleteNode(newValue, newParent);
        }
    }

    /**
     * Private helper method used in kthSmallest(int key)
     * @param content --> node that will be checked at the time
     * @param key --> passed key which is called from kthSmallest
     * @return --> the node of the kth smallest element in BST
     */
    private Node<T> getKthSmallest(Node<T> content, int key) {
        /*
        If statement that returns null if the current node is null
         */
        if (content == null) {
            return null;
        }
        /*
        Sets the left value of the current node and returns it
         */
        Node<T> leftValue = getKthSmallest(content.getLeft(), key);
        if (leftValue != null) {
            return leftValue;
        }
        /*
        Increments the count to see if the Node is found
        If statement, that if the count is equal to the key then it is returned
         */
        count++;
        if (count == key) {
            return content;
        }
        /*
        Sets the right value fo the current node and returns it
         */
        Node<T> rightValue = getKthSmallest(content.getRight(), key);
        return rightValue;
    }
}
