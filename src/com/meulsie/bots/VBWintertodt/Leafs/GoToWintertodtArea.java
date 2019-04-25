package com.meulsie.bots.VBWintertodt.Leafs;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Goes to Wintertodt area
 */

public class GoToWintertodtArea extends LeafTask {

    private VBWintertodt bot;

    public GoToWintertodtArea(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if (VBWintertodt.getLumbridgeArea().contains(bot.getPlayer())) {
            bot.logText("In Lumbridge Stopping bot");
            RuneScape.logout();
            Execution.delayWhile(RuneScape::isLoggedIn, 10, 5000);
            bot.stop("In  Lumbridge stopping bot");
        }
        RegionPath toWintertodt = RegionPath.buildTo(bot.getWintertodtArea().getRandomCoordinate());
        if (toWintertodt != null) {
            bot.logText("Walking to Wintertodt");
            toWintertodt.step(true);
            Execution.delay(200, 5000, 3000);
        } else {
            bot.logText("Path is null");
        }
    }
}