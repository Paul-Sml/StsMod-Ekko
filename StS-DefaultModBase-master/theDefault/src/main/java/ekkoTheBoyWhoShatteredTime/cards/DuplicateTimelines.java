package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.DelayedDamage;
import ekkoTheBoyWhoShatteredTime.powers.DelayedResonance;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class DuplicateTimelines extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(DuplicateTimelines.class.getSimpleName());
    public static final String IMG = makeCardPath("DuplicateTimelines.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;

    /*private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 4;*/

    // /STAT DECLARATION/


    public DuplicateTimelines(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
        selfRetain = false;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //Energy
        if (p.hasPower(EnergizedPower.POWER_ID)) {
            int e = p.getPower(EnergizedPower.POWER_ID).amount;
            p.gainEnergy(e);
        }

        //Draw
        if (p.hasPower(DrawCardNextTurnPower.POWER_ID)) {
            int dr = p.getPower(DrawCardNextTurnPower.POWER_ID).amount;
            this.addToBot(new DrawCardAction(p, dr));
        }

        //Block
        if (p.hasPower(NextTurnBlockPower.POWER_ID)) {
            int b = p.getPower(NextTurnBlockPower.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, b));
        }

        int r;
        int da;

        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            //Resonance
            if (q.hasPower(DelayedResonance.POWER_ID)) {
                r = q.getPower(DelayedResonance.POWER_ID).amount;
                for (int i = 0; i < r; i++) {
                    this.addToBot(new ApplyPowerAction(q, AbstractDungeon.player, new Resonance(q, AbstractDungeon.player, 1), 1));
                }
            }
            //Damage
            if (q.hasPower(DelayedDamage.POWER_ID)) {
                da = q.getPower(DelayedDamage.POWER_ID).amount;
                this.addToBot(new DamageAction(q, new DamageInfo(q, da, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }

    }

    /*public List<TooltipInfo> getCustomTooltips() {
        return DefaultMod.resonanceTooltip;// 394
    }*/

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            selfRetain = true;
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
//            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
