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
        Npc target = Npcs.newQuery().names(bot.getTarget()).actions("Attack").targeting(bot.getPlayer()).results().nearest();
        if (target == null){
            bot.logText("No target found that was already attacking me, finding a new target");
            target = Npcs.newQuery().names(bot.getTarget()).actions("Attack").filter(i -> i.getTarget() == null).results().nearest();
            if (target != null){
                if(!target.isVisible()){
                    Camera.turnTo(target);
                }
                if(!bot.getPlayer().isMoving() && target.isVisible()){
                    if(target.interact("Attack")){
                        Execution.delayWhile(() -> bot.getPlayer().getTarget() != null, 100, 3000);
                    }
                } else {
                    BresenhamPath path = BresenhamPath.buildTo(target);
                    if (path != null){
                        path.step();
                    }
                }
            }
        }
    }
}
