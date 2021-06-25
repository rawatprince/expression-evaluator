package com.phaidra.expressionevaluator.controller;

import com.phaidra.expressionevaluator.business.IExpressionEvaluatorBusiness;
import com.phaidra.expressionevaluator.business.impl.ExpressionEvaluatorBusinessImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExpressionEvaluatorController {

    private final IExpressionEvaluatorBusiness expressionEvaluatorBusiness;

    public ExpressionEvaluatorController(ExpressionEvaluatorBusinessImpl expressionEvaluatorBusiness) {
        this.expressionEvaluatorBusiness = expressionEvaluatorBusiness;
    }

    @GetMapping("/api/evaluate/{input}")
    public Long evaluate(@PathVariable String input) {
        return expressionEvaluatorBusiness.evaluate(input);
    }

    @PostMapping("/api/evaluateList")
    public List<Long> evaluateList(@RequestBody List<String> inputList) {
        List<Long> result = new ArrayList<>();
        for (String input : inputList)
            result.add(evaluate(input));
        return result;
    }


}
