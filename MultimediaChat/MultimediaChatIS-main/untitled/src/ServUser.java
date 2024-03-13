import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServUser {
    private String INSERT = "insert into users (username,email,password,pic) values (?,?,?,?)";
    protected static String ISINTABLE = "select username,password from users where username=?";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private String SELECTALL = "select * from users";

    public ServUser() {
        this.connection = DBConnection.getConnection();

    }

    public String register(UserModel data) throws SQLException {
        String m = new String();
        boolean continuee = true;
        try {
            PreparedStatement check = connection.prepareStatement(ISINTABLE);
            check.setString(1, data.getUserName());
            System.out.println(data.getUserName());
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                m = "User Already Exists!";
                continuee = false;

            }
            rs.close();
            check.close();


        } catch (SQLException e) {
            System.out.println("Error in SQL statement");
            e.printStackTrace();
        }
        if (continuee) {
            try {
                System.out.println("CEVA");
                PreparedStatement insert = connection.prepareStatement(INSERT);
                insert.setString(1, data.getUserName());
                insert.setString(2, data.getEmail());
                insert.setString(3, data.getPassword());
                insert.setBytes(4, data.getPic());
                insert.executeUpdate();
                insert.close();


            } catch (SQLException e) {
                System.out.println("ERROR in sql statement");
            }
        }
        this.printTable();
        return "";
    }

    public byte[] printTable() throws SQLException {
        PreparedStatement select = connection.prepareStatement(SELECTALL);
        ResultSet rs = select.executeQuery();
        byte pic1[]=null;
        while (rs.next()) {
            String userName = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            byte[] pic = rs.getBytes("pic");

            System.out.println(userName + " " + password + " " + email + " " + pic);
            pic1=pic;
        }
        return pic1;
    }
}

