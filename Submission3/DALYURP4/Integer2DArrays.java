package arrays;

public class Integer2DArrays {

	public static String toString(int[][] array) {     //Metoden returnerar en heltals-array som en sträng.
		String str = "{";
		for (int i = 0; i < array.length; i++) {
			str += "{";
			for (int j = 0; j < array[i].length; j++) {
				str += array[i][j];
				if (j < (array[i].length - 1)) {
					str += ",";
				}
			}
			str += "}";
			if (i < (array.length - 1)) {
				str += ",";
			}
		}
		str += "}";
		return str;
	}

	public static int elements(int[][] array) {   //Metoden returnerar antalet element i en tvådimensionell int-array.
		int element = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				element++;
			}
		}

		return element;
	}

	public static int max(int[][] array) {     //Metoden returnerar största värdet i en tvådimensionell int-array.
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] > max) {
					max = array[i][j];
				}
			}
		}
		return max;
	}

	public static int min(int[][] array) {     //Metoden returnerar minsta värdet i en tvådimensionell int-array.
		int min = (int) Double.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] < min) {
					min = array[i][j];
				}
			}
		}
		return min;
	}

	public static int sum(int[][] array) {      //Metoden returnerar summan av talen  i en tvådimensionell int-array.
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				sum += array[i][j];
			}
		}
		return sum;
	}

	public static double average(int[][] array) {     //Metoden returnerar medelvärdet av talen i en tvådimensionell int-array.
		double sum = 0;
		double average;
		int antalNbr = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				sum += array[i][j];
				antalNbr++;
			}
		}
		average = sum / antalNbr;
		return average;
	}
}
