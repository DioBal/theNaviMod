package theNavi.patches.mechanics;


import basemod.patches.com.megacrit.cardcrawl.characters.AbstractPlayer.MaxHandSizePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;


/*
 * Patches have a pretty detailed documentation. Go check em out here:
 *
 *  https://github.com/kiooeht/ModTheSpire/wiki/SpirePatch
 */

@SpirePatch(clz = MaxHandSizePatch.class, method = "RefreshHandLayout")
public class Fuse {
    @SpirePostfixPatch
    public static void Fuse(){
        //stuff
    }
}