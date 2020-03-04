package theNavi.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theNavi.NaviMod;
import theNavi.characters.TheNavi;

import static theNavi.NaviMod.makeCardPath;

public class CyclingStarter extends AbstractDefaultCard {
    int cycle = 0;
    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Cycling 3 : Block 3(5) + || Apply 1 Vulnerable || Draw 1 Cards || Apply 1 Weak ||
     */


    // TEXT DECLARATION

    public static final String ID = NaviMod.makeID("CyclingStarter");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("CyclingStarter.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION[0];

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheNavi.Enums.COLOR_BLUE;

    private static final int COST = 0;
    private static final int BLOCK = 3;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int DRAW = 1;
    private static final int VULNERABLE_WEAK = 1;


    // /STAT DECLARATION/


    public CyclingStarter() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = DRAW;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = VULNERABLE_WEAK;
        this.misc = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (misc == 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, block));
            this.addToBot(new DrawCardAction(p, this.magicNumber));
        }
        else if (misc == 1) {
            AbstractDungeon.actionManager.addToBottom(
                    new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, block));
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.defaultSecondMagicNumber, false), this.defaultSecondMagicNumber));
        }
        else if (misc == 2) {
            AbstractDungeon.actionManager.addToBottom(
                    new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, block));
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.defaultSecondMagicNumber, false), this.defaultSecondMagicNumber));
        }

        advanceCycle();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }

    @Override
    public void applyPowers() {
        if (misc == 0){
            this.name = "Cycler 1: Draw"; //TODO move these to extended descriptions
            this.target = CardTarget.SELF;
        }
        else if (misc == 1){
            this.name = "Cycler 2: Vulnerable";
            this.target = CardTarget.ENEMY;
        }
        else if (misc == 2){
            this.name = "Cycler 3: Weak";
            this.target = CardTarget.ENEMY;
        }
        if(upgraded){
            this.name=this.name+"+";
        }
        //change description
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[misc];
        initializeDescription();
        //change description END
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        advanceCycle();
    }

    public void advanceCycle(){
        misc=(misc+1)%3;
    }
}
