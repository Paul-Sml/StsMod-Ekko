package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.blue.FTL;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Playful extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Playful.class.getSimpleName());
    public static final String IMG = makeCardPath("Playful.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    public Playful() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(StrengthPower.POWER_ID)) {
            int s = this.magicNumber + p.getPower(StrengthPower.POWER_ID).amount;

            if (s > 0)
                this.addToBot(new DrawCardAction(p, s));
            if (s < 0)
                this.addToBot(new DiscardAction(p, p, -s, true));
        }

        if(p.hasPower(DexterityPower.POWER_ID)) {
            int d = this.magicNumber + p.getPower(DexterityPower.POWER_ID).amount;

            if (d > 0)
                this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, d), d));
            if (d < 0)
                this.addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p, -d), -d));
        }
    }

    @Override
    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;

        String descr1 = "Draw !M! card, NL affected by Strength.";
        if(p.hasPower(DexterityPower.POWER_ID)) {
            int s = this.magicNumber + p.getPower(StrengthPower.POWER_ID).amount;
            String color = "";
            if (s > 1)
                color = "#g";
            if (s < 1)
                color = "#r";

            descr1 = "Draw " + color + s + " card, NL affected by Strength.";
        }

        String descr2 = " NL Next turn, draw !M! card, NL affected by Dexterity. Care of negative values.";
        if(p.hasPower(StrengthPower.POWER_ID)) {
            int d = this.magicNumber + p.getPower(StrengthPower.POWER_ID).amount;
            String color = "";
            if (d > 1)
                color = "#g";
            if (d < 1)
                color = "#r";

            descr2 = "Draw " + color + d + " card, NL affected by Strength.";
        }

        this.rawDescription = descr1 + descr2;
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
