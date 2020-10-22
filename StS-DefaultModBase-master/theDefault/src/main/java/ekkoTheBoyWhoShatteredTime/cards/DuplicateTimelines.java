package ekkoTheBoyWhoShatteredTime.cards;

import basemod.ReflectionHacks;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.EndTurnDeathPower;
import com.megacrit.cardcrawl.powers.watcher.VaultPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;
import ekkoTheBoyWhoShatteredTime.powers.DelayedDamage;
import ekkoTheBoyWhoShatteredTime.powers.DelayedResonance;
import ekkoTheBoyWhoShatteredTime.powers.ParallelConvergencePower;
import ekkoTheBoyWhoShatteredTime.powers.Resonance;

import java.util.List;

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

    public List<TooltipInfo> getCustomTooltips() {
        return EkkoMod.dupTooltip;// 394
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //Death
        if (p.hasPower(EndTurnDeathPower.POWER_ID)) {
            this.addToBot(new VFXAction(new LightningEffect(p.hb.cX, p.hb.cY)));
            this.addToBot(new LoseHPAction(p, p, 99999));
        }

        //Play again
        if (p.hasPower(VaultPower.POWER_ID)) {
            this.addToBot(new SkipEnemiesTurnAction());
        }

        //Phantasmal power
        if (p.hasPower(PhantasmalPower.POWER_ID)) {
            int ph = p.getPower(PhantasmalPower.POWER_ID).amount;
            this.addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, 1, false), ph));
        }

        //Nightmare
        if (p.hasPower(NightmarePower.POWER_ID)) {
            int ni = p.getPower(NightmarePower.POWER_ID).amount;
            AbstractCard card = (AbstractCard) ReflectionHacks.getPrivate(p.getPower(NightmarePower.POWER_ID), NightmarePower.class, "card");
            this.addToBot(new MakeTempCardInHandAction(card, ni));
        }

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

        //Discard
        if (p.hasPower(DrawReductionPower.POWER_ID)) {
            int di = p.getPower(DrawReductionPower.POWER_ID).amount;
            this.addToBot(new DiscardAction(p, p, di, true));
        }

        //Block
        if (p.hasPower(NextTurnBlockPower.POWER_ID)) {
            int b = p.getPower(NextTurnBlockPower.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, b));
        }

        //Lose str
        if (p.hasPower(LoseStrengthPower.POWER_ID)) {
            int Ls = p.getPower(LoseStrengthPower.POWER_ID).amount;
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -Ls), -Ls));
        }

        //Lose dext
        if (p.hasPower(LoseDexterityPower.POWER_ID)) {
            int Ld = p.getPower(LoseDexterityPower.POWER_ID).amount;
            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -Ld), -Ld));
        }

        //Gain str
        if (p.hasPower(GainStrengthPower.POWER_ID)) {
            int Gs = p.getPower(GainStrengthPower.POWER_ID).amount;
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, Gs), Gs));
        }

        //Resonance
        if (p.hasPower(DelayedResonance.POWER_ID)) {
            int reP = p.getPower(DelayedResonance.POWER_ID).amount;
            for (int i = 0; i < reP; i++) {
                this.addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new Resonance(p, AbstractDungeon.player, 1), 1));
            }
        }

        //Damage
        if (p.hasPower(DelayedDamage.POWER_ID)) {
            int daP = p.getPower(DelayedDamage.POWER_ID).amount;
            this.addToBot(new DamageAction(p, new DamageInfo(p, daP, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

        int re;
        int da;

        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {

            //Death
            if (q.hasPower(EndTurnDeathPower.POWER_ID)) {
                this.addToBot(new VFXAction(new LightningEffect(q.hb.cX, q.hb.cY)));
                this.addToBot(new LoseHPAction(q, p, 99999));
            }

            //Phantasmal power
            if (q.hasPower(PhantasmalPower.POWER_ID)) {
                int ph = q.getPower(PhantasmalPower.POWER_ID).amount;
                this.addToBot(new ApplyPowerAction(q, p, new DoubleDamagePower(q, 1, false), ph));
            }

            //Block
            if (q.hasPower(NextTurnBlockPower.POWER_ID)) {
                int b = q.getPower(NextTurnBlockPower.POWER_ID).amount;
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(q, p, b));
            }

            //Resonance
            if (q.hasPower(DelayedResonance.POWER_ID)) {
                re = q.getPower(DelayedResonance.POWER_ID).amount;
                for (int i = 0; i < re; i++) {
                    this.addToBot(new ApplyPowerAction(q, AbstractDungeon.player, new Resonance(q, AbstractDungeon.player, 1), 1));
                }
            }

            //Lose str
            if (q.hasPower(LoseStrengthPower.POWER_ID)) {
                int Ls = q.getPower(LoseStrengthPower.POWER_ID).amount;
                this.addToBot(new ApplyPowerAction(q, p, new StrengthPower(q, -Ls), -Ls));
            }

            //Lose dext
            if (q.hasPower(LoseDexterityPower.POWER_ID)) {
                int Ld = q.getPower(LoseDexterityPower.POWER_ID).amount;
                this.addToBot(new ApplyPowerAction(q, p, new DexterityPower(q, -Ld), -Ld));
            }

            //Gain str
            if (q.hasPower(GainStrengthPower.POWER_ID)) {
                int Gs = q.getPower(GainStrengthPower.POWER_ID).amount;
                this.addToBot(new ApplyPowerAction(q, p, new StrengthPower(q, Gs), Gs));
            }

            //Damage
            if (q.hasPower(DelayedDamage.POWER_ID)) {
                da = q.getPower(DelayedDamage.POWER_ID).amount;
                this.addToBot(new DamageAction(q, new DamageInfo(q, da, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }

            //Parallel convergence
            if (q.hasPower(ParallelConvergencePower.POWER_ID) && q.getIntentBaseDmg() >= 0) {
                this.addToBot(new StunMonsterAction(q, q));
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
