package theNavi.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theNavi.NaviMod;
import theNavi.actions.AbsEssnsAction;
import theNavi.characters.TheNavi;

import static theNavi.NaviMod.makeCardPath;

public class AbsEssns extends AbstractDefaultCard {

    // TEXT DECLARATION

    public static final String ID = NaviMod.makeID("AbsEssns");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("AbsEssns.png");// Add template name here later

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //
    private static final CardTarget TARGET = CardTarget.ENEMY;  //
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheNavi.Enums.COLOR_BLUE;

    private static final int COST = 2;

    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DMG = 16;

    // /STAT DECLARATION/


    public AbsEssns() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        if (m != null) {
//            this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
//        }
        //TODO:add absorption effect here... bite or something?
        this.addToBot(new AbsEssnsAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
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