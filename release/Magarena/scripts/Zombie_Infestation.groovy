[
    new MagicPermanentActivation(
        [MagicCondition.HAS_TWO_CARDS_CONDITION],
        new MagicActivationHints(MagicTiming.Token,true),
        "Token"
    ) {
        @Override
        public MagicEvent[] getCostEvent(final MagicPermanent source) {
            return [
                new MagicDiscardEvent(source,2,false)
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "PN puts a 2/2 black Zombie creature token onto the battlefield."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicPlayTokenAction(
                event.getPlayer(),
                TokenCardDefinitions.get("Zombie")
            ));
        }
    }
]
