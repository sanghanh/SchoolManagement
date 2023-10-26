package gui;

import dao.ClassroomDAO;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import entity.Classroom;
import entity.Course;
import entity.Enrollments;
import entity.Student;
import gui.models.ClassroomStatistic;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatisticPageController implements Initializable {

    ObservableList<ClassroomStatistic> classroomStatistics = FXCollections.observableArrayList();
    CourseDAO courseDao = new CourseDAO();
    StudentDAO studentDao = new StudentDAO();
    ClassroomDAO classroomDao = new ClassroomDAO();
    EnrollmentDAO enrollmentDao = new EnrollmentDAO();
    @FXML
    private ComboBox<String> cbRank;

    @FXML
    private TextField searchText;

    @FXML
    private Button searchBtn;

    @FXML
    private TableColumn<ClassroomStatistic, String> classroomName;

    @FXML
    private TableColumn<ClassroomStatistic, String> studentName;

    @FXML
    private TableColumn<ClassroomStatistic, String> courseName;

    @FXML
    private TableColumn<ClassroomStatistic, String> rank;

    @FXML
    private TableView<ClassroomStatistic> table;

    @FXML
    void searchBtnClicked(ActionEvent event) {
        getTableData();
        String search = searchText.getText().trim();
        String rank = cbRank.getSelectionModel().getSelectedItem();
        if(!search.isEmpty() && !rank.equals("Tất cả")){
            List<ClassroomStatistic> statistics = new ArrayList();
            for(ClassroomStatistic statistic : classroomStatistics) {
                if(statistic.getClassroomName().toLowerCase().contains(search.toLowerCase()) 
                        && statistic.getRank().equals(rank)) {
                    statistics.add(statistic);
                }
            }
            getTableData(statistics);
        } else if (!search.isEmpty()) {
            List<ClassroomStatistic> statistics = new ArrayList();
            for(ClassroomStatistic statistic : classroomStatistics) {
                if(statistic.getClassroomName().toLowerCase().contains(search.toLowerCase())) {
                    statistics.add(statistic);
                }
            }
            getTableData(statistics);
        } else if(!rank.equals("Tất cả")){
            List<ClassroomStatistic> statistics = new ArrayList();
            for(ClassroomStatistic statistic : classroomStatistics) {
                if(statistic.getRank().equals(rank)) {
                    statistics.add(statistic);
                }
            }
            getTableData(statistics);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> ranks = new ArrayList<String>();
        ranks.add("Tất cả");
        ranks.add("Yếu");
        ranks.add("Trung bình");
        ranks.add("Khá");
        ranks.add("Giỏi");
        cbRank.setItems(FXCollections.observableArrayList(ranks));
        cbRank.getSelectionModel().select(0);
        getTableData();
    }

    private void getTableData() {
        List<Enrollments> listEnrollment = enrollmentDao.getAll();
        List<ClassroomStatistic> listModel = new ArrayList<>();
        if (listEnrollment != null && listEnrollment.size() > 0) {
            for (Enrollments enrollment : listEnrollment) {
                if (enrollment.getScore() > 0) {
                    Course course = courseDao.getById(enrollment.getCourseId());
                    if (course == null) {
                        continue;
                    }
                    Classroom classroom = classroomDao.getById(enrollment.getClassroomId());
                    if (classroom == null) {
                        continue;
                    }
                    Student student = studentDao.getById(enrollment.getStudentId());
                    if (student == null) {
                        continue;
                    }
                    String rank = "";
                    if (enrollment.getScore() >= 8) {
                        rank = "Giỏi";
                    } else if (enrollment.getScore() >= 6) {
                        rank = "Khá";
                    } else if (enrollment.getScore() >= 4) {
                        rank = "Trung bình";
                    } else {
                        rank = "Yếu";
                    }
                    listModel.add(new ClassroomStatistic(classroom.getName(), course.getName(), student.getFirstName() + " " + student.getLastName(), rank));
                }
            }
        }
        getTableData(listModel);
    }

    private void getTableData(List<ClassroomStatistic> listStudent) {
        if (listStudent != null && listStudent.size() > 0) {
            classroomStatistics = FXCollections.observableArrayList(listStudent);
        }
        table.getItems().clear();
        classroomName.setCellValueFactory(new PropertyValueFactory<>("classroomName"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        table.setItems(classroomStatistics);
    }

}
