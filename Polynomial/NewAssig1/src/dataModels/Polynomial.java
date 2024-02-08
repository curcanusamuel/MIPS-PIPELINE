package dataModels;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
	private TreeMap<Integer, Monomial> map;

	public Polynomial() {
		map = new TreeMap<>();
	}

	public String toString() {

		return "" + map.values().toString();

	}

	public TreeMap<Integer, Monomial> getMap() {
		return map;
	}

	public void setMap(TreeMap<Integer, Monomial> map) {
		this.map = map;
	}

	// the function that transforms a string into a polynomial
	public void poly(String input) throws Exception {
		String[] substrings = new String[500];
		int lenght = 0;
		if (input.length() == 0) {
			return;
		}
		Pattern p = Pattern.compile("[+-]?\\d?[x]\\d?");
		Pattern p2 = Pattern.compile("[+-]?\\d");
		int index = 0;
		int position = 0;
		int n1 = 0;

		// dividing words into substrings with the occurrence of '+' or '-' signs
		for (char c : input.toCharArray()) {
			if (!Character.isLetter(c) && !Character.isDigit(c) && c != '.') {
				substrings[lenght] = input.substring(position, index);
				lenght++;
				position = index;
				n1++;
			}
			index++;
			if (index == input.length()) {
				substrings[lenght] = input.substring(position, index);
				n1++;
			}

		}

		int n3 = 0;
		int firstOfAll = 0;
		// checking each substring separately to check its shape and add it to the
		// polynomial
		for (String str : substrings) {
			if (str == null) {
				continue;
			}
			if (str == "") {

				n3++;
				continue;
			}

			Matcher m = p.matcher(str);
			Matcher m2 = p2.matcher(str);

			int xPosition = str.indexOf('x');
			if (xPosition != -1) {
				String n = str.substring(0, xPosition);
				String powerStr = str.substring(xPosition + 1);
				float n2 = 0;
				if (firstOfAll <= 1) {
					if (n.isEmpty()) {
						n2 = 1;
					} else {
						if (n.charAt(0) == '-' && xPosition == 1) {
							n = n + "1";
							n2 = Float.parseFloat(n);
						} else if (n.charAt(0) == '+' && xPosition == 1) {
							n = n + "1";
							n2 = Float.parseFloat(n);
						} else {
							n2 = Float.parseFloat(n);
							;
						}
					}

				} else {
					if (n.substring(1).isEmpty())
						n2 = 1;
					else

						n2 = Float.parseFloat(n);

				}
				int power;
				if (powerStr.isEmpty())
					power = 1;
				else
					power = Integer.parseInt(powerStr);

				if (map.containsKey(power))
					this.map.put(power, new Monomial(n2 + map.get(power).getNumber(), power, "x"));
				else
					this.map.put(power, new Monomial(n2, power, "x"));
				n3++;
			} else {
				if (m2.find()) {
					n3++;
					float n2 = Float.parseFloat(str);
					if (this.map.containsKey(0))
						this.map.put(0, new Monomial(n2 + map.get(0).getNumber(), 0, "x"));
					else
						this.map.put(0, new Monomial(n2, 0, "x"));

				}
			}
			firstOfAll++;

		}

		// check if the number of substrings found is equal to the number of monomials
		// in the polynomial
		if (n1 != n3) {
			throw new Exception();
		}

	}

	// receives a polynomial and reorders it in a string starting with the highest
	// power
	public String reformatePolynom(Polynomial pol) {
		String result = "";
		if(pol.getMap().isEmpty())
			return "0";
		for (Monomial m : pol.getMap().values()) {
			result = m.toString() + result;
		}

		return result;
	}
}
