//Melissa Hawley 260730658

package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {    	
    	
    	//initializing all the fields
    	numEntries = 0; 
    	numBuckets =(initialCapacity);
    	buckets = new ArrayList<>();
    	for(int i=0; i<numBuckets; i++) {
			buckets.add(i, new LinkedList<>()); 
		}
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
 
    	//the position of the key in the bucket
    	int positionk = hashFunction(key);
    	LinkedList<HashPair<K,V>> bucket = buckets.get(positionk);
    	for(HashPair<K,V> hp : bucket) {
    		if(hp.getKey().equals(key)) {
    			V oldV = hp.getValue();
    			hp.setValue(value);
    			return oldV;
    		}
    	}
    	this.numEntries++;
    	bucket.add(new HashPair<>(key, value));
        return null;
       
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
 
    	//the position of the key in the bucket
    	int positionk = hashFunction(key);
    	
    	for (HashPair<K,V> h : buckets.get(positionk)) {
    		if (h.getKey().equals(key)) {
    			return h.getValue();
    		}
    	}
        return null;
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
    	int positionk = hashFunction(key);
    	
    	LinkedList<HashPair<K, V>> bucket = buckets.get(positionk);
    	int i = 0;
		for (HashPair<K,V> h : bucket) {
    		if (h.getKey().equals(key)) {
    			V val = h.getValue();
    			bucket.remove(i);
    			this.numEntries--;
    			return val;
    		}
    		i++;
    	}
        return null;
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
    	MyHashIterator it = new MyHashIterator();
    	this.numBuckets *=2;
    	this.numEntries = 0;
    	this.buckets = new ArrayList<LinkedList<HashPair<K, V>>>(numBuckets);
    	for(int i=0; i<numBuckets; i++) {
			buckets.add(i, new LinkedList<HashPair<K, V>>());
		}
    	while(it.hasNext()) {
    		HashPair<K,V> hp = it.next();
    		this.put(hp.getKey(), hp.getValue());
    	}
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
    	ArrayList<K> r = new ArrayList<>();
    	for(LinkedList<HashPair<K, V>> l : buckets) {
    		for (HashPair<K, V> hp : l) {
    			r.add(hp.getKey());
    		}
    	}
        return r;//remove
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
    	ArrayList<V> r = new ArrayList<>();
    	for(LinkedList<HashPair<K, V>> l : buckets) {
    		for (HashPair<K, V> hp : l) {
    			r.add(hp.getValue());
    		}
    	}
        return r; 
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
          
        
        private MyHashIterator() {
        	entries = new LinkedList<>();
        	for(LinkedList<HashPair<K, V>> l : buckets) {
        		for (HashPair<K, V> hp : l) {
        			entries.add(hp);
        		}
        	}
            
        }
        
        @Override
        public boolean hasNext() {
            return entries.size() > 0;// remove
        }
        
        @Override
        public HashPair<K,V> next() {
            return entries.remove();
        }
        
    }
}
