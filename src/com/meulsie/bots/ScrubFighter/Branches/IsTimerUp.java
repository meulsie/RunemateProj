package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.concurrent.TimeUnit;

/**
 * NOTES:
 * Is time up for our selected NPC?
 */
public class IsTimerUp extends BranchTask {

    private ScrubFighter bot;

    public IsTimerUp(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return bot.getStopWatchActivity().getRuntime(TimeUnit.MINUTES) > (30 + Random.nextLong(-5, 10));
    }

    @Override
    public TreeTask failureTask() {
        return bot.islootablenearby;
    }

    @Override
    public TreeTask successTask() {
        return bot.choosenewnpc;
    }
}
