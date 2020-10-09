package ekkoTheBoyWhoShatteredTime.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.actions.KeepingMomentumAction;
import ekkoTheBoyWhoShatteredTime.relics.zDriveGreediness;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.ResonanceCheck;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class Resonance extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = EkkoMod.makeID("Resonance");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Resonance84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Resonance32.png"));

    public Resonance(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.DEBUFF;
        isTurnBased = true  ;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 3) {
            ResonanceCheck = true;
            this.addToBot(new DrawCardAction(2));
            if (AbstractDungeon.player.hasRelic(zDriveGreediness.ID)){
                this.addToBot(new GainEnergyAction(1));
            }
            if (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID) && AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount>0) {
                this.addToBot(new DamageAction(this.owner, new DamageInfo(AbstractDungeon.player, (AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SMASH));
            }
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 4));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new CooldownResonance(this.owner), -1));
            if (AbstractDungeon.player.hasPower(AcademyAttractionPower.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
            }
            if (AbstractDungeon.player.hasPower(UpgradedAcademyAttractionPower.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
            }
            if (AbstractDungeon.player.hasPower(KeepingMomentumPower.POWER_ID)) {
                this.addToBot(new KeepingMomentumAction(((KeepingMomentumPower) AbstractDungeon.player.getPower(KeepingMomentumPower.POWER_ID)).c));
            }
        }

    }

    public void atStartOfTurn() {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new Resonance(owner, source, amount);
    }
}
