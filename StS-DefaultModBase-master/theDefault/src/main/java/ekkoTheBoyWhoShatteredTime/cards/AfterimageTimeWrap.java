package ekkoTheBoyWhoShatteredTime.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.EndTurnDeathPower;
import com.megacrit.cardcrawl.powers.watcher.VaultPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.*;

import java.util.ArrayList;
import java.util.List;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class AfterimageTimeWrap extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(AfterimageTimeWrap.class.getSimpleName());
    public static final String IMG = makeCardPath("AfterimageTimeWrap.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;

    private static final int DAMAGE = 4;
    private static final int UPGRADE_PLUS_DMG = 2;

    protected List<TooltipInfo> customTooltips;

    // /STAT DECLARATION/


    public AfterimageTimeWrap(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        if (customTooltips == null) {
            customTooltips = super.getCustomTooltips();
            if (customTooltips == null) {
                customTooltips = new ArrayList<>();
            }
            this.customTooltips.addAll(EkkoMod.resonanceTooltip);
            this.customTooltips.addAll(EkkoMod.dupTooltip);
        }
        return customTooltips;
    }

    public static int countFactor(AbstractPlayer p, AbstractMonster m) {
        int factor = 0;

        //Death
        if (p.hasPower(EndTurnDeathPower.POWER_ID)) {
            factor ++;
        }

        //Play again
        if (p.hasPower(VaultPower.POWER_ID)) {
            factor ++;
        }

        //Phantasmal power
        if (p.hasPower(PhantasmalPower.POWER_ID)) {
            factor ++;
        }

        //Nightmare
        if (p.hasPower(NightmarePower.POWER_ID)) {
            factor ++;
        }

        //Energy
        if (p.hasPower(EnergizedPower.POWER_ID) || p.hasPower(EnergizedBluePower.POWER_ID)) {
            factor ++;
        }

        //Draw
        if (p.hasPower(DrawCardNextTurnPower.POWER_ID)) {
            factor ++;
        }

        //Discard
        if (p.hasPower(DrawReductionPower.POWER_ID)) {
            factor ++;
        }

        //Block
        if (p.hasPower(NextTurnBlockPower.POWER_ID)) {
            factor ++;
        }

        //Lose str
        if (p.hasPower(LoseStrengthPower.POWER_ID)) {
            factor ++;
        }

        //Lose dext
        if (p.hasPower(LoseDexterityPower.POWER_ID)) {
            factor ++;
        }

        //Gain str
        if (p.hasPower(GainStrengthPower.POWER_ID) || p.hasPower(GainStrengthPowerBuff.POWER_ID)) {
            factor ++;
        }

        //Resonance
        if (p.hasPower(DelayedResonance.POWER_ID)) {
            factor ++;
        }

        //Damage
        if (p.hasPower(DelayedDamage.POWER_ID)) {
            factor ++;
        }

        int re;
        int da;

        //
        //MONSTER
        //

        //Death
        if (m.hasPower(EndTurnDeathPower.POWER_ID)) {
            factor ++;
        }

        //Phantasmal power
        if (m.hasPower(PhantasmalPower.POWER_ID)) {
            factor ++;
        }

        //Block
        if (m.hasPower(NextTurnBlockPower.POWER_ID)) {
            factor ++;
        }

        //Resonance
        if (m.hasPower(DelayedResonance.POWER_ID)) {
            factor ++;
        }

        //Lose str
        if (m.hasPower(LoseStrengthPower.POWER_ID)) {
            factor ++;
        }

        //Lose dext
        if (m.hasPower(LoseDexterityPower.POWER_ID)) {
            factor ++;
        }

        //Gain str
        if (m.hasPower(GainStrengthPower.POWER_ID)) {
            factor ++;
        }

        //Gain str 2
        if (m.hasPower(GainStrengthPowerBuff.POWER_ID)) {
            factor ++;
        }

        //Damage
        if (m.hasPower(DelayedDamage.POWER_ID)) {
            factor ++;
        }

        //Parallel convergence
        if (m.hasPower(ParallelConvergencePower.POWER_ID) && m.getIntentBaseDmg() >= 0) {
            factor ++;
        }

        return factor;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0 ; i < countFactor(p, m) ; i ++) {
            this.addToBot(new ApplyPowerAction(m, p, new Resonance(m, p, 1), 1));
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) { ;
        super.calculateCardDamage(mo);
        this.rawDescription = "Deal !D! damage for each delayed effect ([#00FFFF]" + countFactor(AbstractDungeon.player, mo) +"[]) on you and the target. NL ekkotheboywhoshatteredtime:Resonate.";
        this.initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
