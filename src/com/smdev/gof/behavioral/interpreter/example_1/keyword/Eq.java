package com.smdev.gof.behavioral.interpreter.example_1.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.AbstractExpressionTerminal;
import com.smdev.gof.behavioral.interpreter.example_1.Dog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Eq extends AbstractExpressionTerminal {

    private String fieldName;
    private String searchedValue;

    public Eq(Context context) {
        super(context);
    }

    @Override
    public void parse() {
        this.fieldName = getContext().getArgs().pollFirst();
        this.searchedValue = getContext().getArgs().pollFirst();
    }

    @Override
    public List<Dog> evaluate() throws Exception {
        if (this.fieldName == null) {
            return getContext().getData();
        }
        List<Dog> filtered = new ArrayList<>();
        Field field = Dog.class.getDeclaredField(this.fieldName);
        field.setAccessible(true);
        for (Dog e : getContext().getData()) {
            Object value = field.get(e);
            if (this.searchedValue == null && value == null) {
                filtered.add(e);
            } else if (this.searchedValue != null && value != null && String.valueOf(value).equalsIgnoreCase(this.searchedValue)) {
                filtered.add(e);
            }
        }
        return filtered;
    }


}
