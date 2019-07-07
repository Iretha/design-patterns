package com.smdev.gof.behavioral.interpreter.example_1.expression.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.expression.AbstractExpression;
import com.smdev.gof.behavioral.interpreter.example_1.model.Dog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Eq extends AbstractExpression {

    public Eq(Context context) {
        super(context);
    }

    @Override
    public List<Dog> evaluate(List<Dog> data) throws Exception {
        String fieldName = getContext().getArgs().poll();
        String searchedValue = getContext().getArgs().poll();
        if (fieldName != null) {
            Field field = Dog.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            List<Dog> filteredData = new ArrayList<>();
            for (Dog e : data) {
                Object value = field.get(e);

                if (searchedValue == null && value == null) {
                    filteredData.add(e);
                } else if (searchedValue != null && value != null && String.valueOf(value).equalsIgnoreCase(searchedValue)) {
                    filteredData.add(e);
                }
            }
            return filteredData;
        }
        return data;
    }

}
