package theNavi.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theNavi.NaviMod;
import theNavi.characters.TheNavi;

import static theNavi.NaviMod.makeCardPath;

public class ExtraCard extends CustomCard {
//todo: make this a fatal attack
    // TEXT DECLARATION

    public static final String ID = NaviMod.makeID("ExtraCard");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Skill.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheNavi.Enums.COLOR_BLUE;

    private static final int COST = 0;
    private static final int UPGRADE_COST = 0;

    private static final int MAGIC = 1;

    // /STAT DECLARATION/

    public ExtraCard() {

        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        RewardItem cardReward = new RewardItem();
        if (cardReward.cards.size() > 0) {
            AbstractDungeon.getCurrRoom().rewards.add(cardReward);
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
