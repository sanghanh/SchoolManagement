package gui;

import dao.StudentDAO;
import entity.Student;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class StudentPageController implements Initializable {

    StudentDAO dao = new StudentDAO();
    ObservableList<Student> students = FXCollections.observableArrayList();
    @FXML
    private Button updateBtn;

    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> firstName;

    @FXML
    private TableColumn<Student, String> lastName;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableColumn<Student, String> phone;
    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button searchBtn;
    
    @FXML
    private TextField searchText;

    
    @FXML
    void searchBtnClicked(ActionEvent event) {
        String search = searchText.getText().trim();
        if(search != null && search.length() > 0) {
            getTableData(dao.getListByLastName(search));
        } else {
            getTableData();
        }
    }
    
    @FXML
    void tableClicked(ActionEvent event) {

    }

    @FXML
    void addBtnClicked(ActionEvent event) throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("StudentPageDetail.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
        Parent homeFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Transferer.getInstance().putScene(homeFxml, PageTitle.HOME_PAGE);
        GUIUtils.openNewWindow(openFxml, "Thêm học sinh", parentFxml, PageTitle.COURSE_PAGE);
    }

    @FXML
    void updateBtnClicked(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedIndex() > -1) {
            Transferer.getInstance().passData(table.getSelectionModel().getSelectedItem());
            Parent openFxml = FXMLLoader.load(getClass().getResource("StudentPageUpdateDetail.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
            Parent homeFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Transferer.getInstance().putScene(homeFxml, PageTitle.HOME_PAGE);
            GUIUtils.openNewWindow(openFxml, "Sửa học sinh", parentFxml, PageTitle.COURSE_PAGE);
        } else {
            GUIUtils.showFXPopup("Chưa chọn học sinh", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void deleteBtnClicked(ActionEvent event) {
        if (table.getSelectionModel().getSelectedIndex() > -1) {
            Student selectStudent = table.getSelectionModel().getSelectedItem();
            if(selectStudent != null) {
                boolean rs = dao.delete(selectStudent.getId());
                if(rs) {
                    getTableData();
                    GUIUtils.showFXPopup("Xóa thành công", "Thông báo", Alert.AlertType.INFORMATION);
                } else {
                    GUIUtils.showFXPopup("Không thể kết nối vào cơ sở dữ liệu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
                }
            } else {
                GUIUtils.showFXPopup("Chưa chọn học sinh", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            }
        } else {
            GUIUtils.showFXPopup("Chưa chọn học sinh", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
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
        getTableData(dao.getAll());
    }
    
    private void getTableData(List<Student> listStudent) {
        if (listStudent != null && listStudent.size() > 0) {
            students = FXCollections.observableArrayList(listStudent);
        }
        table.getItems().clear();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        table.setItems(students);
    }
    
}
