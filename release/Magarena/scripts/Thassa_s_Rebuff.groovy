[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.NEG_TARGET_SPELL,
                this,
                "Counter target spell\$ unless its controller pays {X}, where X is PN's devotion to blue."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            final int amount = player.getDevotion(MagicColor.Blue);
            game.logAppendMessage(player," (X="+amount+")");
            event.processTargetCardOnStack(game, {
                game.addEvent(new MagicCounterUnlessEvent(
                    event.getSource(), 
                    it, 
                    MagicManaCost.create("{" + amount + "}")
                ));
            });
        }
    }
]
