package NicholasTollner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Queue<Integer> repeatingKey = new LinkedList<>();
        boolean encode = false;

        while (true) {
            System.out.print("Encode or Decode? : ");
            String input = in.next();
            in.nextLine();
            if (input.toLowerCase().equals("encode") || input.toLowerCase().equals("e")) {
                encode = true;
                break;
            } else if (input.toLowerCase().equals("decode") || input.toLowerCase().equals("d")) {
                break;
            }
        }

        // ENCODING
        if (encode) {
            String initialKey;
            while (true) {
                System.out.print("Enter Key: ");
                initialKey = in.next();
                in.nextLine();
                if (initialKey.matches("[0-9]+")) {
                    break;
                } else {
                    System.out.println("Error: Key must contain only numbers");
                }
            }

            /*
             *  charAt returns char
             *  valueOf returns string
             *  parseInt returns int
             *
             *  adds initialKey entered by user to repeatingKey linkedList
             *
             */
            for (int i = 0; i < initialKey.length(); i++) {
                repeatingKey.offer(Integer.parseInt(String.valueOf(initialKey.charAt(i))));
            }

            System.out.print("Enter Message: ");
            String message = in.nextLine();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < message.length(); i++) {
                int shift = repeatingKey.poll();

                // We can add an int to a char value with addition operator
                // We then cast the int back to a char
                char toShift = message.charAt(i);
                int shifted = toShift + shift;
                char shiftedChar = (char) shifted;
                sb.append(shiftedChar);

                // Append the initial shift to the end of the repeatingKey
                repeatingKey.offer(shift);
            }
            String encoded = sb.toString();
            //System.out.println("Encoded: " + encoded);

            try {
                System.out.println("Writing to File ...");
                FileWriter fw = new FileWriter("secrets.txt");
                fw.write(encoded);
                fw.close();
                System.out.println("Success!");
            } catch (IOException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }


            // DECODING
        } else {
            StringBuilder encodedMessage = new StringBuilder();
            StringBuilder decodedBuffer = new StringBuilder();

            System.out.print("Enter key value: ");
            String initialKey = in.next();
            in.nextLine();

            try {
                System.out.println("Reading from File ...");
                File secrets = new File("secrets.txt");
                Scanner fileScan = new Scanner(secrets);

                while (fileScan.hasNextLine()) {
                    encodedMessage.append(fileScan.nextLine());
                }

            } catch (FileNotFoundException e) {
                System.out.println("File could not be found");
                e.printStackTrace();
            }

            /*
             *  charAt returns char
             *  valueOf returns string
             *  parseInt returns int
             *
             *  adds initialKey entered by user to repeatingKey linkedList
             *
             */
            for (int i = 0; i < initialKey.length(); i++) {
                repeatingKey.offer(Integer.parseInt(String.valueOf(initialKey.charAt(i))));
                //System.out.println("Key: " + repeatingKey);
            }

            for (int i = 0; i < encodedMessage.length(); i++) {
                int shift = repeatingKey.poll();

                char toShift = encodedMessage.charAt(i);
                int shifted = toShift - shift;
                char shiftedChar = (char) shifted;

                decodedBuffer.append(shiftedChar);

                repeatingKey.offer(shift);
            }
            System.out.println("Decoded Message: " + decodedBuffer);
        }
    }
}