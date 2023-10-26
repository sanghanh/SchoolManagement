package gui;

import dao.CourseDAO;
import entity.Course;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class CoursePageController implements Initializable {
    
    CourseDAO dao = new CourseDAO();
    ObservableList<Course> courses = FXCollections.observableArrayList();
    @FXML
    private Button updateBtn;

    @FXML
    private TableView<Course> table;

    @FXML
    private TableColumn<Course, String> description;

    @FXML
    private TableColumn<Course, String> name;

    @FXML
    private TableColumn<Course, Integer> id;
    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    void tableClicked(ActionEvent event) {

    }

    @FXML
    void addBtnClicked(ActionEvent event) throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("CoursePageDetail.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("CoursePage.fxml"));
        Parent homeFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Transferer.getInstance().putScene(homeFxml, PageTitle.HOME_PAGE);
        GUIUtils.openNewWindow(openFxml, "Thêm môn học", parentFxml, PageTitle.COURSE_PAGE);
    }

    @FXML
    void updateBtnClicked(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedIndex() > -1) {
            Transferer.getInstance().passData(table.getSelectionModel().getSelectedItem());
            Parent openFxml = FXMLLoader.load(getClass().getResource("CoursePageUpdateDetail.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("CoursePage.fxml"));
            Parent homeFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Transferer.getInstance().putScene(homeFxml, PageTitle.HOME_PAGE);
            GUIUtils.openNewWindow(openFxml, "Sửa môn học", parentFxml, PageTitle.COURSE_PAGE);
        } else {
            GUIUtils.showFXPopup("Chưa chọn môn học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void deleteBtnClicked(ActionEvent event) {
        if (table.getSelectionModel().getSelectedIndex() > -1) {
            Course selectCourse = table.getSelectionModel().getSelectedItem();
            if(selectCourse != null) {
                boolean rs = dao.delete(selectCourse.getId());
                if(rs) {
                    getTableData();
                    GUIUtils.showFXPopup("Xóa thành công", "Thông báo", Alert.AlertType.INFORMATION);
                } else {
                    GUIUtils.showFXPopup("Không thể kết nối vào cơ sở dữ liệu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
                }
            } else {
                GUIUtils.showFXPopup("Chưa chọn môn học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            }
        } else {
            GUIUtils.showFXPopup("Chưa chọn môn học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
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
        List<Course> classrooms = dao.getAll();
        table.getItems().clear();
        if (classrooms != null && classrooms.size() > 0) {
            courses = FXCollections.observableArrayList(classrooms);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(courses);
    }  
    
}
