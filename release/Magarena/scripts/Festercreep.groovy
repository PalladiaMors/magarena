[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "Weaken"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source,"{1}{B}"), 
                new MagicRemoveCounterEvent(source,MagicCounterType.PlusOne,1)
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "All other creatures get -1/-1 until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final Collection<MagicPermanent> targets = game.filterPermanents(MagicTargetFilterFactory.CREATURE);
            for (final MagicPermanent creature : targets) {
                if (creature != event.getPermanent()) {           
                    game.doAction(new MagicChangeTurnPTAction(creature, -1, -1));
                }
            }
        }
    }
]
