package FinalProject;

import java.util.Random;

public class PassGenerator {
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "01234567890123456789012345";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+\\\\/~?";
    private int length;
    private boolean includeUppercase;
    private boolean includeLowercase;
    private boolean includeSpecialCharacters;
    private boolean includeNumbers;

    public PassGenerator() {
        length = 8;
        includeUppercase = true;
        includeLowercase = true;
        includeSpecialCharacters = false;
        includeNumbers = true;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setIncludeUppercase(boolean includeUppercase) {
        this.includeUppercase = includeUppercase;
    }

    public void setIncludeLowercase(boolean includeLowercase) {
        this.includeLowercase = includeLowercase;
    }

    public void setIncludeSpecialCharacters(boolean includeSpecialCharacters) {
        this.includeSpecialCharacters = includeSpecialCharacters;
    }

    public void setIncludeNumbers(boolean includeNumbers) {
        this.includeNumbers = includeNumbers;
    }

    public String generatePassword() {
        StringBuilder password = new StringBuilder();

        Random random = new Random();

        String characters = "";

        if (includeUppercase) {
            characters += UPPERCASE_LETTERS;
        }
        if (includeLowercase) {
            characters += LOWERCASE_LETTERS;
        }
        if (includeSpecialCharacters) {
            characters += SPECIAL_CHARACTERS;
        }
        if (includeNumbers) {
            characters += NUMBERS;
        }

        if (characters.isEmpty()) {
            throw new IllegalArgumentException("No character types selected for password generation.");
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }
    public String checkPasswordStrength(String password) {
        int score = 0;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasSpecialChar = false;
        boolean hasNumber = false;
  
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else {
                hasSpecialChar = true;
            }
        }
  
        if (hasUppercase) score += 2;
        if (hasLowercase) score += 2;
        if (hasSpecialChar) score += 2;
        if (hasNumber) score += 2;
  
        if (password.length() >= 8) score += 2;
        if (password.length() >= 12) score += 2;
        if (password.length() >= 16) score += 2;
  
        if (score <= 4) {
            return "Weak";
        } else if (score <= 8) {
            return "Moderate";
        } else if (score <= 12) {
            return "Strong";
        } else {
            return "Very Strong";
        }
    
      }
  
    
}
