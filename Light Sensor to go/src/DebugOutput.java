
public class DebugOutput {
	
	private final char MAX_LINES = 7;
	
	private String[] descriptions;
	private float[] values;
	private char maxLength = 0;

	public DebugOutput(){
		descriptions = new String[MAX_LINES];
	};
	
	public void setDescription(char lineNumber, String description) {
		if ( lineNumber < 0 || lineNumber >= MAX_LINES )
			return;
		
		boolean refreshMaxLength = descriptions[lineNumber].length() == maxLength;
		
		descriptions[lineNumber] = description;
		
		if ( refreshMaxLength )
		{
			maxLength = 0;
			for ( char i = 0; i < MAX_LINES; ++i )
			{
				maxLength = (char) Math.max( maxLength, descriptions[i].length() );
			}
		}
		else
		{
			maxLength = (char) Math.max( maxLength, description.length() );
		}
	}
	
	public void write(char lineNumber, float value) {
		if ( lineNumber < 0 || lineNumber >= MAX_LINES )
			return;
		
		values[lineNumber] = value;
		
		refresh();
	}
	
	private void refresh() {
		
		for ( char i = 0; i < MAX_LINES; ++i )
		{
			if ( !descriptions[i].isEmpty() )
			{
				System.out.print( descriptions[i] );
				for ( char j = (char)(descriptions[i].length()); j < maxLength; ++j) 
				{
					System.out.print( ' ' );
				}
				System.out.print( ": " );
				System.out.println( values[i] );
			}
			
		}
	}
}
