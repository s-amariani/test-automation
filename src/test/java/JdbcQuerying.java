import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.*;
public class JdbcQuerying extends DatabaseConnection {
    @Test
    public void jdbcQuerying(){

        SoftAssert softAssert = new SoftAssert();

        String insertIntoStudentsStatement = "INSERT INTO students (id,firstName,lastName,phone) VALUES (?,?,?,?)";
        String updateStatement = "update students set firstName = ? where id = ?";

        try  {
            pstmt = conn.prepareStatement(insertIntoStudentsStatement,Statement.RETURN_GENERATED_KEYS);
            //Use autosaved false mode
            conn.setAutoCommit(false);

            //Insert in database students new row
            int id = 1009;
            String firstName = "Userfirstname";
            String lastName = "Userlastname";
            String phone = "599111111";

            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, phone);



            int rowAffected = pstmt.executeUpdate();

            //Validate that row wasn't created
            int insertedId = 0;
            ResultSet resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()){
                insertedId = resultSet.getInt(1);
            }
            softAssert.assertEquals(insertedId,0);


            if (rowAffected ==1){
                System.out.println("Row created,RowAffected = 1");
            }

            //Call commit()
            conn.commit();

            //Validate all the values of inserted row using TestNG
            try {
                Statement selectStatement = conn.createStatement();
                ResultSet selectResultSet = selectStatement.executeQuery("SELECT TOP 1 * FROM students ORDER BY Id DESC");


                while (selectResultSet.next()){

                    int getid = selectResultSet.getInt("id");
                    String getfName = selectResultSet.getString("firstName");
                    String getlName = selectResultSet.getString("lastName");
                    String getphone = selectResultSet.getString("phone");
                    System.out.println(getid +" : "+ getfName + "---" + getlName + "---"+ getphone);


                    softAssert.assertEquals(selectResultSet.getInt("id"),id);
                    softAssert.assertEquals(selectResultSet.getString("firstName"),firstName);
                    softAssert.assertEquals(selectResultSet.getString("lastName"),lastName);
                    softAssert.assertEquals(selectResultSet.getString("phone"),phone);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Update firstName of added student
            String newUserName = "newUserName";
            PreparedStatement pstmtUpdate = conn.prepareStatement(updateStatement);
            pstmtUpdate.setString(1,newUserName);
            pstmtUpdate.setInt(2,id);

            pstmtUpdate.executeUpdate();
            conn.commit();

            //Validate updated firstName using TestNG
            try {
                Statement updateNewUserNameStatement = conn.createStatement();
                ResultSet updatedUserNameSet = updateNewUserNameStatement.executeQuery("SELECT TOP 1 * FROM students ORDER BY Id DESC");
                while (updatedUserNameSet.next()){
                    String updatedUserName = updatedUserNameSet.getString("firstName");
                    softAssert.assertEquals(updatedUserName,newUserName);
                }
            }
            catch (SQLException e){
                System.out.println("Exception");
            }



            softAssert.assertAll();
        }

        catch (SQLException e) {
            //e.printStackTrace();
            if (conn != null) {
                try {
                    System.out.println("\nTransaction is being rolled back");
                    conn.rollback();
                } catch (SQLException exception) {
                    System.out.println("Catch sql exception");
                }
            }
        }
    }
}
