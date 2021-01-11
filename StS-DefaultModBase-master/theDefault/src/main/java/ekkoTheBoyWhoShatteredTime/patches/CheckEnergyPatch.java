package ekkoTheBoyWhoShatteredTime.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.powers.FluidEnergyPower;
import ekkoTheBoyWhoShatteredTime.relics.BronzeTube;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "gainEnergy"
)

public class CheckEnergyPatch {
    @SpirePostfixPatch
    public static void postfix(AbstractPlayer __instance, int e) {
        if (!AbstractDungeon.actionManager.turnHasEnded) {
            EkkoMod.checkEnergy += e;
            if (AbstractDungeon.player.hasPower(FluidEnergyPower.POWER_ID)) {
                for (int i = 0; i < e; i++)
                    AbstractDungeon.player.getPower(FluidEnergyPower.POWER_ID).onSpecificTrigger();
            }
            //HERE
            if (AbstractDungeon.player.hasRelic(BronzeTube.ID))
                AbstractDungeon.player.getRelic(BronzeTube.ID).onTrigger();
        }
    }
}

