[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.NEG_TARGET_SPELL,
                this,
                "Counter target spell\$. Its controller can't cast spells this turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetCardOnStack(game, {
                game.doAction(new MagicCounterItemOnStackAction(it));
                game.doAction(new MagicChangePlayerStateAction(
                    it.getController(),
                    MagicPlayerState.CantCastSpells
                ));
            });
        }
    }
]
