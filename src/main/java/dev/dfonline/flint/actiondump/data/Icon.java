package dev.dfonline.flint.actiondump.data;

import java.util.List;

public record Icon(
        /// Represents the material of an icon
        String material,
        /// Represents the item inside a crossbow
        String loadedItem,
        /// A texture value for the player head if the material is PLAYER_HEAD
        String head,
        /// If the material is a POTION, or SPLASH_POTION, this is it's color
        Color color,
        /// Represents the canonical name associated with the parent object
        String name,

        /// The description of the object, split by each line
        String[] description,
        /// Reasons why an action may be deprecated, split by each line
        String[] deprecatedNote,
        /// Examples of how to use the object, split by each line
        String[] example,
        /// Examples of what the parent object works with, split by each line
        String[] worksWith,
        /// Any additional info necessary for the parent object, split by each purpose, and then each line
        String[][] additionalInfo,

        /// The rank required to use this object
        String requiredRank,
        /// Whether the object must be bought with tokens
        boolean requireTokens,
        /// Whether this shop object must be purchased with Sparks
        boolean requireSparks,
        /// Whether the object requires a rank and tokens to be purchased
        boolean requireRankAndTokens,
        /// Whether this action is inside the advanced category or not
        boolean advanced,
        /// Whether this action is exclusive to world plots or not
        boolean worldExclusive,

        /// The number of block tags an action has
        int tags,

        /// The return type of this game value
        String returnType,
        /// The description of what this game value returns
        String[] returnDescription,

        /// The parameters this action accepts
        ParameterElementType[] arguments,
        /// The return values of variables put into this action
        ParameterElementType[] returnValues,

        /// Whether this event is cancellable or not
        boolean cancellable,
        /// Whether this event is canceled automatically or not
        boolean cancelledAutomatically
) {
}
