[
    new MagicWhenDamageIsDealtTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            final int amount=damage.getDealtAmount();
            return (damage.getTarget()==permanent) ?
                new MagicEvent(
                    permanent,
                    MagicTargetChoice.TARGET_CREATURE_OR_PLAYER,
                    new MagicDamageTargetPicker(amount),
                    amount,
                    this,
                    "SN deals RN damage to target creature or player\$."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTarget(game, {
                final MagicDamage damage=new MagicDamage(event.getSource(),it,event.getRefInt());
                game.doAction(new MagicDealDamageAction(damage));
            });
        }
    }
]
