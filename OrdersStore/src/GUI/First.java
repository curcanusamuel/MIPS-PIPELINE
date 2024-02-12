package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Orders;
import Model.Product;
import Validators.PhoneNumber;

/**
 * @author Samuel-Adrian The main GUI class for the application.
 */

public class First extends JFrame {

	private JPanel contentPane;
	private JButton ordersButton;
	private JButton productButton;
	private JButton clientsButton;
	private JTable clientsTable;
	private JTable ordersTable;
	private JTable productsTable;

	public First() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		clientsButton = new JButton("Clients");
		clientsButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		clientsButton.setBounds(76, 37, 183, 48);
		contentPane.add(clientsButton);

		productButton = new JButton("Product");
		productButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		productButton.setBounds(76, 132, 183, 48);
		contentPane.add(productButton);

		ordersButton = new JButton("Orders");
		ordersButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		ordersButton.setBounds(76, 232, 183, 48);
		contentPane.add(ordersButton);

		clientsButton.addActionListener(e -> {

			showClientsTable();
			refreshProductsTable();
			refreshOrdersTable();
			refreshClientsTable();
		});

		productButton.addActionListener(e -> {
			showProductsTable();
			refreshProductsTable();
			refreshOrdersTable();
			refreshClientsTable();
		});

		ordersButton.addActionListener(e -> {
			showOrdersTable();
			refreshProductsTable();
			refreshOrdersTable();
			refreshClientsTable();
		});
	}

	/**
	 * Displays the clients table.
	 */
	private void showClientsTable() {
		OrderDAO orderDAO = new OrderDAO();
		ordersTable = orderDAO.getOrderTable();
		ClientDAO clientDAO = new ClientDAO();
		clientsTable = clientDAO.getClientTable();
		ProductDAO productDAO = new ProductDAO();
		productsTable = productDAO.getProductTable();

		if (clientsTable != null) {
			JFrame tableFrame = new JFrame("Clients Table");
			tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			tableFrame.setSize(450, 400);
			tableFrame.setLocationRelativeTo(this);

			JPanel panel = new JPanel(new BorderLayout());

			JScrollPane scrollPane = new JScrollPane(clientsTable);
			panel.add(scrollPane, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

			JButton addButton = new JButton("Adaugare");
			addButton.addActionListener(e -> {
				refreshProductsTable();
				refreshOrdersTable();
				refreshClientsTable();
				JFrame clientFrame = createClientFrame();
				clientFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {

					}
				});
				clientFrame.setVisible(true);
			});

			buttonPanel.add(addButton);

			JButton deleteButton = new JButton("Stergere");
			deleteButton.addActionListener(e -> {
				refreshProductsTable();
				refreshOrdersTable();
				refreshClientsTable();
				JFrame clientFrame = createRemoveFrame("Client");
				clientFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {

					}
				});
				clientFrame.setVisible(true);
			});
			buttonPanel.add(deleteButton);

			JButton editButton = new JButton("Editare");
			editButton.addActionListener(e -> {
				refreshProductsTable();
				refreshOrdersTable();
				refreshClientsTable();
				JFrame clientFrame = createEditFrame("Client");
				clientFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {

					}
				});
				clientFrame.setVisible(true);
			});
			buttonPanel.add(editButton);

			panel.add(buttonPanel, BorderLayout.SOUTH);
			tableFrame.add(panel);

			tableFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Failed to retrieve clients table.");
		}
	}

	/**
	 * Displays the products table.
	 */
	private void showProductsTable() {
		
		OrderDAO orderDAO = new OrderDAO();
		ordersTable = orderDAO.getOrderTable();
		ClientDAO clientDAO = new ClientDAO();
		clientsTable = clientDAO.getClientTable();
		ProductDAO productDAO = new ProductDAO();
		productsTable = productDAO.getProductTable();
		refreshOrdersTable();
		if (productsTable != null) {
			JFrame tableFrame = new JFrame("Products Table");
			tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			tableFrame.setSize(450, 400);
			tableFrame.setLocationRelativeTo(this);

			JPanel panel = new JPanel(new BorderLayout());

			JScrollPane scrollPane = new JScrollPane(productsTable);
			panel.add(scrollPane, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

			JButton addButton = new JButton("Adaugare");
			addButton.addActionListener(e -> {
				refreshProductsTable();
				refreshOrdersTable();
				refreshClientsTable();
				JFrame productFrame = createProductFrame();
				productFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {

					}
				});
				productFrame.setVisible(true);
			});
			buttonPanel.add(addButton);

			JButton deleteButton = new JButton("Stergere");
			deleteButton.addActionListener(e -> {
				refreshProductsTable();
				refreshOrdersTable();
				refreshClientsTable();
				JFrame productFrame = createRemoveFrame("Product");
				productFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {

					}
				});
				productFrame.setVisible(true);
			});
			buttonPanel.add(deleteButton);

			JButton editButton = new JButton("Editare");
			editButton.addActionListener(e -> {
				refreshProductsTable();
				refreshOrdersTable();
				refreshClientsTable();
				JFrame productFrame = createEditFrame("Product");
				productFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {

					}
				});
				productFrame.setVisible(true);
			});
			buttonPanel.add(editButton);

			panel.add(buttonPanel, BorderLayout.SOUTH);
			tableFrame.add(panel);

			tableFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Failed to retrieve products table.");
		}
	}

	/**
	 * Displays the orders table.
	 */
	private void showOrdersTable() {
		OrderDAO orderDAO = new OrderDAO();
		ordersTable = orderDAO.getOrderTable();
		ClientDAO clientDAO = new ClientDAO();
		clientsTable = clientDAO.getClientTable();
		ProductDAO productDAO = new ProductDAO();
		productsTable = productDAO.getProductTable();

		if (ordersTable != null) {
			JFrame tableFrame = new JFrame("Orders Table");
			tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			tableFrame.setSize(450, 400);
			tableFrame.setLocationRelativeTo(this);

			JPanel panel = new JPanel(new BorderLayout());

			JScrollPane scrollPane = new JScrollPane(ordersTable);
			panel.add(scrollPane, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

			JButton addButton = new JButton("Adaugare");
			addButton.addActionListener(e -> {
				refreshProductsTable();
				refreshOrdersTable();
				refreshClientsTable();
				JFrame ordersFrame = createOrderFrame();
				ordersFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					}
				});

				ordersFrame.setVisible(true);
			});
			buttonPanel.add(addButton);

			JButton deleteButton = new JButton("Stergere");
			deleteButton.addActionListener(e -> {
				refreshProductsTable();
				refreshOrdersTable();
				refreshClientsTable();
				JFrame ordersFrame = createRemoveFrame("Order");
				ordersFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					}
				});
				refreshOrdersTable();
				ordersFrame.setVisible(true);
			});
			buttonPanel.add(deleteButton);

			panel.add(buttonPanel, BorderLayout.SOUTH);
			tableFrame.add(panel);

			tableFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Failed to retrieve orders table.");
		}
	}

	private JFrame createClientFrame() {
		JFrame clientFrame = new JFrame("Adauga client");
		clientFrame.setSize(300, 250);
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setLocationRelativeTo(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel titleLabel = new JLabel("Adauga client");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titleLabel, BorderLayout.NORTH);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 2, 5, 10));

		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField(20);

		JLabel addressLabel = new JLabel("Address:");
		JTextField addressField = new JTextField(10);

		JLabel phoneLabel = new JLabel("Phone:");
		JTextField phoneField = new JTextField(10);

		formPanel.add(nameLabel);
		formPanel.add(nameField);
		formPanel.add(addressLabel);
		formPanel.add(addressField);
		formPanel.add(phoneLabel);
		formPanel.add(phoneField);

		panel.add(formPanel, BorderLayout.CENTER);

		JButton addButton = new JButton("Adauga");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = "1";
				String name = nameField.getText();
				String address = addressField.getText();
				String phone = phoneField.getText();
				long number = 0;
				int t = 1;
				try {
					number = Long.parseLong(id);
					char n = address.charAt(0);
					char n2 = name.charAt(0);

				} catch (Exception ex) {
					showStringError("Insert valid data");
					clientFrame.dispose();
					t = 0;
				}
				try {
					PhoneNumber phone1 = new PhoneNumber();
					phone1.validate(new Client(number, name, address, phone));
				} catch (Exception p) {
					t = 0;
					showStringError(p.getMessage());
					clientFrame.dispose();

				}
				if (t == 1) {
					Client newClient = new Client(number, name, address, phone);
					ClientBLL clientBLL = new ClientBLL();
					try {
						clientBLL.insertClient(newClient);
					} catch (IllegalArgumentException sa) {
						showStringError("Insert a valid number phone");
						clientFrame.dispose();
					}

				}
				refreshProductsTable();
				refreshClientsTable();
				refreshOrdersTable();
				clientFrame.dispose();
			}
		});

		panel.add(addButton, BorderLayout.SOUTH);

		clientFrame.getContentPane().add(panel);
		return clientFrame;
	}

	private JFrame createProductFrame() {
		JFrame productFrame = new JFrame("Adauga produs");
		productFrame.setSize(300, 250);
		productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		productFrame.setLocationRelativeTo(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel titleLabel = new JLabel("Adauga produs");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titleLabel, BorderLayout.NORTH);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 2, 5, 10));

		JLabel nameLabel = new JLabel("Nume:");
		JTextField nameField = new JTextField(20);

		JLabel priceLabel = new JLabel("Preț:");
		JTextField priceField = new JTextField(10);

		JLabel quantityLabel = new JLabel("Cantitate:");
		JTextField quantityField = new JTextField(10);

		formPanel.add(nameLabel);
		formPanel.add(nameField);
		formPanel.add(priceLabel);
		formPanel.add(priceField);
		formPanel.add(quantityLabel);
		formPanel.add(quantityField);

		panel.add(formPanel, BorderLayout.CENTER);

		JButton addButton = new JButton("Adauga");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = "1";
				String name = nameField.getText();
				String price = priceField.getText();
				String quantity = quantityField.getText();

				long number = 0;
				int price1 = 0;
				int quantity1 = 0;
				int t = 0;
				try {
					number = Long.parseLong(id);
					price1 = Integer.parseInt(price);
					quantity1 = Integer.parseInt(quantity);

				} catch (Exception ex) {
					t = 1;
					showStringError("Insert valid data");
					productFrame.dispose();
				}
				if (t == 0) {
					Product newProduct = new Product(number, name, price, quantity);
					ProductBLL productBLL = new ProductBLL();
					try {
						productBLL.insertProduct(newProduct);
					} catch (IllegalArgumentException sa) {
						showStringError("Invalid Data");
					}
				}
				refreshProductsTable();
				refreshOrdersTable();
				productFrame.dispose();
			}
		});

		panel.add(addButton, BorderLayout.SOUTH);

		productFrame.getContentPane().add(panel);
		return productFrame;
	}

	private JFrame createOrderFrame() {
		JFrame orderFrame = new JFrame("Adauga comanda");
		orderFrame.setSize(300, 250);
		orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		orderFrame.setLocationRelativeTo(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel titleLabel = new JLabel("Adauga comanda");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titleLabel, BorderLayout.NORTH);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 2, 5, 10));

		JLabel idClientLabel = new JLabel("idClient:");
		ProductDAO prod = new ProductDAO();
		ClientDAO cl = new ClientDAO();
		JComboBox<String> idClientComboBox = new JComboBox<>();
		// Populați combobox-ul cu id-urile clienților existenți
		List<Client> clientIds = cl.findAll();
		for (Client c : clientIds) {
			idClientComboBox.addItem("" + c.getId() + "(" + c.getName() + ")");
		}

		JLabel idProductLabel = new JLabel("idProduct:");
		JComboBox<String> idProductComboBox = new JComboBox<>();
		// Populați combobox-ul cu id-urile produselor existente
		List<Product> productIds = prod.findAll();
		for (Product productId : productIds) {
			idProductComboBox.addItem("" + productId.getId() + "(" + productId.getName() + ")");
		}

		JLabel quantityLabel = new JLabel("quantity:");
		JTextField quantityField = new JTextField(10);

		formPanel.add(idClientLabel);
		formPanel.add(idClientComboBox);
		formPanel.add(idProductLabel);
		formPanel.add(idProductComboBox);
		formPanel.add(quantityLabel);
		formPanel.add(quantityField);

		panel.add(formPanel, BorderLayout.CENTER);

		JButton addButton = new JButton("Adauga");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderDAO ord = new OrderDAO();
				List<Orders> orderList = ord.findAll();
				long id;
				if (!orderList.isEmpty()) {
					Orders o = orderList.get(orderList.size() - 1);
					id = o.getId() + 1;
				} else
					id = 1;

				String idClient = (String) idClientComboBox.getSelectedItem();
				String idProduct = (String) idProductComboBox.getSelectedItem();
				String quantity = quantityField.getText();
				String idP = "";
				String idC = "";
				for (int i = 0; i < idProduct.length() - 1; i++)
					if (idProduct.charAt(i) == '(')
						idP = idProduct.substring(0, i);
				for (int i = 0; i < idClient.length() - 1; i++)
					if (idClient.charAt(i) == '(')
						idC = idClient.substring(0, i);
				long number = 0;
				int t = 0;
				try {
					number = Long.parseLong("" + id);
				} catch (Exception ex) {
					t = 1;
					showStringError("Insert valid data");
					orderFrame.dispose();
				}
				if (t == 0) {
					Orders newOrder = new Orders(number, Integer.parseInt(idC), Integer.parseInt(idP), quantity);
					OrderBLL orderBLL = new OrderBLL();

					try {
						orderBLL.insertOrder(newOrder);
					} catch (Exception ex) {
						if (ex.getMessage().equals("Client does not exist!")) {
							showStringError("Client does not exist!");
						} else if (ex.getMessage().equals("Product does not exist!")) {
							showStringError("Product does not exist!");
						} else if (ex.getMessage().equals("Not enough stock")) {
							showStringError("Not enough stock");
						} else {
							showStringError("Failed to insert the order");
						}
					}
					refreshOrdersTable();
					orderFrame.dispose();
				}

			}
		});

		panel.add(addButton, BorderLayout.SOUTH);

		orderFrame.getContentPane().add(panel);
		return orderFrame;
	}

	private JFrame createRemoveFrame(String componentName) {
		JFrame removeFrame = new JFrame("Remove " + componentName);
		removeFrame.setSize(300, 150);
		removeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removeFrame.setLocationRelativeTo(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel titleLabel = new JLabel("Remove " + componentName);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titleLabel, BorderLayout.NORTH);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(1, 2, 5, 10));

		JLabel idLabel = new JLabel("ID:");

		// Meniu derulant
		JComboBox<Integer> idComboBox = new JComboBox<>();
		idComboBox.setEditable(true);

		formPanel.add(idLabel);
		formPanel.add(idComboBox);
		ClientDAO clientDAO = new ClientDAO();
		ProductDAO productDAO = new ProductDAO();
		OrderDAO orderDAO = new OrderDAO();
		panel.add(formPanel, BorderLayout.CENTER);
		if (componentName.equals("Client")) {

			List<Client> list = clientDAO.findAll();

			DefaultComboBoxModel<Integer> idComboBoxModel = new DefaultComboBoxModel<>();
			idComboBox.setModel(idComboBoxModel);

			for (Client client : list) {
				idComboBoxModel.addElement((int) client.getId());
			}
		} else if (componentName.equals("Product")) {
			List<Product> list = productDAO.findAll();

			DefaultComboBoxModel<Integer> idComboBoxModel = new DefaultComboBoxModel<>();
			idComboBox.setModel(idComboBoxModel);

			for (Product product : list) {
				idComboBoxModel.addElement((int) product.getId());
			}
		} else if (componentName.equals("Order")) {
			List<Orders> list = orderDAO.findAll();

			DefaultComboBoxModel<Integer> idComboBoxModel = new DefaultComboBoxModel<>();
			idComboBox.setModel(idComboBoxModel);

			for (Orders order : list) {
				idComboBoxModel.addElement((int) order.getId());
			}
		}

		int selectedId = 0;
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (componentName.equals("Client")) {

					final int selectedId = (int) idComboBox.getSelectedItem();
					if (selectedId == 0) {
						showStringError("Invalid id");
						return;
					}

					Client c = clientDAO.findById(selectedId);
					if (c == null) {
						showStringError("No client with this id");
					} else {
						ClientBLL cl = new ClientBLL();
						try {
							cl.removeClient(c);
						} catch (Exception e1) {
							showStringError(e1.getMessage());
						}
						refreshClientsTable();
						refreshOrdersTable();
					}
				} else if (componentName.equals("Product")) {

					final int selectedId = (int) idComboBox.getSelectedItem();
					Product c = productDAO.findById(selectedId);
					if (c == null) {
						showStringError("No product with this id");
					} else {
						ProductBLL p = new ProductBLL();
						try {
							p.removeProduct(c);
						} catch (Exception e1) {
							showStringError(e1.getMessage());
						}
						refreshProductsTable();
						refreshOrdersTable();
					}
				} else if (componentName.equals("Order")) {
					final int selectedId = (int) idComboBox.getSelectedItem();
					Orders c = orderDAO.findById(selectedId);
					if (c == null) {
						showStringError("No order with this id");
					} else {
						OrderBLL p = new OrderBLL();
						try {
							p.deleteOrder(c);
						} catch (Exception e1) {
							showStringError(e1.getMessage());
						}
						refreshOrdersTable();
						refreshProductsTable();
					}
				}

				removeFrame.dispose();
			}
		});

		panel.add(removeButton, BorderLayout.SOUTH);

		removeFrame.getContentPane().add(panel);
		return removeFrame;
	}

	private JFrame createEditFrame(String componentName) {
		JFrame editFrame = new JFrame("Edit " + componentName);
		editFrame.setSize(300, 250);
		editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editFrame.setLocationRelativeTo(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel titleLabel = new JLabel("Edit " + componentName);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titleLabel, BorderLayout.NORTH);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(3, 2, 5, 10));

		JLabel idLabel = new JLabel("ID:");
		JComboBox<Integer> idComboBox = new JComboBox<>();
		idComboBox.setEditable(true);

		JLabel columnLabel = new JLabel("Column to edit:");
		JComboBox<String> columnComboBox = new JComboBox<>();

		JLabel valueLabel = new JLabel("New value:");
		JTextField valueField = new JTextField(10);

		formPanel.add(idLabel);
		formPanel.add(idComboBox);
		formPanel.add(columnLabel);
		formPanel.add(columnComboBox);
		formPanel.add(valueLabel);
		formPanel.add(valueField);

		panel.add(formPanel, BorderLayout.CENTER);
		ClientDAO clientDAO = new ClientDAO();
		ProductDAO productDAO = new ProductDAO();
		OrderDAO orderDAO = new OrderDAO();

		panel.add(formPanel, BorderLayout.CENTER);
		if (componentName.equals("Client")) {
			List<Client> list = clientDAO.findAll();
			DefaultComboBoxModel<Integer> idComboBoxModel = new DefaultComboBoxModel<>();
			idComboBox.setModel(idComboBoxModel);

			for (Client client : list) {
				idComboBoxModel.addElement((int) client.getId());
			}

			DefaultComboBoxModel<String> columnComboBoxModel = new DefaultComboBoxModel<>();
			columnComboBox.setModel(columnComboBoxModel);
			columnComboBoxModel.addElement("name");
			columnComboBoxModel.addElement("address");
			columnComboBoxModel.addElement("number");
		} else if (componentName.equals("Product")) {
			List<Product> list = productDAO.findAll();
			DefaultComboBoxModel<Integer> idComboBoxModel = new DefaultComboBoxModel<>();
			idComboBox.setModel(idComboBoxModel);

			for (Product product : list) {
				idComboBoxModel.addElement((int) product.getId());
			}

			DefaultComboBoxModel<String> columnComboBoxModel = new DefaultComboBoxModel<>();
			columnComboBox.setModel(columnComboBoxModel);
			columnComboBoxModel.addElement("name");
			columnComboBoxModel.addElement("price");
			columnComboBoxModel.addElement("quantity");
		} else if (componentName.equals("Order")) {
			List<Orders> list = orderDAO.findAll();
			DefaultComboBoxModel<Integer> idComboBoxModel = new DefaultComboBoxModel<>();
			idComboBox.setModel(idComboBoxModel);

			for (Orders order : list) {
				idComboBoxModel.addElement((int) order.getId());
			}
		}

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedId = (int) idComboBox.getSelectedItem();
				String column = (String) columnComboBox.getSelectedItem();
				String value = valueField.getText();

				if (selectedId == 0) {
					showStringError("Invalid id");
					return;
				}

				if (componentName.equals("Client")) {
					final int selectedId1 = (int) idComboBox.getSelectedItem();
					Client c = clientDAO.findById(selectedId1);
					if (c == null) {
						showStringError("No client with this id");
					} else {
						int t = 1;
						ClientBLL cl = new ClientBLL();
						if (column.equals("number")) {
							try {
								PhoneNumber phone = new PhoneNumber();
								phone.validate(new Client(c.getId(), null, null, value));
							} catch (Exception exx) {
								showStringError(exx.getMessage());
								t = 0;
							}
						}
						
						if (t == 1) {
							try {

								cl.updateClient(c, column, value);
							} catch (Exception e1) {
								showStringError(e1.getMessage());
							}
							refreshClientsTable();
							refreshOrdersTable();
						}
					}
				} else if (componentName.equals("Product")) {
					int t=0;
					ProductDAO productDAO = new ProductDAO();
					final int selectedId1 = (int) idComboBox.getSelectedItem();
					Product p = productDAO.findById(selectedId1);
					if (p == null) {
						showStringError("No product with this id");
						t=1;
					} 
					
					else {
						if(column.equals("quantity"))
						{
							try {
								int val=Integer.parseInt(value);
								
							}
							catch(Exception r)
							{
								t=1;
								showStringError("Invalid quantity!");
							}
						}
						if(column.equals("price"))
						{
							try {
								int val=Integer.parseInt(value);
								
							}
							catch(Exception r)
							{
								t=1;
								showStringError("Invalid price!");
							}
						}
						
					}
					if(t==0)
					{
						ProductBLL pb = new ProductBLL();
						try {
							pb.updateProduct(p, column, value);
						} catch (Exception e1) {
							showStringError(e1.getMessage());
						}
						refreshProductsTable();
						refreshOrdersTable();
					}
				}

				editFrame.dispose();
			}
		});

		panel.add(editButton, BorderLayout.SOUTH);

		editFrame.getContentPane().add(panel);
		return editFrame;
	}

	private void refreshClientsTable() {

		ProductDAO prod = new ProductDAO();
		productsTable.setModel(prod.getProductTable().getModel());
		ClientDAO clientDAO = new ClientDAO();
		clientsTable.setModel(clientDAO.getClientTable().getModel());
	}

	private void refreshProductsTable() {
		OrderDAO ord = new OrderDAO();
		ProductDAO prod = new ProductDAO();
		productsTable.setModel(prod.getProductTable().getModel());

		ordersTable.setModel(ord.getOrderTable().getModel());
	}

	private void refreshOrdersTable() {
		OrderDAO ord = new OrderDAO();
		ProductDAO prod = new ProductDAO();
		productsTable.setModel(prod.getProductTable().getModel());

		ordersTable.setModel(ord.getOrderTable().getModel());
	}

	public void showStringError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				First frame = new First();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
