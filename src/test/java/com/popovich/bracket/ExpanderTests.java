package com.popovich.bracket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpanderTests {

    private Expander expander = new Expander();

    @Test
    public void testAlreadyExpandedBracketExpressionProcessedCorrectly() {
        assertEquals("", expander.expand(""));
        assertEquals("a", expander.expand("a"));
        assertEquals("BBB", expander.expand("BBB"));
    }

    @Test
    public void testNonNestedBracketExpressionsProcessedCorrectly() {
        assertEquals("", expander.expand("0[a]"));
        assertEquals("a", expander.expand("1[a]"));
        assertEquals("bb", expander.expand("2[b]"));
        assertEquals("ababab", expander.expand("3[ab]"));
    }

    @Test
    public void testDepthLevelTwoNestedExpressionProcessedCorrectly() {
        assertEquals("", expander.expand("2[0[a]]"));
        assertEquals("bb", expander.expand("2[1[b]]"));
        assertEquals("cccc", expander.expand("2[2[c]]"));
        assertEquals("aabbbaabbb", expander.expand("2[2[a]3[b]]"));
        assertEquals("bbbaCbbb", expander.expand("3[b]aC3[b]"));
        assertEquals("ababxyZZxyZZ", expander.expand("2[ab]2[xy2[Z]]"));
    }

    @Test
    public void testDeeplyNestedExpressionsProcessedCorrectly() {
        assertEquals("", expander.expand("2[2[0[a]]]"));
        assertEquals("bbbbbbbb", expander.expand("2[2[2[b]]]"));
        assertEquals("c", expander.expand("1[1[1[1[1[1[1[c]]]]]]]"));
        assertEquals("xyxyxyxyxyxyxyxy", expander.expand("2[1[2[1[2[xy]]]]]"));
    }

    @Test
    public void testExpandNonNestedBracketExpression() {
        assertEquals("", expander.expandNonNestedBracketExpression("0[a]"));
        assertEquals("a", expander.expandNonNestedBracketExpression("1[a]"));
        assertEquals("abab", expander.expandNonNestedBracketExpression("2[ab]"));
        assertEquals("abcabcabc", expander.expandNonNestedBracketExpression("3[abc]"));
    }
}
