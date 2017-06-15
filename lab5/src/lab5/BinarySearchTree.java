package lab5;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree {

    private class Node {
	// The value stored in this tree node.
	private int value;

	// The left child of this node. Can be null if there is none.
	private Node leftChild;

	// The right child of this node. Can be null if there is none.
	private Node rightChild;

	// Create a node with given value and no children.
	private Node(int value) {
	    this.value = value;
	    this.leftChild = null;
	    this.rightChild = null;
	}

	private int getValue() {
	    return this.value;
	}

	private void setValue(int value) {
	    this.value = value;
	}

	private Node getLeftChild() {
	    return this.leftChild;
	}

	private Node getRightChild() {
	    return this.rightChild;
	}

	private void setLeftChild(Node child) {
	    this.leftChild = child;
	}

	private void setRightChild(Node child) {
	    this.rightChild = child;
	}

	private void insertNode(Node node) {
	    if (node.value < this.value) {
		// Node value is less than this, so insert
		// the node in the left subtree.
		if (this.leftChild == null) {
		    // No left child, so make node left child.
		    this.leftChild = node;
		} else {
		    // Recurse into left subtree.
		    this.leftChild.insertNode(node);
		}
	    } else if (node.value > this.value) {
		// Node value is greater than this, so insert
		// the node in the right subtree.
		if (this.rightChild == null) {
		    // No right child, so make node right child.
		    this.rightChild = node;
		} else {
		    // Recurse into right subtree.
		    this.rightChild.insertNode(node);
		}
	    }
	}
    }

    private Node root;

    // Constructor for an empty tree.
    public BinarySearchTree() {
	this.root = null;
    }

    // Inserts the given value in this tree.
    // Inserting a value more than once is treated as a no-op.
    public void insert(int value) {
	Node newNode = new Node(value);
	if (this.root == null) {
	    // BinarySearchTree empty, so set the new node as root node.
	    this.root = newNode;
	} else {
	    this.root.insertNode(newNode);
	}
    }

    // Checks if this BinarySearchTree contains the given value.
    // Returns true iff the value is present in this BinarySearchTree.
    public boolean find(int value) {

	// TODO: You should implement find() here.
	// Note that you will likely want to implement a helper
	// method in the Node class above
    	Node newNode = new Node(value);
    	newNode = this.root;
    	while (newNode != null){
    		if (newNode.value == value){
    			return true;
    		}
    		else{
    			if (newNode.value < value) {
    				newNode = newNode.leftChild;
    			}
    			else{
    				newNode = newNode.rightChild;
    			}
    		}
    	}
    	return false;

		
    }

    
    private List<Integer> traversedList = new ArrayList<Integer> ();

    public void inOrder(Node root){
    	if (root != null){
    		inOrder(root.leftChild);
    		traversedList.add(root.value);
    		inOrder(root.rightChild);
    	}
    } 

    // Returns the number of entries in this BinarySearchTree that are
    // less than value.
    public int numLessThan(int value) {

	// TODO: You should implement numLessThan() here.
	// Note that you will likely want to implement a helper
	// method in the Node class above.
    	Node newNode = new Node(value);
    	newNode = this.root;
    	while (newNode != null){
    		if (newNode.value == value){
    			inOrder(newNode);
    			return (traversedList.size() -1);
    		}
    		else{
    			if (newNode.value < value) {
    				newNode = newNode.leftChild;
    			}
    			else{
    				newNode = newNode.rightChild;
    			}
    		}
    	}
    	return 0;
    }

    // Delete the given value in this tree if present
    // Returns true if the item was present and deleted.
    public boolean delete(int value) {

	// TODO: Implement delete here.

	return false;
    }

    //////////////////////////
    //                      //
    // Pretty printing code //
    //                      //
    //////////////////////////
    int maxLevelForTree(Node node) {
	int max = 1;
	if (node.leftChild != null) {
	    int maxLeft = maxLevelForTree(node.leftChild);
	    if (maxLeft + 1 > max) {
		max = maxLeft + 1;
	    }
	}
	if (node.rightChild != null) {
	    int maxRight = maxLevelForTree(node.rightChild);
	    if (maxRight + 1 > max) {
		max = maxRight + 1;
	    }
	}
	return max;
    }

    public String toString() {
	if (root == null) {
	    return "\n";
	} else {
	    return stringNode(root);
	}
    }

    private String stringNode(Node node) {
	int maxLevel = maxLevelForTree(node);
	return stringNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private String stringNodeInternal(List<Node> nodes, int level, int maxLevel) {
	if (nodes.isEmpty() || isAllElementsNull(nodes))
            return "";
	String out = new String();
	int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        out += whitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                out += node.value;
                newNodes.add(node.leftChild);
                newNodes.add(node.rightChild);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                out += " ";
            }

            out += whitespaces(betweenSpaces);
        }
        out += "\n";

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                out += whitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    out += whitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).leftChild != null)
                    out += "/";
                else
                    out += " ";
                out += whitespaces(i + i - 1);

                if (nodes.get(j).rightChild != null)
                    out += "\\";
                else
                    out += whitespaces(1);
                out += whitespaces(endgeLines + endgeLines - i);
            }
            out += "\n";
        }

	return out + stringNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static String whitespaces(int count) {
	String out = new String();
        for (int i = 0; i < count; i++)
            out += " ";
	return out;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }
}