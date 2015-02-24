def EFFECT1 = MagicRuleEventAction.create("Destroy target enchantment.");

def EFFECT2 = MagicRuleEventAction.create("Draw two cards.");

def EFFECT3 = MagicRuleEventAction.create("Target player discards two cards.");

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                new MagicOrChoice(
                    MagicTargetChoice.Negative("target enchantment"),
                    MagicTargetChoice.NONE,
                    MagicTargetChoice.NEG_TARGET_PLAYER
                ),
                this,
                "Choose one\$ - destroy target enchantment; " +
                "or draw two cards; " +
                "or target player discards two cards.\$" 
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.executeModalEvent(game, EFFECT1, EFFECT2, EFFECT3);
        }
    }
]