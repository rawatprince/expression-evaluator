package com.phaidra.expressionevaluator.business.impl;

import com.phaidra.expressionevaluator.business.IExpressionEvaluatorBusiness;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class ExpressionEvaluatorBusinessImpl implements IExpressionEvaluatorBusiness {

    @Override
    public long evaluate(String input) {

        if (input == null || input.isEmpty())
            return 0;

        long result = 0;
        long operand = 0;
        long operandSign = 1;

        Stack<Long> stack = new Stack<>();

        for (char currentCharacter : input.toCharArray()) {

            if (currentCharacter >= '0' && currentCharacter <= '9') {
                // Forming operand
                operand = operand * 10 + (currentCharacter - '0');
            } else if (currentCharacter == '+' || currentCharacter == '-') {
                result += operand * operandSign;
                operandSign = currentCharacter == '+' ? 1 : -1;
                operand = 0;
            } else if (currentCharacter == ')') {
                result += operand * operandSign;
                long prevSign = stack.pop();
                result *= prevSign;
                result += stack.pop();
                operand = 0;
            } else if (currentCharacter == '(') {
                stack.push(result);
                stack.push(operandSign);
                result = 0;
                operandSign = 1;
            }
        }

        return result + (operand * operandSign);

    }
}
