class HashIntNode {
    public HashIntNode(int aKey) {
        key = aKey;
    }
    int key;
    HashIntNode next;
}
 
public class MyHashTableWithChainingForInts {
    private HashIntNode bucketArray[];
    private int numOfBuckets;
    private int size;
 
    // constructor initializes capacity, size, and empty chains
    public MyHashTableWithChainingForInts(int aNumOfBuckets) {
        numOfBuckets = aNumOfBuckets;
        bucketArray = new HashIntNode[numOfBuckets];
        size = 0;
    }
 
    public int size() {
        return size;
    }
 
    public boolean isEmpty() {
        return size() == 0;
    }
 
    // hash function to find index for a key
    private int getHashIndex(int key) {
        // Using modulo for hash index calculation to ensure even distribution
        return key % numOfBuckets;
    }
 
    // Adds a key to the hash table
    public void add(int key) {
        // Calculate hash index using the hash function
        int index = getHashIndex(key);
        
        // Create a new node for the key
        HashIntNode newNode = new HashIntNode(key);
        
        // If the bucket is empty, add the new node as the head of the chain
        if (bucketArray[index] == null) {
            bucketArray[index] = newNode;
        } else {
            // Otherwise, insert the new node at the beginning of the chain
            newNode.next = bucketArray[index];
            bucketArray[index] = newNode;
        }
        
        size++;
    }
 
    // Returns the value for a given key (not implemented in this case)
    public int get(int key) {
        int index = getHashIndex(key);
        HashIntNode head = bucketArray[index];
        
        // Search for the key in the chain
        while (head != null) {
            if (head.key == key) {
                return head.key; // Found the key
            }
            head = head.next;
        }
        
        return -1; // Key not found
    }
 
    // Removes a given key (not implemented in this case)
    public boolean remove(int key) {
        return false;
    }
 
    // Prints all chains in the hash table
    public void printAllChains() {
        for (int i = 0; i < numOfBuckets; i++) {
            System.out.print("Bucket " + i + ": ");
            HashIntNode head = bucketArray[i];
 
            while (head != null) {
                System.out.print(head.key + " -> ");
                head = head.next;
            }
            System.out.println("null");
        }
    }
    
    // Main method for testing
    public static void main(String[] args) {
        MyHashTableWithChainingForInts hashTable = new MyHashTableWithChainingForInts(5);
        
        // Hinzufügen von Elementen
        hashTable.add(5);
        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(9);
        hashTable.add(14);
        hashTable.add(4);
        
        // Ausgeben der Buckets und Ketten
        hashTable.printAllChains();
        
        // Weitere Tests
        System.out.println("Size of hash table: " + hashTable.size());
        System.out.println("Is the hash table empty? " + hashTable.isEmpty());
    }
}

