package com.meulsie.bots.VBWintertodt.Leafs;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Stops the bot
 */

public class StopBot extends LeafTask {

    private VBWintertodt bot;

    public StopBot(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.stop("Bot stopped: Unknown reason");
    }
}
