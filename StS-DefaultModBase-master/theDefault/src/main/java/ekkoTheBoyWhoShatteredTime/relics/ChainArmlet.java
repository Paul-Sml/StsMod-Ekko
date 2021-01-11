//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ekkoTheBoyWhoShatteredTime.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicOutlinePath;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicPath;

public class ChainArmlet extends CustomRelic implements OnReceivePowerRelic {
    public static final String ID = EkkoMod.makeID("ChainArmlet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ChainArmlet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ChainArmlet.png"));

    public ChainArmlet() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature source) {
        if (power.ID.equals(WeakPower.POWER_ID))
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        if (power.ID.equals(FrailPower.POWER_ID))
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));

        return true;
    }
}
