package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Walk to bank
 */
public class WalkToBank extends LeafTask {

    private ScrubFighter bot;

    public WalkToBank(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if (bot.getPlayer() != null && !bot.getPlayer().isMoving())
            Bank.open();
        else
            bot.logText("WalkToBank: player is null or moving");
    }
}
