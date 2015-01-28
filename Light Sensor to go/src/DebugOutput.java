import lejos.nxt.LCD;

/**
 * Writes a certain number of lines to the debug output.
 * 
 * @author Nico
 *
 */
public class DebugOutput {

	private final char MAX_LINES = 8;
	private final char NUMBER_DIGITS = 16;
	private final char OUTPUT_DIGITS = 8;

	private String[] descriptions;
	private float[] values;
	private char maxLength = 0;

	public DebugOutput() {
		descriptions = new String[MAX_LINES];
		values = new float[MAX_LINES];
	};

	/**
	 * Allows to specify the description to be shown before the output value of
	 * the line with lineNumber.
	 * 
	 * @param lineNumber
	 * @param description
	 */
	public void setDescription(char lineNumber, String description) {
		if (lineNumber < 0 || lineNumber >= MAX_LINES)
			return;

		boolean refreshMaxLength = descriptions[lineNumber].length() == maxLength;

		descriptions[lineNumber] = description;

		final char oldLength = maxLength;

		if (refreshMaxLength) {
			maxLength = 0;
			for (char i = 0; i < MAX_LINES; ++i) {
				maxLength = (char) Math.max(maxLength, descriptions[i].length());
			}
		} else {
			maxLength = (char) Math.max(maxLength, description.length());
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
	public void write(char lineNumber, float value) {
		if (lineNumber < 0 || lineNumber >= MAX_LINES)
			return;

		values[lineNumber] = value;
		drawFloat( value, (char)(NUMBER_DIGITS - OUTPUT_DIGITS), lineNumber );
	}

	/**
	 * Refreshes all debug output lines.
	 */
	private void redraw() {
		for (char i = 0; i < MAX_LINES; ++i) {
			if (!descriptions[i].isEmpty()) {
				LCD.drawString(descriptions[i], 0, i);
				LCD.drawString(": ", maxLength, i);
				drawFloat( values[i], (char)(NUMBER_DIGITS - OUTPUT_DIGITS), i );
			}

		}
	}
	
	private void drawFloat(float f, char x, char y) {
		
		float f_abs = Math.abs(f);
		
		if ( f_abs > 9999 ) {
			LCD.drawString( "Owerfl.", x + 1, y);
		}
		else if ( f_abs < 1 ) {
			LCD.drawInt( (int)(10000 * f_abs), OUTPUT_DIGITS, x, y );
			LCD.drawString( "0.", x + 1, y );
		} else {
			LCD.drawInt( (int)(100 * f_abs), OUTPUT_DIGITS, x, y );
			LCD.drawString( ".", x + OUTPUT_DIGITS - 3, y );
			LCD.drawInt( (int)( f_abs ), OUTPUT_DIGITS - 3, x, y);
		}
		
		LCD.drawChar( Math.signum(f) == -1 ? '-' : '+', x, y);
	}
}
