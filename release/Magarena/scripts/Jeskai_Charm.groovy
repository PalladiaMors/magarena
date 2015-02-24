def EFFECT1 = MagicRuleEventAction.create("Put target creature on top of its owner's library.");

def EFFECT2 = MagicRuleEventAction.create("SN deals 4 damage to target opponent.");

def EFFECT3 = MagicRuleEventAction.create("Creatures you control get +1/+1 and gain lifelink until end of turn.");

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                new MagicOrChoice(
                    MagicTargetChoice.NEG_TARGET_CREATURE,
                    MagicTargetChoice.TARGET_OPPONENT,
                    MagicTargetChoice.NONE
                ),
                this,
                "Choose one\$ - put target creature on top of its owner's library; " +
                "or SN deals 4 damage to target opponent; " +
                "or creatures you control get +1/+1 and gain lifelink until end of turn.\$" 
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.executeModalEvent(game, EFFECT1, EFFECT2, EFFECT3);
        }
    }
]