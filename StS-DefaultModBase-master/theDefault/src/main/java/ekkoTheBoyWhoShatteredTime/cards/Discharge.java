package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Discharge extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Discharge.class.getSimpleName());
    public static final String IMG = makeCardPath("Discharge.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int DAMAGE = 5;

    // /STAT DECLARATION/


    public Discharge(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*int x = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            x += c.costForTurn;
        }
        x -= this.costForTurn;
        //for (int i = 0; i < x; i++)
        damage -= DAMAGE*this.costForTurn;*/

        baseDamage = DAMAGE;
        baseDamage *= EkkoMod.usedEnergy;

        super.applyPowers();
        super.calculateCardDamage(m);

        this.addToBot(new DamageAction(m, new DamageInfo(p, (damage), damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
    }

    @Override
    public void applyPowers() {
        baseDamage = DAMAGE;
        /*int x = 0;
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                x += c.costForTurn;
            }
        }*/
        baseDamage *= EkkoMod.usedEnergy;
        super.applyPowers();
        this.rawDescription = "Deal 5 damage for each [E] spent that turn. NL ([#00FFFF] !D! [#00FFFF]total [#00FFFF]damage[]).";
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = DAMAGE;
        /*int x = 0;
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                x += c.costForTurn;
            }
        }*/
        baseDamage *= EkkoMod.usedEnergy;
        super.calculateCardDamage(mo);
        this.rawDescription = "Deal 5 damage for each [E] spent that turn. NL ([#00FFFF] !D! [#00FFFF]total [#00FFFF]damage[]).";
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
        }
    }
}
