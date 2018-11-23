
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;

public class cs421project2 {

    static HashMap<String, LinkedList> map = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList list;

        File file = new File("words.txt");
        Scanner in = new Scanner(file);

        String numberPad;
        while (in.hasNext()) {
            String next = in.next().toLowerCase();
            if (next.length() != 3 && next.length() != 4 && next.length() != 7 && next.length() != 10) {
                continue;
            }
            numberPad = toNumberPad(next);

            if (map.get(numberPad) == null) {
                LinkedList newList = new LinkedList();
                newList.add(next);
                map.put(numberPad, newList);
            } else {
                list = map.get(numberPad);
                list.add(next);
            }
        }
        while (true) {
            in = new Scanner(System.in);
            System.out.print("Enter a 10 digit phone number (or type exit to quit): ");
            String number = in.next();
            if (number.length() != 10) {
                if(number.equalsIgnoreCase("exit")) {
                    System.out.println("Good-bye!");
                    System.exit(0);
                }
                System.out.println("Invalid phone number");
                continue;
            }
            if (!isOnlyNumbers(number)) {
                System.out.println("Invalid phone number");
                continue;
            }
            findWords(number);

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

    private static void findWords(String number) {
        LinkedList<String> list;
        /*
        1) If there is only word-based representation of whole 7 digits
        2) Else If there is only word-based representation of exchange (3 digit) and number (4 digit)
        3) Else If there is word-based representation of only exchange (3 digit)
        4) Else If there is word-based representation of only number (4 digit)
        5) Else there is no word-based representation given number.
         */
        if (!isOnlyNumbers(number)) {
            return;
        }

        if (map.containsKey(number)) {
            list = map.get(number);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("1-" + list.get(i).toUpperCase());
            }
        } else if (map.containsKey(number.substring(3))) {
            list = map.get(number.substring(3));
            for (int i = 0; i < list.size(); i++) {
                System.out.println("1-" + number.substring(0, 3) + "-" + list.get(i).toUpperCase());
            }
        } else if(map.containsKey(number.substring(3,6)) && map.containsKey(number.substring(6))) {
            list = map.get(number.substring(3,6));
            LinkedList<String> list2 = map.get(number.substring(6));
            for(int i = 0; i < list.size(); i++) {
                for(int j = 0; j < list2.size(); j++) {
                    System.out.println("1-" + list.get(i).toUpperCase() + "-" + list2.get(j).toUpperCase());
                }
            }
        } else if (map.containsKey(number.substring(3, 6))) {
            list = map.get(number.substring(3, 6));
            for (int i = 0; i < list.size(); i++) {
                System.out.println("1-" + number.substring(0, 3) + "-" + list.get(i).toUpperCase() + "-" + number.substring(6));
            }
        } else if (map.containsKey(number.substring(6))) {
            list = map.get(number.substring(6));
            for (int i = 0; i < list.size(); i++) {
                System.out.println("1-" + number.substring(0, 3) + "-" + number.substring(3,6) + "-" + list.get(i).toUpperCase());
            }
        } else {
            System.out.println("1-" + number.substring(0, 3) + "-" + number.substring(3,6) + "-" + number.substring(6));
        }
    }

    // do our best to not check HashMap, however, there are too many symbols to try to check
    private static boolean isOnlyNumbers(String number) {
        number = number.toLowerCase();
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) != '0' && number.charAt(i) != '1' && number.charAt(i) != '2' && number.charAt(i) != '3'
                    && number.charAt(i) != '4' && number.charAt(i) != '5' && number.charAt(i) != '6' && number.charAt(i) != '7'
                    && number.charAt(i) != '8' && number.charAt(i) != '9') {
                return false;
            }
        }
        return true;
    }
}
