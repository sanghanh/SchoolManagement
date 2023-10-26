package gui.models;

public class EnrollmentModel {
    private int id;
    private String studentName;
    private String courseName;
    private String classroomName;
    private String score;

    public EnrollmentModel(int id, String StudentName, String CourseName, String classroomName, String score) {
        this.id = id;
        this.studentName = StudentName;
        this.courseName = CourseName;
        this.classroomName = classroomName;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String StudentName) {
        this.studentName = StudentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String CourseName) {
        this.courseName = CourseName;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    
    
}
