package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Select a new NPC to attack at random (Seagulls, north lumby Cows, Goblins, Frogs, Men) 
Potentially have different attack styles (melee, mage, ranged)
Restart timer
 */
public class ChooseNewNPC extends LeafTask {

    private ScrubFighter bot;

    public ChooseNewNPC(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        //Choose next NPC
        bot.getStopWatchActivity().reset();
    }
}
