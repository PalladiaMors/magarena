[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPayedCost payedCost) {
            return new MagicEvent(
                permanent,
                permanent.getOpponent(),
                new MagicMayChoice(
                "Search your library?"
                ),
                this,
                "PN may \$ search his or her library for a creature card, "+
                "and put it onto the battlefield. Then PN shuffles his or her library."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.addEvent(new MagicSearchOntoBattlefieldEvent(
                event,
                new MagicFromCardFilterChoice(
                    MagicTargetFilterFactory.CREATURE_CARD_FROM_LIBRARY,
                    1, 
                    true, 
                    "to put onto the battlefield"
                ),
            ));
        }
    }
]
