import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import Logical.Operation;
import dataModels.Polynomial;

public class JUnit {

	@SuppressWarnings("resource")
	@Test
	public void add_Test() throws Exception {
		String[] pol1 = new String[6];
		String[] pol2 = new String[6];
		String[] result = new String[6];
		BufferedReader readpol1;
		BufferedReader readpol2;
		BufferedReader readresult;
		readpol1 = new BufferedReader(new FileReader("Polinom1.txt"));
		readpol2 = new BufferedReader(new FileReader("Polinom2.txt"));
		readresult = new BufferedReader(new FileReader("Add.txt"));
		for (int i = 0; i <= 5; i++) {

			pol1[i] = readpol1.readLine();

			pol2[i] = readpol2.readLine();
			result[i] = readresult.readLine();

		}
		for (int i = 0; i < 5; i++) {
			Polynomial first = new Polynomial();
			first.poly(pol1[i]);
			Polynomial second = new Polynomial();
			second.poly(pol2[i]);
			Operation operation = new Operation();
			String result1 = first.reformatePolynom(operation.add(first, second));

			assertEquals(result1, result[i]);

		}
	}

	@SuppressWarnings("resource")
	@Test
	public void substract_Test() throws Exception {
		String[] pol1 = new String[6];
		String[] pol2 = new String[6];
		String[] result = new String[6];
		BufferedReader readpol1;
		BufferedReader readpol2;
		BufferedReader readresult;
		readpol1 = new BufferedReader(new FileReader("Polinom1.txt"));
		readpol2 = new BufferedReader(new FileReader("Polinom2.txt"));
		readresult = new BufferedReader(new FileReader("Substract.txt"));
		for (int i = 0; i <= 5; i++) {

			pol1[i] = readpol1.readLine();

			pol2[i] = readpol2.readLine();
			result[i] = readresult.readLine();

		}
		for (int i = 0; i <= 5; i++) {
			Polynomial first = new Polynomial();
			first.poly(pol1[i]);
			Polynomial second = new Polynomial();
			second.poly(pol2[i]);
			Operation operation = new Operation();
			String result1 = first.reformatePolynom(operation.substract(first, second));
			assertEquals(result1, result[i]);

		}
	}

	@SuppressWarnings("resource")
	@Test
	public void derivate_Test() throws Exception {
		String[] pol1 = new String[6];
		String[] pol2 = new String[6];
		String[] result1 = new String[6];
		String[] result2 = new String[6];
		BufferedReader readpol1;
		BufferedReader readpol2;
		BufferedReader readresult;
		readpol1 = new BufferedReader(new FileReader("Polinom1.txt"));
		readpol2 = new BufferedReader(new FileReader("Polinom2.txt"));
		readresult = new BufferedReader(new FileReader("Derivate.txt"));
		for (int i = 0; i <= 5; i++) {

			pol1[i] = readpol1.readLine();

			pol2[i] = readpol2.readLine();
			result1[i] = readresult.readLine();
			result2[i] = readresult.readLine();
		}

		for (int i = 0; i <= 5; i++) {
			Polynomial first = new Polynomial();
			first.poly(pol1[i]);
			Polynomial second = new Polynomial();
			second.poly(pol2[i]);
			Operation operation = new Operation();
			String result12 = first.reformatePolynom(operation.derivation(first));
			String result13 = first.reformatePolynom(operation.derivation(second));
			assertEquals(result12, result1[i]);
			assertEquals(result13, result2[i]);

		}
	}

	@SuppressWarnings("resource")
	@Test
	public void integrate_Test() throws Exception {
		String[] pol1 = new String[6];
		String[] pol2 = new String[6];
		String[] result1 = new String[6];
		String[] result2 = new String[6];
		BufferedReader readpol1;
		BufferedReader readpol2;
		BufferedReader readresult;
		readpol1 = new BufferedReader(new FileReader("Polinom1.txt"));
		readpol2 = new BufferedReader(new FileReader("Polinom2.txt"));
		readresult = new BufferedReader(new FileReader("Integrate.txt"));
		for (int i = 0; i <= 5; i++) {

			pol1[i] = readpol1.readLine();

			pol2[i] = readpol2.readLine();
			result1[i] = readresult.readLine();
			result2[i] = readresult.readLine();
		}

		for (int i = 0; i <= 5; i++) {
			Polynomial first = new Polynomial();
			first.poly(pol1[i]);
			Polynomial second = new Polynomial();
			second.poly(pol2[i]);
			Operation operation = new Operation();
			String result12 = first.reformatePolynom(operation.integration(first));
			String result13 = first.reformatePolynom(operation.integration(second));

			assertEquals(result12, result1[i]);
			assertEquals(result13, result2[i]);

		}
	}

	@SuppressWarnings("resource")
	@Test
	public void divide_Test() throws Exception {
		String[] pol1 = new String[6];
		String[] pol2 = new String[6];
		String[] result = new String[6];
		BufferedReader readpol1;
		BufferedReader readpol2;
		BufferedReader readresult;
		readpol1 = new BufferedReader(new FileReader("Polinom1.txt"));
		readpol2 = new BufferedReader(new FileReader("Polinom2.txt"));
		readresult = new BufferedReader(new FileReader("Divide.txt"));
		for (int i = 0; i <= 5; i++) {

			pol1[i] = readpol1.readLine();

			pol2[i] = readpol2.readLine();
			result[i] = readresult.readLine();

		}
		for (int i = 0; i <= 5; i++) {
			Polynomial first = new Polynomial();
			first.poly(pol1[i]);
			Polynomial second = new Polynomial();
			second.poly(pol2[i]);
			Operation operation = new Operation();
			String result1 = operation.divide(first, second);

			assertEquals(result1, result[i]);

		}
	}

	@SuppressWarnings("resource")
	@Test
	public void multiplicate_Test() throws Exception {
		String[] pol1 = new String[6];
		String[] pol2 = new String[6];
		String[] result = new String[6];
		BufferedReader readpol1;
		BufferedReader readpol2;
		BufferedReader readresult;
		readpol1 = new BufferedReader(new FileReader("Polinom1.txt"));
		readpol2 = new BufferedReader(new FileReader("Polinom2.txt"));
		readresult = new BufferedReader(new FileReader("Multiplicate.txt"));
		for (int i = 0; i <= 5; i++) {

			pol1[i] = readpol1.readLine();

			pol2[i] = readpol2.readLine();
			result[i] = readresult.readLine();

		}
		for (int i = 0; i <= 5; i++) {
			Polynomial first = new Polynomial();
			first.poly(pol1[i]);
			Polynomial second = new Polynomial();
			second.poly(pol2[i]);
			Operation operation = new Operation();
			String result1 = first.reformatePolynom(operation.multiplicate(first, second));
			assertEquals(result1, result[i]);

		}
	}

	@SuppressWarnings("resource")
	@Test
	public void formatPolynomial() throws Exception {
		

		
	}

}
