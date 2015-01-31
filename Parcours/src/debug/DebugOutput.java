package debug;
import lejos.nxt.LCD;

/**
 * Writes a certain number of lines to the debug output.
 * 
 * @author Nico
 *
 */
public class DebugOutput {

	private static final short MAX_LINES = 8;
	private static final short TOTAL_DIGITS = 16;
	private static final short OUTPUT_NUMERIC_DIGITS = 8;
	private static final short MAX_DESCRIPTION_LENGTH =
			TOTAL_DIGITS - OUTPUT_NUMERIC_DIGITS - 1;

	private String[]  descriptions;
	private int[]     intValues;
	private float[]   floatValues;
	private boolean[] isIntValue;
	
	private short maxLength = 0;

	public DebugOutput() {
		descriptions = new String[MAX_LINES];
		intValues    = new int[MAX_LINES];
		floatValues  = new float[MAX_LINES];
		isIntValue   = new boolean[MAX_LINES];
		
		for ( short i = 0; i < MAX_LINES; ++i ) {
			descriptions[i] = new String("");
			intValues[i]    = 0;
			floatValues[i]  = 0.0f;
			isIntValue[i]   = true;
		}
	};

	/**
	 * Allows to specify the description to be shown before the output value of
	 * the line with lineNumber.
	 * 
	 * @param lineNumber
	 * @param description
	 */
	public void setDescription(int lineNumber, String description) {
		if ( lineNumber < 0 ||
			 lineNumber > MAX_LINES )
			return;
		
		if ( description.length() > MAX_DESCRIPTION_LENGTH ) {
			description = "Overfl.";
		}

		boolean refreshMaxLength = ( descriptions[lineNumber].length()
				                     == maxLength );

		descriptions[lineNumber] = description;

		final short oldLength = maxLength;

		if (refreshMaxLength) {
			maxLength = 0;
			for (short i = 0; i < MAX_LINES; ++i) {
				maxLength = (short) Math.max( maxLength, descriptions[i].length() );
			}
			
		} else {
			maxLength = (short) Math.max(maxLength, description.length());
		}

		if (oldLength != maxLength) {
			redraw();
		} else {
			LCD.drawString(description, 0, lineNumber);
			LCD.drawChar(':', maxLength, lineNumber);
		}
	}

	/**
	 * Writes a float value to the output line with the specified lineNumber.
	 * @param lineNumber
	 * @param value
	 */
	public void write(int lineNumber, float value) {
		if (lineNumber < 0 || lineNumber >= MAX_LINES)
			return;

		floatValues[lineNumber] = value;
		isIntValue[lineNumber]  = false;
		drawFloat( value, TOTAL_DIGITS - OUTPUT_NUMERIC_DIGITS, lineNumber );
	}
	
	/**
	 * Writes an integer value to the output line with the specified line number.
	 * @param lineNumber
	 * @param value
	 */
	public void write(int lineNumber, int value) {
		if (lineNumber < 0 || lineNumber >= MAX_LINES)
			return;

		intValues[lineNumber] = value;
		isIntValue[lineNumber]  = true;
		drawInt( value, TOTAL_DIGITS - OUTPUT_NUMERIC_DIGITS, lineNumber );
	}

	/**
	 * Draws the float f at position (x; y).
	 * @param f
	 * @param x
	 * @param y
	 */
	private void drawFloat(float f, int x, int y) {
		
		float f_abs = Math.abs(f);
		
		if ( f_abs > 9999.99 ) {
			LCD.drawString( "Owerfl.", x + 1, y);
		}
		else if ( f_abs < 10 ) {
			LCD.drawInt( (int)(100000 * f_abs), OUTPUT_NUMERIC_DIGITS, x, y );
			LCD.drawInt( (int) f_abs, 1 , x + 1, y );
			LCD.drawChar( '.', x + 2, y);
		} else {
			LCD.drawInt( (int)(100 * f_abs), OUTPUT_NUMERIC_DIGITS, x, y );
			LCD.drawChar( '.', x + OUTPUT_NUMERIC_DIGITS - 3, y );
			LCD.drawInt( (int)( f_abs ), OUTPUT_NUMERIC_DIGITS - 3, x, y);
		}
		
		LCD.drawChar( Math.signum(f) == -1 ? '-' : '+', x, y);
	}
	
	/**
	 * Draws the int i at position (x;y)
	 * @param i
	 * @param x
	 * @param y
	 */
	private void drawInt(int i, int x, int y) {
		LCD.drawInt( Math.abs(i), OUTPUT_NUMERIC_DIGITS, x, y );
		LCD.drawChar( Math.signum(i) == -1 ? '-' : '+', x, y);
	}
	
	
	/**
	 * Redraws all output lines.
	 */
	private void redraw() {
		LCD.clear();
		for (short i = 0; i < MAX_LINES; ++i) {
			if ( !descriptions[i].isEmpty() ) {
				LCD.drawString(descriptions[i], 0, i);
				LCD.drawChar(':', maxLength, i);
				
				if ( isIntValue[i] ) {
					drawInt( intValues[i], TOTAL_DIGITS - OUTPUT_NUMERIC_DIGITS, i);
				} else {
					drawFloat( floatValues[i], TOTAL_DIGITS - OUTPUT_NUMERIC_DIGITS, i );
				}
			}

		}
	}
}
