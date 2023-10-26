package repository;

import entity.Teacher;

public class AccountRepository {
    private static AccountRepository instace;
    public static AccountRepository GetInstance() {
        if(instace == null) {
            instace = new AccountRepository();
        }
        return instace;
    }
    
    private Teacher currentTeacher;
    private AccountRepository() {
        
    }

    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }

    public void setCurrentTeacher(Teacher currentTeacher) {
        this.currentTeacher = currentTeacher;
    }
    
}
