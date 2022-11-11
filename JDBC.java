import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBC {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Jukebox", "root", "admin");
        return connection;

    }

    public void SignUP() throws SQLException, ClassNotFoundException {
        // call getConnection() method
        Statement statement = getConnection().createStatement();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your name : ");
            String name = sc.nextLine();
            System.out.println("Welcome to JukeBox " + name);
            System.out.print("Enter username : ");
            String username = sc.nextLine();
            System.out.print("Enter Email : ");
            String email = sc.nextLine();
            System.out.print("Enter password : ");
            String password = sc.nextLine();
            String query = "INSERT INTO User (userName,Name,EmailAddress,Password) VALUES ('" + username + "','" + name
                    + "','" + email + "','" + password + "')";
            statement.executeUpdate(query);
        } catch (Exception c) {
            System.out.println(c.getMessage());
            System.out.println("Username is already exists: Smart People Think Same Way ");
        }
    }

    public String[] authenticate() throws Exception {
        String[] returnvalue = new String[2];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username : ");
        String username = sc.nextLine();
        returnvalue[1] = username;
        Console c = System.console();
        System.out.print("Enter password : ");
        char[] ch = c.readPassword();
        String password = String.valueOf(ch);
        String sql = "select userid,username, password from user where username='" + username + "'and password='"
                + password
                + "';";
        Statement statement = getConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            System.out.println("Login successfully ^_^");

            Interface interface1 = new Interface();
            interface1.displayinterface(username);
            System.out.println("Welcome back " + username);
            returnvalue[0] = "login successfully";
            return returnvalue;

        } else {
            returnvalue[0] = "Login failed";
            System.out.println(
                    "Try to login Again Maybe your username and password is incorrect (o_O)");
            return returnvalue;
        }

    }

}
