[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "Each player discards all the cards in his or her hand, then draws that many cards minus one."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final int hand1 = event.getPlayer().getHandSize() - 1;
            final int hand2 = event.getPlayer().getOpponent().getHandSize() - 1;
            for (final MagicPlayer player : game.getAPNAP()) {
                final MagicCardList hand = new MagicCardList(player.getHand());
                for (final MagicCard card : hand) {
                    game.doAction(new MagicDiscardCardAction(player,card));
                }
            };
                game.doAction(new MagicDrawAction(event.getPlayer(),hand1));
                game.doAction(new MagicDrawAction(event.getPlayer().getOpponent(),hand2));
        }
    }
]
