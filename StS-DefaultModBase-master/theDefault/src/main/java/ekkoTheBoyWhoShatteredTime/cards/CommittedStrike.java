package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.CommittedStrikePower;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class CommittedStrike extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(CommittedStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("CommittedStrike.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;

    private static final int DAMAGE = 9;
    //private static final int UPGRADE_PLUS_DMG = 3;

    // /STAT DECLARATION/


    public CommittedStrike(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(EkkoMod.RESONATE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded)
            this.addToBot(new ApplyPowerAction(m, p, new Resonance(m, p, 1), 1));

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        AbstractCard cardVariable = this;
        this.addToBot(new ApplyPowerAction(p, p, new CommittedStrikePower(p, p, 1, cardVariable), 1));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.tags.remove(EkkoMod.RESONATE);
            this.tags.add(CardTags.STARTER_STRIKE);
            //  upgradeDamage(UPGRADE_PLUS_DMG);
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
