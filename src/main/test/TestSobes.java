import org.junit.Ignore;
import org.junit.Test;

public class TestSobes {

    @Test
    @Ignore
    public void test1() {
        Integer i = 1;
        inc(i);
        System.out.println(i);
    }

    private static void inc(Integer i) {
        i++;
    }

    // ------------ Что выведет данный тест? -------------
    @Test
    @Ignore
    public void test2() {
        String i = "1";
        inc(i);
        System.out.println(i);
    }

    private static void inc(String s) {
        s = s + "2";
    }

    // ------------ Что выведет данный тест? -------------
    @Test
    @Ignore
    public void test3() {
        Integer i1 = 717;
        Integer i2 = Integer.valueOf(717);
        System.out.println(i1 == i2);
    }

    // ------------ Что выведет данный тест? -------------
    @Test
    @Ignore
    public void test4() {
        System.out.println(show(10));
        System.out.println(show(11));
    }

    private static Integer show(Integer i) {
        return (i % 2 == 0 ? null : i + 1);
    }

}
