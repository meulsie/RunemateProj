package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Attack selected NPC
 */
public class AttackTarget extends LeafTask {

    private ScrubFighter bot;
    //Giant frog, Big frog, Frog, Giant rat
    public AttackTarget(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        Npc target = Npcs.newQuery().names(bot.getTarget()).actions("Attack").filter(i -> i.getHealthGauge() == null && i.getAnimationId() == -1).results().nearest();
        if (target != null){
            if(!target.isVisible()){
                Camera.turnTo(target);
            }
            if(!bot.getPlayer().isMoving()){
                if (target.isVisible()){
                    if(target.interact("Attack")){
                        bot.logText("Target found, visible and attacking");
                        Execution.delayUntil(() -> bot.getPlayer().isMoving(), 1000, 3000);
                    }
                } else {
                    bot.logText("target is found but not visible so Bresenham to target location");
                    BresenhamPath path = BresenhamPath.buildTo(target);
                    if (path != null){
                        path.step();
                    }
                }
            }
        } else {
            bot.logText("Moving around to find a target");
            BresenhamPath searchPath = BresenhamPath.buildTo(bot.getFightArea().getRandomCoordinate());
            if(searchPath != null && bot.getPlayer() != null){
                searchPath.step();
            }
        }
    }
}
