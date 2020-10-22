package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Overflow extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Overflow.class.getSimpleName());
    public static final String IMG = makeCardPath("Overflow.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 2;

    private AbstractPlayer p;

    public Overflow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(StrengthPower.POWER_ID) && p.getPower(StrengthPower.POWER_ID).amount > 0) {
            this.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
            this.addToBot(new RemoveSpecificPowerAction(p, p, WeakPower.POWER_ID));
        }
        if(p.hasPower(DexterityPower.POWER_ID) && p.getPower(DexterityPower.POWER_ID).amount > 0) {
            this.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
            this.addToBot(new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}
