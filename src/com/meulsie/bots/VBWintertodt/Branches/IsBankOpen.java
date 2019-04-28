package com.meulsie.bots.VBWintertodt.Branches;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if bank is open
 */

public class IsBankOpen extends BranchTask {

    private VBWintertodt bot;

    public IsBankOpen(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return bot.openbank;
    }

    @Override
    public TreeTask successTask() {
        return bot.depositwillowlogs;
    } //deposit supply crate and withdraw food + required items (knife, hammer, axe, tinderbox)
}
