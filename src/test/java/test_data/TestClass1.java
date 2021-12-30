package test_data;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

@NotNull(value="TestClass1 annotation")
public class TestClass1<GENERIC> {
    @NotNull(value="TestClass1.var1 field annotation")
    private int var1 = 20;

    public static int method1() {
        System.out.println("abc");
        System.exit(1);
        new File("./data").length();
        return 1;
    }

    @NotNull(value="TestClass1.method2 annotation1")
    @Deprecated(since="TestClass1.method2 annotation2")
    public int method2(int i, @Deprecated(since = "TestClass1.method2 param annotation") GENERIC ii, String str1, String str2, String str3) {

        int var2 = 10;
        System.out.println("abc");
        var2++;
        ArrayList<GENERIC> list = new ArrayList<>();
        return var2 + var1;
    }

    public void method3(@NotNull String str) {
        GENERIC gen;
    }

    public static void main(String[] args) {
        new TestClass1<TestClass2>();
    }

    @Deprecated(since = "class annotation TestClass2")
    public static class TestClass2 {
        @Deprecated(since = "TestClass2.field annotation")
        public String field1;
        public int field2 = 1;

        @Deprecated(since = "TestClass2.method1 annotation")
        public int method1(@Deprecated(since = "TestClass2.method1 param annotation") int as) {
            @Deprecated(since = "TestClass2.method1 field var2 annotation")
            int var2 = 10;
            System.out.println("abc");
            var2++;
            return var2 + field2;
        }
    }
}
