package arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class IntegerArrays {         
	
	public static String toString(int[] array){    //Metoden returnerar en heltals-array som en sträng.
		String str="{";
		for(int i=0;i<array.length;i++){
			str+=array[i];
			if(i<(array.length-1)){
			str+=",";
			}
		}
		str+="}";
		return str;
	}
	public static int max(int[] array){         //Metoden returnerar största värdet i en heltalsarray.
		int max=0;
		for(int i=0;i<array.length;i++){
			if(array[i]>max){
				max=array[i];
			}
		}
		return max;
	}
	public static int min(int[] array){         //Metoden returnerar minsta värdet i en heltalsarray.
		int min= Integer.MAX_VALUE;
		for(int i=0;i<array.length;i++){
			if(array[i]<min){
				min=array[i];
			}
		}
		return min;
	}
	public static int sum(int[] array){      //Metoden returnerar summan av talen i heltalsarray
		int sum=0;
		for(int i=0;i<array.length;i++){
			sum+=array[i];
			}
		return sum;
	}
	public static double average(int[] array){      //Metoden returnerar medelvärdet av talen i en heltalsarray.
		double average;
		double sum=0;
		int nbr=0;
		for(int i=0;i<array.length;i++){
		    	sum+=array[i];	
		    	nbr++;
		}
		average=sum/nbr;
		return average;
	}
	public static int range(int[] array){    //Metoden returnerar skillnaden mellan det största värdet och det minsta värdet i arrayen.
		int max=0;
		int min=(int) Double.MAX_VALUE;
		int range;
		for(int i=0;i<array.length;i++){
			if(array[i]>max){
				max=array[i];
			}
		    if(array[i]<min){
				min=array[i];
			}
	}
		range=max-min;
		return range;
	}
	public static void sortDesc(int[] array){       //  Metoden sorterar talen i en heltalsarray växande.
		Arrays.sort(array);
		int indexCopy=0;
		for(int i=array.length-1;i>=array.length/2;i--){   //Loopen ska köras hälften av antal element
			int lastIndex=array[indexCopy];                //Sparas värdet i arreyen för varje positionen
			array[indexCopy]=array[i];                     
			array[i]=lastIndex;                           
			indexCopy++;
		}
		
	
	}
	
	public static void sortAsc(int[] array){      // Metoden sorterar talen i en heltalsarray växande.
		Arrays.sort(array);
	}
	public static int[] copy(int[] array){        //Metoden returnerar en kopia av en heltalsarray.
		
		int kapacitet=0;
		for(int i=0;i<array.length;i++){
			kapacitet++;
		}
		int[] copyArray =new int[kapacitet];
		for (int i=0;i<array.length;i++){
			copyArray[i]=array[i];
		}
		return copyArray;
	}
}
