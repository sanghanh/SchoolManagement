package gui;

import dao.ClassroomDAO;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import entity.Classroom;
import entity.Course;
import entity.Enrollments;
import entity.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class EnrollmentPageDetailController implements Initializable {

    EnrollmentDAO enrollmentDao = new EnrollmentDAO();
    StudentDAO studentDAO = new StudentDAO();
    ClassroomDAO classroomDao = new ClassroomDAO();
    CourseDAO courseDao = new CourseDAO();
    @FXML
    private Button enrollmentBtn;

    @FXML
    private ComboBox<String> cbCourse;

    @FXML
    private ComboBox<String> cbStudent;

    @FXML
    private ComboBox<String> cbClassroom;

    @FXML
    void enrollmentBtnClicked(ActionEvent event) throws IOException {
        String courseStr = cbCourse.getSelectionModel().getSelectedItem();
        String classroomStr = cbClassroom.getSelectionModel().getSelectedItem();
        String studentStr = cbStudent.getSelectionModel().getSelectedItem();
        if (courseStr.isEmpty()) {
            GUIUtils.showFXPopup("Chưa chọn môn học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if (classroomStr.isEmpty()) {
            GUIUtils.showFXPopup("Chưa chọn lớp học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if (studentStr.isEmpty()) {
            GUIUtils.showFXPopup("Chưa chọn học sinh", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        float scoreFloat = 0;
        int classroomId = -1;
        try {
            classroomId = Integer.parseInt(classroomStr.split(" ")[0]);
        } catch (Exception e) {
            GUIUtils.showFXPopup("Không có lớp học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        int courseId = -1;
        try {
            courseId = Integer.parseInt(courseStr.split(" ")[0]);
        } catch (Exception e) {
            GUIUtils.showFXPopup("Không có môn học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        int studentId = -1;
        try {
            studentId = Integer.parseInt(studentStr.split(" ")[0]);
        } catch (Exception e) {
            GUIUtils.showFXPopup("Không có học sinh", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }

        boolean rs = enrollmentDao.add(new Enrollments(-1, studentId, courseId, classroomId, scoreFloat));
        if (rs) {
            Parent openFxml = FXMLLoader.load(getClass().getResource("EnrollmentPage.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
            GUIUtils.openNewWindow(openFxml, PageTitle.ENROLLMENT_PAGE, parentFxml, PageTitle.HOME_PAGE);
        } else {
            GUIUtils.showFXPopup("Không kết nối được với cơ sở dữ liệu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getComboboxClassroom();
        getComboboxCourse();
        getComboboxStudent();
    }

    private void getComboboxStudent() {
        cbStudent.getItems().clear();
        List<Student> students = studentDAO.getAll();
        List<String> studentNames = new ArrayList<>();
        if (students != null && students.size() > 0) {
            for (Student student : students) {
                studentNames.add(student.getId() + " " + student.getFirstName() + " " + student.getLastName());
            }
            ObservableList<String> list = FXCollections.observableArrayList(studentNames);
            cbStudent.setItems(list);
            cbStudent.getSelectionModel().select(0);
        }
    }

    private void getComboboxClassroom() {
        cbClassroom.getItems().clear();
        List<Classroom> classrooms = classroomDao.getAll();
        List<String> classroomNames = new ArrayList<>();
        if (classrooms != null && classrooms.size() > 0) {
            for (Classroom classroom : classrooms) {
                classroomNames.add(classroom.getId() + " " + classroom.getName());
            }
            ObservableList<String> list = FXCollections.observableArrayList(classroomNames);
            cbClassroom.setItems(list);
            cbClassroom.getSelectionModel().select(0);
        }
    }

    private void getComboboxCourse() {
        cbCourse.getItems().clear();
        List<Course> courses = courseDao.getAll();
        List<String> courseNames = new ArrayList<>();
        if (courses != null && courses.size() > 0) {
            for (Course course : courses) {
                courseNames.add(course.getId() + " " + course.getName());
            }
            ObservableList<String> list = FXCollections.observableArrayList(courseNames);
            cbCourse.setItems(list);
            cbCourse.getSelectionModel().select(0);
        }
    }

}
