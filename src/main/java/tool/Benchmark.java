package tool;

public class Benchmark {

	private double startTime;

	public void start() {

		this.startTime = ((double)System.nanoTime() / 1_000_000_000);
	}

	public double finish() {

		return ((double)System.nanoTime() / 1_000_000_000) - this.startTime;
	}
}
