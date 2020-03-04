package theNavi.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theNavi.NaviMod;
import theNavi.util.TextureLoader;

import static theNavi.NaviMod.makeRelicOutlinePath;
import static theNavi.NaviMod.makeRelicPath;

public class MegaBuster extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Art of war but gives vigor
     */

    // ID, images, text.
    public static final String ID = NaviMod.makeID("MegaBuster");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MegaBuster.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    private boolean gainVigorNext = false;
    private boolean firstTurn = false;
    private static final int VIGOR = 3;

    public MegaBuster() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    public void atPreBattle() {
        this.flash();
        this.firstTurn = true;
        this.gainVigorNext = true;

        if (!this.pulse) {
            this.beginPulse();
            this.pulse = true;
        }

    }

    public void atTurnStart() {
        this.beginPulse();
        this.pulse = true;
        if (this.gainVigorNext && !this.firstTurn) {
            this.flash();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, VIGOR), VIGOR));
        }

        this.firstTurn = false;
        this.gainVigorNext = true;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.gainVigorNext = false;
            this.pulse = false;
        }

    }

    public void onVictory() {
        this.pulse = false;
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
