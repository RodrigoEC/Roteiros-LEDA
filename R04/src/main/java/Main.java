import orderStatistic.OrderStatisticsSelectionImpl;
import problems.FloorCeilBinarySearch;

public class Main {
    public static void main(String[] args) {
        Integer[] i = {1,4,5,8,9};

        OrderStatisticsSelectionImpl<Integer> o = new OrderStatisticsSelectionImpl<>();

        FloorCeilBinarySearch f = new FloorCeilBinarySearch();

        System.out.println(f.ceil(i, 8));


    }
}
