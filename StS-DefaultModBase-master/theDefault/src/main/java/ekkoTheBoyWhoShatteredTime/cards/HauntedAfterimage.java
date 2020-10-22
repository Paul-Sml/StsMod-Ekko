package ekkoTheBoyWhoShatteredTime.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import ekkoTheBoyWhoShatteredTime.actions.HauntedAfterimageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;

import java.util.List;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class HauntedAfterimage extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(HauntedAfterimage.class.getSimpleName());
    public static final String IMG = makeCardPath("HauntedAfterimage.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 2;

    private static final int DAMAGE = 8;
    //private static final int UPGRADE_PLUS_DMG = 2;

    // /STAT DECLARATION/


    public HauntedAfterimage(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        baseDamage = DAMAGE;
        this.tags.add(EkkoMod.RESONATE);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (this.upgraded) {
            this.addToBot(new ExhaustAction(1, false));
            this.addToBot(new ApplyPowerAction(m, p, new Resonance(m, p, 1), 1));
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            this.addToBot(new ApplyPowerAction(m, p, new Resonance(m, p, 1), 1));
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        } else {
            this.addToBot(new ExhaustAction(1, true, false, false));
            for(int i = 0; i < 2; ++i) {
                this.addToBot(new HauntedAfterimageAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }


    }


    public List<TooltipInfo> getCustomTooltips() {
        return EkkoMod.resonanceTooltip;// 394
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.target = CardTarget.ENEMY;
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
            //upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
