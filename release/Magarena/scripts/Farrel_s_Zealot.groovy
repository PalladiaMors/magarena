[
    new MagicWhenAttacksUnblockedTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPermanent creature) {
            return (creature == permanent) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(MagicTargetChoice.NEG_TARGET_CREATURE),
                    new MagicDamageTargetPicker(3),
                    this,
                    "PN may\$ have SN deal 3 damage to target creature\$. " +
                    "If you do, SN assigns no combat damage this turn."
                ) :
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game, {
                    final MagicPermanent permanent = event.getPermanent();
                    final MagicDamage damage = new MagicDamage(
                        permanent,
                        it,
                        3
                    );
                    game.doAction(new MagicDealDamageAction(damage));
                    game.doAction(MagicChangeStateAction.Set(
                        permanent,
                        MagicPermanentState.NoCombatDamage
                    ));
                });
            }
        }
    }
]
