package com.meulsie.bots.ScrubFighter;

import com.meulsie.bots.ScrubFighter.Branches.IsBankOpen;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.DepositBox;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ScrubFighter extends TreeBot {

    public IsBankOpen isbankopen;
    private boolean LogoutOnStop = false;


    @Override
    public void onStart(String... a) {
        logText("starting ScrubFighter");
        LogoutOnStop = true;
        setLoopDelay(100, 300);
        Execution.delayUntil(RuneScape::isLoggedIn, 100, 6000);
    }

    @Override
    public TreeTask createRootTask(){

        return new IsHealthLow();
    }

    @Override
    public void onPause() {
        super.onPause();
        //timer.stop();
    }

    @Override
    public void onStop() {
        logText("[STOPBOT]: Stopping bot");
        if (RuneScape.isLoggedIn()) {
            if (LogoutOnStop) {
                if (Bank.isOpen()) {
                    if (Bank.close()) {
                        logText("[STOPBOT]: Closing bank");
                        Execution.delayWhile(Bank::isOpen, 150, 6000);
                    } else {
                        logText("[STOPBOT]: Failed to close bank");
                    }
                }
                if (DepositBox.isOpen()) {
                    if (DepositBox.close()) {
                        logText("[STOPBOT]: Closing deposit box");
                        Execution.delayWhile(DepositBox::isOpen, 130, 3000);
                    } else {
                        logText("[STOPBOT]: Failed to close deposit box");
                    }
                }
                if (RuneScape.logout()) {
                    logText("[STOPBOT]: Logging out");
                    Execution.delayWhile(RuneScape::isLoggedIn, 150, 6000);
                } else {
                    logText("[STOPBOT]: Failed logging out");
                }
            } else {
                logText("[STOPBOT]: Not logging out");
            }
        } else {
            logText("[STOPBOT]: Not logged in");
        }
        super.onStop();
        //timer.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        //timer.start();
    }

    public void logText(String outputText) {
        if (outputText != null && !outputText.equals("")) {
            Environment.getBot().getLogger().info("Scrub - " + outputText);
        } else {
            logText("Output text is null or empty");
        }
    }

    public Player getPlayer() { return Players.getLocal(); }
}
