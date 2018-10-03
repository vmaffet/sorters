
public class Bubble implements Runnable {
	
	int[] data;
	boolean swap;
	HistoDisp graph;
	long tic, toc;
	boolean perf;
	
	public Bubble (int[] input, HistoDisp h, boolean performance) {
		data= input;
		graph= h;
		perf= performance;
	}
	
	public void run() {
		tic= System.nanoTime();
		int temp;
		do {
			swap= false;
			for (int i= 0; i<data.length-1; i++) {
				if (data[i] > data[i+1]) {
					temp= data[i];
					data[i]= data[i+1];
					data[i+1]= temp;
					swap= true;
				}
				if (!perf) { display();}
			}
		} while (swap);
		toc= System.nanoTime();
		System.out.println("Bubble : "+(toc-tic)+"  Success : "+Sorting.isSorted(data));
	}

	public void display () {
		graph.setData(data);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {}
	}
}
