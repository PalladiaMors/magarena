[
    new MagicWhenOtherSpellIsCastTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicCardOnStack cardOnStack) {
            final int damage=cardOnStack.getConvertedCost();
            return permanent.isEnemy(cardOnStack) ?
                new MagicEvent(
                    permanent,
                    MagicTargetChoice.NEG_TARGET_CREATURE_OR_PLAYER,
                    new MagicDamageTargetPicker(damage),
                    damage,
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
