package collections;

/**
 * Class kontains two search methods. binearSearch and linearSearch.
 * 
 * @author Yurdaer Dalkic
 *
 */
public class Searching {
	/**
	 * The method searches for elements of the list list . If the element is
	 * found, the element's position is returned , otherwise a negative value is
	 * returned. Binary search is used.
	 * 
	 * @param list
	 *            Binary search is uses in this list.
	 * @param element
	 * 
	 * @return position of the element in the list.
	 */
	public static <E> int binearSearch(ArrayList<E> list, E element) {
		int res = -1, compare, min = 0, max = list.size() - 1, pos;
		Comparable<E> comp = (Comparable<E>) element;
		while ((min <= max) && (res == -1)) {
			pos = (min + max) / 2;
			compare = comp.compareTo(list.get(pos));
			if (compare == 0)
				res = pos;
			else if (compare < 0)
				max = pos - 1;
			else
				min = pos + 1;
		}
		return res;
	}

	/**
	 * The method searches for elements of the list list . If the element is
	 * found, the element's position is returned , otherwise a negative value is
	 * returned. Linear search is used.
	 * 
	 * @param list
	 *            Binary search is uses in this list.
	 * @param element
	 * 
	 * @return position of the element in the list.
	 */
	public static int linearSearch(List list, Object element) {
		int value = -1;
		for (int i = 0; i < list.size(); i++) {
			if (element.equals(list.get(i))) {
				value = i;
			}
		}
		return value;
	}
}
