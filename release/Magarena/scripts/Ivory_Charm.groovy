def EFFECT1 = MagicRuleEventAction.create("All creatures get -2/-0 until end of turn.");

def EFFECT2 = MagicRuleEventAction.create("Tap target creature.");

def EFFECT3 = MagicRuleEventAction.create("Prevent the next 1 damage that would be dealt to target creature or player this turn.");

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                new MagicOrChoice(
                    MagicTargetChoice.NONE,
                    MagicTargetChoice.NEG_TARGET_CREATURE,
                    MagicTargetChoice.POS_TARGET_CREATURE_OR_PLAYER
                ),
                this,
                "Choose one\$ - all creatures get -2/-0 until end of turn; " +
                "or tap target creature; " +
                "or prevent the next 1 damage that would be dealt to target creature or player this turn.\$" 
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.executeModalEvent(game, EFFECT1, EFFECT2, EFFECT3);
        }
    }
]