def EFFECT1 = MagicRuleEventAction.create("Target player discards a card.");

def EFFECT2 = MagicRuleEventAction.create("Target creature gets +2/-1 until end of turn.");

def EFFECT3 = MagicRuleEventAction.create("Target creature gains swampwalk until end of turn.");


[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                new MagicOrChoice(
                    MagicTargetChoice.Negative("target player"),
                    MagicTargetChoice.TARGET_CREATURE,
                    MagicTargetChoice.POS_TARGET_CREATURE
                ),
                this,
                "Choose one\$ - target player discards a card; " +
                "or target creature gets +2/-1 until end of turn; " +
                "or target creature gains swampwalk until end of turn.\$" 
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.executeModalEvent(game, EFFECT1, EFFECT2, EFFECT3);
        }
    }
]