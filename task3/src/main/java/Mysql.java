import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Mysql {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/fintech";
    private static final String user = "fintech";
    private static final String password = "123456";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public ArrayList<PersonInfo> GetPersonInfo (int randomCount) {
        String query = "select fname,lname,patronymic,city,street,gender,date,postcode,house,apartment from testInfo LIMIT " + randomCount;
        ArrayList<PersonInfo> personInfos = new ArrayList<PersonInfo>();

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String patronymic = rs.getString(3);
                String city = rs.getString(4);
                String street = rs.getString(5);
                String gender = rs.getString(6);
                Date date = rs.getDate(7);
                String postcode = rs.getString(8);
                int house = rs.getInt(9);
                int apartment = rs.getInt(10);
                TestInfoFromApi testInfo = new TestInfoFromApi (fname, lname, patronymic, city, street,
                        gender, date, postcode, house, apartment);
                personInfos.add (new PersonInfo (testInfo));
            }

        }catch (IOException sqlEx) {
            sqlEx.printStackTrace();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return personInfos;
    }

    public void InsertPersonInfo (ArrayList<PersonInfo> personInfos) {
        try {
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query

            for (Iterator<PersonInfo> i = personInfos.iterator(); i.hasNext();) {
                PersonInfo person = i.next();
                String query = "INSERT INTO fintech.testInfo (fname,lname,patronymic,city,street,gender,date,postcode,house,apartment) \n" +
                        " VALUES (" + "'" + person.getFName() + "', " + "'" + person.getLName() + "', " + "'" + person.getPatronymic() + "', " +
                        "'" + person.getCity() + "', " + "'" + person.getStreet() + "', "  + "'" + person.getSex() + "', " +
                        "'" + "2010-01-01"  + "', " + "'" + person.getIndex()  + "', " + "'" + person.getHouse() + "', " + "'" + person.getApartment() + "'"+ ");";
                //System.out.println (query);
                stmt.executeUpdate(query);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }

        }
    }

}
