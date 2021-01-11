package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.ResonanceCheck;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Backtrack extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Backtrack.class.getSimpleName());
    public static final String IMG = makeCardPath("Backtrack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 2;

    private static final int BLOCK = 12;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public Backtrack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
    }

    @Override
    public void applyPowers() {
        //boolean bruh = freeToPlayOnce;
        if (ResonanceCheck == true)
            this.setCostForTurn(0);
            /*{
            freeToPlayOnce = true;
        } else {
            freeToPlayOnce = bruh;
        }*/
        super.applyPowers();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
