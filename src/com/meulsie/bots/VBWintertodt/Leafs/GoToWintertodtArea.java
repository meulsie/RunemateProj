package com.meulsie.bots.VBWintertodt.Leafs;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GameObjects;
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
        GameObject wintertodtDoor = GameObjects.newQuery().names("Doors of Dinh").results().nearest();
        if(wintertodtDoor != null && bot.getPlayer().distanceTo(wintertodtDoor.getPosition()) < 20){
            bot.logText("Interacting with door to enter Wintertodt");
            if(!wintertodtDoor.isVisible()){
                Camera.turnTo(wintertodtDoor);
            }
            wintertodtDoor.interact("Enter");
        } else {
            //WebPath toWintertodt = Traversal.getDefaultWeb().getPathBuilder().buildTo(bot.getWintertodtEntranceArea().getRandomCoordinate());
            RegionPath toWintertodt = RegionPath.buildTo(bot.getWintertodtEntranceArea().getRandomCoordinate());
            if (toWintertodt != null) {
                bot.logText("Couldn't find Wintertodt entrance, walking towards Wintertodt");
                toWintertodt.step(true);
                Execution.delay(200, 5000, 3000);
            } else {
                bot.logText("Path to Wintertodt area is null");
            }
        }
    }
}