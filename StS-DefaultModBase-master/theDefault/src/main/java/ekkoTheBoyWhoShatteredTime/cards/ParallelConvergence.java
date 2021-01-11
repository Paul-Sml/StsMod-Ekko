package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.ParallelConvergencePower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class ParallelConvergence extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(ParallelConvergence.class.getSimpleName());
    public static final String IMG = makeCardPath("ParallelConvergence.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 2;
    //private static final int UPGRADED_COST = 1;

    public ParallelConvergence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!q.isDying && !q.isDead) {
                    this.addToBot(new ApplyPowerAction(q, p, new ParallelConvergencePower(q), 1));
                }
            }
        } else {
            this.addToBot(new ApplyPowerAction(m, p, new ParallelConvergencePower(m), 1));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.target = CardTarget.ALL_ENEMY;
//upgradeBaseCost(UPGRADED_COST);
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
