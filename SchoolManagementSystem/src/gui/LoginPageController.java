package gui;

import dao.TeacherDAO;
import entity.Teacher;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import repository.AccountRepository;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class LoginPageController implements Initializable {

    @FXML
    Button loginBtn;
    @FXML
    Button registerBtn;
    @FXML
    TextField emailText;
    @FXML
    PasswordField passwordText;
    TeacherDAO dao = new TeacherDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Transferer.getInstance().clearQueue();
    }

    public void loginAction() throws IOException {
        String email = emailText.getText().trim();
        String password = passwordText.getText().trim();
        Teacher teacher = dao.login(email, password);
        if (teacher != null) {
            AccountRepository.GetInstance().setCurrentTeacher(teacher);
            Parent openFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            GUIUtils.openNewWindow(openFxml, PageTitle.HOME_PAGE, parentFxml, PageTitle.LOGIN_PAGE);
        } else {
            GUIUtils.showFXPopup("Sai email hoặc mật khẩu", "Một lỗi đã xảy ra", AlertType.ERROR);
        }
    }
    
    public void registerAction() throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        GUIUtils.openNewWindow(openFxml, PageTitle.REGISTER_PAGE, parentFxml, PageTitle.LOGIN_PAGE);
    }
}
