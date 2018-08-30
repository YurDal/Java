package collections;

/**
 * Class contains sorts methots.
 * 
 * @author Yurdaer Dalkic
 *
 */
public class Sorting {

	private static final double[] Integer = null;

	/**
	 * List will be sorted using quicksort
	 * 
	 * @param array
	 *            The list which will sorts
	 */
	public static void sort(double[] array) {
		sort(array, 0, array.length - 1);
	}

	private static void sort(double[] array, int left, int right) {
		int pivotIndex;
		if (left < right) {
			pivotIndex = partition(array, left, right, (left + right) / 2);
			sort(array, left, pivotIndex - 1);
			sort(array, pivotIndex + 1, right);
		}
	}

	private static int partition(double[] array, int left, int right, int pivotIndex) {
		double pivotValue = array[pivotIndex];
		int storeIndex = left;
		swap(array, pivotIndex, right);
		for (int i = left; i < right; i++) {
			if (array[i] < pivotValue) {
				swap(array, i, storeIndex);
				storeIndex++;
			}
		}
		swap(array, storeIndex, right);
		return storeIndex;
	}

	private static void swap(double[] array, int pos1, int pos2) {
		double temp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = temp;
	}

	/**
	 * List will be sorted using mergesort
	 * @param list
	 */
	public static void sort(ArrayList list) {
		ArrayList temp = new ArrayList(list.size());
		mergesortDesc(list, 0, list.size(), temp);
	}

	private static void mergesortDesc(ArrayList list, int first, int n, ArrayList temp) {
		int n1, n2;
		if (n > 1) {
			n1 = n / 2;
			n2 = n - n1;
			mergesortDesc(list, first, n1, temp);
			mergesortDesc(list, first + n1, n2, temp);
			merge(list, first, n1, n2, temp);
		}
	}

	private static void merge(ArrayList list, int first, int n1, int n2, ArrayList temp) {
		int counter = 0, cursor1 = 0, cursor2 = n1, last = n1 + n2;
		Comparable comp1, comp2;
		while ((cursor1 < n1) && (cursor2 < last)) {
			comp1 = (Comparable) (list.get(first + cursor1));
			comp2 = (Comparable) (list.get(first + cursor2)); // börja igen här
			if (comp1.compareTo(comp2) > 0) {
				temp.set(counter, list.get(first + cursor1));
				cursor1++;
			} else {
				temp.set(counter, list.get(first + cursor2));
				cursor2++;
			}
			counter++;
		}
		while (cursor1 < n1) {
			temp.set(counter, list.get(first + cursor1));
			cursor1++;
			counter++;
		}
		while (cursor2 < last) {
			temp.set(counter, list.get(first + cursor2));
			cursor2++;
			counter++;
		}
		for (int i = 0; i < n1 + n2; i++) {
			list.set(first + i, temp.get(i));
		}
	}

}
