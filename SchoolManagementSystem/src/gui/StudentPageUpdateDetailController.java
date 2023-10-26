package gui;

import dao.StudentDAO;
import entity.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class StudentPageUpdateDetailController implements Initializable {

    StudentDAO studentDao = new StudentDAO();
    Student currentStudent;
    @FXML
    private TextField firstNameText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField idText;

    @FXML
    private Button classroomBtn;

    @FXML
    void classroomBtnClicked(ActionEvent event) throws IOException {
        String firstName = firstNameText.getText().trim();
        String lastName = lastNameText.getText().trim();
        String phone = phoneText.getText().trim();
        if(lastName.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô tên", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if(phone.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô số điện thoại", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        try {
            Integer.parseInt(phone);
        } catch(Exception e) {
            GUIUtils.showFXPopup("Số điện thoại phải là số", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if(phone.length() < 10 || phone.length() > 12) {
            GUIUtils.showFXPopup("Số điện thoại phải trong khoảng ( 10 - 12 kí tự )", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setPhone(phone);
        boolean rs = studentDao.update(currentStudent);
        if (rs) {
            Parent openFxml = FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
            GUIUtils.openNewWindow(openFxml, PageTitle.STUDENT_PAGE, parentFxml, PageTitle.HOME_PAGE);
        } else {
            GUIUtils.showFXPopup("Không kết nối được với cơ sở dữ liệu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getDetail();
    }    
    private void getDetail() {
        try {
            currentStudent = (Student) Transferer.getInstance().getLastPassData();
            idText.setText(currentStudent.getId() + "");
            firstNameText.setText(currentStudent.getFirstName());
            lastNameText.setText(currentStudent.getLastName());
            emailText.setText(currentStudent.getEmail());
            phoneText.setText(currentStudent.getPhone());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    } 
    
}
