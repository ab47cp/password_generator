import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {

    private static char generateRandomCharacter() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+{}|:<>?-=[];,./";
        Random random = new Random();
        return characters.charAt(random.nextInt(characters.length()));
    }

    private static String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(generateRandomCharacter());
        }
        return password.toString();
    }

    private static String determinePasswordStrength(String password) {
        int length = password.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
            if ("!@#$%^&*()_+{}|:<>?-=[];,./".indexOf(c) >= 0) hasSpecial = true;
        }

        if (length < 5) {
            return "weak";
        } else if (length < 10) {
            return "medium";
        } else if (hasLower && hasUpper && hasDigit && hasSpecial) {
            return "strong";
        } else {
            return "medium";
        }
    }

    private static String[] generatePasswords(int numPasswords, int passwordLength) {
        String[] passwords = new String[numPasswords];
        for (int i = 0; i < numPasswords; i++) {
            passwords[i] = generateRandomPassword(passwordLength);
        }
        return passwords;
    }

    private static void printPasswords(String[] passwords) {
        for (int i = 0; i < passwords.length; i++) {
            String strength = determinePasswordStrength(passwords[i]);
            System.out.println("Password " + (i + 1) + ": " + passwords[i] + " (Strength: " + strength + ")");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total number of random passwords you want: ");
        int numPasswords = scanner.nextInt();

        System.out.print("Enter the length of each password (12-15 characters is a strong password length): ");
        int passwordLength = scanner.nextInt();

        String[] passwords = generatePasswords(numPasswords, passwordLength);

        printPasswords(passwords);

        scanner.close();
    }
}
