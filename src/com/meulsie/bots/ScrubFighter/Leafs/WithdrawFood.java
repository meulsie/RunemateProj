package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * withdraw food
 */
public class WithdrawFood extends LeafTask {

    private ScrubFighter bot;

    public WithdrawFood(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if (bot.getPlayer() != null)
            Bank.withdraw("Trout", 10);
            Bank.close();
    }
}
