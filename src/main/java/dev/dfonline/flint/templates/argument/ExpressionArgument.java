package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class ExpressionArgument extends Argument {
    private String expression;
    public ExpressionArgument(JsonObject json, JsonObject data) {
        super(json);
        expression = data.get("expr").getAsString();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public ExpressionArgument(int slot, String expression) {
        super(slot);
        this.expression = expression;
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("expr", expression);
        return data;
    }

    @Override
    public String getID() {
        return "expr";
    }
}
