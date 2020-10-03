package ekkoTheBoyWhoShatteredTime.relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import java.util.List;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicOutlinePath;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicPath;

public class zDriveResonance extends CustomRelic {

    // ID, images, text.
    public static final String ID = EkkoMod.makeID("zDriveResonance");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("device.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("device.png"));

    public zDriveResonance() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.hasTag(AbstractCard.CardTags.STARTER_STRIKE) && useCardAction.target != null){
            this.addToTop(new ApplyPowerAction(useCardAction.target, AbstractDungeon.player, new Resonance(useCardAction.target, AbstractDungeon.player, 1), 1));
        }
    }

    public List<TooltipInfo> getCustomTooltips() {
        return EkkoMod.resonanceTooltip;// 394
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
