package in.wordofgod.bible.concordance;

public class StopClock {

	private final long startTime;
	
	public StopClock() {
		startTime = System.nanoTime();
    }
	
	public long elapsedTime() {
        long now = System.nanoTime();
        return ((now - startTime) / 1000 / 1000 / 1000);
    }
	
}
