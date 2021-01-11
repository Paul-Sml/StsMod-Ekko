//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ekkoTheBoyWhoShatteredTime.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.powers.DelayedDamage;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicOutlinePath;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicPath;


public class TwistedClockwork extends CustomRelic implements OnApplyPowerRelic {
    public static final String ID = EkkoMod.makeID("TwistedClockwork");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TwistedClockwork.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TwistedClockwork.png"));

    public TwistedClockwork() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TwistedClockwork();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 3 + DESCRIPTIONS[1];
    }

    @Override
    public boolean onApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        return true;
    }

    @Override
    public int onApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (source == AbstractDungeon.player && !target.isPlayer && power.ID.equals(DelayedDamage.POWER_ID)) {
            this.flash();
            power.amount += 3;
            return stackAmount + 3;
        }
        if (source == AbstractDungeon.player && target.isPlayer && power.ID.equals(NextTurnBlockPower.POWER_ID)) {
            this.flash();
            power.amount += 3;
            return stackAmount + 3;
        }
        return stackAmount;
    }
}
