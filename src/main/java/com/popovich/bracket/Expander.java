package com.popovich.bracket;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.popovich.bracket.Constants.LEFT_BRACKET;
import static com.popovich.bracket.Constants.RIGHT_BRACKET;

@Component
public class Expander {

    private static final Pattern PATTERN_WITHOUT_NESTED_BRACKETS = Pattern.compile(buildNonNestedBracketExpressionRegexp());

    /**
     * We start with inner-most bracket pairs and expand them and
     * put back into the original expression i.e. 2[2[x]] becomes 2[xx]
     * We continue this process until no more brackets remain
     */
    public String expand(String input) {
        var buffer = input;

        while (!allBracketsExpanded(buffer)) {
            buffer = expandInnermostBracketPair(buffer);
        }
        return buffer;
    }

    private boolean allBracketsExpanded(String input) {
        return !input.contains(LEFT_BRACKET.toString());
    }

    private String expandInnermostBracketPair(String input) {
        var match = PATTERN_WITHOUT_NESTED_BRACKETS.matcher(input);

        // If input is abc2[2[x]]xyz
        if (match.find()) {
            //this part will be abc2[
            var beforeBracketExpression = input.substring(0, match.start());
            //this part will be 2[x] and after return from method xx
            var expandedExpression = expandNonNestedBracketExpression(input.substring(match.start(), match.end()));
            //this part will be xyz
            var afterBracketExpression = input.substring(match.end());

            return beforeBracketExpression + expandedExpression + afterBracketExpression;
        }

        return input;
    }

    /**
     * @param nonNestedBracketExpression is an expression of the form 3[xy] with NO nested brackets
     *                                   for example it won't work for an expression like 3[3[x]]
     * @return expanded expression 3[xy] = xyxyxy
     */
    String expandNonNestedBracketExpression(String nonNestedBracketExpression) {

        if ("[]".equals(nonNestedBracketExpression)) {
            return "";
        }

        var stringBuilder = new StringBuilder();
        var positionOfLeftBracket = 0;
        for (int i = 0; i < nonNestedBracketExpression.length(); i++) {
            if (nonNestedBracketExpression.charAt(i) == LEFT_BRACKET) {
                positionOfLeftBracket = i;
            }
        }
        var multiplier = Integer.parseInt(nonNestedBracketExpression.substring(0, positionOfLeftBracket));
        if (multiplier == 0) {
            return "";
        }

        var lettersInBrackets = nonNestedBracketExpression.substring(positionOfLeftBracket + 1, nonNestedBracketExpression.length() - 1);

        for (int i = 0; i < multiplier; i++) {
            stringBuilder.append(lettersInBrackets);
        }

        return stringBuilder.toString();
    }

    private static String buildNonNestedBracketExpressionRegexp() {
        return "\\d+" + "\\" + LEFT_BRACKET + "[a-zA-z]*?" + "\\" + RIGHT_BRACKET;
    }
}


