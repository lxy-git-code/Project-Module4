package com.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * @author LiXingyu
 * @date 2020-05-09 11:35 上午
 */
class employee {
    String name;
    int age;
    double salary;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

public class OgnlTest {
    public static void main(String[] args) throws OgnlException {
        OgnlContext ognlContext = new OgnlContext();

        ognlContext.put("employee1", new employee("Allen", 28, 7500.00));

        Object value = Ognl.getValue("#employee1.name.toString()", ognlContext, ognlContext.getRoot());

        System.out.println(value);

        Object value2 = Ognl.getValue("@java.lang.String@valueOf(#employee1.salary)", ognlContext, ognlContext.getRoot());
        System.out.println(value2);

        int[] ints = {1, 2, 3, 4, 5};

        ognlContext.put("ints", ints);

        Object value1 = Ognl.getValue("{'1','2','3','4','5'}.{$ #this > 2}", ognlContext, ognlContext.getRoot());

        System.out.println(value1);

        Object value3 = Ognl.getValue("#ints.{ #this}", ognlContext, ognlContext.getRoot());

        System.out.println(value3);

        Object value4 = Ognl.getValue("#{'key1':'value1'}['key1']", ognlContext, ognlContext.getRoot());

        System.out.println(value4);

    }
}
