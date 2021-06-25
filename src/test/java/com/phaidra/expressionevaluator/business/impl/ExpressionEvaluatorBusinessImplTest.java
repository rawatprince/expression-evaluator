package com.phaidra.expressionevaluator.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExpressionEvaluatorBusinessImplTest {

    private ExpressionEvaluatorBusinessImpl expressionEvaluatorBusinessImplUnderTest;

    @BeforeEach
    void setUp() {
        expressionEvaluatorBusinessImplUnderTest = new ExpressionEvaluatorBusinessImpl();
    }

    @Test
    void testPositive() {
        // Setup
        String input = "1+2+3+4+5";

        // Run the test
        final long result = expressionEvaluatorBusinessImplUnderTest.evaluate(input);

        // Verify the results
        assertThat(result).isEqualTo(15);
    }

    @Test
    void testNegative() {
        // Setup
        String input = "1-2-3-4-5";

        // Run the test
        final long result = expressionEvaluatorBusinessImplUnderTest.evaluate(input);

        // Verify the results
        assertThat(result).isEqualTo(-13);
    }

    @Test
    void testPositiveNegative() {
        // Setup
        String input = "1+2+3-4+5";

        // Run the test
        final long result = expressionEvaluatorBusinessImplUnderTest.evaluate(input);

        // Verify the results
        assertThat(result).isEqualTo(7);
    }

    @Test
    void testWithParenthesis() {
        // Setup
        String input = "1+2+((3-4)+5+(6+7)-1)";

        // Run the test
        final long result = expressionEvaluatorBusinessImplUnderTest.evaluate(input);

        // Verify the results
        assertThat(result).isEqualTo(19);
    }

    @Test
    void testEmpty() {
        // Setup
        String input = "";

        // Run the test
        final long result = expressionEvaluatorBusinessImplUnderTest.evaluate(input);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }


}
