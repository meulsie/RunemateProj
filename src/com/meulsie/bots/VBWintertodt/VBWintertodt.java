package com.meulsie.bots.VBWintertodt;

import com.meulsie.bots.VBWintertodt.Branches.IsAxeEquipped;
import com.meulsie.bots.VBWintertodt.Branches.IsHealthLow;
import com.meulsie.bots.VBWintertodt.Branches.IsBankOpen;
import com.meulsie.bots.VBWintertodt.Branches.IsBrazierActive;
import com.meulsie.bots.VBWintertodt.Branches.IsInventoryFull;
import com.meulsie.bots.VBWintertodt.Branches.IsFoodInInventory;
import com.meulsie.bots.VBWintertodt.Leafs.*;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.DepositBox;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.regex.Pattern;

/**
 * Created by Meulsie on 25/04/2019.
 * Main Class
 */

public class VBWintertodt extends TreeBot implements InventoryListener {

    public IsHealthLow ishealthlow;
    public IsInventoryFull isinventoryfull;
    public IsBankOpen isbankopen;
    public CutBruma cutbruma;
    public EatFood eatfood;
    public IsBrazierActive isbrazieractive;
    public DepositWillowLogs depositwillowlogs;
    public OpenBank openbank;
    public StopBot stopbot;
    public IsAxeEquipped isaxeequipped;
    public IsFoodInInventory isfoodinventory;
    GoToWintertodtArea gotowintertodtarea;

    private static final Area WintertodtArea = new Area.Rectangular(new Coordinate(1608, 4029, 0), new Coordinate(1652, 3967, 0));
    private static final Area BankArea = new Area.Rectangular(new Coordinate(3092, 3245, 0), new Coordinate(3095, 3241, 0));
    private static final Area LumbridgeArea = new Area.Rectangular(new Coordinate(3217, 3212, 0), new Coordinate(3226, 3225, 0));
    private static final Coordinate bankCoordinate = new Coordinate(3091, 3245, 0);
    private static final Coordinate InWintertodtArea = new Coordinate(1629, 4004, 0);
    private boolean LogoutOnStop = false;
    private int woodCuttingXPBefore;
    private Pattern axePattern = Pattern.compile("axe");
    private String[] Axes = new String[]{"Bronze axe", "Iron axe", "Steel axe", "Black axe", "Mithril axe", "Adamant axe", "Rune axe", "Dragon axe", "Infernal axe"};
    private String treeName;
    private String foodName;

    @Override
    public void onStart(String... a) {
        logText("Starting bot ...");
        setTreeName("Bruma roots");
        setFoodName("Cake");
        LogoutOnStop = true;
        setLoopDelay(100, 300);
        Execution.delayUntil(RuneScape::isLoggedIn, 100, 6000);
    }

    @Override
    public TreeTask createRootTask() {
        // branches
        isinventoryfull = new IsInventoryFull(this);
        isaxeequipped = new IsAxeEquipped(this);
        isbankopen = new IsBankOpen(this);

        // leafs
        eatfood = new EatFood(this);
        cutbruma = new CutBruma(this);
        depositwillowlogs = new DepositWillowLogs(this);
        gotowintertodtarea = new GoToWintertodtArea(this);
        openbank = new OpenBank(this);
        stopbot = new StopBot(this);

        return new InWintertodtArea(this);
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
            Environment.getBot().getLogger().info("Wi - " + outputText);
        } else {
            logText("Output text is null or empty");
        }
    }

    public Area getWintertodtArea() {
        return WintertodtArea;
    }

    public int getWoodCuttingXPBefore() {
        return woodCuttingXPBefore;
    }

    public void setWoodCuttingXPBefore(int woodCuttingXPBefore) {
        this.woodCuttingXPBefore = woodCuttingXPBefore;
    }

    public Player getPlayer() {
        return Players.getLocal();
    }

    public Coordinate getBankCoordinate() {
        return bankCoordinate;
    }

    public Area getBankArea() {
        return BankArea;
    }

    Coordinate getInWintertodtArea() {
        return InWintertodtArea;
    }

    public String[] getAxes() {
        return Axes;
    }

    public Pattern getAxePattern() {
        return axePattern;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getFoodName() { return foodName; }

    public void setFoodName(String foodName) { this.foodName = foodName; }

    public static Area getLumbridgeArea() {
        return LumbridgeArea;
    }

}
