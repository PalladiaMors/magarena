[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.NEG_TARGET_PLAYER,
                this,
                "Target player\$ sacrifices a creature and loses 1 life."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                game.addEvent(new MagicSacrificePermanentEvent(
                    event.getSource(),
                    it,
                    MagicTargetChoice.SACRIFICE_CREATURE
                ));
                game.doAction(new MagicChangeLifeAction(it,-1));
            });
        }
    }
]
