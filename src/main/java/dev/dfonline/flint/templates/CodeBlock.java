package dev.dfonline.flint.templates;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.*;
import dev.dfonline.flint.templates.codeblock.Process;
import dev.dfonline.flint.templates.codeblock.StartProcess;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockWithArguments;
import org.jetbrains.annotations.Nullable;

import static dev.dfonline.flint.templates.Template.print;

public abstract class CodeBlock {
    public static @Nullable CodeBlock fromJson(JsonObject json) {
        String id = json.get("id").getAsString();
        if (id.equals("block")) {
            return switch (json.get("block").getAsString()) {
                case "player_action" -> new PlayerAction(json);
                case "if_var" -> new IfVariable(json);
                case "entity_action" -> new EntityAction(json);
                case "if_entity" -> new IfEntity(json);
                case "if_player" -> new IfPlayer(json);
                case "game_action" -> new GameAction(json);
                case "if_game" -> new IfGame(json);
                case "set_var" -> new SetVariable(json);
                case "control" -> new Control(json);
                case "else" -> new Else();
                case "repeat" -> new Repeat(json);
                case "call_func" -> new CallFunction(json);
                case "start_process" -> new StartProcess(json);
                case "func" -> new Function(json);
                case "process" -> new Process(json);
                case "event" -> new PlayerEvent(json);
                case "entity_event" -> new EntityEvent(json);
                case "select_obj" -> new SelectObject(json);
                default -> null;
            };
        }
        if (id.equals("bracket")) {
            return new Bracket(json);
        }
        return null;
    }

    public void printToChat() {
        print(this.toString());
        if (this instanceof CodeBlockWithArguments codeBlockWithArguments) {
            codeBlockWithArguments.getArguments().printToChat();
        }
    }
}
