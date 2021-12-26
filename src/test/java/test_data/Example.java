package test_data;

import java.io.File;

public class Example {
    private int var1 = 20;

    public static int method1() {
        System.out.println("abc");
        System.exit(1);
        new File("./data").length();
        return 1;
    }

    @Deprecated
    public int method2(int i, Integer ii) {
        int var2 = 10;
        System.out.println("abc");
        var2++;
        //z++;
        return var2 + var1;
    }

    private int method3() {
        return 3;
    }

    int method4() {
        return 3;
    }
}
