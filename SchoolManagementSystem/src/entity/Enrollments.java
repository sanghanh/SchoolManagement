package entity;

public class Enrollments {
    private int id;
    private int studentId;
    private int courseId;
    private int classroomId;
    private Float score;

    public Enrollments(int id, int studentId, int courseId, int classroomId, Float score) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.score = score;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }
    
    
}
