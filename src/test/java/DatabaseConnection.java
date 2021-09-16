import org.testng.annotations.*;

import java.sql.*;

// create a connection to the database
public class DatabaseConnection {
    Connection conn = null;
    // for insert
    PreparedStatement pstmt = null;

    // for getting  id
    ResultSet rs = null;

    @BeforeMethod
    public void connectSqlServer(){

        try{
            String dbURL = "jdbc:sqlserver://SERGI-PC\\MSSQLSERVER;databaseName=students";
            String user = "sa";
            String pass = "sa";
            conn = DriverManager.getConnection(dbURL, user, pass);

            //Use autosaved false mode
            //conn.setAutoCommit(false);

            if (conn != null) {

                /*DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());*/
                System.out.println("Connected to SQL Server\n");
            }
        }
        catch (SQLException e){
            System.out.println("Cannot connect to Database server");
        }
    }

    @AfterMethod
    public void closingDB(){
        if (rs != null) {
            try {
                System.out.println("Closing Result Set!");
                rs.close();
            } catch (SQLException e) { e.printStackTrace();}
        }
        if (pstmt != null) {
            try {
                System.out.println("Closing Prepared Statement!");
                pstmt.close();
            } catch (SQLException e) { e.printStackTrace();}
        }
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Closing SQL Server Connection!");
            } catch (SQLException e) { e.printStackTrace();}
        }
    }
}
