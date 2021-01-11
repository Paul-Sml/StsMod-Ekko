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

public class IceBreak extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(IceBreak.class.getSimpleName());
    public static final String IMG = makeCardPath("IceBreak.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;

    //private static int UPDATED_DAMAGE = DAMAGE;

    // /STAT DECLARATION/


    public IceBreak(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        baseDamage = DAMAGE;
        if (this.upgraded)
            baseDamage += UPGRADE_PLUS_DMG;
        baseDamage *= EkkoMod.checkEnergy;

        super.applyPowers();
        super.calculateCardDamage(m);

        this.addToBot(new DamageAction(m, new DamageInfo(p, (damage), damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
    }

    @Override
    public void applyPowers() {
        baseDamage = DAMAGE;
        if (this.upgraded)
            baseDamage += UPGRADE_PLUS_DMG;
        int tmp = baseDamage;

        baseDamage *= EkkoMod.checkEnergy;
        super.applyPowers();
        this.rawDescription = "Deal "+tmp+" damage for each extra [E] that you generated this turn. NL ([#00FFFF] !D! [#00FFFF]total [#00FFFF]damage[]).";
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = DAMAGE;
        if (this.upgraded)
            baseDamage += UPGRADE_PLUS_DMG;
        int tmp = baseDamage;

        baseDamage *= EkkoMod.checkEnergy;
        super.calculateCardDamage(mo);
        this.rawDescription = "Deal "+tmp+" damage for each extra [E] that you generated this turn. NL ([#00FFFF] !D! [#00FFFF]total [#00FFFF]damage[]).";
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
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
