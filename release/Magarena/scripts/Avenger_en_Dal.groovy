def choice = new MagicTargetChoice("target attacking creature");

[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "Exile"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicTapEvent(source),
                new MagicPayManaCostEvent(source, "{2}{W}"),
                new MagicDiscardEvent(source)
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                choice,
                MagicExileTargetPicker.create(),
                this,
                "Exile target attacking creature\$. Its controller gains life equal to its toughness."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                game.addEvent(new MagicExileEvent(it));
                game.doAction(new MagicChangeLifeAction(it.getController(),it.getToughness()));
                game.logAppendMessage(event.getPlayer(),"("+it.getToughness()+")");
            });
        }
    }
]
