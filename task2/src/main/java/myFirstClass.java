import java.io.File;
import java.io.IOException;
import java.util.Random;

public class myFirstClass {
    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static void main(String[] args) throws IOException {
        myFirstClass msg = new myFirstClass ();
        String path = msg.generateRandomString ();
        System.out.println(path);

        File file = new File(path + "/hello.txt");
        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println("Directory is created!");
                if (!file.createNewFile()){
                    System.out.println("File is created!");
                } else {
                    System.out.println("Failed to create file!");
                }
            } else {
                System.out.println("Failed to create directory!");
            }
        } else {
            System.out.println("File already exists!");
        }

    }
    private String generateRandomString() {
        int RANDOM_STRING_LENGTH = 0;
        Random randomGenerator = new Random();
        RANDOM_STRING_LENGTH = randomGenerator.nextInt(15) + 5;

        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
}
