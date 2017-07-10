package motor;

public class RunRobot2WD
{
	private Robot2WD robot = new Robot2WD();

	public static void main(String[] args) throws InterruptedException
	{
		RunRobot2WD test = new RunRobot2WD();
		test.run(800, 1000);
	}

	public void run(int speed, int stepDuration) throws InterruptedException
	{
		System.out.println("forward " + stepDuration);
		robot.forward();
		Thread.sleep(stepDuration);

		System.out.println("backward " + stepDuration);
		robot.backward();
		Thread.sleep(stepDuration);

		System.out.println("brake " + stepDuration);
		robot.brake();
		Thread.sleep(stepDuration);

		System.out.println("turn right " + stepDuration);
		robot.turnRight();
		Thread.sleep(stepDuration);

		System.out.println("turn left " + stepDuration);
		robot.turnLeft();
		Thread.sleep(stepDuration);
	}
}
