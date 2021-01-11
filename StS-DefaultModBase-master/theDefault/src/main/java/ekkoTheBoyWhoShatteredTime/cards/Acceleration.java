package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Acceleration extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Acceleration.class.getSimpleName());
    public static final String IMG = makeCardPath("Acceleration.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 0;

    private int count = 0;


    public Acceleration() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*int x = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            x += c.costForTurn;
        }
        x -= this.costForTurn;*/

        if (this.upgraded) {
            if (AbstractDungeon.player.discardPile.size() > 0) {
                this.addToBot(new EmptyDeckShuffleAction());
                this.addToBot(new ShuffleAction(AbstractDungeon.player.drawPile, false));
            }
        }

        this.addToBot(new DrawCardAction(p, EkkoMod.usedEnergy));
    }

    @Override
    public void applyPowers() {

        /*int x = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            x += c.costForTurn;
        }*/

        if (!this.upgraded) {
            this.rawDescription = "Draw a card for each [E] ([#00FFFF]" + EkkoMod.usedEnergy + "[]) spent that turn.";
        } else {
            this.rawDescription = "Shuffle your discard pile into your draw pile. Draw a card for each [E] ([#00FFFF]" + EkkoMod.usedEnergy + "[]) spent that turn.";
        }

        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
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
    