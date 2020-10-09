package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class SandstormSympathy extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(SandstormSympathy.class.getSimpleName());
    public static final String IMG = makeCardPath("SandstormSympathy.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;
    //private static final int UPGRADED_COST = 0;

    public SandstormSympathy() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!this.upgraded) {
            this.addToBot(new MakeTempCardInHandAction(new Timewinder(), 1));
            this.addToBot(new MakeTempCardInHandAction(new ParallelConvergence(), 1));
            this.addToBot(new MakeTempCardInHandAction(new PhaseDive(), 1));
            this.addToBot(new MakeTempCardInHandAction(new Chronobreak(), 1));
        } else {
            AbstractCard tw = new Timewinder();
            tw.upgrade();
            this.addToBot(new MakeTempCardInHandAction(tw, 1));

            AbstractCard pc = new ParallelConvergence();
            pc.upgrade();
            this.addToBot(new MakeTempCardInHandAction(pc, 1));

            AbstractCard pd = new PhaseDive();
            pd.upgrade();
            this.addToBot(new MakeTempCardInHandAction(pd, 1));

            AbstractCard cb = new Chronobreak();
            cb.upgrade();
            this.addToBot(new MakeTempCardInHandAction(cb, 1));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
