package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Process extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Process.class.getSimpleName());
    public static final String IMG = makeCardPath("Process.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 2;

    //private int count = 0;


    public Process() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded)
            if (EkkoMod.checkEnergy > 0)
                this.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, EkkoMod.checkEnergy), EkkoMod.checkEnergy));

        if (this.upgraded) {
            this.addToBot(new GainEnergyAction(1));
            if (EkkoMod.checkEnergy+1 > 0)
                this.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, EkkoMod.checkEnergy+1), EkkoMod.checkEnergy+1));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (!this.upgraded)
            this.rawDescription = "Gain Dexterity equal to the amount of extra [E] ([#00FFFF]"+ EkkoMod.checkEnergy +"[]) that you generated this turn. NL Exhaust.";
        if (this.upgraded)
            this.rawDescription = "Gain [E] . NL Then gain Dexterity equal to the amount of extra [E] ([#00FFFF]"+ EkkoMod.checkEnergy +" [#00FFFF]+ [#00FFFF]1[]) that you generated this turn. NL Exhaust.";
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        if (!this.upgraded)
            this.rawDescription = languagePack.getCardStrings(ID).DESCRIPTION;
        if (this.upgraded)
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
    