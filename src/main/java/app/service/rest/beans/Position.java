package app.service.rest.beans;

public class Position {

	public int degree;
	public int minute;
	public int second;
	public String worldSide;

	public Position() {
	}

	public Position(int degree, int minute, int second, String worldSide) {
		super();
		this.degree = degree;
		this.minute = minute;
		this.second = second;
		this.worldSide = worldSide;
	}

	public Position(double decimalDegrees, String worldSide) {
		double minutes = (decimalDegrees - Math.floor(decimalDegrees)) * 60.0;
		double seconds = (minutes - Math.floor(minutes)) * 60.0;
		double degree = (seconds - Math.floor(seconds)) * 10.0;

		this.minute = (int) Math.floor(minutes);
		this.second = (int) Math.floor(seconds);
		this.degree = (int) Math.floor(degree);
		this.worldSide = worldSide;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public String getWorldSide() {
		return worldSide;
	}

	public void setWorldSide(String worldSide) {
		this.worldSide = worldSide;
	}

	@Override
	public String toString() {
		return "Position [degree=" + degree + ", minute=" + minute + ", second=" + second + ", worldSide=" + worldSide
				+ "]";
	}

}
