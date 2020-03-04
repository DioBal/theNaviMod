package theNavi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theNavi.powers.CommonPower;

public class FuseAction extends AbstractGameAction {
    private AbstractCard thisCard;
    private int FuseValue;
    private AbstractCard targetCard;

    public FuseAction(AbstractCard thisCard,int FuseValue,AbstractCard targetCard) {
        this.thisCard = thisCard;
        this.FuseValue = FuseValue;
        this.targetCard = targetCard;
        this.duration=0.15F;
    }

    @Override
    public void update() {
        int count;
        boolean upgradeTheFusion=false;
        count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(thisCard.cardID)) {
                ++count;
            }
        }
        if (count >= FuseValue) {
            count = 0;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.cardID.equals(thisCard.cardID)) {

                    ++count;
                    if (c.upgraded){
                        upgradeTheFusion=true;
                    }
                    this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand, true));
                }
                if (count == FuseValue) {
                    break;
                }
            }
            if (upgradeTheFusion){
                AbstractCard s = targetCard.makeCopy();
                s.upgrade();
                this.addToBot(new MakeTempCardInHandAction(s, 1));
            } else {
                this.addToBot(new MakeTempCardInHandAction(targetCard, 1));
            }
        }
        isDone = true;
    }
}
