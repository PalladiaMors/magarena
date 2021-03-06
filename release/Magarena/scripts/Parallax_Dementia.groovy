[
    new MagicWhenSelfLeavesPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicRemoveFromPlayAction act) {
            final MagicPermanent enchantedPermanent = permanent.getEnchantedPermanent();
            return enchantedPermanent.isValid() ? 
                new MagicEvent(
                    permanent,
                    enchantedPermanent,
                    this,
                    "Destroy RN. It can't be regenerated."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(MagicChangeStateAction.Set(event.getRefPermanent(),MagicPermanentState.CannotBeRegenerated));
            game.doAction(new MagicDestroyAction(event.getRefPermanent()));
        }
    }
]
