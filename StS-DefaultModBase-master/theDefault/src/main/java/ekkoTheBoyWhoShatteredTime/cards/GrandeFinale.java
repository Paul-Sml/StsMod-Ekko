package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.actions.GrandeFinaleAction;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class GrandeFinale extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(GrandeFinale.class.getSimpleName());
    public static final String IMG = makeCardPath("GrandeFinale.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 2;

    private static final int DAMAGE = 17;
    private static final int UPGRADE_PLUS_DMG = 4;

    // /STAT DECLARATION/

    public GrandeFinale(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
        this.baseMagicNumber = 15;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int h = 3;
        if (this.upgraded)
            h += 2;
        this.addToBot(new GrandeFinaleAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber, h));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(5);
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
