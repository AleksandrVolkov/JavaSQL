import com.example.sweater.Users;

import java.sql.*;

public class JavaSQL {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static String url = "jdbc:postgresql://localhost:2020/postgres";
    private static String username = "postgres";
    private static String password = "1234";
    private static String sql;

    public static void createTable(Connection conn) {
        try {
            sql = "CREATE TABLE USERS"
                    + "("
                    + " ID serial,"
                    + " FIRST_NAME TEXT NOT NULL,"
                    + " LAST_NAME TEXT NOT NULL,"
                    + " LOGIN TEXT NOT NULL,"
                    + " PRIMARY KEY (ID)"
                    + ")";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            pstmt.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void insertQuery(Connection conn, String fName, String lName, String login) {
        try {
            String sql = "INSERT INTO USERS (FIRST_NAME,LAST_NAME,LOGIN) VALUES (?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setString(3, login);

            pstmt.executeUpdate();
            pstmt.close();

        } catch (Exception ex) {
            System.out.println(ex);

        }

    }

    public static void removeQuery(Connection conn, int id) {
        try {
            sql = "DELETE from USERS where ID=?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    public static void selectQuery(Connection conn) {
        try {
            String sql = "SELECT * FROM USERS";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String fName = resultSet.getString("FIRST_NAME");
                String lName = resultSet.getString("LAST_NAME");
                String login = resultSet.getString("LOGIN");

                Users users = new Users(id, fName, lName, login);
                print(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void print(Users users) {
        System.out.printf("ID - %d ,FIRST_NAME - %s , LAST_NAME - %s , LOGIN - %s  \n", users.getId(), users.getfName(), users.getlName(), users.getLogin());
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//            createTable(conn);
            System.out.println('\n');


            selectQuery(conn);
//            insertQuery(conn, "Alex1", "Petrov", "Logon_2008");
            removeQuery(conn, 4);
            System.out.println('\n');
            selectQuery(conn);
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
}
