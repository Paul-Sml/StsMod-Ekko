package ekkoTheBoyWhoShatteredTime.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import javassist.CtBehavior;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.ResonanceCheck;

@SpirePatch(
        clz = GameActionManager.class,
        method = "getNextAction"
)
public class TurnStartCheck {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(GameActionManager __instance) {
        EkkoMod.gainedStrDexThisTurn = false;
        EkkoMod.hpAtTurnStart = AbstractDungeon.player.currentHealth;
        ResonanceCheck = false;
        EkkoMod.checkEnergy = 0;
        EkkoMod.usedEnergy = 0;
    }
    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnRelics");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}