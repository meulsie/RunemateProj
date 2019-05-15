package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * This leaf can potentially be deleted, otherwise check we are on the right fight style otherwise pause?
 */
public class WaitForFight extends LeafTask {

    private ScrubFighter bot;

    public WaitForFight(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {

    }
}
