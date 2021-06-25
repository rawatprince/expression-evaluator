package com.phaidra.expressionevaluator.business.impl;

import com.phaidra.expressionevaluator.business.IExpressionEvaluatorBusiness;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class ExpressionEvaluatorBusinessImpl implements IExpressionEvaluatorBusiness {

    @Override
    public long evaluate(String input) {

        // Check if the input is valid string or not
        if (input == null || input.isEmpty())
            return 0;

        long result = 0; // Running Result
        long operand = 0; // Form Operand, since it can be more than 1 digit number
        long operandSign = 1; // 1 for +ve, -1 for -ve

        Stack<Long> stack = new Stack<>();

        for (char currentCharacter : input.toCharArray()) {

            // Keep forming operand if it's more than one digit
            if (currentCharacter >= '0' && currentCharacter <= '9') {
                // Forming operand
                operand = operand * 10 + (currentCharacter - '0');
            } else if (currentCharacter == '+' || currentCharacter == '-') {
                // Evaluate the expression
                result += operand * operandSign;
                operandSign = currentCharacter == '+' ? 1 : -1;
                operand = 0;
            } else if (currentCharacter == ')') {
                // Marks the end of expression
                result += operand * operandSign;
                long prevSign = stack.pop();
                result *= prevSign;
                result += stack.pop();
                operand = 0;
            } else if (currentCharacter == '(') {
                // Marks the start of expression
                stack.push(result);
                stack.push(operandSign);
                result = 0;
                operandSign = 1;
            }
        }

        return result + (operand * operandSign);

    }
}
