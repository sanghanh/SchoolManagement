package gui.models;

public class ClassroomStatistic {
    private String classroomName;
    private String courseName;
    private String studentName;
    private String rank;

    public ClassroomStatistic(String classroomName, String courseName, String studentName, String rank) {
        this.classroomName = classroomName;
        this.courseName = courseName;
        this.studentName = studentName;
        this.rank = rank;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    
}
