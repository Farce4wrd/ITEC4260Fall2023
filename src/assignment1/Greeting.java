package assignment1;

import java.util.List;

public class Greeting {
    private int id;
    private String content;
    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent(){
        return content;
    }
    public Greeting(int id){
        this.id = id;
    }


}
