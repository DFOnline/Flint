package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class NumberArgument extends Argument {
    private String number;
    public NumberArgument(JsonObject json, JsonObject data) {
        super(json);
        this.number = data.get("name").getAsString();
    }

    public NumberArgument(int slot, double number) {
        super(slot);
        this.number = String.valueOf(number);
    }
    public NumberArgument(int slot, String number) {
        super(slot);
        this.number = number;
    }

    @Override
    public String toString() {
        return "Number [value=" + number + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("name", number);
        return data;
    }

    @Override
    public String getID() {
        return "num";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public void setNumber(double number) {
        setNumber(String.valueOf(number));
    }
}
