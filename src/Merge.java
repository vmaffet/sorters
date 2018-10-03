
public class Merge implements Runnable {
	
	int[] data;
	HistoDisp graph;
	long tic, toc;
	boolean perf;
	
	public Merge (int[] input, HistoDisp h, boolean performance) {
		data= input;
		graph= h;
		perf= performance;
	}
	
	public void run() {
		tic= System.nanoTime();
		int[][] parts= new int[data.length][1];
		for (int i= 0; i<parts.length; i++) {
			parts[i][0]= data[i];
		}
		do {
			parts= dothething(parts);
			concatenate(parts);
			if (!perf) { display();}
		} while (parts.length != 1);
		toc= System.nanoTime();
		System.out.println("Merge  : "+(toc-tic)+"  Success : "+Sorting.isSorted(data));
	}
	
	public int[][] dothething (int[][] parts) {
		int[][] result= new int[parts.length/2][];
		for (int i= 0; i<result.length+parts.length%2; i++) {
			if (i != result.length) {
				result[i]= arrange(parts[2*i], parts[2*i+1]);
			} else {
				result[i-1]= arrange(result[i-1], parts[parts.length-1]);
			}
		}
		return result;
	}
	
	public int[] arrange (int[] a, int[] b) {
		int[] result= new int[a.length+b.length];
		int k= 0;
		int l= 0;
		for (int i= 0; i<result.length; i++) {
			if (k >= a.length) {
				result[i]= b[l];
				l++;
			} else if (l >= b.length) {
				result[i]= a[k];
				k++;
			} else {
				if (a[k] < b[l]) {
					result[i]= a[k];
					k++;
				} else {
					result[i]= b[l];
					l++;
				}
			}
		}
		return result;
	}
	
	public void concatenate (int[][] tab) {
		int k= 0;
		for (int i= 0; i<tab.length; i++) {
			for (int j= 0; j<tab[i].length; j++) {
				data[k]= tab[i][j];
				k++;
			}
		}
	}
	
	public void display () {
		graph.setData(data);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}

}
