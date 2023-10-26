package dao;

import entity.Classroom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBConnection;

public class ClassroomDAO {

    private String TABLE_NAME = "Classroom";
    private String ATTR_1 = "Id";
    private String ATTR_2 = "Name";
    private String ATTR_3 = "TeacherId";

    public List<Classroom> getAll() {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Classroom> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                int id = rs.getInt(ATTR_1);
                String name = rs.getString(ATTR_2);
                int teacherId = rs.getInt(ATTR_3);
                list.add(new Classroom(id, name, teacherId));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }
    
    public Classroom getByName(String name) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_2 + " = ?", name);
            if (rs.next()) {
                int id = rs.getInt(ATTR_1);
                int teacherId = rs.getInt(ATTR_3);
                return new Classroom(id, name, teacherId);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public Classroom getById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String name = rs.getString(ATTR_2);
                int teacherId = rs.getInt(ATTR_3);
                return new Classroom(id, name, teacherId);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public List<Classroom> getListById(int id) {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Classroom> list = new ArrayList();
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            while (rs.next()) {
                String name = rs.getString(ATTR_2);
                int teacherId = rs.getInt(ATTR_3);
                list.add(new Classroom(id, name, teacherId));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbConn.closeConnection();
        }
        return list;
    }

    public boolean add(Classroom classroom) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, classroom.getName(),
                    classroom.getTeacherId());
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

    public boolean update(Classroom classroom) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;", classroom.getName(), classroom.getTeacherId(), 
                    classroom.getId());
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
