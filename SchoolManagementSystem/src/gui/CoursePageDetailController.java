package gui;

import dao.CourseDAO;
import entity.Course;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utils.GUIUtils;
import utils.PageTitle;
import utils.Transferer;

public class CoursePageDetailController implements Initializable {

    CourseDAO courseDao = new CourseDAO();

    @FXML
    private TextField nameText;

    @FXML
    private Button classroomBtn;
    
    @FXML
    private TextArea descriptionText;

    @FXML
    void classroomBtnClicked(ActionEvent event) throws IOException {
        String name = nameText.getText().trim();
        String description = descriptionText.getText().trim();
        if(name.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô tên lớp học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        if(description.isEmpty()) {
            GUIUtils.showFXPopup("Không được để trống ô mô tả", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        Course course = courseDao.getByName(name);
        if(course != null) {
            GUIUtils.showFXPopup("Đã tồn tại tên môn học", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
            return;
        }
        boolean rs = courseDao.add(new Course(-1, name, description));
        if (rs) {
            Parent openFxml = FXMLLoader.load(getClass().getResource("CoursePage.fxml"));
            Parent parentFxml = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Parent loginFxml = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Transferer.getInstance().putScene(loginFxml, PageTitle.LOGIN_PAGE);
            GUIUtils.openNewWindow(openFxml, PageTitle.CLASSROOM_PAGE, parentFxml, PageTitle.HOME_PAGE);
        } else {
            GUIUtils.showFXPopup("Không kết nối được với cơ sở dữ liệu", "Một lỗi đã xảy ra", Alert.AlertType.ERROR);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
