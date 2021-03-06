[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Draw),
        "Mill"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source,"{4}"),
                new MagicTapEvent(source)
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.TARGET_OPPONENT,
                this,
                "Target opponent\$ loses 2 life, gets a poison counter, then puts the top six cards of his or her library into his or her graveyard."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                game.doAction(new MagicChangeLifeAction(it, -2));
                game.doAction(new MagicChangePoisonAction(it, 1));
                game.doAction(new MagicMillLibraryAction(it, 6));
            });
        }
    }
]
