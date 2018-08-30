package arrays;

public class ObjectArrays {

	public static String toString(Object[] array) {   //Metoden returnerar en objekt-array som en sträng.
		String str = "{";
		for (int i = 0; i < array.length; i++) {
			str += array[i];
			if (i < (array.length - 1)) {
				str += ",";
			}
		}
		str += "}";
		;
		return str;
	}

	public static int indexOf(Object[] array, Object elem) {    //Metoden returnerar positionen för ett element (elem nedan) i en array. Om elementet inte
	                                                             //	finns i arrayen ska -1 returneras
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (elem.equals(array[i])) {
				index = i;
				return index;
			} else {
				index = -1;
			}

			}

		
		return index;
	}
	public static boolean member(Object[] array, Object elem){   //Metoden returnerar true om ett element finns i arrayen och annars false
		for (int i = 0; i < array.length; i++) {
			if (elem.equals(array[i])) {
				return true;
			} 
			}
		
		return false;
	}
	public static Object max(Object[] array) {   //Metoden returnerar största elementet i en objekt-array.
		Object res = array[0];
		Comparable comp;
		for(int i=0;i<array.length;i++){
		comp = (Comparable)array[i];
		if(comp.compareTo( res ) > 0 ) { 
			res=array[i];
		
		}
		}
		return res;
	}
	public static Object min(Object[] array){     //Metoden returnerar minsta elementet i en objekt-array
		Object res = array[0]; 
		Comparable comp;
		for(int i=0;i<array.length;i++){
		comp = (Comparable)array[i];
		if(comp.compareTo( res ) < 0 ) { 
		 res=array[i];
		}
		}
		return res;
	}
	

}
