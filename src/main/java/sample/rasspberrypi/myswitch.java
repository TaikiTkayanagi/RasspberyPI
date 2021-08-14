import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class myswitch{

  public static void main(String[] args) throws InterruptedException {
    System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

    // create gpio controller
    final GpioController gpio = GpioFactory.getInstance();

    // provision gpio pin #07 as an input pin with its internal pull down resistor enabled
    final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);

    // set shutdown state for this input pin
    myButton.setShutdownOptions(true);

    // create and register gpio pin listener
    myButton.addListener(new GpioPinListenerDigital() {
        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            // display pin state on console
            System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
        }

    });

    System.out.println(" ... complete the GPIO #07 circuit and see the listener feedback here in the console.");

    // keep program running until user aborts (CTRL-C)
    while(true) {
        Thread.sleep(500);
    }

    // stop all GPIO activity/threads by shutting down the GPIO controller
    // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
    gpio.shutdown();
  }

}
