    //
    // Source code recreated from a .class file by IntelliJ IDEA
    // (powered by FernFlower decompiler)
    //

    package ekkoTheBoyWhoShatteredTime.relics;

    import basemod.abstracts.CustomRelic;
    import com.badlogic.gdx.graphics.Texture;
    import com.megacrit.cardcrawl.actions.AbstractGameAction;
    import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
    import com.megacrit.cardcrawl.characters.AbstractPlayer;
    import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
    import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
    import com.megacrit.cardcrawl.relics.AbstractRelic;
    import ekkoTheBoyWhoShatteredTime.EkkoMod;
    import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

    import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicOutlinePath;
    import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicPath;

    public class BronzeTube extends CustomRelic {
        public static final String ID = EkkoMod.makeID("BronzeTube");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BronzeTube.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BronzeTube.png"));

        public BronzeTube() {
            super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
        }

        private static boolean alreadyDone = false;

        @Override
        public void onTrigger() {
            if (!alreadyDone) {
                AbstractRelic r = this;
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {

                        if (!alreadyDone) {

                            AbstractPlayer p = AbstractDungeon.player;
                            r.flash();
                            this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 1), 1));
                            alreadyDone = true;


                        }
                        isDone = true;
                    }

                /*AbstractPlayer p = AbstractDungeon.player;
                this.flash();
                this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 1), 1));
                alreadyDone = true;*/

                });
            }
        }

        //reset
        @Override
        public void atTurnStart() {
            alreadyDone = false;
        }

        @Override
        public void atBattleStart() {
            alreadyDone = false;
        }
        //reset

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }
    }
