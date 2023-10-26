package dao;

import entity.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBConnection;

public class StudentDAO {
    private String TABLE_NAME = "Student";
    private String ATTR_1 = "Id";
    private String ATTR_2 = "FirstName";
    private String ATTR_3 = "LastName";
    private String ATTR_4 = "Email";
    private String ATTR_5 = "Phone";

    public List<Student> getAll() {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Student> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                int id = rs.getInt(ATTR_1);
                String firstName = rs.getString(ATTR_2);
                String lastName = rs.getString(ATTR_3);
                String email = rs.getString(ATTR_4);
                String phone = rs.getString(ATTR_5);
                list.add(new Student(id, firstName, lastName, email, phone));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public Student getById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String firstName = rs.getString(ATTR_2);
                String lastName = rs.getString(ATTR_3);
                String email = rs.getString(ATTR_4);
                String phone = rs.getString(ATTR_5);
                return new Student(id, firstName, lastName, email, phone);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }
    
    public List<Student> getListById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Student> list = new ArrayList();
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            while (rs.next()) {
                String firstName = rs.getString(ATTR_2);
                String lastName = rs.getString(ATTR_3);
                String email = rs.getString(ATTR_4);
                String phone = rs.getString(ATTR_5);
                list.add(new Student(id, firstName, lastName, email, phone));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return list;
    }
    
    public List<Student> getListByLastName(String lastName) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Student> list = new ArrayList();
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_3 + " LIKE N'%" + lastName + "%'");
            while (rs.next()) {
                int id = rs.getInt(ATTR_1);
                String firstName = rs.getString(ATTR_2);
                String lastNameDb = rs.getString(ATTR_3);
                String email = rs.getString(ATTR_4);
                String phone = rs.getString(ATTR_5);
                list.add(new Student(id, firstName, lastNameDb, email, phone));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return list;
    }

    public boolean add(Student student) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, student.getFirstName(),
                    student.getLastName(), student.getEmail(), student.getPhone());
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

    public boolean update(Student student) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                    + ATTR_4 + " = ? ," + ATTR_5 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;", student.getFirstName(), student.getLastName()
                    , student.getEmail(), student.getPhone(), student.getId());
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
