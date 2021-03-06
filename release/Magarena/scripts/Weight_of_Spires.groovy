[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.NEG_TARGET_CREATURE,
                this,
                "SN deals damage to target creature\$ equal to the number of nonbasic lands that creature's controller controls."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                final int amount = it.getController().getNrOfPermanents(MagicTargetFilterFactory.NONBASIC_LAND)
                game.doAction(new MagicDealDamageAction(event.getSource(), it, amount));
            });
        }
    }
]
