import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();

        System.out.println("Enter your username: ");
        String user = reader.readLine();
        properties.getProperty(user);

        System.out.println("Enter your password: ");
        String password = reader.readLine();
        properties.getProperty(password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", user, password);

        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT first_name, last_name FROM employees WHERE salary > ?");

        System.out.println("Enter salary: ");
        String salary = reader.readLine();

        preparedStatement.setDouble(1, Double.parseDouble(salary));

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            System.out.println(firstName + " " + lastName);
        }
    }
}
