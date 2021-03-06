[
    new MagicWhenSelfBecomesBlockedTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPermanent attacker) {
            final MagicPermanentList plist = new MagicPermanentList(permanent.getBlockingCreatures());
            return new MagicEvent(
                permanent,
                plist,
                this,
                "SN deals 1 damage to each creature blocking it."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanentList plist = event.getRefPermanentList();
            for (final MagicPermanent blocker : plist) {
                final MagicDamage damage = new MagicDamage(event.getSource(),blocker,1);
                game.doAction(new MagicDealDamageAction(damage));
            }
        }
    }
]
