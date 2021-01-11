//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ekkoTheBoyWhoShatteredTime.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.*;

public class zDriveGreediness extends CustomRelic {
    public static final String ID = EkkoMod.makeID("zDriveGreediness");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("device_2.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("device.png"));

    public zDriveGreediness() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.hasTag(AbstractCard.CardTags.STARTER_STRIKE) && useCardAction.target != null){
            this.addToTop(new ApplyPowerAction(useCardAction.target, AbstractDungeon.player, new Resonance(useCardAction.target, AbstractDungeon.player, 1), 1));
        }
    }

    public void onPlayerEndTurn() {
        if (ResonanceCheck == false) {
            //System.out.println("hello");
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, 1, false), 1));
        }
    }

    /*@Override
    public void atStartOfTurn() {
        if (AbstractDungeon.player.hasRelic(zDriveGreediness.ID) && check == false){
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, 2, false), 2));
            this.addToBot(new LoseEnergyAction(1));
        }*/

    @Override
    public void obtain() {
        // Replace the starter relic, or just give the relic if starter isn't found
        if (AbstractDungeon.player.hasRelic(zDriveResonance.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(zDriveResonance.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(zDriveResonance.ID);
    }
}
