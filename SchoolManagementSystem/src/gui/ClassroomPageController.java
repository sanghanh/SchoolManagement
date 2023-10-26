package gui;

import dao.ClassroomDAO;
import dao.TeacherDAO;
import entity.Classroom;
import entity.Teacher;
import gui.models.ClassroomModel;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class ClassroomPageController implements Initializable {

    ClassroomDAO dao = new ClassroomDAO();
    TeacherDAO teacherDao = new TeacherDAO();
    ObservableList<ClassroomModel> classRooms = FXCollections.observableArrayList();
    
    @FXML
    private Button updateBtn;

    @FXML
    private TableView<ClassroomModel> table;

    @FXML
    private TableColumn<ClassroomModel, String> teacherName;

    @FXML
    private TableColumn<ClassroomModel, String> name;

    @FXML
    private TableColumn<ClassroomModel, Integer> id;
    
    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    void tableClicked(ActionEvent event) {

    }

    @FXML
    void addBtnClicked(ActionEvent event) throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("ClassroomPageDetail.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("ClassroomPage.fxml"));
        Parent homeFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Transferer.getInstance().putScene(homeFxml, PageTitle.HOME_PAGE);
        GUIUtils.openNewWindow(openFxml, "Thêm lớp học", parentFxml, PageTitle.CLASSROOM_PAGE);
    }

    @FXML
    void updateBtnClicked(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedIndex() > -1) {
            ClassroomModel model = table.getSelectionModel().getSelectedItem();
            Transferer.getInstance().passData(dao.getById(model.getId()));
            Parent openFxml = FXMLLoader.load(getClass().getResource("ClassroomPageUpdateDetail.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("ClassroomPage.fxml"));
            Parent homeFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Transferer.getInstance().putScene(homeFxml, PageTitle.HOME_PAGE);
            GUIUtils.openNewWindow(openFxml, "Sửa lớp học", parentFxml, PageTitle.CLASSROOM_PAGE);
        } else {
            GUIUtils.showFXPopup("Chưa chọn lớp học", "Một lỗi đã xảy ra", AlertType.ERROR);
        }
    }

    @FXML
    void deleteBtnClicked(ActionEvent event) {
        if (table.getSelectionModel().getSelectedIndex() > -1) {
            ClassroomModel selectClassroom = table.getSelectionModel().getSelectedItem();
            if(selectClassroom != null) {
                boolean rs = dao.delete(selectClassroom.getId());
                if(rs) {
                    getTableData();
                    GUIUtils.showFXPopup("Xóa thành công", "Thông báo", AlertType.INFORMATION);
                } else {
                    GUIUtils.showFXPopup("Không thể kết nối vào cơ sở dữ liệu", "Một lỗi đã xảy ra", AlertType.ERROR);
                }
            } else {
                GUIUtils.showFXPopup("Chưa chọn lớp học", "Một lỗi đã xảy ra", AlertType.ERROR);
            }
        } else {
            GUIUtils.showFXPopup("Chưa chọn lớp học", "Một lỗi đã xảy ra", AlertType.ERROR);
        }
    }

    @FXML
    void backBtnClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTableData();
    }

    private void getTableData() {
        List<Classroom> listClassroom = dao.getAll();
        List<ClassroomModel> listModel = new ArrayList<>();
        if(listClassroom != null && listClassroom.size() > 0) {
            for(Classroom classroom : listClassroom) {
                Teacher teacher = teacherDao.getById(classroom.getTeacherId());
                if(teacher == null) continue;
                listModel.add(new ClassroomModel(classroom.getId(), classroom.getName(), teacher.getFirstName() + " " + teacher.getLastName()));
            }
        }
        table.getItems().clear();
        if (listModel != null && listModel.size() > 0) {
            classRooms = FXCollections.observableArrayList(listModel);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        table.setItems(classRooms);
    }

}
