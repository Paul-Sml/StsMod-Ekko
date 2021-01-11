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
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;

import java.util.List;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Greed extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Greed.class.getSimpleName());
    public static final String IMG = makeCardPath("Greed.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;

    private static final int DAMAGE = 13;
    private static final int UPGRADE_PLUS_DMG = 4;

    // /STAT DECLARATION/


    public Greed(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(EkkoMod.RESONATE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(p, new DamageInfo(p, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
        if (m.hasPower(Resonance.POWER_ID) && m.getPower(Resonance.POWER_ID).amount == 2)
            this.addToBot(new GainEnergyAction(1));
        this.addToBot(new ApplyPowerAction(m, p, new Resonance (m, p, 1), 1));
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (q.hasPower(Resonance.POWER_ID) && q.getPower(Resonance.POWER_ID).amount == 2) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public List<TooltipInfo> getCustomTooltips() {
        return EkkoMod.resonanceTooltip;// 394
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(-1);
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
