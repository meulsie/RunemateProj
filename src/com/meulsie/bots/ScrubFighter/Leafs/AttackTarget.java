package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Attack selected NPC
 */
public class AttackTarget extends LeafTask {

    private ScrubFighter bot;

    public AttackTarget(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {

    }
}
