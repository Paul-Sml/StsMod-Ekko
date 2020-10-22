package ekkoTheBoyWhoShatteredTime.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.DelayedDamage;
import ekkoTheBoyWhoShatteredTime.powers.DelayedResonance;
import ekkoTheBoyWhoShatteredTime.powers.ResochargePower;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;

import java.util.List;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class SpHit extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(SpHit.class.getSimpleName());
    public static final String IMG = makeCardPath("SpHit.png");


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 2;

    /*private static final int DAMAGE_SECOND_OCCURRENCE = 7;
    private static final int UPGRADE_DAMAGE_SECOND_OCCURRENCE = 4;
    private static int FINAL_DAMAGE_SECOND_OCCURRENCE = 0;*/

    public SpHit() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage =DAMAGE;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(EkkoMod.RESONATE);
    }

    @Override
    public void applyPowers()
    {
        magicNumber = baseMagicNumber;

        int tmp = baseDamage;
        baseDamage = baseMagicNumber;

        AbstractPower strength = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
        if (strength != null) {
            baseDamage -= strength.amount;
        }

        AbstractPower dext = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
        if (dext != null) {
            baseDamage += dext.amount;
        }

        if (AbstractDungeon.player.getPower(ResochargePower.POWER_ID) != null) {
            baseDamage -= AbstractDungeon.player.getPower(ResochargePower.POWER_ID).amount;
        }

        super.applyPowers();

        magicNumber = damage;
        baseDamage = tmp;

        super.applyPowers();

        isMagicNumberModified = (magicNumber != baseMagicNumber);

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        magicNumber = baseMagicNumber;

        int tmp = baseDamage;
        baseDamage = baseMagicNumber;

        AbstractPower strength = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
        if (strength != null) {
            baseDamage -= strength.amount;
        }

        AbstractPower dext = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
        if (dext != null) {
            baseDamage += dext.amount;
        }

        if (AbstractDungeon.player.getPower(ResochargePower.POWER_ID) != null) {
            baseDamage -= AbstractDungeon.player.getPower(ResochargePower.POWER_ID).amount;
        }

        super.calculateCardDamage(mo);


        magicNumber = damage;
        baseDamage = tmp;

        super.calculateCardDamage(mo);

        isMagicNumberModified = (magicNumber != baseMagicNumber);
    }

    public List<TooltipInfo> getCustomTooltips() {
        return EkkoMod.resonanceTooltip;// 394
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //DAMAGE
        this.addToBot(new ApplyPowerAction(m, p, new Resonance(m, p, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        this.addToBot(new ApplyPowerAction(m, p, new DelayedResonance(m, p, 1), 1));
        this.addToBot(new ApplyPowerAction(m, p, new DelayedDamage(m, p, magicNumber), magicNumber));

    }

    @Override
    public void onMoveToDiscard()
    {
        magicNumber = baseMagicNumber;
        isMagicNumberModified = false;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(2);
            //this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
