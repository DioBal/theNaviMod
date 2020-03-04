package theNavi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class AbsEssnsAction extends AbstractGameAction {

    private DamageInfo info;

    public AbsEssnsAction(AbstractCreature target, DamageInfo info)
    {
        this.setValues(target, info);
        actionType = ActionType.DAMAGE;  //add action type
        this.info = info;
        this.duration = 0.1F;
    }

    @Override
    public void update() {
        if (this.duration == 0.1F && this.target != null) {

            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE)); //TODO: Don't know what this does

            this.target.damage(this.info);

            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                RewardItem cardReward = new RewardItem();
                if (cardReward.cards.size() > 0) {
                    AbstractDungeon.getCurrRoom().rewards.add(cardReward);
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
