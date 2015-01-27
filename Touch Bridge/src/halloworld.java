
import lejos.nxt.*;



public class halloworld {
       public static void main(String[] args)  throws Exception {
             TouchSensor touch= new TouchSensor(SensorPort.S4);
            	     while(true){
            	       if (touch.isPressed()){
            	    	   Motor.B.forward();
            	    	   Motor.C.forward();
            	       }
            	       else {
            	    	   Motor.B.stop();
            	    	   Motor.C.stop();
            	       }
            	     }       	    
               

     }

}
