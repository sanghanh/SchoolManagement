package dao;

import entity.Enrollments;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBConnection;

public class EnrollmentDAO {

    private String TABLE_NAME = "Enrollments";
    private String ATTR_1 = "Id";
    private String ATTR_2 = "StudentID";
    private String ATTR_3 = "CourseId";
    private String ATTR_4 = "ClassroomId";
    private String ATTR_5 = "Score";

    public List<Enrollments> getAll() {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Enrollments> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                int id = rs.getInt(ATTR_1);
                int studentId = rs.getInt(ATTR_2);
                int courseId = rs.getInt(ATTR_3);
                int classroomId = rs.getInt(ATTR_4);
                Float score = rs.getFloat(ATTR_5);
                list.add(new Enrollments(id, studentId, courseId, classroomId, score));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EnrollmentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public Enrollments getById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                int studentId = rs.getInt(ATTR_2);
                int courseId = rs.getInt(ATTR_3);
                int classroomId = rs.getInt(ATTR_4);
                Float score = rs.getFloat(ATTR_5);
                return new Enrollments(id, studentId, courseId, classroomId, score);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EnrollmentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }
    
    public List<Enrollments> getListById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Enrollments> list = new ArrayList();
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            while (rs.next()) {
                int studentId = rs.getInt(ATTR_2);
                int courseId = rs.getInt(ATTR_3);
                int classroomId = rs.getInt(ATTR_4);
                Float score = rs.getFloat(ATTR_5);
                list.add(new Enrollments(id, studentId, courseId, classroomId, score));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EnrollmentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return list;
    }

    public boolean add(Enrollments enrollment) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, enrollment.getStudentId(),
                    enrollment.getCourseId(), enrollment.getClassroomId(), enrollment.getScore());
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

    public boolean update(Enrollments enrollment) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                    + ATTR_4 + " = ? ," + ATTR_5 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;", enrollment.getStudentId(), enrollment.getCourseId()
                    , enrollment.getClassroomId(), enrollment.getScore(), enrollment.getId());
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
