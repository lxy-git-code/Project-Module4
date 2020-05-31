package InnerClass;

/**
 * @author LiXingyu
 * @date 2020-04-21 9:25 上午
 */
interface Inter{
    void show();
}
class Outers{
    public static Inter method(){
        return new Inter() {
            @Override
            public void show() {
                System.out.println("hello world");
            }
        };
    };
}
public class  OuterDemo {
    public static void main(String[] args) {
        Outers.method().show();
    }
}
