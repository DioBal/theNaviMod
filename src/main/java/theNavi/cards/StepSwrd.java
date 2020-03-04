package theNavi.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theNavi.NaviMod;
import theNavi.characters.TheNavi;

import javax.swing.text.html.HTMLDocument;

import static theNavi.NaviMod.makeCardPath;

// public class StepSwrd extends AbstractDefaultCard
public class StepSwrd extends AbstractDefaultCard {

    // TEXT DECLARATION

    public static final String ID = NaviMod.makeID("StepSwrd");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("StepSwrd.png");// Add template name here later

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  
    private static final CardTarget TARGET = CardTarget.ENEMY;  //
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheNavi.Enums.COLOR_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 5;
    private static final int BLOCK = 5;
    private static final int Draw = 1;
    private static final int Draw_PLUS = 1;

    // /STAT DECLARATION/


    public StepSwrd() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = Draw; //erase if 1
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new DrawCardAction(p, this.defaultSecondMagicNumber));

    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription=UPGRADE_DESCRIPTION;
            upgradeDefaultSecondMagicNumber(Draw_PLUS);
            initializeDescription();
        }
    }
}