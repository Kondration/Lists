public class Main {
    public static void main(String[] args) {
        MyArrayList arrayList = new MyArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");

        for (int i = 0; i < arrayList.filledCapacity(); i++) {
            System.out.println(arrayList.get(i));
        }

        arrayList.delete(3);
        System.out.println();

        for (int i = 0; i < arrayList.filledCapacity(); i++) {
            System.out.println(arrayList.get(i));
        }
    }
}
