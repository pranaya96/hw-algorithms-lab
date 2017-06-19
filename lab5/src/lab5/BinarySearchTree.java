package lab5;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
    public boolean find(int value, Node root) {

	// TODO: You should implement find() here.
	// Note that you will likely want to implement a helper
	// method in the Node class above
        while (root != null){
            if(root.value == value){
                return true;
            }
            else if (value < root.value){
                find(value, root.leftChild);
                return true;
            }
            else{
                find(value, root.rightChild);
                return true;
            }
        }
        return false;
		
    }

    
    
    public List putItems(Node newNode){
    	List<Integer> resultList = new ArrayList<Integer> ();
    	if(newNode== null){
    		return resultList;
    	}
    	Stack<Node> myStack = new Stack<Node>();
    	myStack.push(newNode);
    	while (!myStack.empty()){
    		newNode= myStack.pop();
    		resultList.add(newNode.value);
    		if(newNode.leftChild!=null){
    			myStack.push(newNode.leftChild);
    		}
    		if(newNode.rightChild != null){
    			myStack.push(newNode.rightChild);
    		}

    	}
    	return resultList;
   

    } 

    // Returns the number of entries in this BinarySearchTree that are
    // less than value.
    public int numLessThan(int value) {
    	Node n = this.root;
    	List<Integer> list = new ArrayList<Integer>();
    	list = putItems(n);
	// TODO: You should implement numLessThan() here.
	// Note that you will likely want to implement a helper
	// method in the Node class above
        // System.out.println(list.toString());
    	int counter = 0;
    	for(int i=0; i < list.size(); i++){
    		if (list.get(i) < value){
    			counter+=1;
    		}
    	}
       // Node current = this.root;
       // while (current != null){
       //      if(current.value == value){
       //          inOrder(current);
       //          for (int i =0; i < traversedList.size()-1; i++){
       //  			System.out.println(traversedList.get(i));
       //  		}
       //          return(traversedList.size()-1);
       //      }
       //      else if (value < current.value){
       //          current = current.leftChild;
       //      }
       //      else{
       //          current = current.rightChild;
       //      }
       //  }
       return counter;
    }

    
    // public Node getMinVal(Node node){
    // 	Node sucessor = node.rightChild;
    // 	while (sucessor != null){
    // 		sucessor = sucessor.leftChild;
    // 	}   
    // 	return sucessor;
    // 	// Node current = node;
    // 	// if (current.leftChild == null){
    // 	// 	return current;
    // 	// } 
    // 	// return getMin(current.leftChild);
    // }



    // Delete the given value in this tree if present
    // Returns true if the item was present and deleted.
    


    
   public Node findNode(int value, Node node){
   		if(node.value == value){
   			return node;
   		}
   		else if(value < node.value){
   			return findNode(value, node.leftChild);
   		}
   		else{
   			return findNode(value, node.rightChild);
   		}
   }


    // public Node deleteNode(Node node) {
    // 	if (node.leftChild == null && node.rightChild == null) {
    // 		node = null;
    // 		return node;
    // 	} else if (node.leftChild == null) {
    // 		node = node.rightChild;
    // 		return node;
    // 	} else if (node.rightChild == null) {
    // 		node = node.leftChild;
    // 		return node;
    // 	} else {
    // 		int val = getMinVal(node).value ;
    // 		deleteNode(getMinVal(node));
    // 		node.value = val ;
    // 		return node;
    // 	}
    // }


    public Node minRightChild(Node node) {
    	Node current = node ;
    	while(current != null) {
    		current = current.leftChild;
    	}
    	return current;
    }



    public Node deleteTest(Node node, int value) {
    	if (node == null) {
    		return node;
    	} else if (node.value > value) {
    		node.leftChild = deleteTest(node.leftChild, value);
    		return node;
    	} else if (node.value < value) {
    		node.rightChild = deleteTest(node.rightChild, value);
    		return node;
    	}

    	if (node.leftChild == null && node.rightChild == null) {
    		node = null ;
    		return node;
    	} else if (node.leftChild == null) {
    		Node temp = node.rightChild;
    		node = null;
    		return temp; 
    	} else if (node.rightChild == null) {
    		Node temp = node.leftChild;
    		node = null;
    		return temp; 
    	} 
    	Node temp = minRightChild(node.rightChild);
    	// System.out.println(temp);
    	
    	temp.value = node.value;
    	// System.out.println("goes here");
    	node.rightChild = deleteTest(node.rightChild, temp.value);
    	return temp;
     }

    public boolean delete(int value) {
    	this.root = deleteTest(root, value);
    	return true;

	// // TODO: Implement delete here
 //    	Node a = findNode(value,root);
    	
 //    	System.out.println(deleteNode(a));
 //    	return deleteNode(a);    	
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