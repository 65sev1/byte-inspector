package test_data;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

@SuppressWarnings("all")
@NotNull(value="TestClass1 annotation")
public class Example<GENERIC> {
    private int var1 = 10;

    @NotNull(value="TestClass1.var1 field annotation")
    private Integer var2 = 20;

    public static int method1() {
        System.out.println("abc");
        System.exit(1);
        new File("./data").length();
        return 1;
    }

    @NotNull(value="TestClass1.method2 annotation1")
    @Deprecated(since="TestClass1.method2 annotation2")
    public Integer method2(int i, @Deprecated(since = "TestClass1.method2 param annotation index 1") GENERIC ii, String str1, String str2, @Deprecated(since = "TestClass1.method2 param annotation index 4") String str3) {

        int var2 = 10;
        System.out.println("abc");
        var2++;
        ArrayList<GENERIC> list = new ArrayList<>();
        return var2 + var1;
    }

    @ApiStatus.NonExtendable
    public void method3(@Deprecated(since = "TestClass1.method3 param annotation index 0") String str1, @NotNull String str2) {
        GENERIC gen;
    }

    public static void main(String[] args) {
        new Example<TestClass2>();
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
