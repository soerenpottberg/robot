import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Motor;



public class Lightsensor {
	 public static void main(String[] args)  throws Exception {
             LightSensor light= new LightSensor(SensorPort.S1);
            	     while(true){
            	       int a;
            	       a=light.getLightValue();
            	       if (a>=40 && a< 50){
            	    	   Motor.A.forward();
            	    	   Motor.B.forward();
            	    	   }
            	       else if (a<40){
            	    	   Motor.A.forward();
            	    	   Motor.B.stop();
            	       }   
            	       
            	       else {
            	    	   Motor.A.stop();
            	    	   Motor.B.forward();
            	       }   
     }
 }
}