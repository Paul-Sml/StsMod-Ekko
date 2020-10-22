package ekkoTheBoyWhoShatteredTime.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class ZhonyasHourglass extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(ZhonyasHourglass.class.getSimpleName());
    public static final String IMG = makeCardPath("ZhonyasHourglass.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    private AbstractPlayer p;

    public ZhonyasHourglass() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.tags.add(EkkoMod.ITEM);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!q.isDying && !q.isDead) {
                    if (q.getIntentBaseDmg() >= 0 || q.intent == AbstractMonster.Intent.DEBUFF || q.intent == AbstractMonster.Intent.DEFEND_BUFF || q.intent == AbstractMonster.Intent.STRONG_DEBUFF)
                this.addToBot(new StunMonsterAction(q, p));
            }
        }
        this.addToBot(new PressEndTurnButtonAction());
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
        }
    }
}
