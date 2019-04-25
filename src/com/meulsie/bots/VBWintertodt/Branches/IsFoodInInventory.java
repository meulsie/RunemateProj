package com.meulsie.bots.VBWintertodt.Branches;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if we have an axe (equipped)
 */
public class IsFoodInInventory extends BranchTask {

    private VBWintertodt bot;

    public IsFoodInInventory(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return Inventory.containsAnyOf(bot.getFoodName());
    }

    @Override
    public TreeTask failureTask() {
        return bot.stopbot;
    }

    @Override
    public TreeTask successTask() {
        return bot.eatfood;
    }
}
