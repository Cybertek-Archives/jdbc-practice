package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jdbc_utilexample {
    //connection string
    String dbUrl = "jdbc:oracle:thin:@54.161.128.36:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void metadata() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");


        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many column we have ?
        System.out.println("Column count:"+rsMetadata.getColumnCount());

        //column names
       // System.out.println(rsMetadata.getColumnName(8));

        //print all the column names dynamically
        int columnCount = rsMetadata.getColumnCount();

        for (int i = 1; i <=columnCount; i++) {
            //System.out.println(rsMetadata.getColumnName(i));

        }

        //creating list for keeping rows
        List<Map<String,Object>> queryData = new ArrayList<>();


        Map<String,Object> row1 = new HashMap<>();

        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRESS");

        System.out.println(row1.toString());

        // get the first
        System.out.println(row1.get("first_name"));

        //--------------get row 2
        Map<String,Object> row2 = new HashMap<>();

        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        System.out.println();
        System.out.println("Neena Salary: "+queryData.get(1).get("salary"));
        System.out.println("Steven JobId: "+queryData.get(0).get("job_id"));





        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void queryresult() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id \n" +
                "from employees\n" +
                "where rownum<=5");


        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();



        //creating list for keeping rows
        List<Map<String,Object>> queryData = new ArrayList<>();
        Map<String,Object> row1 = new HashMap<>();

        resultSet.next();

        row1.put(rsMetadata.getColumnName(1),resultSet.getObject(1));
        row1.put(rsMetadata.getColumnName(2),resultSet.getObject(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getObject(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getObject(4));

        System.out.println(row1.toString());

        // get the first
        System.out.println(row1.get("first_name"));

        //--------------get row 2
        Map<String,Object> row2 = new HashMap<>();
        resultSet.next();
        row2.put(rsMetadata.getColumnName(1),resultSet.getObject(1));
        row2.put(rsMetadata.getColumnName(2),resultSet.getObject(2));
        row2.put(rsMetadata.getColumnName(3),resultSet.getObject(3));
        row2.put(rsMetadata.getColumnName(4),resultSet.getObject(4));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);


        System.out.println(queryData.get(0).toString());
        System.out.println(queryData.get(1).toString());






        resultSet.close();
        statement.close();
        connection.close();
    }


}
