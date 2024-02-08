package Logical;

import java.util.Iterator;

import dataModels.Monomial;
import dataModels.Polynomial;

public class Operation {

	// add pol1+pol2
	public Polynomial add(Polynomial firstPol, Polynomial secondPol) {
		Polynomial result = new Polynomial();

		// runs through the polynomials and adds their coefficients with the coefficient
		// where the monomials have equal powers
		for (Monomial m : firstPol.getMap().values()) {
			for (Monomial n : secondPol.getMap().values()) {
				if (m.getPower() == n.getPower()) {
						result.getMap().put(m.getPower(),
								new Monomial(m.getNumber() + n.getNumber(), m.getPower(), "x"));
				}

			}
		}

		// it goes through the rest of the remaining monomials and adds them to the
		// result
		for (Monomial m : firstPol.getMap().values()) {
			if (!result.getMap().containsKey(m.getPower())) {
				result.getMap().put(m.getPower(), new Monomial(m.getNumber(), m.getPower(), "x"));
			}

		}
		for (Monomial n : secondPol.getMap().values()) {
			if (!result.getMap().containsKey(n.getPower())) {
				result.getMap().put(n.getPower(), new Monomial(n.getNumber(), n.getPower(), "x"));
			}
		}

		return result;
	}

	// substract pol1-pol2
	public Polynomial substract(Polynomial firstPol, Polynomial secondPol) {
		Polynomial result = new Polynomial();

		// just like the addition operation
		for (Monomial m : firstPol.getMap().values()) {
			for (Monomial n : secondPol.getMap().values()) {
				if (m.getPower() == n.getPower()) {
					if (m.getNumber() + n.getNumber() != 0)
						result.getMap().put(m.getPower(),
								new Monomial(m.getNumber() - n.getNumber(), m.getPower(), "x"));

				}

			}
		}
		for (Monomial m : firstPol.getMap().values()) {
			if (!result.getMap().containsKey(m.getPower())) {
				result.getMap().put(m.getPower(), new Monomial(m.getNumber(), m.getPower(), "x"));
			}

		}
		for (Monomial n : secondPol.getMap().values()) {
			if (!result.getMap().containsKey(n.getPower())) {
				result.getMap().put(n.getPower(), new Monomial(-n.getNumber(), n.getPower(), "x"));
			}
		}

		return result;
	}

	// multiplicate pol1*pol2
	public Polynomial multiplicate(Polynomial firstPol, Polynomial secondPol) {
		Polynomial result = new Polynomial();

		for (Monomial m : firstPol.getMap().values()) {
			for (Monomial n : secondPol.getMap().values()) {
				{
					result.getMap().put(m.getPower() + n.getPower(),
							new Monomial(m.getNumber() * n.getNumber(), m.getPower() + n.getPower(), "x"));

				}

			}
		}
		return result;
	}

	// divide pol1/pol2
	public String divide(Polynomial firstPol, Polynomial secondPol) throws Exception {
		Polynomial result = new Polynomial();
		Polynomial result1 = new Polynomial();
		Operation operation1=new Operation();
		result1=operation1.substract(firstPol, secondPol);
		for(Monomial m1: result1.getMap().values())
			if(m1.getNumber()!=0)
			break;
			else return "1";
		
		// two conditions in case we have a division by 0
		if (firstPol == null || secondPol == null || firstPol.getMap() == null || secondPol.getMap() == null) {
			throw new NullPointerException("One or both polynomials are null");
		}
		if (secondPol.getMap().isEmpty() || secondPol.getMap().lastEntry().getValue().getNumber() == 0) {
			throw new ArithmeticException("Division by zero");
		}
		while (firstPol.getMap().lastEntry().getValue().getPower() >= secondPol.getMap().lastEntry().getValue()
				.getPower()) {

			Monomial firstTerm = firstPol.getMap().lastEntry().getValue();
			Monomial secondTerm = secondPol.getMap().lastEntry().getValue();
			if (firstTerm.getNumber() == 0 || secondTerm.getNumber() == 0) {
				result.getMap().put(firstPol.getMap().lastEntry().getValue().getPower(), firstTerm);
				break;
			}
			float n = firstTerm.getNumber() / secondTerm.getNumber();
			int power = firstTerm.getPower() - secondTerm.getPower();

			Monomial m = new Monomial(n, power, "x");
			result.getMap().put(power, m);
			Polynomial intermediate = new Polynomial();
			intermediate.getMap().put(power, m);

			Operation operation = new Operation();
			Polynomial product = operation.multiplicate(secondPol, intermediate);
			firstPol = operation.substract(firstPol, product);

			Iterator<Monomial> iterator = firstPol.getMap().values().iterator();
			while (iterator.hasNext()) {
			    Monomial mon = iterator.next();
			    if (mon.getNumber() == 0) {
			        iterator.remove();
			        firstPol.getMap().remove(mon.getPower());
			    }
			}
			if (firstPol.getMap().isEmpty())
			    break;


		}
		String resultFinal = "";

		resultFinal = result.reformatePolynom(result) + "    R:" + firstPol.reformatePolynom(firstPol);
		return resultFinal;
	}

	// integration pol1
	public Polynomial integration(Polynomial pol) {
		Polynomial result = new Polynomial();

		for (Monomial m : pol.getMap().values()) {
			result.getMap().put(m.getPower() + 1,
					new Monomial(m.getNumber() / (m.getPower() + 1), m.getPower() + 1, "x"));
		}

		return result;
	}

	// derivation pol1
	public Polynomial derivation(Polynomial pol) {
		Polynomial result = new Polynomial();

		for (Monomial m : pol.getMap().values()) {
			result.getMap().put(m.getPower() - 1, new Monomial(m.getNumber() * m.getPower(), m.getPower() - 1, "x"));
		}

		return result;
	}

}