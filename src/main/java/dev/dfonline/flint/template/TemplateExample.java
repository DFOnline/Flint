package dev.dfonline.flint.template;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.template.block.CodeBlock;
import dev.dfonline.flint.template.block.ConditionBlock;
import dev.dfonline.flint.template.block.impl.*;
import dev.dfonline.flint.template.value.impl.NumberValue;
import dev.dfonline.flint.template.value.impl.StringValue;
import dev.dfonline.flint.template.value.impl.TextValue;
import dev.dfonline.flint.template.value.impl.VectorValue;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Please excuse this monstrosity of a class.
 * Debugging is hard.
 */
public final class TemplateExample {

    private TemplateExample() {
    }

    public static void main(String[] args) {
        test(false);
    }

    public static void test(boolean ingame) {
        /*if (ingame) {
            List<CodeBlock> blocks = new ArrayList<>();
            blocks.add(new IfPlayer("NameEquals", "AllPlayers", true, new ArgumentContainer()));
            blocks.add(new Bracket(Bracket.Direction.OPEN, Bracket.Type.NORMAL));
            blocks.add(new PlayerAction("SendMessage", "Selection", ArgumentBuilder
                .create()
                .set(0, new StringValue("Hi!"))
                .build()));
            blocks.add(new Bracket(Bracket.Direction.CLOSE, Bracket.Type.NORMAL));

            Template test = new Template("Name", "Author", blocks);
            Flint.getUser().getPlayer().giveItemStack(test.toItem(Text.literal("Template"), Items.GLOWSTONE));

            Flint.getUser().getPlayer().sendMessage(Text.of(test.getCode()), false);
        }*/ // Test for adding some stuff - zBinFinn :)

        if (true) return;
//        blocks.add(new PlayerEvent("Join", Attribute.LS_CANCEL));
//
//        TagValue alignmentMode = new TagValue();
//        alignmentMode.setBlock("player_action");
//        alignmentMode.setAction("SendMessageSeq");
//        alignmentMode.setTag("Alignment Mode");
//        alignmentMode.setOption("Regular");
//        ArgumentContainer messageArgs = new ArgumentContainer()
//                .set(0, new ComponentValue("<green>Message 1"))
//                .set(1, new ComponentValue("<red>Message 2"))
//                .set(2, new TextValue("Unstyled"))
//                .set(3, new NumberValue(5))
//                .set(26, alignmentMode);
//
//        blocks.add(new PlayerAction("SendMessageSeq", messageArgs));
//
//        blocks.add(new Bracket(Bracket.Direction.OPEN, Bracket.Type.NORMAL));
//
//        blocks.add(new Control("End"));
//
//        blocks.add(new Bracket(Bracket.Direction.CLOSE, Bracket.Type.NORMAL));
//
//        blocks.add(new Function("hello"));

        List<CodeBlock> blocks = new ArrayList<>();

        blocks.add(new PlayerEvent("Hello!"));

        ArgumentContainer args = new ArgumentContainer();
        args.set(0, new TextValue("<green>Hello people"));
        args.set(3, new StringValue("This is a string!!"));
        args.set(8, new NumberValue(5));
        args.set(1, new VectorValue(5, 5, 5));

        blocks.add(new PlayerAction("SendMessage", args));
        blocks.add(new CallFunction("real"));
        blocks.add(new StartProcess("real"));
        blocks.add(new SelectObject("a"));
        blocks.add(new SelectObject("a", "b"));
        blocks.add(new SelectObject("a", "b", true));
        blocks.add(new IfPlayer("Doin", true));
        blocks.add(new Bracket(Bracket.Direction.OPEN, Bracket.Type.NORMAL));
        blocks.add(new Bracket(Bracket.Direction.CLOSE, Bracket.Type.NORMAL));
        blocks.add(new Else());
        blocks.add(new Bracket(Bracket.Direction.OPEN, Bracket.Type.NORMAL));
        blocks.add(new Bracket(Bracket.Direction.CLOSE, Bracket.Type.NORMAL));
        blocks.addAll(
                List.of(
                        new Repeat("Forever"),
                        new Bracket(Bracket.Direction.OPEN, Bracket.Type.REPEAT),
                        new Bracket(Bracket.Direction.CLOSE, Bracket.Type.REPEAT)
                )
        );

        Template template = new Template("Example Template", "Developer", blocks);

        Template builtTemplate = new Template("Example Template", "Developer", CodeBuilder.create()
                .add(new PlayerAction("SendMessage", ArgumentBuilder.create()
                        .set(0, new TextValue("<green>Hello people"))
                        .set(3, new StringValue("This is a string!!"))
                        .set(8, new NumberValue(5))
                        .set(1, new VectorValue(5, 5, 5))
                        .build()))
                .add(new CallFunction("real"))
                .add(new StartProcess("real"))
                .add(new SelectObject("a"))
                .add(new SelectObject("a", "b"))
                .add(new SelectObject("a", "b", true))
            //.add(new ConditionBlock("Doin", true)) this was throwing an error so I decided to comment it out
                .add(new Bracket(Bracket.Direction.OPEN, Bracket.Type.NORMAL))
                .add(new Bracket(Bracket.Direction.CLOSE, Bracket.Type.NORMAL))
                .add(new Else())
                .add(new Bracket(Bracket.Direction.OPEN, Bracket.Type.NORMAL))
                .add(new Bracket(Bracket.Direction.CLOSE, Bracket.Type.NORMAL))
                .add(new Repeat("Forever"))
                .build());

        String json = template.toJson();
        System.out.println(json);

        Template loadedTemplate = Template.fromJson(json);

        Template.EqualsScope equals = template.equals(loadedTemplate, Template.EqualsScope.ALL);
        if (equals == null) {
            System.out.println("Templates are equal");
        } else {
            System.out.println("Templates are not equal, failed at scope " + equals);
        }
//
//        String encodedCode = loadedTemplate.getCode();
//        System.out.println("Encoded code: " + encodedCode);

//        Template template = Template.fromJson("{\"author\":\"RedVortx\",\"name\":\"&bCode Template &3» &bEvent\",\"version\":1,\"code\":\"H4sIAAAAAAAA/91X3W7TMBR+lSo3u4lQx35gkbgZA4QECInBLuhkuc5paubYke2066Y8D+/Bk3HspKs7L/vVGFpuEjv+zs/nzz72eTIWip2YJPt5nvA8ydp2knbvLAFpuV0QmOEHdlNd4GAca6F0qOMG+5jlSuLYpEl7rFSCLkCTbmRspv3yPQ5uT52vnFrquiQtATuN1VwWSYMejVA2yYbeXYhjqqxioAU0t4JtRjBZlzFqGEBeRhDMK4Bws98mOqHCQOr/YvcpxvhimCaL7n3WvStu2XT5j879V7PythV5m0Ho7WqzK/x2hDcyD/Cd+02HnSmB6bkvo2rpxn4V6yzvRNYqqsPpcU3OhOPsrVC188REbSxo95eWaNexniZTpfmZkpaKLvAZOGTXbNYS3LyKt1I5+fygmtNWcZvDkLfdOFK1FqgblHyrADwbtfYG3IOCROFkAYev4jmgOhbJn9/gwxlj+ghlqnK9tTR0hk5W5l5H5goyw8RXBu3CQz8pRrs1YnGRgIv4ACa0FqGE9+JMJQERx+df6dI4lQtsVKLWjvNOq6py/i46gnUSry/fCGTv/yXnB+/Jl/3DbGtvZyd1S1BJ3CxMdj5KSi6BaTqxGbW4fMe1BVKqnE84aDNCGxcN3EpSM1VzwiWxSgnLq2w4btLQBkNVqdIZAEFcEGhheNUIlzb+28AImBJK4/coWYAQaj5KEMBRg5yNlhyM/BbhB30UKF3kAxDTbKzZzmlJC4hcTnkOhOY5b2lcBu+SQy6ZUz+Kn+dZCJpxmaPyrdLEVHSOk1cU6DDcpeJt6vmz/4FTaR+V+DNVjjn0kh5v9M+f9CNup48s97l30ct6XPCeP+vvZA56cKBpoeSjcg/OEcm9o94Z2G5ueZDjE9Ke5e5wFtSUnYAvxFwDcxUNy6RclSWpdHntcCaUgd7xlyI0WDctqbRiYMzNR80x0kiLQGttPXRlV8kNO8CSvvDFuOiqsxgsK763vsw3X6AWOOsNJDhMxieq/iDc4hywWmsU+qA9EZiLcA59e/AZZXrPUHb9vLder5l1zFqQSS1Zz6zfbACPfVqJh0zHIXf3lIvUeQmD75LbMPEj6tuXnUb5rgP6lQSWtKe+a5X+5trV0t6f/tvV0l3vei9mt98W7sjUP94UQCCKqPGv++dY4F7/YKIAy8CTsaChAnqXW/xN4XUG7xBghIiFVLR3lqddL8fNX6Nn8lsbEQAA\"}");
//            Template template = Template.fromJson("{\"author\":\"RedVortx\",\"name\":\"§b§lFunction §3» §bUnnamed\",\"version\":1,\"code\":\"H4sIAAAAAAAA/32OMQ7CMAxFr4I8d2JgyAEQnAFVyDimipomVWKGqsrdcdoiEBJMsf1f/v8z3HykPoO5zOAsmHWHZnsN3B+BdMXUKaSM8LDROi2X9ddVsFPOomC9xlFcDKoc0WdWocoGznl3ctZyqJa0IXYKODj6Di2lgeyjgNkfSlte3gCl+dGVa9RbTUg9Sy3lEpP6aKslWaaRdQsxDX9x8nHt/sm35QkytM8mNQEAAA==\"}");
        System.out.println("Template Name: " + template.getName());
        for (CodeBlock block : template.getBlocks()) {
            System.out.println(block);
        }

        List<CodeBlock> testBlocks = List.of(
                new Else(),
                new PlayerAction("test"),
//                new Bracket(Bracket.Direction.OPEN, Bracket.Type.NORMAL),
//                new Bracket(Bracket.Direction.CLOSE, Bracket.Type.NORMAL),
//                new Bracket(Bracket.Direction.OPEN, Bracket.Type.REPEAT),
                new Bracket(Bracket.Direction.CLOSE, Bracket.Type.REPEAT)
        );
        Template template2 = new Template("a", "B", testBlocks);

        Template template3 = Template.fromJson(template2.toJson());


        if (ingame) {
            Flint.getUser().getPlayer().giveItemStack(template.toItem(Text.literal("Template"), Items.GLOWSTONE));
            Flint.getUser().getPlayer().giveItemStack(loadedTemplate.toItem(Text.literal("Template reserialized"), Items.GLOWSTONE));
            Flint.getUser().getPlayer().giveItemStack(template2.toItem(Text.literal("Template 2"), Items.GLOWSTONE));
            Flint.getUser().getPlayer().giveItemStack(template3.toItem(Text.literal("Template 2 reserialized"), Items.GLOWSTONE));
        }

        System.out.println("Template Code: " + template.getCode());
        try {
            System.out.println("Template2 Code: " + template2.getCode()); // correct
            System.out.println("Template3 Code: " + template3.getCode()); // incorrect
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
