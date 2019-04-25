package com.meulsie.bots.VBWintertodt.Branches;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Check if inventory is empty
 */

public class IsInventoryEmpty extends BranchTask {

    private VBWintertodt bot;

    public IsInventoryEmpty(VBWintertodt bot) {
        this.bot = bot;
    }


    @Override
    public boolean validate() {
        return Inventory.contains("Bruma");
    }

    @Override
    public TreeTask failureTask() {
        return bot.cutbruma;
    }

    @Override
    public TreeTask successTask() {
        return bot.isbankopen;
    }
}
