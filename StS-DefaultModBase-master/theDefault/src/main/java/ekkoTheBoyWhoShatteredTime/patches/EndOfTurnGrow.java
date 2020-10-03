package ekkoTheBoyWhoShatteredTime.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ekkoTheBoyWhoShatteredTime.EkkoMod;

@SpirePatch(
        clz = GameActionManager.class,
        method = "callEndOfTurnActions"
)
public class EndOfTurnGrow {
    public static void Postfix(GameActionManager __instance) {
        EkkoMod.lastTurnAttacked = false;
        for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (q.type == AbstractCard.CardType.ATTACK) {
                EkkoMod.lastTurnAttacked = true;
                break;
            }
        }
    }
}