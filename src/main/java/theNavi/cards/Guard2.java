package theNavi.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theNavi.NaviMod;
import theNavi.actions.FuseAction;
import theNavi.characters.TheNavi;

import static theNavi.NaviMod.makeCardPath;

// public class Guard2 extends AbstractDefaultCard
public class Guard2 extends AbstractDefaultCard {

    // TEXT DECLARATION

    public static final String ID = NaviMod.makeID("Guard2");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Guard2.png");// Add template name here later

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL; //
    private static final CardTarget TARGET = CardTarget.SELF;  //
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheNavi.Enums.COLOR_BLUE;

    private static final int COST = 2;

    private static final int BLOCK = 14;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int FUSE = 2; //erase if 0

    // /STAT DECLARATION/


    public Guard2() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = FUSE; //erase if 0
        this.cardsToPreview = new Guard3();
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, block));
    }


    // Upgraded stats.
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
        super.applyPowers();
        AbstractDungeon.actionManager.addToBottom(new FuseAction(this,magicNumber,new Guard3()));
    }
}