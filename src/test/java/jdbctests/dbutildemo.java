package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutildemo {


    @Test
    public void test1(){

        //create the connection to db
        DBUtils.createConnection();

        //save the query result as a list of maps(just like we did together)
        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap("Select * from departments");

        //printing the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row);
        }

        //close connection
        DBUtils.destroy();
    }

    @Test
    public void test2(){

        //create the connection to db
        DBUtils.createConnection();
        String query = "Select * from employees where employee_id=101";

        //save the query result as a list of maps(just like we did together)
        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap);

        //close connection
        DBUtils.destroy();
    }



}
