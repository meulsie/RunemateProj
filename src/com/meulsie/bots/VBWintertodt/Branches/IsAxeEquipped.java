package com.meulsie.bots.VBWintertodt.Branches;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if we have an axe (equipped)
 */
public class IsAxeEquipped extends BranchTask {

    private VBWintertodt bot;

    public IsAxeEquipped(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return Equipment.containsAnyOf(bot.getAxePattern()) || Inventory.contains(bot.getAxePattern()); //Does inventory/Player contain Axe, Tinder box, knife, hammer and FOOD
    }

    @Override
    public TreeTask failureTask() {
        return bot.stopbot;
    }

    @Override
    public TreeTask successTask() {
        return bot.isinventoryfull;
    }
}
