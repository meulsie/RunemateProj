package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Walk to selected NPC's area
 */
public class WalkToFightArea extends LeafTask {

    private ScrubFighter bot;
    private Coordinate fightCoord;

    public WalkToFightArea(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if (bot.getPlayer() != null && !bot.getFightArea().contains(bot.getPlayer())){
            fightCoord = bot.getFightArea().getRandomCoordinate();
            if (bot.getPlayer() != null && fightCoord != null){
                WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(fightCoord);
                if (path != null){
                    path.step();
                }
            }
        }
    }
}
