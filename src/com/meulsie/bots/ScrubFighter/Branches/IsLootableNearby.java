package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.LootItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Check for nearby loot
 */
public class IsLootableNearby extends BranchTask {

    private LootItems lootitems = new LootItems();
    private IsPlayerInFightArea isplayerinfightarea = new IsPlayerInFightArea();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isplayerinfightarea;
    }

    @Override
    public TreeTask successTask() {
        return lootitems;
    }
}
