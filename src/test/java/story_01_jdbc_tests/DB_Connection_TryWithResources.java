import java.sql.*;

public class DB_Connection_TryWithResources {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/orders_management", "postgres", "password")) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM customers";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("customer_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
