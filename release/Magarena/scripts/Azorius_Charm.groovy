def EFFECT1 = MagicRuleEventAction.create("Creatures you control gain lifelink until end of turn.");

def EFFECT2 = MagicRuleEventAction.create("Draw a card.");

def EFFECT3 = MagicRuleEventAction.create("Put target attacking or blocking creature on top of its owner's library.");


[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                new MagicOrChoice(
                    MagicTargetChoice.NONE,
                    MagicTargetChoice.NONE,
                    MagicTargetChoice.NEG_TARGET_ATTACKING_OR_BLOCKING_CREATURE
                ),
                this,
                "Choose one\$ - creatures you control gain lifelink until end of turn; " +
                "or draw a card; " +
                "or put target attacking or blocking creature\$ on top of its owner's library." 
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.executeModalEvent(game, EFFECT1, EFFECT2, EFFECT3);
        }
    }
]