package ekkoTheBoyWhoShatteredTime.cards;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.characters.EkkoTheBoyWhoShatteredTime;

import java.util.List;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeCardPath;

public class TimeIndustry extends AbstractDynamicCard {

    public static final String ID = EkkoMod.makeID(TimeIndustry.class.getSimpleName());
    public static final String IMG = makeCardPath("TimeIndustry.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = EkkoTheBoyWhoShatteredTime.Enums.COLOR_LIGHTNINGBLUE_EKKO;

    private static final int COST = 2;
//    private static final int UPGRADED_COST = 2;

    private AbstractPlayer p;

    public TimeIndustry() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) {
            for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                if (q.hasTag(EkkoMod.ITEM) && !q.purgeOnUse) {
                    AbstractCard tmp = q.makeSameInstanceOf();
                    AbstractDungeon.player.limbo.addToBottom(tmp);
                    tmp.current_x = q.current_x;
                    tmp.current_y = q.current_y;
                    tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                    tmp.target_y = (float) Settings.HEIGHT / 2.0F;

                    tmp.purgeOnUse = true;
                    AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, null, q.energyOnUse, true, true), true);

                }
            }
        }

    }

    public List<TooltipInfo> getCustomTooltips() {
        return EkkoMod.itemTooltip;// 394
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            GraveField.grave.set(this, true);
            this.rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
//            upgradeBaseCost(UPGRADED_COST);
        }
    }
}
