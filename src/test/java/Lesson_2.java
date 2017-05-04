import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.annotations.*;

public class Lesson_2 {
    @DataProvider
    public Object[][] correctData(){
        return new Object[][]{{1, "rrr"},{2, "ttt"}};
    }
    @BeforeMethod
    public void before(){
        System.out.println("beforeMethod");
    }
    @AfterMethod
    public void after(){
        System.out.println("afterMethod");
    }
    @BeforeTest
    public void beforeT(){
        System.out.println("beforeT");
    }
    @AfterTest
    public void afterT(){
        System.out.println("afterT");
    }
    @Test(dataProvider = "correctData")
    public void test_3(int num, String name){
        System.out.println(num + " : " + name);
    }


    @Test(invocationCount = 10, threadPoolSize = 2)
    public void test_1(){
        System.out.println("test1" + hello());
    }
    @Test(expectedExceptions = ArithmeticException.class)
    public void test_2(){
        System.out.println("test2" + 0.0/0.0);
    }
    private static int id = 0;
    private String hello(){
        return "hello" + (id++) + " : " + Thread.currentThread().getId();
    }
}
