package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.EatFood;
import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Is there food in inventory?
 */
public class IsFoodInInventory extends BranchTask {

    private ScrubFighter bot;

    public IsFoodInInventory(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return bot.getFood() != null;
    }

    @Override
    public TreeTask failureTask() {
        return bot.isbankopen;
    }

    @Override
    public TreeTask successTask() {
        return bot.eatfood;
    }
}
