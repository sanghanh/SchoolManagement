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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class ClassroomPageUpdateDetailController implements Initializable {

    TeacherDAO teacherDao = new TeacherDAO();
    ClassroomDAO classroomDao = new ClassroomDAO();
    Classroom currentClassroom;
    @FXML
    private ComboBox<String> cbTeacher;

    @FXML
    private Button backBtn;

    @FXML
    private TextField nameText;

    @FXML
    private TextField idText;

    @FXML
    private Button classroomBtn;

    @FXML
    void classroomBtnClicked(ActionEvent event) throws IOException {
        String name = nameText.getText().trim();
        String teacherStr = cbTeacher.getSelectionModel().getSelectedItem();
        if (name.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô tên lớp học", "Một lỗi đã xảy ra", AlertType.ERROR);
            return;
        }
        if (teacherStr.isEmpty()) {
            GUIUtils.showFXPopup("Chưa chọn 1 giáo viên", "Một lỗi đã xảy ra", AlertType.ERROR);
            return;
        }
        int teacherId = -1;
        try {
            teacherId = Integer.parseInt(teacherStr.split(" ")[0]);
        } catch (Exception e) {
            GUIUtils.showFXPopup("Không có giáo viên", "Một lỗi đã xảy ra", AlertType.ERROR);
            return;
        }
        Classroom classRoom = classroomDao.getByName(name);
        if (classRoom != null && classRoom.getId() != currentClassroom.getId()) {
            GUIUtils.showFXPopup("Đã tồn tại tên lớp học", "Một lỗi đã xảy ra", AlertType.ERROR);
            return;
        }
        currentClassroom.setName(name);
        currentClassroom.setTeacherId(teacherId);
        boolean rs = classroomDao.update(currentClassroom);
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
        getComboboxTeacher();
        getDetail();
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

    private void getDetail() {
        try {
            currentClassroom = (Classroom) Transferer.getInstance().getLastPassData();
            idText.setText(currentClassroom.getId() + "");
            nameText.setText(currentClassroom.getName());
            for (int i = 0; i < cbTeacher.getItems().size(); i++) {
                try {
                    int teacherId = Integer.parseInt(cbTeacher.getItems().get(i).split(" ")[0]);
                    if (teacherId == currentClassroom.getTeacherId()) {
                        cbTeacher.getSelectionModel().select(i);
                    }
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
