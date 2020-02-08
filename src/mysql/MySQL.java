package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test")) {

            System.out.println(conn.isClosed());

            insert(conn, "Majra", "1234");
            update(conn, "DINA", "987", 4);
            select(conn);
           

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public static void insert(Connection con, String name, String password) throws SQLException {

        PreparedStatement prp = con.prepareStatement("insert into test.users (name, password) values (?,?)");
        prp.setString(1, name);
        prp.setString(2, password);
        prp.execute();

    }

    public static void update(Connection con, String name, String password, int id) throws SQLException {

        PreparedStatement prp = con.prepareStatement("update users set name=?, password=? where user_id=?");
        prp.setString(1, name);
        prp.setString(2, password);
        prp.setInt(3, id);
        prp.execute();

    }

    public static void select(Connection con) throws SQLException {

        Statement st = con.createStatement();
        st.execute("select * from users");
        
        ResultSet rs =st.getResultSet();
        while(rs.next()){
        System.out.println("Imena "+rs.getString("name") + "passwordi " + rs.getString("password"));
        
        }
        
            
        }
     
       
    }
    

