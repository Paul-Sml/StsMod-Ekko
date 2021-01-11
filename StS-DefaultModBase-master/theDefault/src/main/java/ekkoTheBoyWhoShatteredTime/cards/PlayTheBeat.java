package ekkoTheBoyWhoShatteredTime.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.actions.PlayTheBeatAction;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class PlayTheBeat extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(PlayTheBeat.class.getSimpleName());
    public static final String IMG = makeCardPath("PlayTheBeat.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 0;

    // /STAT DECLARATION/


    public PlayTheBeat(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int PDBStrength = 0;
        int PDBDexterity = 0;
        if(p.hasPower(StrengthPower.POWER_ID))
            PDBStrength = p.getPower(StrengthPower.POWER_ID).amount*2;
        if(p.hasPower(DexterityPower.POWER_ID))
            PDBDexterity = p.getPower(DexterityPower.POWER_ID).amount*2;
        if (PDBStrength > 0)
            this.addToBot(new DamageAction(m, new DamageInfo(p, PDBStrength, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (PDBStrength < 0)
            m.heal(-PDBStrength);
        if (PDBDexterity > 0)
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, PDBDexterity));
        if (PDBDexterity < 0)
            this.addToBot(new DamageAction(p, new DamageInfo(p, -PDBDexterity, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (this.upgraded) {
            this.addToBot(new PlayTheBeatAction());
        }
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
