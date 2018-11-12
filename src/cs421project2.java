
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.HashMap;

public class cs421project2 {

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList list;
        HashMap<String, LinkedList> map = new HashMap<>();

        File file = new File("words.txt");
        Scanner in = new Scanner(file);

        String numberPad;
        while (in.hasNext()) {
            String next = in.next().toLowerCase();
            if (next.length() != 3 && next.length() != 4 && next.length() != 6
                    && next.length() != 7 && next.length() != 10) {
                continue;
            }
            numberPad = toNumberPad(next);
            System.out.println(numberPad);

            if (map.get(numberPad) == null) {
                LinkedList newList = new LinkedList();
                newList.add(next);
                map.put(numberPad, newList);
            } else {
                list = map.get(numberPad);
                list.add(next);
                map.put(numberPad, list);
            }
        }
        System.out.println(map.size());

        while (true) {
            in = new Scanner(System.in);
            System.out.print("Enter a 10 digit phone number: ");
            String number = in.next();
            // Find words here
        }
    }

    private static String toNumberPad(String s) {
        String numbers = "";
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'a':
                case 'b':
                case 'c':
                    numbers += '2';
                    break;
                case 'd':
                case 'e':
                case 'f':
                    numbers += '3';
                    break;
                case 'g':
                case 'h':
                case 'i':
                    numbers += '4';
                    break;
                case 'j':
                case 'k':
                case 'l':
                    numbers += '5';
                    break;
                case 'm':
                case 'n':
                case 'o':
                    numbers += '6';
                    break;
                case 'p':
                case 'q':
                case 'r':
                case 's':
                    numbers += '7';
                    break;
                case 't':
                case 'u':
                case 'v':
                    numbers += '8';
                    break;
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    numbers += '9';
                    break;
            }
        }
        return numbers;
    }

    private void findWords(String number) {
        // need to write this
    }
}