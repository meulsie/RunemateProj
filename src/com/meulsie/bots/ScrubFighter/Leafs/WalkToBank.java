package com.meulsie.bots.ScrubFighter.Leafs;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Walk to bank
 */
public class WalkToBank extends LeafTask {

    private ScrubFighter bot;
    private Coordinate bankCoord;

    public WalkToBank(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if (bot.getPlayer() != null && !bot.getPlayer().isMoving()){
            if(bot.getBankArea().contains(bot.getPlayer())){
                Bank.open();
            } else {
                bankCoord = bot.getBankArea().getRandomCoordinate();
                if (bot.getPlayer() !=null && bankCoord != null) {
                    WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bankCoord);
                    if(path != null){
                        path.step();
                    }
                }
            }
        }
        else
            bot.logText("WalkToBank: player is null or moving");
    }
}
