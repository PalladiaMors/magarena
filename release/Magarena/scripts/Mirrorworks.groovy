[
    new MagicWhenOtherComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(
                final MagicGame game,
                final MagicPermanent permanent,
                final MagicPermanent otherPermanent) {
            return (otherPermanent != permanent &&
                    otherPermanent.isNonToken() &&
                    otherPermanent.isArtifact()) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(
                        new MagicPayManaCostChoice(MagicManaCost.create("{2}"))
                    ),
                    otherPermanent,
                    this,
                    "You may\$ pay {2}\$. If you do, put a token that's a " +
                    "copy of RN onto the battlefield."
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                game.doAction(new MagicPlayTokenAction(
                    event.getPlayer(),
                    event.getRefPermanent()
                ));
            }
        }
    }
]
