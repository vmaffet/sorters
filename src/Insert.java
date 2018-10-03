
public class Insert implements Runnable {
	
	int[] data;
	HistoDisp graph;
	long tic, toc;
	boolean perf;
	
	public Insert (int[] input, HistoDisp h, boolean performance) {
		data= input;
		graph= h;
		perf= performance;
	}
	
	public void run() {
		tic= System.nanoTime();
		for (int i= 1; i<data.length; i++) {
			for (int j= i; j>0; j--) {
				if (data[i] >= data[j-1]) {
					data= put(j,i);
					break;
				}
				if (j == 1) {
					data= put(0,i);
				}
			}
			if (!perf) { display();}
		}
		toc= System.nanoTime();
		System.out.println("Insert : "+(toc-tic)+"  Success : "+Sorting.isSorted(data));
	}
	
	public int[] put (int here, int from) {
		int[] result= new int[data.length];
		int k= 0;
		for (int i= 0; i<data.length; i++) {	
			if(i == from+1) {
				k++;
			}
			if (i != here) {
				result[i]= data[k];
			} else if (i == here) {
				result[i]= data[from];
				k--;
			}
			k++;
		}
		return result;
	}
	
	public void display () {
		graph.setData(data);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
	}

}
