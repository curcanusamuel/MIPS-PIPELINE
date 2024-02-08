import UserInterface.FirstPage;
import controller.Controller;

public class Main {

	public static void main(String[] args) throws Exception {	
		 FirstPage page = new FirstPage(); 
		 Controller controller = new Controller(page);
		 page.setVisible(true);
		 

	}

}
