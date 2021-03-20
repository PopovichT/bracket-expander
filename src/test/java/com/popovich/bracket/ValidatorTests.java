package com.popovich.bracket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTests {

    private Validator validator = new Validator();

    @Test
    public void testEachSymbolInBracketExpressionsValid() {
        assertEquals(true, validator.validateIsEachSymbolValid("3"));
        assertEquals(true, validator.validateIsEachSymbolValid("32"));
        assertEquals(true, validator.validateIsEachSymbolValid("]"));
        assertEquals(true, validator.validateIsEachSymbolValid("]["));
        assertEquals(true, validator.validateIsEachSymbolValid("sdq"));
        assertEquals(true, validator.validateIsEachSymbolValid("asd3ae"));
        assertEquals(true, validator.validateIsEachSymbolValid("sdq3[]sf"));


        assertEquals(false, validator.validateIsEachSymbolValid("("));
        assertEquals(false, validator.validateIsEachSymbolValid(")"));
        assertEquals(false, validator.validateIsEachSymbolValid("34f)"));
        assertEquals(false, validator.validateIsEachSymbolValid("ы"));
        assertEquals(false, validator.validateIsEachSymbolValid("f5dkыав]["));
        assertEquals(false, validator.validateIsEachSymbolValid("4ш3fdk(]["));
    }

    @Test
    public void testBracketSequenceValidatesCorrectly() {
        assertEquals(true, validator.validateBracketSequence("3"));
        assertEquals(true, validator.validateBracketSequence("asd"));
        assertEquals(true, validator.validateBracketSequence("23AsF"));
        assertEquals(true, validator.validateBracketSequence("3[xy][][][]"));
        assertEquals(true, validator.validateBracketSequence("asd[[[]]][][]"));
        assertEquals(true, validator.validateBracketSequence("3[x3[2[xc]3[xv]]]"));


        assertEquals(false, validator.validateBracketSequence("]"));
        assertEquals(false, validator.validateBracketSequence("["));
        assertEquals(false, validator.validateBracketSequence("]asd"));
        assertEquals(false, validator.validateBracketSequence("sd[s"));
        assertEquals(false, validator.validateBracketSequence("]"));
        assertEquals(false, validator.validateBracketSequence("]["));
        assertEquals(false, validator.validateBracketSequence("[]]"));
                }

    @Test
    public void testAllNumbersAreFollowedByLeftBrackets() {
        assertEquals(true, validator.validateAreSymbolsInRightOrder("x"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("4[x]"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("4[1[]"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("as2[df34[]"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("asd4[]f34[]"));

        assertEquals(false, validator.validateAreSymbolsInRightOrder("3"));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("12d"));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("x3]x"));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("asf]34ad"));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("3asd45][["));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("atr]3]]"));
    }

    @Test
    public void testAllLeftBracketsArePrecededByNumbers() {
        assertEquals(true, validator.validateAreSymbolsInRightOrder("a"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("10[m"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("1[f]"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("4[1[]"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("as3[df34[]"));
        assertEquals(true, validator.validateAreSymbolsInRightOrder("asd1[f34[]sfd2["));

        assertEquals(false, validator.validateAreSymbolsInRightOrder("["));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("d[]"));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("x[1[3]x"));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("as[f]34[a]d"));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("3asd45][["));
        assertEquals(false, validator.validateAreSymbolsInRightOrder("a[tr]3]]"));
    }


    @Test
    public void testAllConditionsOfBracketExpressionAreComplied() {
        assertEquals(true, validator.validate(""));
        assertEquals(true, validator.validate("z"));
        assertEquals(true, validator.validate("aaa"));
        assertEquals(true, validator.validate("3[x]"));
        assertEquals(true, validator.validate("2[2[T]]"));
        assertEquals(true, validator.validate("2[a]2[bv]"));
        assertEquals(true, validator.validate("22[3[x]2[T]]"));
        assertEquals(true, validator.validate("2[11[xt2[a]x]2[xc2[a]]]"));
        assertEquals(true, validator.validate("2[2[2[2[aSd]]]]"));


        assertEquals(false, validator.validate("2"));
        assertEquals(false, validator.validate("[]"));
        assertEquals(false, validator.validate("ыш"));
        assertEquals(false, validator.validate("[x]"));
        assertEquals(false, validator.validate("2[э]"));
        assertEquals(false, validator.validate("2[x]2"));
        assertEquals(false, validator.validate("2[x[["));
        assertEquals(false, validator.validate("2[[f]"));
        assertEquals(false, validator.validate("2(ds)"));
        assertEquals(false, validator.validate("2[x[["));
        assertEquals(false, validator.validate("2[x[[sd]]]]"));
        assertEquals(false, validator.validate("3[a2[1[daasd3x]]]"));
        assertEquals(false, validator.validate("2[11[xt[A]x]0[Xc2[a]]"));
        assertEquals(false, validator.validate("2[11[xt1[A]x]0[Xc2[ыу]]"));
        assertEquals(false, validator.validate("2[11[xt[A]x]0[Xc2(a}]"));
        assertEquals(false, validator.validate("3[11[xt[a]x]2[xC2[axs3]]]"));
        assertEquals(false, validator.validate("2[11[xt[a]x]2[xc2[a]]]["));
        assertEquals(false, validator.validate("1[11s[xt[a]x]2[xc2[a]]]"));
    }
}
