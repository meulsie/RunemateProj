package com.meulsie.bots.VBWintertodt.Leafs;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Cut willows
 */

public class CutBruma extends LeafTask {

    private VBWintertodt bot;

    public CutBruma(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if (Bank.isOpen()) {
            if (Bank.close()) {
                bot.logText("Closing bank");
                Execution.delayWhile(Bank::isOpen);
            } else {
                bot.logText("Failed to close bank");
            }
        }
        if (!bot.getWintertodtArea().contains(bot.getPlayer())) {
            RegionPath toWintertodt = RegionPath.buildTo(bot.getWintertodtArea().getRandomCoordinate());
            if (toWintertodt != null) {
                if (toWintertodt.step(true)) {
                    bot.logText("Walking to Wintertodt Area");
                    Execution.delayUntil(() -> (bot.getPlayer() != null && bot.getPlayer().getAnimationId() == -1) || GameObjects.newQuery().within(bot.getWintertodtArea()).names(bot.getTreeName()).results().nearest().isVisible(), 100, 5000);
                } else {
                    bot.logText("Failed to step");
                }
            } else {
                bot.logText("toWintertodt is null");
            }
        }

        if (!Bank.isOpen()) {
            GameObject cutableBruma = GameObjects.newQuery().within(bot.getWintertodtArea()).names(bot.getTreeName()).results().nearest();
            if (cutableBruma != null) {
                bot.setWoodCuttingXPBefore(Skill.WOODCUTTING.getExperience());
                if (!cutableBruma.isVisible()) {
                    if (Camera.turnTo(cutableBruma)) {
                        bot.logText("Turning camera to Bruma root");
                        Execution.delayUntil(cutableBruma::isVisible, 100, 5000);
                    } else {
                        bot.logText("Failed to turn camera");
                    }
                }

                if (cutableBruma.interact("Chop")) {
                    // bot.logText("Cutting Willow");
                    Execution.delayWhile(() -> GameObjects.newQuery().on(cutableBruma.getPosition()).names("Tree Stump").results().isEmpty() && !Inventory.isFull() && (ChatDialog.getContinue() == null), 300, 60000);
                } else {
                    bot.logText("No chop down option found.");
                }
            } else {
                bot.logText("No cutable Bruma found.");
            }
        } else {
            bot.logText("Bank is still open?");
        }
    }
}
