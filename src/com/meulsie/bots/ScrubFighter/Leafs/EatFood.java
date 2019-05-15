package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Eat food
 */
public class EatFood extends LeafTask {

    private ScrubFighter bot;

    public EatFood(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if (bot.getPlayer() != null){
            if (bot.getFood().interact("Eat")){
                bot.logText("eating food");
            }
        }
    }
}
