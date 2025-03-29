package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;
import dev.dfonline.flint.template.value.impl.HintValue;
import dev.dfonline.flint.template.value.impl.TagValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Function extends BaseBlock {

    private String data;

    public Function() {
        super("func");
    }

    public Function(String functionName) {
        this();
        this.data = functionName;

        // Add default items
        this.addArgument(25, new HintValue(HintValue.Type.FUNCTION));

        TagValue isHidden = new TagValue();
        isHidden.setAction("dynamic");
        isHidden.setBlock("func");
        isHidden.setTag("Is Hidden");
        isHidden.setOption("False");
        this.addArgument(26, isHidden);
    }

    public Function(String functionName, ArgumentContainer args) {
        this(functionName);
        this.getArguments().setItems(args.getItems());
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
