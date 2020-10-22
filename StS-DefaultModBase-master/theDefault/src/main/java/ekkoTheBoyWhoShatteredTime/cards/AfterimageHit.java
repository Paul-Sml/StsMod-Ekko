package ekkoTheBoyWhoShatteredTime.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.DelayedDamage;
import ekkoTheBoyWhoShatteredTime.powers.DelayedResonance;

import java.util.List;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class AfterimageHit extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(AfterimageHit.class.getSimpleName());
    public static final String IMG = makeCardPath("AfterimageHit.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;

    public AfterimageHit() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage =DAMAGE;
        this.tags.add(EkkoMod.RESONATE);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new DelayedResonance(m, p, 1), 1));
        this.addToBot(new ApplyPowerAction(m, p, new DelayedDamage(m, p, damage), damage));
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 1), 1));
    }

    public List<TooltipInfo> getCustomTooltips() {
        return EkkoMod.resonanceTooltip;// 394
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
