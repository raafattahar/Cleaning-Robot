package motor;

import motor.Motor2WD.MotorType;

public class Robot2WD
{
	private Motor2WD right;
	private Motor2WD left;

	public Robot2WD()
	{
		right = new Motor2WD(MotorType.RIGHT);
		left = new Motor2WD(MotorType.LEFT);
	}

	public void setSpeed(int speed)
	{
		right.setSpeed(speed);
		left.setSpeed(speed);
	}

	public void brake()
	{
		right.brake();
		left.brake();
	}

	public void forward()
	{
		right.forward();
		left.forward();
	}

	public void backward()
	{
		right.backward();
		left.backward();
	}

	public void turnRight()
	{
		right.backward();
		left.forward();
	}

	public void turnLeft()
	{
		right.forward();
		left.backward();
	}
}
