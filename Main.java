public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            table.put(new MyTestingClass(random.nextInt(100000)), "Value" + i);
        }

        for (int i = 0; i < 11; i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, String> head = table.chainArray[i];
            while (head != null) {
                count++;
                head = head.next;
            }
            System.out.println("Bucket " + i + " has " + count + " elements.");
        }
    }
}
