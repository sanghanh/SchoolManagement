package gui;

import dao.ClassroomDAO;
import dao.TeacherDAO;
import entity.Classroom;
import entity.Teacher;
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
import javafx.scene.control.TextField;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class ClassroomPageDetailController implements Initializable {

    TeacherDAO teacherDao = new TeacherDAO();
    ClassroomDAO classroomDao = new ClassroomDAO();
    @FXML
    private ComboBox<String> cbTeacher;

    @FXML
    private Button backBtn;

    @FXML
    private TextField nameText;

    @FXML
    private Button classroomBtn;

    @FXML
    void classroomBtnClicked(ActionEvent event) throws IOException {
        String name = nameText.getText().trim();
        String teacherStr = cbTeacher.getSelectionModel().getSelectedItem();
        if(name.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô tên lớp học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if(teacherStr.isEmpty()) {
            GUIUtils.showFXPopup("Chưa chọn 1 giáo viên", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        int teacherId = -1;
        try {
            teacherId = Integer.parseInt(teacherStr.split(" ")[0]);
        } catch (Exception e) {
            GUIUtils.showFXPopup("Không có giáo viên", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        Classroom classRoom = classroomDao.getByName(name);
        if(classRoom != null) {
            GUIUtils.showFXPopup("Đã tồn tại tên lớp học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        boolean rs = classroomDao.add(new Classroom(-1, name, teacherId));
        if (rs) {
            Parent openFxml = FXMLLoader.load(getClass().getResource("ClassroomPage.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
            GUIUtils.openNewWindow(openFxml, PageTitle.CLASSROOM_PAGE, parentFxml, PageTitle.HOME_PAGE);
        } else {
            GUIUtils.showFXPopup("Không kết nối được với cơ sở dữ liệu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void backBtnClicked(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getComboboxTeacher();
    }

    private void getComboboxTeacher() {
        cbTeacher.getItems().clear();
        List<Teacher> teachers = teacherDao.getAll();
        List<String> teacherNames = new ArrayList<>();
        if (teachers != null && teachers.size() > 0) {
            for (Teacher teacher : teachers) {
                teacherNames.add(teacher.getId() + " " + teacher.getFirstName() + " " + teacher.getLastName());
            }
            ObservableList<String> list = FXCollections.observableArrayList(teacherNames);
            cbTeacher.setItems(list);
            cbTeacher.getSelectionModel().select(0);
        }
    }

}
