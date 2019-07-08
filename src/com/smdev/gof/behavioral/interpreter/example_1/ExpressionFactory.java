package com.smdev.gof.behavioral.interpreter.example_1;

import com.smdev.gof.behavioral.interpreter.example_1.keyword.And;
import com.smdev.gof.behavioral.interpreter.example_1.keyword.Or;
import com.smdev.gof.behavioral.interpreter.example_1.keyword.Eq;
import com.smdev.gof.behavioral.interpreter.example_1.keyword.Find;
import com.smdev.gof.behavioral.interpreter.example_1.keyword.Where;

import java.lang.reflect.Constructor;

public enum ExpressionFactory {

    FIND("find", Find.class),
    WHERE("where", Where.class),
    AND("and", And.class),
    OR("or", Or.class),
    EQ("eq", Eq.class);

    private String keyword;
    private Class<? extends Expression> clz;

    ExpressionFactory(String keyword, Class<? extends Expression> clz) {
        this.keyword = keyword;
        this.clz = clz;
    }

    public static Expression createInstance(String keyword, Context context) throws Exception {
        ExpressionFactory type = getExpressionType(keyword);
        if (type == null) {
            return null;
        }

        Constructor<? extends Expression> constructor = type.getClz().getConstructor(context.getClass());
        if (constructor != null) {
            return constructor.newInstance(context);
        }
        return null;
    }

    private static ExpressionFactory getExpressionType(String keyword) {
        if (keyword == null) {
            return null;
        }
        ExpressionFactory[] values = ExpressionFactory.values();
        for (ExpressionFactory t : values) {
            if (t.getKeyword().toLowerCase().equals(keyword.toLowerCase())) {
                return t;
            }
        }
        return null;
    }

    private String getKeyword() {
        return this.keyword;
    }

    private Class<? extends Expression> getClz() {
        return this.clz;
    }
}
