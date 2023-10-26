package gui;

import dao.ClassroomDAO;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import entity.Classroom;
import entity.Course;
import entity.Enrollments;
import entity.Student;
import gui.models.EnrollmentModel;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class EnrollmentPageController implements Initializable {

    EnrollmentDAO enrollmentDao = new EnrollmentDAO();
    StudentDAO studentDao = new StudentDAO();
    CourseDAO courseDao = new CourseDAO();
    ClassroomDAO classroomDao = new ClassroomDAO();
    ObservableList<EnrollmentModel> enrollments = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<EnrollmentModel, String> score;

    @FXML
    private TableColumn<EnrollmentModel, String> courseName;
    
    @FXML
    private TableColumn<EnrollmentModel, Integer> id;
    
    @FXML
    private TableColumn<EnrollmentModel, String> studentName;

    @FXML
    private TableColumn<EnrollmentModel, String> classroomName;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField searchText;

    @FXML
    private Button searchBtn;


    @FXML
    private TableView<EnrollmentModel> table;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;
    
    @FXML
    void searchBtnClicked(ActionEvent event) {
        String search = searchText.getText().trim();
        if(search != null && search.length() > 0) {
            List<EnrollmentModel> listModel = new ArrayList<>();
            if(enrollments.size() > 0) {
                for(EnrollmentModel enrollmentModel : enrollments) {
                    if(enrollmentModel.getClassroomName().toLowerCase().contains(search.toLowerCase())) {
                        listModel.add(enrollmentModel);
                    }
                }
                getTableData(listModel);
            }
        } else {
            getTableData();
        }
    }

    @FXML
    void addBtnClicked(ActionEvent event) throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("EnrollmentPageDetail.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("EnrollmentPage.fxml"));
        Parent homeFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Transferer.getInstance().putScene(homeFxml, PageTitle.HOME_PAGE);
        GUIUtils.openNewWindow(openFxml, "Thêm phiếu đăng ký", parentFxml, PageTitle.ENROLLMENT_PAGE);
    }

    @FXML
    void updateBtnClicked(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedIndex() > -1) {
            EnrollmentModel model = table.getSelectionModel().getSelectedItem();
            Transferer.getInstance().passData(enrollmentDao.getById(model.getId()));
            Parent openFxml = FXMLLoader.load(getClass().getResource("EnrollmentPageUpdateDetail.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("EnrollmentPage.fxml"));
            Parent homeFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Transferer.getInstance().putScene(homeFxml, PageTitle.HOME_PAGE);
            GUIUtils.openNewWindow(openFxml, "Sửa học sinh", parentFxml, PageTitle.ENROLLMENT_PAGE);
        } else {
            GUIUtils.showFXPopup("Chưa chọn phiếu đăng ký", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void deleteBtnClicked(ActionEvent event) {
        if (table.getSelectionModel().getSelectedIndex() > -1) {
            EnrollmentModel selectEnrollmentModel = table.getSelectionModel().getSelectedItem();
            if (selectEnrollmentModel != null) {
                boolean rs = enrollmentDao.delete(selectEnrollmentModel.getId());
                if (rs) {
                    getTableData();
                    GUIUtils.showFXPopup("Xóa thành công", "Thông báo", Alert.AlertType.INFORMATION);
                } else {
                    GUIUtils.showFXPopup("Không thể kết nối vào cơ sở dữ liệu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
                }
            } else {
                GUIUtils.showFXPopup("Chưa chọn phiếu đăng ký", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            }
        } else {
            GUIUtils.showFXPopup("Chưa chọn phiếu đăng ký", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTableData();
    }

    private void getTableData() {
        List<Enrollments> listEnrollment = enrollmentDao.getAll();
        List<EnrollmentModel> listModel = new ArrayList<>();
        if(listEnrollment != null && listEnrollment.size() > 0) {
            for(Enrollments enrollment : listEnrollment) {
                Course course = courseDao.getById(enrollment.getCourseId());
                if(course == null) continue;
                Classroom classroom = classroomDao.getById(enrollment.getClassroomId());
                if(classroom == null) continue;
                Student student = studentDao.getById(enrollment.getStudentId());
                if(student == null) continue;
                String score = "Chưa có điểm";
                if(enrollment.getScore() > 0) {
                    score = enrollment.getScore().toString();
                }
                listModel.add(new EnrollmentModel(enrollment.getId(), student.getFirstName() + " " + student.getLastName(), course.getName(), classroom.getName(), score));
            }
        }
        getTableData(listModel);
    }

    private void getTableData(List<EnrollmentModel> listStudent) {
        if (listStudent != null && listStudent.size() > 0) {
            enrollments = FXCollections.observableArrayList(listStudent);
        }
        table.getItems().clear();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        classroomName.setCellValueFactory(new PropertyValueFactory<>("classroomName"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        table.setItems(enrollments);
    }

}
