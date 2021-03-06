[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPayedCost payedCost) {
            return payedCost.isKicked() ?
                new MagicEvent(
                    permanent,
                    MagicTargetChoice.NEG_TARGET_PLAYER,
                    payedCost.getKicker(),
                    this,
                    "SN deals damage to target player\$ equal to twice the number of times it was kicked. (RN)"
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                final MagicDamage damage = new MagicDamage(
                    event.getSource(),
                    it,
                    event.getRefInt() * 2
                );
                game.doAction(new MagicDealDamageAction(damage));
            });
        }
    }
]
