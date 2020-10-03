package ekkoTheBoyWhoShatteredTime.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.cards.GuinsoosRageblade;
import ekkoTheBoyWhoShatteredTime.relics.zDriveGreediness;
import ekkoTheBoyWhoShatteredTime.relics.zDriveResonance;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class ResochargePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = EkkoMod.makeID("ResochargePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Resocharge84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Resocharge32.png"));

    public ResochargePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = true  ;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0]+ amount + DESCRIPTIONS[1];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (
                (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) && AbstractDungeon.player.hasPower(GuinsoosRagebladePower.POWER_ID)) ||
                (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) && AbstractDungeon.player.hasRelic(zDriveGreediness.ID)) ||
                (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) && AbstractDungeon.player.hasRelic(zDriveResonance.ID)) ||
                card.hasTag(EkkoMod.RESONATE)
        ) {
            return damage + (amount);
        }else
        return damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (
                (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) && AbstractDungeon.player.hasPower(GuinsoosRagebladePower.POWER_ID)) ||
                (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) && AbstractDungeon.player.hasRelic(zDriveGreediness.ID)) ||
                (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) && AbstractDungeon.player.hasRelic(zDriveResonance.ID)) ||
                card.hasTag(EkkoMod.RESONATE)
        ) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new ResochargePower(owner, source, amount);
    }
}
