/**
 * Created by ming on 2015/11/15.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
        threadLocal.get();

    }
}
