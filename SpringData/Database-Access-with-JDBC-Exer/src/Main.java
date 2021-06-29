import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Main {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {

        connection = getConnection();

        System.out.println("Enter exercise number: ");
        int exerciseNumber = Integer.parseInt(reader.readLine());

        switch (exerciseNumber) {
            case 2 -> exerTwo();
            case 3 -> exerThree();
           // case 4 -> exerFour();
            case 5 -> exerFive();
           // case 6 -> exerSix();
            case 7 -> exerSeven();
            case 8 -> exerEight();
        }
    }

    private static void exerEight() throws IOException, SQLException {
        System.out.println("Enter minions id separated by space: ");
        String input = reader.readLine();

        String[] minionsToIncrease = input.split("\\s+");

        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE minions m SET m.age = m.age + 1, m.name = LOWER(m.name) WHERE id = ?;");

        for (int i = 0; i < minionsToIncrease.length; i++) {
            int minionId = Integer.parseInt(minionsToIncrease[i]);
            preparedStatement.setInt(1, minionId);
            preparedStatement.executeUpdate();
        }

        PreparedStatement stmt = connection
                .prepareStatement("SELECT m.name, m.age FROM minions m;");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " +  resultSet.getInt("age"));
        }
        System.out.println();
    }

    private static void exerSeven() throws SQLException {
        ArrayDeque<String> minions = new ArrayDeque<>();
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT m.name FROM minions m;");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            minions.add(resultSet.getString("name"));
        }
        while (!minions.isEmpty()) {
            System.out.println(minions.pollFirst());
            System.out.println(minions.pollLast());
        }
    }

//    private static void exerSix() {
//
//    }

    private static void exerFive() throws IOException, SQLException {
        System.out.println("Enter country name: ");
        String countryName = reader.readLine();

        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE towns SET name = UPPER(name) WHERE country = ?;");

        preparedStatement.setString(1, countryName);

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated == 0) {
            System.out.println("No town names were affected.");
            return;
        }
        System.out.printf("%d town names were affected.%n", rowsUpdated);

        PreparedStatement preparedStatementTowns = connection
                .prepareStatement("SELECT name FROM towns WHERE country = ?");

        preparedStatementTowns.setString(1, countryName);

        ResultSet resultSet = preparedStatementTowns.executeQuery();

        List<String> towns = new ArrayList<>();
        while (resultSet.next()) {
            towns.add(resultSet.getString("name"));
        }
        System.out.println(towns);
    }

//    private static void exerFour() throws IOException, SQLException {
//        System.out.println("Enter info -> Minion name, age, town: ");
//        String[] input = reader.readLine().split(": ");
//
//        String[] minionInfo = input[1].split(" ");
//        String minionName = minionInfo[0];
//        int minionAge = Integer.parseInt(minionInfo[1]);
//        String minionTown = minionInfo[2];
//
//       int townId = getEntityIdByName(minionTown, "towns");
//
//        System.out.println(townId);
//
//    }

//    private static int getEntityIdByName(String entityName, String tableName) throws SQLException {
//        String query = String.format("SELECT id FROM %s WHERE name ?", tableName);
//
//        PreparedStatement preparedStatement = connection
//                .prepareStatement(query);
//        preparedStatement.setString(1, entityName);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        return resultSet.next() ? resultSet.getInt(1) : -1;
//
//    }

    private static void exerThree() throws SQLException, IOException {

        System.out.println("Enter villain id: ");
        int villainId = Integer.parseInt(reader.readLine());
        if(villainId < 1 || villainId > 6) {
            System.out.println("No villain with ID " + villainId + " exists in the database.");
            return;
        }

        String villainName = findVillainNameById(villainId);
        System.out.println("Villain: " + villainName);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT m.name, m.age FROM villains v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id " +
                "JOIN minions m on m.id = mv.minion_id " +
                "WHERE villain_id = ? " +
                "GROUP BY v.name, m.name;");
        preparedStatement.setInt(1, villainId);


        ResultSet resultSet = preparedStatement.executeQuery();

        int count = 0;
        while (resultSet.next()) {
            count++;
            System.out.printf("%d. %s %d %n", count,
                    resultSet.getString("name"),
                    resultSet.getInt("age"));
        }

    }

    private static String findVillainNameById(int villainId) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT name FROM villains WHERE id = ?;");

        preparedStatement.setInt(1, villainId);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("name");

    }

    private static void exerTwo() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT v.name, COUNT(DISTINCT mv.minion_id) count " +
                "FROM villains v " +
                "JOIN minions_villains mv on v.id = mv.villain_id " +
                "GROUP BY villain_id " +
                "HAVING count > ? " +
                "ORDER BY count DESC;");

        preparedStatement.setInt(1,15);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.printf("%s %d %n", resultSet.getString(1), resultSet.getInt(2));
        }
    }


    private static Connection getConnection() throws IOException, SQLException {
        System.out.println("Enter your username: ");
        String user = Main.reader.readLine();
        System.out.println("Enter your password: ");
        String password = Main.reader.readLine();

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        return DriverManager
                .getConnection(CONNECTION_STRING + DATABASE_NAME, properties);

    }
}
