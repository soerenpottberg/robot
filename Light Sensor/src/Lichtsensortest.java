
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;



public class Lichtsensortest {
       public static void main(String[] args)  throws Exception {
             LightSensor light= new LightSensor(SensorPort.S1);
            	     while(true){
            	       int a;
            	       a=light.getLightValue();
            	       System.out.println(a); 
            	     }       	        

     }

}