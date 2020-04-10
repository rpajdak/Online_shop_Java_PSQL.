package DAO;

import Model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDatabaseDAO implements AdminDAO {
    private String url = "jdbc:postgresql://localhost:5432/online_shop";
    private String user = "dariusz";
    private String password = "polska";
    private List<Admin> AdminList;


    @Override
    public void getAllAdmins() {


        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM user_table");
             ResultSet rs = pst.executeQuery()) {
            int attributesNumber = rs.getMetaData().getColumnCount();
            AdminList = new ArrayList<>();
            String[] adminAttributes = new String[attributesNumber];
            while (rs.next()) {
            for(int index = 0;index < attributesNumber; index++){
                adminAttributes[index] = rs.getString(index+1);
            }
            Admin admin = new Admin(adminAttributes);
            AdminList.add(admin);
            con.close();
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(AdminDatabaseDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }


    @Override
    public void addAdmin() {

    }

    @Override
    public void updateAdmin() {

    }

    @Override
    public void deleteAdmin() {

    }

    @Override
    public List<Admin> getAdminList() {
        return AdminList;
    }
}
