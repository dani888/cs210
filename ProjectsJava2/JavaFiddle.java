import java.util.*;

public class JavaFiddle {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        // Iterator<String> it = list1.iterator();
        // it.next();
        // it.remove();

        System.out.println(list1.iterator().next());
        System.out.println(list1.listIterator().next());
        System.out.println(list1.listIterator(2).next());
        System.out.println(list1.listIterator(4).previous());
        // list1.iterator().next();
        // list1.iterator().remove();
        // System.out.println(list1);
        // list1.listIterator().remove();
        // System.out.println(list1);
        // list1.listIterator(2).remove();
        // System.out.println(list1);
        // list1.listIterator(4).remove();
        // System.out.println(list1.listIterator(2).next());
        // list1.listIterator(2).remove();
        // System.out.println(list1.listIterator(4).previous());

        System.out.println(list1);
    }
}









