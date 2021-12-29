import Project.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class MenuTest {
    Cinema cinema;
    Menu menu;
    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("SUCCEEDED->>"+description.getClassName()+"| METHOD--> "+description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("FAILED->>"+description.getClassName()+"| METHOD--> "+description.getMethodName());
            System.out.println(e.fillInStackTrace());
        }
    };
    @Before
    public void beforeTest(){
        cinema = new Cinema(new TimeTest(12,11),new TimeTest(23,22));
        menu = new Menu(cinema);
    }
    @After
    public void afterTestMenu(){
        cinema  = null;
        menu= null;
    }
    @Test
    public void addSeance2Test() throws InterruptedException {
        cinema.addSeance2("monday",22,13 ,"rer",2,34);
    }
    @Test
    public void addMovieTest() throws InterruptedException {
        menu.addMovie(new Movie("rer",new TimeTest(2,34)));
    }

    @Test
    public void removeMovieTest() throws InterruptedException {
        menu.removeMovie("rer");
    }
}
