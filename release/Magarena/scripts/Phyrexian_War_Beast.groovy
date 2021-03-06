[
    new MagicWhenSelfLeavesPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent, final MagicRemoveFromPlayAction data) {
            return new MagicEvent(
                permanent,
                this,
                "PN sacrifices a land and SN deals 1 damage to PN."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanent permanent = event.getPermanent();
            final MagicDamage damage = new MagicDamage(permanent,permanent.getController(),1);
            game.addEvent(new MagicSacrificePermanentEvent(permanent, MagicTargetChoice.LAND_YOU_CONTROL));
            game.doAction(new MagicDealDamageAction(damage));
        }
    }
]
