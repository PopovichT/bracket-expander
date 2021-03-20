package com.popovich.bracket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.popovich.bracket.Constants.LEFT_BRACKET;
import static com.popovich.bracket.Constants.RIGHT_BRACKET;

@Slf4j
@Component
public class Validator {

    public boolean validate(String string) {
        if ("".equals(string)) {
            return true;
        }

        return validateIsEachSymbolValid(string)
                && validateBracketSequence(string)
                && validateAreSymbolsInRightOrder(string);
    }

    public boolean validateIsEachSymbolValid(String string) {
        for (int i = 0; i < string.length(); i++) {
            boolean isValid = false;
            if (Character.isDigit(string.charAt(i))) {
                isValid = true;
            }

            if (string.charAt(i) == LEFT_BRACKET) {
                isValid = true;
            }

            if (string.charAt(i) == RIGHT_BRACKET) {
                isValid = true;
            }

            if ('a' <= string.charAt(i) && string.charAt(i) <= 'z' || 'A' <= string.charAt(i) && string.charAt(i) <= 'Z') {
                isValid = true;
            }

            if (!isValid) {
                log.error("Invalid symbol at position " + (i + 1));
                return isValid;
            }
        }
        return true;
    }

    public boolean validateBracketSequence(String string) {
        var counter = 0;
        for (int i = 0; i < string.length(); i++) {

            if (string.charAt(i) == LEFT_BRACKET) {
                counter++;
            }

            if (string.charAt(i) == RIGHT_BRACKET) {
                counter--;
            }

            if (counter < 0) {
                log.error("Closing bracket without opening bracket at position " + (i + 1));
                return false;
            }
        }
        if (counter != 0) {
            log.error("Not all brackets are closed");
            return false;
        }
        return true;
    }

    public boolean validateAreSymbolsInRightOrder(String string) {
        var chars = string.toCharArray();

        if (chars[0] == LEFT_BRACKET) {
            log.error("All brackets must be preceded by multipier at position 1");
            return false;
        }

        if (chars.length > 1) {
            if ((Character.isDigit(chars[0]) & !Character.isDigit(chars[1]) & chars[1] != '[')) {
                log.error("Number must be followed by expression in brackets at position 2");
                return false;
            }
        }

        if (Character.isDigit(chars[chars.length - 1])) {
            log.error("Number must be followed by expression in brackets at position " + chars.length);
            return false;
        }

        for (int i = 1; i < chars.length - 1; i++) {

            if (Character.isDigit(chars[i])) {
                if (!Character.isDigit(chars[i + 1]) & chars[i + 1] != LEFT_BRACKET) {
                    log.error("Number must be followed by expression in brackets at position " + (i + 1));
                    return false;
                }
            }

            if (chars[i] == LEFT_BRACKET) {
                if (!Character.isDigit(chars[i - 1])) {
                    log.error("All brackets must be preceded by multipier at position " + (i + 1));
                    return false;
                }
            }
        }
        return true;
    }
}

