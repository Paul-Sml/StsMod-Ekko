package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.KeepingMomentumPower;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class KeepingMomentum extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(KeepingMomentum.class.getSimpleName());
    public static final String IMG = makeCardPath("KeepingMomentum.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    public KeepingMomentum() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard cardVariable = this;
        this.addToBot(new ApplyPowerAction(p, p, new KeepingMomentumPower(p, p, 1, cardVariable), 1));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            //this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
