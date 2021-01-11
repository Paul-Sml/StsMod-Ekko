//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ekkoTheBoyWhoShatteredTime.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.OddlySmoothStone;
import com.megacrit.cardcrawl.relics.Vajra;
import ekkoTheBoyWhoShatteredTime.EkkoMod;
import ekkoTheBoyWhoShatteredTime.util.TextureLoader;

import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicOutlinePath;
import static ekkoTheBoyWhoShatteredTime.EkkoMod.makeRelicPath;

//
//          T O    B E    M O V E D    T O    A N O T H E R    M O D
//

public class SmoothedVajra extends CustomRelic {
    public static final String ID = EkkoMod.makeID("SmoothedVajra");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SmoothedVajra.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SmoothedVajra.png"));

    public SmoothedVajra() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.SOLID);
    }

    @Override
    public void obtain() {
        super.obtain();
        if (!AbstractDungeon.player.hasRelic(Vajra.ID))
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(500,500, RelicLibrary.getRelic(Vajra.ID).makeCopy());
        if (!AbstractDungeon.player.hasRelic(OddlySmoothStone.ID))
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(500,500, RelicLibrary.getRelic(OddlySmoothStone.ID).makeCopy());
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public boolean canSpawn() {
        return (!AbstractDungeon.player.hasRelic(OddlySmoothStone.ID) && !AbstractDungeon.player.hasRelic(Vajra.ID));
    }
}
