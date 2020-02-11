package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        //connection string
        String dbUrl = "jdbc:oracle:thin:@54.161.128.36:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create connection to database
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement
        Statement statement = connection.createStatement();
        //run query and get the result in result object
        ResultSet resultSet = statement.executeQuery("select * from employees");


        //move pointer to next row
        resultSet.next();
       System.out.println(resultSet.getString("region_name"));


        while(resultSet.next()){
            System.out.println(resultSet.getString(2)+"-"+
                    resultSet.getString(3)+"-"+
                    resultSet.getInt("salary")) ;
        }



        //close connection
        resultSet.close();
        statement.close();
        connection.close();

        // BREAK 2:10


    }
}
