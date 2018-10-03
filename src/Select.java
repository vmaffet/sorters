
public class Select implements Runnable {
	
	int[] data;
	HistoDisp graph;
	long tic, toc;
	boolean perf;
	
	public Select (int[] input, HistoDisp h, boolean performance) {
		data= input;
		graph= h;
		perf= performance;
	}
	
	public void run() {
		tic= System.nanoTime();
		int temp;
		int minIndex;
		for (int i= 0; i<data.length-1; i++) {
			minIndex= i;
			for (int k= i+1; k<data.length; k++) {
				if (data[minIndex] > data[k]) {
					minIndex= k;
				}
			}
			temp= data[i];
			data[i]= data[minIndex];
			data[minIndex]= temp;
			if (!perf) { display();}
		}
		toc= System.nanoTime();
		System.out.println("Select : "+(toc-tic)+"  Success : "+Sorting.isSorted(data));
	}
	
	public void display () {
		graph.setData(data);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
	}

}
