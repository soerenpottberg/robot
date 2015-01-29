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
			TOTAL_DIGITS - OUTPUT_NUMERIC_DIGITS - 2;

	private String[] descriptions;
	private float[] values;
	private short maxLength = 0;

	public DebugOutput() {
		descriptions = new String[MAX_LINES];
		for ( short i = 0; i < MAX_LINES; ++i ) {
			descriptions[i] = new String("");
		}
		
		values = new float[MAX_LINES];
		for ( short i = 0; i < MAX_LINES; ++i ) {
			values[i] = 0.0f;
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
			 lineNumber > MAX_LINES ||
			 description.length() > MAX_DESCRIPTION_LENGTH )
			return;

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
		}
	}

	/**
	 * Writes a value to debug output line lineNumber.
	 * 
	 * @param lineNumber
	 * @param value
	 */
	public void write(int lineNumber, float value) {
		if (lineNumber < 0 || lineNumber >= MAX_LINES)
			return;

		values[lineNumber] = value;
		drawFloat( value, (char)(TOTAL_DIGITS - OUTPUT_NUMERIC_DIGITS), lineNumber );
	}

	/**
	 * Refreshes all debug output lines.
	 */
	private void redraw() {
		for (char i = 0; i < MAX_LINES; ++i) {
			if (!descriptions[i].isEmpty()) {
				LCD.drawString(descriptions[i], 0, i);
				LCD.drawString(": ", maxLength, i);
				drawFloat( values[i], (char)(TOTAL_DIGITS - OUTPUT_NUMERIC_DIGITS), i );
			}

		}
	}
	
	private void drawFloat(float f, int x, int y) {
		
		float f_abs = Math.abs(f);
		
		if ( f_abs > 9999 ) {
			LCD.drawString( "Owerfl.", x + 1, y);
		}
		else if ( f_abs < 1 ) {
			LCD.drawInt( (int)(10000 * f_abs), OUTPUT_NUMERIC_DIGITS, x, y );
			LCD.drawString( "0.", x + 1, y );
		} else {
			LCD.drawInt( (int)(100 * f_abs), OUTPUT_NUMERIC_DIGITS, x, y );
			LCD.drawString( ".", x + OUTPUT_NUMERIC_DIGITS - 3, y );
			LCD.drawInt( (int)( f_abs ), OUTPUT_NUMERIC_DIGITS - 3, x, y);
		}
		
		LCD.drawChar( Math.signum(f) == -1 ? '-' : '+', x, y);
	}
}
