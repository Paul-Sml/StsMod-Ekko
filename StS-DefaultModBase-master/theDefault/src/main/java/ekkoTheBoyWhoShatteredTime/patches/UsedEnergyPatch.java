package ekkoTheBoyWhoShatteredTime.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import ekkoTheBoyWhoShatteredTime.EkkoMod;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "useEnergy"
)

public class UsedEnergyPatch {
    /*@SpireInsertPatch(
            rloc = 1
    )*/
    /*public static void Insert(GainEnergyAction __instance) {
        int energyGained = (int) ReflectionHacks.getPrivate( __instance, GainEnergyAction.class, "energyGain");
        */
    public static void Prefix(int e) {
        EkkoMod.usedEnergy += Math.min(EnergyPanel.totalCount, e);
    }
}

