[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPayedCost payedCost) {
            return new MagicEvent(
                permanent,
                MagicTargetChoice.NEG_TARGET_PERMANENT,
                MagicExileTargetPicker.create(),
                this,
                "Gain control of target permanent\$ until end of turn. " +
                "Untap that permanent. It gains haste until end of turn."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                game.doAction(new MagicGainControlAction(event.getPlayer(),it,MagicStatic.UntilEOT));
                game.doAction(new MagicUntapAction(it));
                game.doAction(new MagicGainAbilityAction(it,MagicAbility.Haste));
            });
        }
    }
]
