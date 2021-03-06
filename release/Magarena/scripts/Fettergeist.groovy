[
    new MagicAtYourUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPlayer upkeepPlayer) {
            final int amount = upkeepPlayer.getNrOfPermanents(MagicType.Creature) - 1;
            final MagicManaCost cost = MagicManaCost.create("{"+amount+"}");
            return new MagicEvent(
                permanent,
                new MagicMayChoice(
                    "Pay ${cost}?",
                    new MagicPayManaCostChoice(cost)
                ),
                this,
                "PN may\$ pay {1} for each other creature he/she controls. If PN doesn't, sacrifice SN."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isNo()) {
                game.doAction(new MagicSacrificeAction(event.getPermanent()));
            }
        }
    }
]
