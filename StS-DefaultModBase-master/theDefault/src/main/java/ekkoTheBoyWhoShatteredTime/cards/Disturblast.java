package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.CooldownResonance;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Disturblast extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Disturblast.class.getSimpleName());
    public static final String IMG = makeCardPath("Disturblast.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;

    private static final int DAMAGE = 12;

    // /STAT DECLARATION/


    public Disturblast(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RemoveSpecificPowerAction(m, p, Resonance.POWER_ID));
        if (this.upgraded)
            this.addToBot(new RemoveSpecificPowerAction(m, p, CooldownResonance.POWER_ID));
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
