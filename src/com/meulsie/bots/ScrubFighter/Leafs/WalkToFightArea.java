package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.queries.GameObjectQueryBuilder;
import com.runemate.game.api.hybrid.region.GameObjects;
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
            bot.logText("We aren't in fight area");
            fightCoord = bot.getFightArea().getRandomCoordinate();
            if (bot.getPlayer() != null && fightCoord != null){
                //WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(fightCoord);
                WebPath path = Traversal.getDefaultWeb().getPathBuilder().useMinigameTeleports(false).buildTo(fightCoord);
                if (path != null){
                    bot.logText("moving to fight area");
                    path.step();
                } else {
                    bot.logText("path to fight area is null");
                    GameObject stairs = GameObjects.newQuery().actions("Climb-down").results().nearest();
                    if (!bot.getPlayer().isMoving() && stairs != null){
                        if (stairs.isVisible()){
                            bot.logText("Stairs found and visible, manually clicking them");
                            stairs.interact("Climb-down");
                        } else {
                            Camera.turnTo(stairs);
                        }
                    } else {
                        bot.logText("Stairs are null");
                    }
                }
            }
        }
    }
}
