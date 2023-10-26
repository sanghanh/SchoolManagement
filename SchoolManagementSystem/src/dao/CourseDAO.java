package dao;

import entity.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBConnection;

public class CourseDAO {
    private String TABLE_NAME = "Course";
    private String ATTR_1 = "Id";
    private String ATTR_2 = "Name";
    private String ATTR_3 = "Description";

    public List<Course> getAll() {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Course> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                int id = rs.getInt(ATTR_1);
                String name = rs.getString(ATTR_2);
                String description = rs.getString(ATTR_3);
                list.add(new Course(id, name, description));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public Course getById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String name = rs.getString(ATTR_2);
                String description = rs.getString(ATTR_3);
                return new Course(id, name, description);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }
    
    public Course getByName(String name) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_2 + " = ?", name);
            if (rs.next()) {
                int id = rs.getInt(ATTR_1);
                String description = rs.getString(ATTR_3);
                return new Course(id, name, description);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public List<Course> getListById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Course> list = new ArrayList();
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            while (rs.next()) {
                String name = rs.getString(ATTR_2);
                String description = rs.getString(ATTR_3);
                list.add(new Course(id, name, description));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return list;
    }

    public boolean add(Course course) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, course.getName(),
                    course.getDescription());
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

    public boolean update(Course course) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;", course.getName(), course.getDescription()
                    ,course.getId());
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
