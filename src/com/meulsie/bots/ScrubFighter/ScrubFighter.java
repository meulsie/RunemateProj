package com.meulsie.bots.ScrubFighter;

import com.meulsie.bots.ScrubFighter.Branches.*;
import com.meulsie.bots.ScrubFighter.Leafs.*;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.DepositBox;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.ArrayList;
import java.util.List;

public class ScrubFighter extends TreeBot {

    public IsBankOpen isbankopen;
    public IsHealthLow ishealthlow;
    public IsLootableNearby islootablenearby;
    public IsPlayerInCombat isplayerincombat;
    public IsPlayerInFightArea isplayerinfightarea;
    public IsTimerUp istimerup;
    public AttackTarget attacktarget;
    public ChooseNewNPC choosenewnpc;
    public EatFood eatfood;
    public LootItems lootitems;
    public WaitForFight waitforfight;
    public WalkToBank walktobank;
    public WalkToFightArea walktofightarea;
    public WithdrawFood withdrawfood;

    private boolean LogoutOnStop = false;
    private StopWatch stopWatchTotal = new StopWatch();
    private StopWatch stopWatchActivity = new StopWatch();

    private List<ItemDefinition> equippedItems = new ArrayList<>();

    private static final Area BankArea = new Area.Rectangular(new Coordinate(3206, 3219, 2), new Coordinate(3209, 3217, 2));
    private static final Area SwampArea = new Area.Polygonal(
            new Coordinate(3181, 3196, 0),
            new Coordinate(3223, 3197, 0),
            new Coordinate(3239, 3185, 0),
            new Coordinate(3233, 3159, 0),
            new Coordinate(3174, 3154, 0),
            new Coordinate(3158, 3198, 0)
    );

    @Override
    public void onStart(String... a) {
        stopWatchTotal.start();
        stopWatchActivity.start();
        logText("starting ScrubFighter");
        LogoutOnStop = true;
        setLoopDelay(100, 300);
        Execution.delayUntil(RuneScape::isLoggedIn, 100, 6000);
        equippedItems.addAll(getPlayer().getEquipment());
        System.out.println(equippedItems);
    }

    @Override
    public TreeTask createRootTask(){
        //Branches
        isbankopen = new IsBankOpen(this);
        ishealthlow = new IsHealthLow(this);
        islootablenearby = new IsLootableNearby(this);
        isplayerincombat = new IsPlayerInCombat(this);
        isplayerinfightarea = new IsPlayerInFightArea(this);
        istimerup = new IsTimerUp(this);

        //Leafs
        attacktarget = new AttackTarget(this);
        choosenewnpc = new ChooseNewNPC(this);
        eatfood = new EatFood(this);
        lootitems = new LootItems(this);
        waitforfight = new WaitForFight(this);
        walktobank = new WalkToBank(this);
        walktofightarea = new WalkToFightArea(this);
        withdrawfood = new WithdrawFood(this);

        return new IsFoodInInventory(this);
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

    public SpriteItem getFood() { return Inventory.newQuery().actions("Eat").results().random(); }

    public StopWatch getStopWatchTotal(){ return stopWatchTotal; }

    public StopWatch getStopWatchActivity(){ return stopWatchActivity; }

    public Area getFightArea() { return SwampArea; }

    public Area getBankArea() { return BankArea; }

    public String getTarget(){ return "Giant frog"; }

    public Boolean getFoodReq(){ return true; }
}

/* NOTES / TO-DO
-I've added get equipment into onStart() this will fail if not logged in and throw an error

 */