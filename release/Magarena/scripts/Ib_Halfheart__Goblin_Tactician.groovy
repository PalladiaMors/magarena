[
    new MagicWhenBecomesBlockedTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPermanent otherPermanent) {
            return (permanent != otherPermanent &&
                    otherPermanent.isFriend(permanent) &&
                    otherPermanent.isCreature() &&
                    otherPermanent.hasSubType(MagicSubType.Goblin)) ?
                new MagicEvent(
                    permanent,
                    otherPermanent,
                    this,
                    "PN sacrifices RN. If PN does, RN deals 4 damage to each creature blocking it."
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanent goblin = event.getRefPermanent();
            final MagicSacrificeAction sac = new MagicSacrificeAction(goblin);
            final MagicPermanentList blockingCreatures = goblin.getBlockingCreatures();
            game.doAction(sac);
            if (sac.isValid()) {
                for (final MagicPermanent blocker : blockingCreatures) {
                    final MagicDamage damage = new MagicDamage(goblin, blocker, 4);
                    game.doAction(new MagicDealDamageAction(damage));
                }
            }
        }
    }
]
