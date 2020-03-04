package theNavi.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
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

// public class MCannon extends AbstractDefaultCard
public class MCannon extends AbstractDefaultCard { //NOT REGISTERED IN THE EDIT CARD SUBSCRIBER

    // TEXT DECLARATION

    public static final String ID = NaviMod.makeID("MCannon");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("MCannon.png");// Add template name here later

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL; // TODO: make special and change frame and register it
    private static final CardTarget TARGET = CardTarget.ENEMY;  //
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheNavi.Enums.COLOR_BLUE;

    private static final int COST = 2;

    private static final int DAMAGE = 30;    //
    private static final int UPGRADE_PLUS_DMG = 6;

    private static final int VULNERABLE = 2; //erase if 1

    // /STAT DECLARATION/


    public MCannon() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = VULNERABLE; //erase if 1
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.defaultSecondMagicNumber, false), this.defaultSecondMagicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);

            initializeDescription();
        }
    }
}