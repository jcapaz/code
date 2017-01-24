// LinearTable: a simple linear probing hash table implementation
class LinearTable
{
   // declare table size and element array
   public final int SIZE = 7;
   private HashItem[] table = new HashItem[SIZE];
   
   // calculate a hash code for a given string
   public int code(String key)
   {
      return (Math.abs(key.hashCode()) % SIZE);
   }

   //add():  add a key-data pair to the table
   public boolean add(String key, String data)
   {
      // probe place variable
      int probe;
      
      // calculate hashcode from key
      int code = code(key);
      
      // if hash item is empty, add straight away
      if ((table[code] == null) || table[code].isDeleted())
      {
         table[code] = new HashItem(key, data);
         probe = -1;
      }
      // otherwise, probe through the list for a free spot
      else
      {
         // initialise probe to next spot
         if (code == (table.length - 1) )
            probe = 0;
         else
            probe = code + 1;
      }
      
   
      // keep probing while data hasn't been stored, and it
      // hasn't looped back to the original item
      while ((probe != -1) && (probe != code))
      {
         // if the probed element is null
         if ((table[probe] == null) || table[probe].isDeleted())
         {
            // add the data and mark as being stored
            table[probe] = new HashItem(key, data);
            probe = -1;
         }
         // otherwise probe to the next item
         else
         {
            if (probe == (table.length -1) )
               probe = 0;
            else
               probe++;
         }
      }
   
      // return operation success
      // if data was stored, return true, otherwise false
      if (probe != -1)
         return false;
      else
         return true;
   }
   
   // retrieve(): retrieve the data for a given key
   public String retrieve(String key)
   {
      // probe place variable
      int probe;
      
      // calculate hashcode from key
      int code = code(key);
      
   
      // if its empty to start, return false
      if (table[code] == null)
         return null;
      // if hash item is a match, return straight away
      else if (table[code].getKey().equals(key))
         return table[code].getData();
      // otherwise, probe through the list for a free spot
      else
      {
         // initialise probe to next spot
         if (code == (table.length - 1) )
            probe = 0;
         else
            probe = code + 1;
      }
      
      // keep probing while data hasn't been stored, and it
      // hasn't looped back to the original item
      while ((probe != -1) && (probe != code))
      {
         // if the probed element is COMPLETELY empty, return
         if (table[probe] == null)
            return null;
         // if the probed element is a match
         else if (table[probe].getKey().equals(key))
         {
            // return the data
            return table[probe].getData();
         }
         // otherwise probe to the next item
         else
         {
            if (probe == (table.length - 1) )
               probe = 0;
            else
               probe++;
         }
      }
   
   
      // if nothing has been returned, data is not present.
      // return null
      return null;
   }
   // delete(): delete the data corresponding to a key
   public boolean delete(String key)
   {
      // probe place variable
      int probe;
      
      // calculate hashcode from key
      int code = code(key);
   
      // if hash item is empty, fail straight away
      if (table[code] == null)
      {
         System.out.println("Straight null");
         return false;
      }
      // otherwise match for a key
      // if it's a straight match, delete straight away
      else if (table[code].getKey().equals(key))
      {
         table[code].setDeleted();
         probe = -1;
         return true;
      }
      // otherwise, probe through the list for the next spot
      else
      {
         // initialise probe to next spot
         if (code == (table.length - 1) )
            probe = 0;
         else
            probe = code + 1;
      }
      
   
      // keep probing while data hasn't been stored, and it
      // hasn't looped back to the original item
      while ((probe != -1) && (probe != code))
      {
         // if the probed element is COMPLETELY empty, return failure
         if (table[probe] == null)
            return false;
         // otherwise match for the key
         else if (table[probe].getKey().equals(key))
         {
            // flag as deleted
            table[code].setDeleted();
            probe = -1;
            return true;
         }
         // otherwise probe to the next item
         else
         {
            if (probe == (table.length -1) )
               probe = 0;
            else
               probe++;
         }
      }
   
      // if nothing has been deleted, data is not present.
      // return failure
      return false;
   }

}