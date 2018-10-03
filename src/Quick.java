
public class Quick implements Runnable {
	
	int[] data;
	HistoDisp graph;
	long tic, toc;
	boolean perf;
	Data[] base;
	
	public Quick (int[] input, HistoDisp h, boolean performance) {
		data= input;
		graph= h;
		perf= performance;
	}
	
	public Quick () {
	}
	
	public void run() {
		tic= System.nanoTime();
		splitCompare(0,data.length);
		toc= System.nanoTime();
		System.out.println("Quick  : "+(toc-tic)+"  Success : "+Sorting.isSorted(data));
	}
	
	public void splitCompare (int start, int end) {
		if (end-start < 2) {
			return;
		}
		int limit= start+1;
		int temp;
		for (int i= start+1; i<end; i++) {
			if (data[i] < data[start]) {
				temp= data[i];
				data[i]= data[limit];
				data[limit]= temp;
				limit++;
			}
			if (!perf) { display();}
		}
		temp= data[start];
		data[start]= data[limit-1];
		data[limit-1]= temp;
		if (!perf) { display();}
		splitCompare(start, limit-1);
		splitCompare(limit, end);
	}
	
	public <T> T[] quickSortThat (T[] labels, int[] values) {
		base= new Data[labels.length];
		for (int i= 0; i<labels.length; i++) {
			base[i]= new Data(i, values[i]);
		}
		splitCompareGeneric(0,base.length);
		T[] result= labels.clone();
		for (int j= 0; j<labels.length; j++) {
			result[j]= labels[base[j].index];
		}
		return result;
	}
	
	
	public void splitCompareGeneric (int start, int end) {
		if (end-start < 2) {
			return;
		}
		int limit= start+1;
		Data temp;
		for (int i= start+1; i<end; i++) {
			if (base[i].value < base[start].value) {
				temp= base[i].clone();
				base[i]= base[limit].clone();
				base[limit]= temp.clone();
				limit++;
			}
		}
		temp= base[start].clone();
		base[start]= base[limit-1].clone();
		base[limit-1]= temp.clone();
		splitCompareGeneric(start, limit-1);
		splitCompareGeneric(limit, end);
	}
	
	public void display () {
		graph.setData(data);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {}
	}

}
