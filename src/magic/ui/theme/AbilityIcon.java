package magic.ui.theme;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import magic.data.IconImages;
import magic.model.MagicAbility;
import magic.model.MagicCardDefinition;
import magic.model.MagicObject;
import magic.ui.card.CardIcon;
import magic.utility.MagicStyle;

/**
 * Used to assign an ability with an icon which is displayed
 * on the annotated card image.
 */
public enum AbilityIcon {

    //
    // !! Please try to keep in order so it is easier to maintain !!
    //
    BATTLECRY(MagicAbility.BattleCry, "Battle Cry", null, "Whenever a creature with battle cry attacks, each other attacking creature gets +1 power for the rest of the turn. This affects all attacking creatures, not just those controlled by the controller of the creature with battle cry."),
    DEATHTOUCH(MagicAbility.Deathtouch, "Deathtouch", IconImages.DEATHTOUCH, "A creature with Deathtouch will destroy any creature it damages, regardless of the opponent's monster's toughness."),
    DEFENDER(MagicAbility.Defender, "Defender", IconImages.DEFENDER, "A permanent with the defender ability cannot attack. It is commonly found on creatures with low power, and high toughness, such as most walls."),
    DOUBLE_STRIKE(MagicAbility.DoubleStrike, "Double Strike", IconImages.DOUBLESTRIKE, "A creature with double strike deals damage during the first combat damage step alongside creatures with first strike, then additionally deal damage during the second damage step along with regular creatures."),
    FEAR(MagicAbility.Fear, "Fear", IconImages.FEAR, "A creature with fear can't be blocked, except by artifact creatures and by creatures that are black."),
    FIRST_STRIKE(MagicAbility.FirstStrike, "First Strike", IconImages.STRIKE, "First strike creates an additional combat damage step. A creature with first strike will deal its combat damage before a creature that doesn't. This often leads to the other creature dying before it gets a chance to strike."),
    FLYING(MagicAbility.Flying, "Flying", IconImages.FLYING, "A creature with flying cannot be blocked except by other creatures with either flying or reach. Creatures with flying can block other creatures with or without flying."),
    HASTE(MagicAbility.Haste, "Haste", null, "Haste allows creatures to ignore the rules informally known as \"summoning sickness\". This means that they can attack, and activate tap or untap abilities on the same turn they enter the battlefield."),
    HEXPROOF(MagicAbility.Hexproof, "Hexproof", IconImages.HEXPROOF, "A permanent or player with hexproof cannot be the target of spells or abilities your opponents control, but can still be targeted by spells or abilities you control."),
    INTIMIDATE(MagicAbility.Intimidate, "Intimidate", IconImages.INTIMIDATE, "A creature with intimidate can't be blocked, except by artifact creatures and by creatures that share a color with it."),
    INDESTRUCTIBLE(MagicAbility.Indestructible, "Indestructible", IconImages.INDESTRUCTIBLE, "Any permanent that has indestructible cannot be destroyed, and cannot die as a result of lethal damage."),
    INFECT(MagicAbility.Infect, "Infect", IconImages.INFECT, "A creature with infect doesn't deal damage as normal. If an infect creature deals damage to another creature, it will put that many -1/-1 counters on that creature instead. If an infect creature tries to deal damage to a player, that player gains that many poison counters instead. A player with 10 or more poison counters loses the game."),
    LIFELINK(MagicAbility.Lifelink, "Lifelink", IconImages.LIFELINK, "When a creature with lifelink deals combat damage, the controller of that creature also gains an amount of life equal to the amount of damage dealt damage, in addition to dealing the damage as normal."),
    PROTECTION_BLACK(MagicAbility.ProtectionFromBlack, "Protection from Black", IconImages.PROTBLACK, AbilityIcon.PROTECTION_TOOLTIP),
    PROTECTION_BLUE(MagicAbility.ProtectionFromBlue, "Protection from Blue", IconImages.PROTBLUE, AbilityIcon.PROTECTION_TOOLTIP),
    PROTECTION_COLORS(MagicAbility.ProtectionFromAllColors, "Protection from All Colors", IconImages.PROTALLCOLORS, AbilityIcon.PROTECTION_TOOLTIP),
    PROTECTION_GREEN(MagicAbility.ProtectionFromGreen, "Protection from Green", IconImages.PROTGREEN, AbilityIcon.PROTECTION_TOOLTIP),
    PROTECTION_RED(MagicAbility.ProtectionFromRed, "Protection from Red", IconImages.PROTRED, AbilityIcon.PROTECTION_TOOLTIP),
    PROTECTION_WHITE(MagicAbility.ProtectionFromWhite, "Protection from White", IconImages.PROTWHITE, AbilityIcon.PROTECTION_TOOLTIP),
    REACH(MagicAbility.Reach, "Reach", IconImages.REACH, "Creatures with reach can block creatures with flying."),
    SHROUD(MagicAbility.Shroud, "Shroud", IconImages.SHROUD, "A permanent or player with shroud cannot be the target of any spells or abilities. Shroud can still be affected by effects that do not target. Such as deathtouch; spells that affect all creatures, such as Planar Cleansing; or effects that don't have the word target in them, such as Clone."),
    TOTEM_ARMOR(MagicAbility.TotemArmor, "Totem Armor", null, "If a creature enchanted by an Aura with totem armor would be destroyed, all damage is removed from the creature and the Aura with totem armor is destroyed instead. If a creature is enchanted by multiple Auras with totem armor, only one Aura with totem armor (of the creature’s controller’s choice) is destroyed."),
    TRAMPLE(MagicAbility.Trample, "Trample", IconImages.TRAMPLE, "If a creature with trample would deal enough combat damage to its blockers to destroy them, it deals the rest of its damage to the defending player."),
    VIGILANCE(MagicAbility.Vigilance, "Vigilance", IconImages.VIGILANCE, "A creature with vigilance does not need to tap when attacking, this allows that creature to be able to block on the next player's turn."),
    WITHER(MagicAbility.Wither, "Wither", IconImages.WITHER, "Whenever a creature with wither deals damage to another creature, a -1/-1 counter is put on that creature instead for each point of damage dealt. A creature with more than one instance of wither does not deal twice the amount of -1/-1 counters in damage."),
    ;

    private final static String PROTECTION_TOOLTIP = "Protection is normally written \"Protection from {quality}\". For example, something with protection from black can’t be the target of black spells or abilities of black cards. Black spells and abilities that don’t use the word “target” still affect a creature with protection. Anything with protection from black can’t be damaged by black cards. Black creatures can’t block an attacking creature with protection from black.";

    private final ImageIcon defaultIcon;
    private final String iconName;
    private final String tooltip;
    private final MagicAbility ability;

    private AbilityIcon(final MagicAbility ability, final String name, final ImageIcon defaultIcon, final String tooltip) {
        this.iconName = name;
        this.defaultIcon = defaultIcon;
        this.tooltip = tooltip;
        this.ability = ability;
    }

    public ImageIcon getIcon() {
        final ImageIcon icon = MagicStyle.getTheme().getAbilityIcon(this);
        return icon == null ? defaultIcon : icon;
    }

    public String getName() {
        return iconName;
    }

    public String getTooltip() {
        return tooltip;
    }

    public MagicAbility getAbility() {
        return ability;
    }

    public static List<CardIcon> getIcons(final MagicObject magicObject) {
        final List<CardIcon> icons = new ArrayList<>();
        for (AbilityIcon abilityIcon : AbilityIcon.values()) {
            if (abilityIcon.getIcon() != null && magicObject.hasAbility(abilityIcon.getAbility())) {
                icons.add(getAbilityIcon(abilityIcon));
            }
        }
        return icons;
    }

    public static List<CardIcon> getIcons(final MagicCardDefinition cardDef) {
        final List<CardIcon> icons = new ArrayList<>();
        for (AbilityIcon abilityIcon : AbilityIcon.values()) {
            if (abilityIcon.getIcon() != null && cardDef.hasAbility(abilityIcon.getAbility())) {
                icons.add(getAbilityIcon(abilityIcon));
            }
        }
        return icons;
    }

    private static CardIcon getAbilityIcon(final AbilityIcon abilityIcon) {
        final CardIcon icon = new CardIcon(IconImages.BACKDROP_ICON);
        icon.setName(abilityIcon.getName());
        icon.setDescription(abilityIcon.getTooltip());
        icon.setIcon(abilityIcon.getIcon());
        return icon;
    }

}
