package com.meulsie.bots.VBWintertodt.Leafs;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Deposits willow logs into the bank
 */

public class DepositWillowLogs extends LeafTask {

    private VBWintertodt bot;

    public DepositWillowLogs(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.logText("Depositing.");
        if (!Inventory.containsOnly(bot.getAxePattern())) {
            if (Bank.depositAllExcept(bot.getAxePattern())) {
                bot.logText("Depositing all expect axes");
                Execution.delayUntil(() -> Inventory.containsOnly(bot.getAxePattern()), 100, 5000);
            } else {
                bot.logText("Failed to deposit all expect axes");
            }
        }
        if (Bank.open()) {
            if (Bank.close()) {
                bot.logText("Closing bank");
                Execution.delayWhile(Bank::isOpen, 150, 5500);
            } else {
                bot.logText("Failed to close bank");
            }
        }
    }
}
