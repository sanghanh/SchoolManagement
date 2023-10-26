package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class HomePageController implements Initializable {

    @FXML
    private Button enrollmentBtn;

    @FXML
    private Button courseManageBtn;

    @FXML
    private Button statisticBtn;

    @FXML
    private Button studentManageBtn;

    @FXML
    private Button classroomManageBtn;

    public void studentManageClicked() throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
        GUIUtils.openNewWindow(openFxml, PageTitle.STUDENT_PAGE, parentFxml, PageTitle.HOME_PAGE);
    }

    public void classroomClicked() throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("ClassroomPage.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
        GUIUtils.openNewWindow(openFxml, PageTitle.CLASSROOM_PAGE, parentFxml, PageTitle.HOME_PAGE);
    }

    public void courseClicked() throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("CoursePage.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
        GUIUtils.openNewWindow(openFxml, PageTitle.COURSE_PAGE, parentFxml, PageTitle.HOME_PAGE);
    }

    public void statisticClicked() throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("StatisticPage.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
        GUIUtils.openNewWindow(openFxml, PageTitle.STATISTIC_PAGE, parentFxml, PageTitle.HOME_PAGE);
    }

    public void enrollmentClicked() throws IOException {
        Parent openFxml = FXMLLoader.load(getClass().getResource("EnrollmentPage.fxml"));
        Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
        GUIUtils.openNewWindow(openFxml, PageTitle.ENROLLMENT_PAGE, parentFxml, PageTitle.HOME_PAGE);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
