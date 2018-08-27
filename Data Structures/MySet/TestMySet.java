package MySet;


public class TestMySet {
    public static void main(String[] args) {
        MySet os1 = new MySet();
        MySet os2 = new MySet();
        MySet os3 = new MySet();

        os1.add(new Integer(0));
        os1.add(new Integer(2));
        os1.add(new Integer(5));

        os2.add(new Integer(5));
        os2.add(new Integer(0));
        os2.add(new Integer(2));
        os2.add(new Integer(5));

        os3.add(new Integer(7));
        os3.remove(new Integer(7));

        System.out.print("Set of os1 is: {");
        os1.print();
        System.out.println("}");

        System.out.print("Set of os2 is: {");
        os2.print();
        System.out.println("}");

        System.out.print("Set of os3 is: {");
        os3.print();
        System.out.println("}");

        if (os1.include(os3))
            System.out.println("os1 including os3");
        else
            System.out.println("os1 does not including os3");
        if (os1.include(os2))
            System.out.println("os1 including os2");
        else
            System.out.println("os1 does not including os2");

        if (os1.equals(os2))
            System.out.println("os1 is equal with os2");
        else
            System.out.println("os1 is not equal with os2");
    }
}
