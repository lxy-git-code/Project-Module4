package InnerClass;

/**
 * @author LiXingyu
 * @date 2020-04-21 8:24 上午
 * 内部类：成员内部类、静态内部类、局部内部类、匿名内部类
 * 成员内部类：1.里面不能有静态代码块 2.可以访问外部类成员变量 3.内部类成员变量和外部类成员变量相同时，外部类变量 Outer.this.i
 * 静态内部类：1.只能访问外部类的类变量(包含静态方法、静态变量、其他静态内部类及其内部成员）
 * 局部内部类：1.可以访问其他成员内部类、及静态内部类、外部类成员
 * 成员内部类、静态内部类可以用4中访问权限修饰符及final、abstract
 * 局部内部类没有访问权限修饰符(和局部变量一样)但有final、abstract
 * 外部类只有两种访问权限修饰符public、默认及final、abstract
 *
 */
/*

class Outer {
private  int i=10;

    public class inner {
        {}
        int i = 0;
        void method(){
            i=Outer.this.i;
        }
    }

    void method(){
        inner inner = new inner();
        System.out.println(inner.i);
         class inner3{
            void method(){
                Outer.inner inner1 = new inner();
                System.out.println(inner2.a);
                new inner2().method();
                System.out.println(inner.i);
                System.out.println(i);
            }
        }
    }

    public static class inner2 {
      {}
        static int a = 10;
        void method(){
            new inner4().method4();
            System.out.println(i);
        }
    }

    public static class inner4{
        public void method4(){

        }
    }
}

public class Test {
    public static void main(String[] args) {
        Outer.inner inner = new Outer().new inner();
    }
}
*/
