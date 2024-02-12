package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectionFactory;
import Start.Reflexion;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */


public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append(" * ");
		sb.append(" from ");
		sb.append(type.getSimpleName());
		sb.append(" where " + field + " =?");
		return sb.toString();
	}


	private String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append(" * ");
		sb.append(" from ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	public T findById(long id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setFloat(1, id);
			resultSet = statement.executeQuery();

			List<T> objects = createObjects(resultSet);
			if (!objects.isEmpty()) {
				return objects.get(0);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}


	private String createDeleteQuery(T t) {
		String result = Reflexion.delete(t);
		return result;
	}


	protected String insertIntoReflection(T t) {
		String result = Reflexion.retrieveProperties(t);
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append(t.getClass().getSimpleName());
		return sb + result;
	}


	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	 private String createSimpleSelectQuery(){
	        return "select " +
	                " * " +
	                " FROM " +
	                type.getSimpleName();
	    }
	public JTable getTable() {
	    String query = createSimpleSelectQuery();
	    try (Connection connection = ConnectionFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {

	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        DefaultTableModel tableModel = new DefaultTableModel();

	      
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
	        }

	        
	        while (resultSet.next()) {
	            Object[] row = new Object[columnCount];
	            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	                row[columnIndex - 1] = resultSet.getObject(columnIndex);
	            }
	            tableModel.addRow(row);
	        }

	        return new JTable(tableModel);
	    } catch (SQLException e) {
	        LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
	    }
	    return null;
	}


	

	private List<T> createObjects(ResultSet resultSet) {
	    List<T> list = new ArrayList<>();
	    try {
	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        while (resultSet.next()) {
	            T instance = type.getDeclaredConstructor().newInstance();
	            for (int i = 1; i <= columnCount; i++) {
	                String columnName = metaData.getColumnName(i);
	                Object value = resultSet.getObject(i);

	                Field field;
	                try {
	                    field = type.getDeclaredField(columnName);
	                } catch (NoSuchFieldException e) {
	                    // Property is a boolean with "is" prefix instead of "get"
	                    String methodName;
	                    if (value instanceof Boolean) {
	                        methodName = "get" + Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
	                    } else {
	                        methodName = "is" + Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
	                    }

	                    try {
	                        field = type.getDeclaredField(methodName);
	                    } catch (NoSuchFieldException ex) {
	                        LOGGER.log(Level.WARNING, "Field not found: " + columnName);
	                        continue;
	                    }
	                }

	                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
	                Method writeMethod = propertyDescriptor.getWriteMethod();
	                writeMethod.invoke(instance, value);
	            }
	            list.add(instance);
	        }
	    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
	            NoSuchMethodException | SQLException | IntrospectionException e) {
	        e.printStackTrace();
	    }
	    return list;
	}





	

	public void insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query;
		query = insertIntoReflection(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public void delete(T t) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createDeleteQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate(query);
		} catch (SQLException e) {
			throw new Exception("Can't be removed! this is in another table");
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public T update(T t, String col, String newValue) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.getConnection();
			String tableName = type.getSimpleName();
			String updateQuery = "UPDATE " + tableName + " SET " + col + " = ? WHERE id = ?";
			statement = connection.prepareStatement(updateQuery);

			statement.setString(1, newValue);

			Field idField = type.getDeclaredField("id");
			idField.setAccessible(true);
			Object idValue = idField.get(t);

			statement.setObject(2, idValue);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				return t;
			}
		} catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
}