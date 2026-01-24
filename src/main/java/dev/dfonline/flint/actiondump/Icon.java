package dev.dfonline.flint.actiondump;

import java.util.List;

public record Icon(
        String material,
        String name,
        List<String> description,
        List<String> deprecatedNote,
        List<String> example,
        List<String> worksWith,
        List<List<String>> additionalInfo,

        String requiredRank,
        boolean requireTokens,
        boolean requireSparks,
        boolean requireRankAndTokens,
        boolean advanced,
        boolean worldExclusive
) {
}
