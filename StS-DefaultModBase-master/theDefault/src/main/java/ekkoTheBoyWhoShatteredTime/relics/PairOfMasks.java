//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ekkoTheBoyWhoShatteredTime.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicOutlinePath;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicPath;

public class PairOfMasks extends CustomRelic {
    public static final String ID = EkkoMod.makeID("PairOfMasks");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PairOfMasks.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PairOfMasks.png"));

    public PairOfMasks() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.hasTag(EkkoMod.ITEM)){
            this.addToBot(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomColorlessCardInCombat().makeCopy(), 1));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
