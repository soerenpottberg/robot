package parcours.debug;

/**
 * The TimingDebug class allows it easily to debug timing issues in coordination with the DebugOutput class.
 * @author Nico Weyand
 *
 */
public class TimingDebug {
	
	private static final int HIGHER_TIMING_START_VALUE = 0;
	private static final int LOWER_TIMING_START_VALUE  = 240000;
	private long tLastCall = System.currentTimeMillis();
	private int sampleIterator = 0;
	
	private int lowerTimingValue  = LOWER_TIMING_START_VALUE;
	private int higherTimingValue = HIGHER_TIMING_START_VALUE;
	
	private final int lowerTimingLine;
	private final int higherTimingLine;
	private final int sampleTargetCount;
	private final DebugOutput out;
	
	
	/**
	 * Creates a new TimingDebug object.
	 * @param out : The DebugOutput object used for writing the measure to.
	 * @param sampleCount : The number of iterations between two output calls to DebugOutput.
	 * @param lowerTimingLine : The line where the lowest cycle time in <code>sampleCount</code> iterations is written to.
	 * @param higherTimingLine : Same as above with highest cycle time.
	 */
	public TimingDebug(DebugOutput out, int sampleCount, int lowerTimingLine, int higherTimingLine) {
		this.out = out;
		this.sampleTargetCount = sampleCount;
		this.lowerTimingLine = lowerTimingLine;
		this.higherTimingLine = higherTimingLine;
		
		out.setDescription(lowerTimingLine,  "cycl_tl");
		out.setDescription(higherTimingLine, "cycl_th");
	}
	
	/**
	 * Call this each time you would like to trigger a cycle time measure.
	 * @return The diffenence in ms since the last call of this method.
	 */
	public int triggerCycle() {
		final long tNow = System.currentTimeMillis();
		final int tDifference = (int)( tNow - tLastCall );
		tLastCall = tNow;
		
		++sampleIterator;
		sampleIterator %= sampleTargetCount;
		
		lowerTimingValue  = Math.min( tDifference, lowerTimingValue );
		higherTimingValue = Math.max( tDifference, higherTimingValue );
		
		if ( sampleIterator == 0 ) {
			out.write(lowerTimingLine,  lowerTimingValue);
			out.write(higherTimingLine, higherTimingValue);
			
			lowerTimingValue  = LOWER_TIMING_START_VALUE;
			higherTimingValue = HIGHER_TIMING_START_VALUE;
		}
		
		return tDifference;
	}
}
