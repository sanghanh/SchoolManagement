package gui;

import dao.TeacherDAO;
import entity.Teacher;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.GUIUtils;
import utils.PageTitle;

public class RegisterPageController implements Initializable {
    
    TeacherDAO dao = new TeacherDAO();
    @FXML
    private TextField firstNameText;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField phoneText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField emailText;

    @FXML
    void registerBtnClicked(ActionEvent event) throws IOException {
        String firstName = firstNameText.getText().trim();
        String lastName = lastNameText.getText().trim();
        String email = emailText.getText().trim();
        String phone = phoneText.getText().trim();
        String password = passwordText.getText().trim();
        if(lastName.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô tên", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if(email.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô email", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if(phone.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô số điện thoại", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if(password.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô mật khẩu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if(!email.contains("@")) {
            GUIUtils.showFXPopup("Nhập sai định dạng địa chỉ email", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
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
        boolean rs = dao.add(new Teacher(-1, firstName, lastName, email, phone, password));
        if (rs) {
            Parent openFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            GUIUtils.openNewWindow(openFxml, PageTitle.STUDENT_PAGE, null, null);
        } else {
            GUIUtils.showFXPopup("Không kết nối được với cơ sở dữ liệu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
