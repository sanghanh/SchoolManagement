package utils;

import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.Parent;

public class Transferer {
    public static Transferer getInstance() {
        if(instance == null) {
            instance = new Transferer();
        }
        return instance;
    }
    
    private static Transferer instance;
    private Queue<Object> datas = new LinkedList<>();
    private Queue<Parent> scenes = new LinkedList<>();
    private Queue<String> titles = new LinkedList<>();
    
    public void passData(Object data) {
        datas.add(data);
    }
    
    public Object getLastPassData() {
        return datas.poll();
    }
    
    public void putScene(Parent parent, String title) {
        scenes.add(parent);
        titles.add(title);
    }
    
    public Parent getLastScene() {
        return scenes.poll();
    }
    public String getLastTitle() {
        return titles.poll();
    }
    
    public void clearQueue() {
        datas.clear();
        titles.clear();
        scenes.clear();
    }
}
