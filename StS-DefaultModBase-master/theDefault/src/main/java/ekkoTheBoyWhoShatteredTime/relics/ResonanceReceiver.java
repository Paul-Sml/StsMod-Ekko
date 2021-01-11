//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ekkoTheBoyWhoShatteredTime.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.powers.CooldownResonance;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicOutlinePath;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicPath;


public class ResonanceReceiver extends CustomRelic implements OnApplyPowerRelic {
    public static final String ID = EkkoMod.makeID("ResonanceReceiver");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ResonanceReceiver.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ResonanceReceiver.png"));

    public ResonanceReceiver() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ResonanceReceiver();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
    }

    @Override
    public boolean onApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if (abstractPower.ID.equals(Resonance.POWER_ID) && abstractCreature1 == AbstractDungeon.player && !abstractCreature.hasPower(CooldownResonance.POWER_ID) && !abstractCreature.hasPower(ArtifactPower.POWER_ID))
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 1));

        return true;
    }
}
