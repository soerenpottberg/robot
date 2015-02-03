
public class Angle {

	public static void main(String[] args) {
		final int a = 17;
		final int teta = 20;
		double rad = Math.toRadians(teta);
		System.out.println(rad);
		int dist = (int) ((30-a)/(Math.sin(rad)));
		System.out.println(dist);
	 
	}

}
