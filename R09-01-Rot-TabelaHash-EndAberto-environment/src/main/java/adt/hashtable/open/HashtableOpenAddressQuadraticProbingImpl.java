package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         int j, i = 0;
         boolean exit = false;
         while (i < this.table.length && !exit) {
            j = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
            if (table[j] == null || table[j] instanceof DELETED) {
               table[j] = element;
               elements++;
               exit = true;
            } else {
               this.COLLISIONS++;
               i++;
            }
         }
         if (i == this.table.length - 1) {
            throw new HashtableOverflowException();
         }
      }
   }

   @Override
   public void remove(T element) {
      if (element != null) {
         int i = 0;
         int j;
         boolean noExit = true;
         while (i < this.table.length && noExit) {
            j = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
            if (this.table[j] != null && element.equals(this.table[j])) {
               this.table[j] = this.deletedElement;
               this.elements--;
               noExit = false;
            } else {
               i++;
            }

         }
      }
   }

   @Override
   public T search(T element) {
      T result = null;
      if (element != null) {
         int i = 0;
         int j = 0;
         while (i < this.table.length) {
            j = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
            if (this.table[j] != null && this.table[j].equals(element)) {
               result = (T) this.table[j];
            }
            i++;
         }

      }
      return result;
   }

   @Override
   public int indexOf(T element) {
      if (element != null) {
         int i = 0;
         int j;
         while (i < this.table.length) {
            j = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
            if (element.equals(this.table[j])) {
               return j;
            }
            i++;
         }
      }
      return -1;
   }

}
