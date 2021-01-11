package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.SheenPower;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class IcebornGauntlet extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Hold Place Gain 1(2) Keywords(s).
     */


    // TEXT DECLARATION

    public static final String ID = EkkoMod.makeID(IcebornGauntlet.class.getSimpleName());
    public static final String IMG = makeCardPath("IcebornGauntlet.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 2;
//    private static final int UPGRADED_COST = 1;

    public IcebornGauntlet() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 4;
        this.baseDamage = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(EkkoMod.ITEM);
    }

    public void applyPowers() {
    }

    public void calculateCardDamage(AbstractMonster mo) {
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded)
            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
        if (this.upgraded)
            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 3), 3));
        this.addToBot(new ApplyPowerAction(p, p, new SheenPower(p, p, magicNumber), magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeDamage(1);
            initializeDescription();
        }
    }
}