package com.meulsie.bots.VBWintertodt.Leafs;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.cache.sprites.Sprites;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.queries.SpriteItemQueryBuilder;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Deposits willow logs into the bank
 */

public class EatFood extends LeafTask {

    private VBWintertodt bot;

    public EatFood(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.logText("health is low eating food");
        SpriteItem Food = Inventory.newQuery().names(bot.getFoodName()).actions("Eat").results().first();
        if (Food != null) {
            Food.interact("Eat");
        }
    }
}
