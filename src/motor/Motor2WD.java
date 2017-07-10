package motor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Motor2WD
{
	public static enum MotorType
	{
		RIGHT, LEFT;
	}

	// right motor
	private final Pin ENA = RaspiPin.GPIO_26;
	private final Pin IN1 = RaspiPin.GPIO_27;
	private final Pin IN2 = RaspiPin.GPIO_28;

	// left motor
	private final Pin ENB = RaspiPin.GPIO_23;
	private final Pin IN3 = RaspiPin.GPIO_21;
	private final Pin IN4 = RaspiPin.GPIO_22;

	private MotorType type;
	private GpioController gpio;

	private GpioPinPwmOutput speedPin;
	private GpioPinDigitalOutput forwardPin;
	private GpioPinDigitalOutput backwardPin;

	private int speed = 800;

	public Motor2WD(MotorType type)
	{
		gpio = GpioFactory.getInstance();
		this.type = type;

		// initialise instance variables
		if (type == MotorType.RIGHT)
		{
			speedPin = gpio.provisionPwmOutputPin(ENA, "SPEED", speed);
			forwardPin = gpio.provisionDigitalOutputPin(IN1, "FORWARD", PinState.LOW);
			backwardPin = gpio.provisionDigitalOutputPin(IN2, "BACKWARD", PinState.LOW);
		}
		else
		{
			speedPin = gpio.provisionPwmOutputPin(ENB, "SPEED", speed);
			forwardPin = gpio.provisionDigitalOutputPin(IN3, "FORWARD", PinState.LOW);
			backwardPin = gpio.provisionDigitalOutputPin(IN4, "BACKWARD", PinState.LOW);
		}
		// set shutdown state for this input pin
		speedPin.setShutdownOptions(true);
		forwardPin.setShutdownOptions(true);
		backwardPin.setShutdownOptions(true);
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
		speedPin.setPwm(speed);
	}

	public void brake()
	{
		forwardPin.setState(PinState.LOW);
		backwardPin.setState(PinState.LOW);
	}

	public void forward()
	{
		forwardPin.setState(PinState.HIGH);
		backwardPin.setState(PinState.LOW);
	}

	public void backward()
	{
		forwardPin.setState(PinState.LOW);
		backwardPin.setState(PinState.HIGH);
	}

	public MotorType getType()
	{
		return type;
	}
}
