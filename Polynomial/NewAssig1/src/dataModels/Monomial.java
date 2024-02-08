package dataModels;

public class Monomial {

	private float number;
	private int power;
	private String x;

	public Monomial(float n, int p, String x) {
		this.number = n;
		this.power = p;
		this.x = x;
	}

	public float getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	
	//toString function to display a polynomial as nicely as possible
	public String toString() {
		if(power!=1) {
		if (power != 0) {

			if (number == Math.ceil(number) && number > 0 && number != 1)
				return "+" + (int) number + "" + x + "^" + power;
			if (number == 1)
				return "+" + x + "^" + power;
			if (number == Math.ceil(number) && number < 0 && number != -1)
				return "" + (int) number + "" + x + "^" + power;
			if (number == -1)
				return "-" + x + "^" + power;

			if (number != Math.ceil(number) && number > 0 && number != 1)
				return "+" + number + "" + x + "^" + power;
			if (number != Math.ceil(number) && number < 0 && number != -1)
				return "" + number + "" + x + "^" + power;

		} else {
			if (number == Math.ceil(number) && number > 0 && number != 1)
				return "+" + (int) number;
			if (number == 1)
				return "+" + number;
			if (number == Math.ceil(number) && number < 0 && number != -1)
				return "" + (int) number;
			if (number == -1.0)
				return "" + number;

			if (number != Math.ceil(number) && number > 0 && number != 1)
				return "+" + number;
			if (number != Math.ceil(number) && number < 0 && number != -1)
				return "" + number;
		}}
		else {
			if (number == Math.ceil(number) && number > 0 && number != 1)
				return "+" + (int) number + "" + x;
			if (number == 1.0)
				return "+" + x + "^" + power;
			if (number == Math.ceil(number) && number < 0 && number != -1)
				return "" + (int) number + "" + x;
			if (number == -1.0)
				return "-" + x + "^" + power;

			if (number != Math.ceil(number) && number > 0 && number != 1)
				return "+" + number + "" + x;
			if (number != Math.ceil(number) && number < 0 && number != -1)
				return "" + number + "" + x;
		}
		if (number == 0)
			return "";
		else {
			return "" + number + "" + x + "^" + power;

		}

	}
}

