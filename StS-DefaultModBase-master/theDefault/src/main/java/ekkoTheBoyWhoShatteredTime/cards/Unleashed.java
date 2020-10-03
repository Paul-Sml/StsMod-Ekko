package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class Unleashed extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(Unleashed.class.getSimpleName());
    public static final String IMG = makeCardPath("Unleashed.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private AbstractPlayer p;

    public Unleashed() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int UnleashedPreStrength = 0;
        int UnleashedPreDexterity = 0;
        if(p.hasPower(StrengthPower.POWER_ID))
            UnleashedPreStrength = p.getPower(StrengthPower.POWER_ID).amount;
        if(p.hasPower(DexterityPower.POWER_ID))
            UnleashedPreDexterity = p.getPower(DexterityPower.POWER_ID).amount;
        if (UnleashedPreStrength > 0)
            this.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, UnleashedPreStrength), UnleashedPreStrength));
        if (UnleashedPreDexterity > 0)
            this.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, UnleashedPreDexterity), UnleashedPreDexterity));
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
