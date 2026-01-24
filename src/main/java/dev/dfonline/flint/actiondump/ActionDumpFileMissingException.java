package dev.dfonline.flint.actiondump;

public class ActionDumpFileMissingException extends RuntimeException {
    public ActionDumpFileMissingException() {
        super("There is no action dump file at `./Flint/actiondump_minimessage.json`, please obtain one.");
    }
}
