/**
 * {@author Caio Albuquerque}
 *
 * the Main class that gives a demo of the BinarySearchTree class
 */
public class Main {
    public static void main(String[] args) {
        /*
        Creates a BinarySearchTree known as bst
        Creates an array called tree that holds the values of 2, 1, 3, 5, 4
         */
        BinarySearchTree bst = new BinarySearchTree();
        int[] tree = {2, 1, 3, 5, 4};
        /*
        Creates a tree with the values of 2, 1, 3, 5, 4
         */
        bst.createTree(tree);
        /*
        Prints out the inorder traversal of bst --> 1, 2, 3, 4, 5
         */
        bst.inorderRec();
        System.out.println();
        /*
        Prints out the preorder traversal of bst --> 2, 1, 3, 5, 4
         */
        bst.preorderRec();
        System.out.println();
        /*
        Prints out the postorder traversal of bst --> 1, 4, 5, 3, 2
         */
        bst.postorderRec();
        System.out.println();
        /*
        Prints out the kth smallest element in BST
         */
        System.out.println(bst.kthSmallest(2));
        /*
        Inserts 9 into bst and prints out the inorder traversal of bst --> 1, 2, 3, 4, 5, 9
         */
        bst.insert(9);
        bst.inorderRec();
        System.out.println();
        /*
        Checks the search method to see if it prints true for a value found in the tree (3)
        Checks the search method to see if it prints false for a value not found in the tree (7)
         */
        System.out.println(bst.search(3));
        System.out.println(bst.search(7));
        /*
        Deletes 3 from bst and uses the search method to see if 3 is in bst or not --> false
        Deletes 44 from bst (which is not in bst) therefore printing null
         */
        bst.delete(3);
        System.out.println(bst.search(3));
        System.out.println(bst.delete(44));
        /*
        Inserts 77 and 85 into bst making the tree
         */
        bst.insert(77);
        bst.insert(85);
        /*
        inorderRec() --> 1, 2, 4, 5, 9, 77, 85
        preorderRec() --> 2, 1, 5, 4, 9, 77, 85
        postorderRec() --> 1, 4, 85, 77, 9, 5, 2
         */
        bst.inorderRec();
        System.out.println();
        bst.preorderRec();
        System.out.println();
        bst.postorderRec();
        System.out.println();
        /*
        Prints out the kthSmallest element in bst
         */
        System.out.println(bst.kthSmallest(2));
        /*
        Now, calls the delete method to delete the kthSmallest element and recalls kthSmallest
         */
        bst.delete(2);
        System.out.println(bst.kthSmallest(1));
    }
}