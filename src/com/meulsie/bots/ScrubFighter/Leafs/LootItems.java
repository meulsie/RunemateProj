package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Check for nearby loot
 */
public class LootItems extends LeafTask {

    private ScrubFighter bot;
    private String[] lootables = new String[]{"Bones", "Cowhide", "Coins"};
    private GroundItem loot;

    public LootItems(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if(bot.getPlayer() != null && !bot.getPlayer().isMoving()){
            if (!Inventory.isFull()){
                loot = GroundItems.newQuery().names(lootables).results().nearest(); //Add a "within area" to query
                if (loot != null){
                    if (!loot.isVisible()){
                        Camera.turnTo(loot);
                        Execution.delay(2000,3000);
                    } else if (loot.interact("Take")) {
                        bot.logText("looting: " + loot.toString());
                    }
                }
            }
        }
    }
}
