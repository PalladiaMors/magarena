[
    new MagicWhenSelfCombatDamagePlayerTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            return new MagicEvent(
                permanent,
                new MagicMayChoice(
                    MagicTargetChoice.SACRIFICE_MERFOLK
                ),
                MagicSacrificeTargetPicker.create(),
                this,
                "PN may\$ sacrifice a Merfolk\$. " +
                "If you do, take an extra turn after this one"
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game, {
                    game.doAction(new MagicSacrificeAction(it));
                    game.doAction(new MagicChangeExtraTurnsAction(event.getPlayer(),1));
                });
            }
        }
    }
]
