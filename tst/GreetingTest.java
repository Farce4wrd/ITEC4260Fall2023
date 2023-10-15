import assignment1.Greeting;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GreetingTest {

    @Test
    public void testHighestGreeting(){
        Greeting a = new Greeting(20);
        Greeting b = new Greeting(30);
        Greeting c = new Greeting(90);
        List<Greeting> myGreetings = new ArrayList<>();
        myGreetings.add(a);
        myGreetings.add(b);
        myGreetings.add(c);
        System.out.println(getHighestGreeting(myGreetings));
        Assert.assertEquals("This should return the highest id", 90, getHighestGreeting(myGreetings).getId());
    }

    public Greeting getHighestGreeting(List<Greeting> list){
        if(list == null){
            return null;
        }
        Greeting highestGreeting = list.get(0);
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getId() > highestGreeting.getId()){
                highestGreeting = list.get(i);
            }
        }
        return highestGreeting;
    }
}
