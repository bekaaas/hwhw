import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(100);

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(1_000_000);
            String keyName = "key" + random.nextInt(1_000_000);

            MyTestingClass key = new MyTestingClass(id, keyName);

            String studentName = "Student" + i;
            double gpa = 1.0 + random.nextDouble() * 3.0;

            Student student = new Student(studentName, gpa);

            table.put(key, student);
        }

        System.out.println("Total elements: " + table.size());
        System.out.println();

        table.printBucketSizes();
    }
}
