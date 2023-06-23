package hashtable;

import java.util.LinkedList;
import java.util.Scanner;

// USING CHAINING
class HashTable {
	
	//**************** Static class for key-Value pair, This cannot be public *****************
	static class Pair{
		
		// Instance variables
		private int key;
		private String value;
		
		// Default constructor for creating (~Zero) pair
		public Pair() {
			key=0;
			value="";
		}
		
		// Parametrised constructor to create initialized pair
		public Pair(int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		// Override to string to display pair details
		@Override
		public String toString() {
			return "Pair [key=" + key + ", value=" + value + "]";
		}	
		
	}
	//*****************************************************************************************
	
	// Instance Variable
	private LinkedList<Pair>[] table;
	private static final int SLOTS = 10;	//Why static and final?
	
	public HashTable(){
		this.table=new LinkedList[SLOTS];	// Creates array of type of LL
		for(int i=0; i<SLOTS; i++)			
			table[i]=new LinkedList<>();	// Initializes all Buckets with head of LL as null
	}
	
	// Hash Function to get pair from key
	public int hashFunction(int key) {
		return key%SLOTS;
	}
	
	// Method to search a value using key
	public String get(int key) {
		// Find slot for given key using hash function, slot is like array index
		int slot=hashFunction(key);
		// Access the bucket(LL) in that slot(bucket means ref to the LL associated with that slot
		LinkedList<Pair> bucket=table[slot];
		// Find key-value pair sequentially in LL
		for(Pair pair: bucket) {	// we can use this here as we are using java LL here
			if(pair.key==key)
				return pair.value;
		}
		// if key not found return null
		return null;
	}
	
	//******* HASH TABLE CANNOT HAVE DUPLICATE KEYS ***********
	
	// Method to put/update the pair
	public void put(int key, String value) {
		//****** 1st see if key exists *******
		int slot=hashFunction(key);
		LinkedList<Pair> bucket=table[slot];
		boolean found=false;
		for(Pair pair: bucket) {
			if(pair.key==key) {
				found=true;
				pair.value=value;
			}
		}
		if(found==false) {
			// create new pair in bucket
			Pair newPair=new Pair(key, value);
			// add it to the bucket
			bucket.add(newPair);
		}
	}
}
	
public class HashTableMain{
	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in)){
			HashTable ht = new HashTable();
			ht.put(1, "Nilesh");
			ht.put(4, "Nitin");
			ht.put(8, "Sandeep");
			ht.put(5, "Amit");
			ht.put(24, "Vishal");
			ht.put(34, "Yogesh");
			ht.put(25, "Aakash");
			ht.put(1, "Rohan");
			
			System.out.print("Enter roll to find: ");
			int roll = sc.nextInt();
			String name = ht.get(roll);
			System.out.println("Name found: " + name);
		}

	}// Main
}// Class
