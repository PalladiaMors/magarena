[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "For each nonland permanent, choose a player at random. Then each player gains control of each permanent for which he or she was chosen. Untap those permanents."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanentList permanents = new MagicPermanentList(
                game.filterPermanents(MagicTargetFilterFactory.NONLAND_PERMANENT)
            );
            final MagicRandom rng = new MagicRandom(permanents.getStateId());
            for(final MagicPermanent permanent : permanents) {
                final int newCtrlNr = rng.nextInt(2); 
                game.doAction(new MagicGainControlAction(game.getPlayer(newCtrlNr), permanent));
                game.doAction(new MagicUntapAction(permanent));
            }
        }
    }
]
