package com.popovich.shell;

import com.popovich.bracket.Expander;
import com.popovich.bracket.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;



@ShellComponent
public class ShellCommands {

    private final Expander evaluator;
    private final Validator validator;

    @Autowired
    public ShellCommands(Expander evaluator, Validator validator) {
        this.evaluator = evaluator;
        this.validator = validator;
    }

    @ShellMethod("Evaluate bracket expression")
    public String eval(String input){

        if (!validator.validate(input)) {
            throw new IllegalArgumentException("Expression is not valid");
        }

        return evaluator.expand(input);
    }
}
