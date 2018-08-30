package project;



/**
 * Class used for HashtableOH
 *
 * 
 * 
 * @author Grupp3
 * 
 */
class Entry<K,V> {
    K key;
    V value;
    
    
    
    
    
    /**
     * Constructor creating an entry with a key and a value as parameters
     * 
     * @param K key, V value
     * 
     */
    public Entry( K key, V value ) {
        this.key = key;
        this.value = value;
    }
    
    
    
   
    

    /**
     * Compares 2 keys and reeturns truee if they ar equal
     * @return boolean
     * 
     */
    public boolean equals( Object obj ) {
        if( !(obj instanceof Entry) )
            return false;
        Entry<K,V> entry = (Entry<K,V> )obj;
        return key.equals( entry.key );
    }
}
