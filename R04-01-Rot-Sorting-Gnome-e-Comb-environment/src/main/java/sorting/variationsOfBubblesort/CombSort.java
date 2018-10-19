package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (validaEntrada(array, leftIndex, rightIndex)) {
         int gap = rightIndex;
         boolean swapped = true;
         while (gap > leftIndex || swapped) {
            if (gap > leftIndex) {
               gap = (int) (gap / 1.25);
            }
            swapped = false;
            for (int i = leftIndex; i + gap <= rightIndex; i++) {
               if (array[i].compareTo(array[i + gap]) > 0) {
            	  Util.swap(array, i, i + gap);
                  swapped = true;
               }
            }
         }
      }
}

   public boolean validaEntrada(Object[] array, int leftIndex, int rightIndex) {

      return (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length && array.length != 0);

   }

}
