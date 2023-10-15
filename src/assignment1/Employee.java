package assignment1;

public class Employee {
    private String name;

    public String getName() {
        return name;
    }

    public long getAddTime() {
        return addTime;
    }

    private long addTime;

    public Employee(String name) {
        this.name = name;
        this.addTime = System.currentTimeMillis();
    }

    public int getVacationDays(){
        return 20;
    }
}
