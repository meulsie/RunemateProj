package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.LootItems;
import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Check for nearby loot
 */
public class IsLootableNearby extends BranchTask {

    private ScrubFighter bot;

    public IsLootableNearby(ScrubFighter bot){this.bot = bot;}

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return bot.isplayerinfightarea;
    }

    @Override
    public TreeTask successTask() {
        return bot.lootitems;
    }
}
