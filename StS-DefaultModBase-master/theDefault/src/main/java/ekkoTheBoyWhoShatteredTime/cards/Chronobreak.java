package ekkoTheBoyWhoShatteredTime.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.ResochargePower;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;

import java.util.List;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Chronobreak extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Chronobreak.class.getSimpleName());
    public static final String IMG = makeCardPath("Chronobreak.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 16;

    // /STAT DECLARATION/


    public Chronobreak(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
        this.exhaust = true;
        this.tags.add(EkkoMod.RESONATE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            //Heal
        p.currentHealth = (int) EkkoMod.hpAtTurnStart;
            //E reco
        /*int x = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            x += c.costForTurn;
        }
        x -= this.costForTurn;*/

        this.addToBot(new GainEnergyAction(EkkoMod.usedEnergy));
            //Damage
        if (EkkoMod.lastTurnAttacked == true) {
            this.addToBot(new ApplyPowerAction(m, p, new Resonance(m, p, 1), 1));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (EkkoMod.lastTurnAttacked == true) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
        /*int x = 0;*/

        if (strength != null) {
            strength.amount *= 2;
        }

        /*for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            x += c.costForTurn;
        }*/

        if (AbstractDungeon.player.getPower(ResochargePower.POWER_ID) != null) {
            baseDamage += AbstractDungeon.player.getPower(ResochargePower.POWER_ID).amount;
        }

        super.applyPowers();

        if (AbstractDungeon.player.getPower(ResochargePower.POWER_ID) != null) {
            baseDamage -= AbstractDungeon.player.getPower(ResochargePower.POWER_ID).amount;
        }

        this.rawDescription = "Heal back to turn start HP. Recover [E] ([#00FFFF]"+EkkoMod.usedEnergy+"[]) used that turn. If you attacked last turn, deal !D! damage. Strength affects this twice. NL ekkotheboywhoshatteredtime:Resonate Exhaust.";
        this.initializeDescription();

        if (strength != null) {
            strength.amount /= 2;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        AbstractPower strength = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
        /*int x = 0;*/

        if (strength != null) {
            strength.amount *= 2;
        }

        if (AbstractDungeon.player.getPower(ResochargePower.POWER_ID) != null) {
            baseDamage += AbstractDungeon.player.getPower(ResochargePower.POWER_ID).amount;
        }

        super.calculateCardDamage(mo);

        if (AbstractDungeon.player.getPower(ResochargePower.POWER_ID) != null) {
            baseDamage -= AbstractDungeon.player.getPower(ResochargePower.POWER_ID).amount;
        }

        if (strength != null) {
            strength.amount /= 2;
        }
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }

    public List<TooltipInfo> getCustomTooltips() {
        return EkkoMod.resonanceTooltip;// 394
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
        }
    }
}
