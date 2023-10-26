package dao;

import entity.Teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBConnection;

public class TeacherDAO {

    private String TABLE_NAME = "Teacher";
    private String ATTR_1 = "Id";
    private String ATTR_2 = "FirstName";
    private String ATTR_3 = "LastName";
    private String ATTR_4 = "Email";
    private String ATTR_5 = "Phone";
    private String ATTR_6 = "Password";

    public List<Teacher> getAll(){
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Teacher> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                int id = rs.getInt(ATTR_1);
                String firstName = rs.getString(ATTR_2);
                String lastName = rs.getString(ATTR_3);
                String email = rs.getString(ATTR_4);
                String phone = rs.getString(ATTR_5);
                list.add(new Teacher(id, firstName, lastName, email, phone, ""));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public Teacher getById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String firstName = rs.getString(ATTR_2);
                String lastName = rs.getString(ATTR_3);
                String email = rs.getString(ATTR_4);
                String phone = rs.getString(ATTR_5);
                String password = rs.getString(ATTR_6);
                return new Teacher(id, firstName, lastName, email, phone, password);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }
    
    public Teacher login(String email, String password) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_4 + " = ? AND " + ATTR_6 + " = ?", email, password);
            if (rs.next()) {
                int id = rs.getInt(ATTR_1);
                String firstName = rs.getString(ATTR_2);
                String lastName = rs.getString(ATTR_3);
                String phone = rs.getString(ATTR_5);
                return new Teacher(id, firstName, lastName, email, phone, password);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public List<Teacher> getListById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Teacher> list = new ArrayList();
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            while (rs.next()) {
                String firstName = rs.getString(ATTR_2);
                String lastName = rs.getString(ATTR_3);
                String email = rs.getString(ATTR_4);
                String phone = rs.getString(ATTR_5);
                list.add(new Teacher(id, firstName, lastName, email, phone, ""));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return list;
    }

    public boolean add(Teacher teacher) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, teacher.getFirstName(),
                    teacher.getLastName(), teacher.getEmail(), teacher.getPhone(), teacher.getPassword());
            if (check > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            dbConn.closeConnection();
        }
        return false;
    }

    public boolean update(Teacher teacher) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                    + ATTR_4 + " = ? ," + ATTR_5 + " = ? ," + ATTR_6 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;", teacher.getFirstName(), teacher.getLastName()
                    , teacher.getEmail(), teacher.getPhone(), teacher.getPassword(), teacher.getId());
            if (check > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            dbConn.closeConnection();
        }
        return false;
    }

    public boolean delete(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Delete From " + TABLE_NAME + " "
                    + "WHERE " + ATTR_1 + " = ?;", id);
            if (check > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            dbConn.closeConnection();
        }
        return false;
    }
}
