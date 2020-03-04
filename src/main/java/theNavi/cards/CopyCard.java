package theNavi.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.NightmareAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theNavi.NaviMod;
import theNavi.actions.CopyAnyAction;
import theNavi.characters.TheNavi;

import static theNavi.NaviMod.makeCardPath;

// public class CopyCard extends AbstractDefaultCard
public class CopyCard extends AbstractDefaultCard {

    // TEXT DECLARATION

    public static final String ID = NaviMod.makeID("CopyCard");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("CopyCard.png");// Add template name here later

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC; //  change if needed
    private static final CardTarget TARGET = CardTarget.SELF;  //
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheNavi.Enums.COLOR_BLUE;

    private static final int COST = 1;

    private static final int COPIES = 1;

    // /STAT DECLARATION/


    public CopyCard() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = COPIES;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new CopyAnyAction(p, p, this.magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            this.selfRetain=true;
            initializeDescription();
        }
    }
}