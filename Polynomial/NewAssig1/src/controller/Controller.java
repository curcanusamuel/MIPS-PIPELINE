package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Logical.Operation;
import UserInterface.FirstPage;
import UserInterface.SecondPage;
import dataModels.Polynomial;

public class Controller {

	private FirstPage page;

	public Controller(FirstPage page) {
		this.page = page;
		
		//for each operation it tests if the polynomials are valid
		page.addListener(new ActionListener() {
			String firstInput = "";
			String secondInput = "";

			public void actionPerformed(ActionEvent e) {
				firstInput = page.getFirstInput();
				secondInput = page.getSecondInput();
				Polynomial firstPol = new Polynomial();
				Polynomial secondPol = new Polynomial();
				int first = 0;
				int second = 0;
				try {
					firstPol.poly(firstInput);
				} catch (Exception ex) {
					first = 1;
				}
				try {
					secondPol.poly(secondInput);
				} catch (Exception ex) {
					second = 1;
				}
				if (first == 1 && second == 0)
					page.showStringError("The first polynomial entered is wrong");
				else if (first == 0 && second == 1)

					page.showStringError("The second polynomial entered is wrong");
				else if (first == 1 && second == 1) {
					page.showStringError("Both polynomials entered are wrong");
				}

				else {

					Polynomial result = new Polynomial();
					Operation operation = new Operation();

					result = operation.add(firstPol, secondPol);

					page.updateResultLabel(result.reformatePolynom(result));
				}
			}

		});

		page.multiplicateListener(new ActionListener() {
			String firstInput = "";
			String secondInput = "";

			public void actionPerformed(ActionEvent e) {
				firstInput = page.getFirstInput();
				secondInput = page.getSecondInput();
				Polynomial firstPol = new Polynomial();
				Polynomial secondPol = new Polynomial();
				int first = 0;
				int second = 0;
				try {
					firstPol.poly(firstInput);
				} catch (Exception ex) {
					first = 1;
				}
				try {
					secondPol.poly(secondInput);
				} catch (Exception ex) {
					second = 1;
				}
				if (first == 1 && second == 0)
					page.showStringError("The first polynomial entered is wrong");
				else if (first == 0 && second == 1)

					page.showStringError("The second polynomial entered is wrong");
				else if (first == 1 && second == 1) {
					page.showStringError("Both polynomials entered are wrong");
				}

				else {

					Polynomial result = new Polynomial();
					Operation operation = new Operation();
					result = operation.multiplicate(firstPol, secondPol);

					page.updateResultLabel(result.reformatePolynom(result));
				}
			}

		});

		page.divideListener(new ActionListener() {

			String firstInput = "";
			String secondInput = "";

			public void actionPerformed(ActionEvent e) {
				firstInput = page.getFirstInput();
				secondInput = page.getSecondInput();
				Polynomial firstPol = new Polynomial();
				Polynomial secondPol = new Polynomial();

				int first = 0;
				int second = 0;
				try {
					firstPol.poly(firstInput);
				} catch (Exception ex) {
					first = 1;
				}
				try {
					secondPol.poly(secondInput);
				} catch (Exception ex) {
					second = 1;
				}
				if (first == 1 && second == 0)
					page.showStringError("The first polynomial entered is wrong");
				else if (first == 0 && second == 1)

					page.showStringError("The second polynomial entered is wrong");
				else if (first == 1 && second == 1) {
					page.showStringError("Both polynomials entered are wrong");
				}

				else {
					String result = "";
					Operation operation = new Operation();
					try {
						result = operation.divide(firstPol, secondPol);

					} catch (Exception e1) {
						page.showStringError("Division by 0");
					}
					page.updateResultLabel(result);
				}
			}

		});

		page.substractListener(new ActionListener() {
			String firstInput = "";
			String secondInput = "";

			public void actionPerformed(ActionEvent e) {
				firstInput = page.getFirstInput();
				secondInput = page.getSecondInput();
				Polynomial firstPol = new Polynomial();
				Polynomial secondPol = new Polynomial();

				int first = 0;
				int second = 0;
				try {
					firstPol.poly(firstInput);
				} catch (Exception ex) {
					first = 1;
				}
				try {
					secondPol.poly(secondInput);
				} catch (Exception ex) {
					second = 1;
				}
				if (first == 1 && second == 0)
					page.showStringError("The first polynomial entered is wrong");
				else if (first == 0 && second == 1)

					page.showStringError("The second polynomial entered is wrong");
				else if (first == 1 && second == 1) {
					page.showStringError("Both polynomials entered are wrong");
				}

				else {

					Polynomial result = new Polynomial();
					Operation operation = new Operation();
					result = operation.substract(firstPol, secondPol);

					page.updateResultLabel(result.reformatePolynom(result));
				}
			}

		});

		page.integrateListener(new ActionListener() {
			String firstInput = "";
			String secondInput = "";

			public void actionPerformed(ActionEvent e) {
				firstInput = page.getFirstInput();
				secondInput = page.getSecondInput();
				Polynomial firstPol = new Polynomial();
				Polynomial secondPol = new Polynomial();

				int first = 0;
				int second = 0;
				try {
					firstPol.poly(firstInput);
				} catch (Exception ex) {
					first = 1;
				}
				try {
					secondPol.poly(secondInput);
				} catch (Exception ex) {
					second = 1;
				}
				if (first == 1 && second == 0)
					page.showStringError("The first polynomial entered is wrong");
				else if (first == 0 && second == 1)

					page.showStringError("The second polynomial entered is wrong");
				else if (first == 1 && second == 1) {
					page.showStringError("Both polynomials entered are wrong");
				}

				else {

					SecondPage sPage = new SecondPage();
					Polynomial result1 = new Polynomial();
					Polynomial result2 = new Polynomial();
					Operation operation = new Operation();
					result1 = operation.integration(firstPol);
					result2 = operation.integration(secondPol);
					sPage.updateFirstLabel(result1.reformatePolynom(result1));
					sPage.updateSecondLabel(result2.reformatePolynom(result2));
					sPage.setVisible(true);
				}
			}

		});

		page.derivateListener(new ActionListener() {
			String firstInput = "";
			String secondInput = "";

			public void actionPerformed(ActionEvent e) {
				firstInput = page.getFirstInput();
				secondInput = page.getSecondInput();
				Polynomial firstPol = new Polynomial();
				Polynomial secondPol = new Polynomial();

				int first = 0;
				int second = 0;
				try {
					firstPol.poly(firstInput);
				} catch (Exception ex) {
					first = 1;
				}
				try {
					secondPol.poly(secondInput);
				} catch (Exception ex) {
					second = 1;
				}
				if (first == 1 && second == 0)
					page.showStringError("The first polynomial entered is wrong");
				else if (first == 0 && second == 1)

					page.showStringError("The second polynomial entered is wrong");
				else if (first == 1 && second == 1) {
					page.showStringError("Both polynomials entered are wrong");
				}

				else {
					SecondPage sPage = new SecondPage();
					Polynomial result1 = new Polynomial();
					Polynomial result2 = new Polynomial();
					Operation operation = new Operation();
					result1 = operation.derivation(firstPol);
					result2 = operation.derivation(secondPol);
					sPage.updateFirstLabel(result1.reformatePolynom(result1));
					sPage.updateSecondLabel(result2.reformatePolynom(result2));
					sPage.setVisible(true);

				}
			}

		});

	}
}
