package lab5;

import java.util.Scanner;

public class DataCenterOrders {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int numOperations = in.nextInt();

       // TODO: Define any data structures that you will need here.
	BinarySearchTree myTree = new BinarySearchTree();

	for (int i = 0; i < numOperations; ++i) {
	    String op = in.next();
	    if (op.contentEquals("Insert")) {
		int dataCenterSize = in.nextInt();

		// TODO: Insert dataCenterSize into your data structure.
		myTree.insert(dataCenterSize);
		System.out.println("Inserted " + dataCenterSize);
	    } else if (op.contentEquals("Range")) {
		int min = in.nextInt();
		int max = in.nextInt();
		int count = 0;
		
		count = myTree.numLessThan(max) - myTree.numLessThan(min);
		// TODO: Set count to the number of data center of sizes
		// greater than or equal to min, but less than max.

		System.out.println("Range " + min + " " + max +
				   " - Result: " + count);
	    } else if (op.contentEquals("Delete")) {
		int dataCenterSize = in.nextInt();

		// TODO: Delete dataCenterSize from your data structure.
		myTree.delete(dataCenterSize);
		System.out.println("Deleted " + dataCenterSize);

	    } else {
		System.err.println("Unknown operation: " + op);
		System.exit(1);
	    }
	}
    }

}
