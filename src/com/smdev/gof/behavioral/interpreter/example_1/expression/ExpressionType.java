package com.smdev.gof.behavioral.interpreter.example_1.expression;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.expression.keyword.And;
import com.smdev.gof.behavioral.interpreter.example_1.expression.keyword.Eq;
import com.smdev.gof.behavioral.interpreter.example_1.expression.keyword.Find;
import com.smdev.gof.behavioral.interpreter.example_1.expression.keyword.Where;

import java.lang.reflect.Constructor;

public enum ExpressionType {

    FIND("find", Find.class),
    WHERE("where", Where.class),
    AND("and", And.class),
    EQ("eq", Eq.class);

    private String keyword;
    private Class<? extends Expression> clz;

    ExpressionType(String keyword, Class<? extends Expression> clz) {
        this.keyword = keyword;
        this.clz = clz;
    }

    public static ExpressionType getExpressionType(String keyword) {
        if (keyword == null) {
            return null;
        }
        ExpressionType[] values = ExpressionType.values();
        for (ExpressionType t : values) {
            if (t.getKeyword().toLowerCase().equals(keyword.toLowerCase())) {
                return t;
            }
        }
        return null;
    }

    public Expression createInstance(Context context) throws Exception {
        Constructor<? extends Expression> constructor = getClz().getConstructor(context.getClass());
        if (constructor != null) {
            return constructor.newInstance(context);
        }
        return null;
    }

    public String getKeyword() {
        return keyword;
    }

    public Class<? extends Expression> getClz() {
        return clz;
    }
}
