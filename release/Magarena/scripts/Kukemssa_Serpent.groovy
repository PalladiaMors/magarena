def ST1 = new MagicStatic(MagicLayer.Ability, MagicStatic.UntilEOT) {
    @Override
    public void modAbilityFlags(final MagicPermanent source, final MagicPermanent permanent, final Set<MagicAbility> flags) {
        permanent.loseAllAbilities();
        permanent.addAbility(new MagicTapManaActivation(MagicManaType.getList("{U}")));
    }
};

def ST2 = new MagicStatic(MagicLayer.Type, MagicStatic.UntilEOT) {
    @Override
    public void modSubTypeFlags(final MagicPermanent permanent,final Set<MagicSubType> flags) {
        flags.clear();
        flags.add(MagicSubType.Island);
    }
}

def TARGET_LAND_YOUR_OPPONENT_CONTROLS = new MagicTargetChoice("target land an opponent controls");

[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Pump),
        "Island"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source,"{U}"),
                new MagicSacrificePermanentEvent(source, new MagicTargetChoice("an Island to sacrifice"))
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                TARGET_LAND_YOUR_OPPONENT_CONTROLS,
                MagicExileTargetPicker.create(),
                this,
                "Target land\$ an opponent controls becomes an Island until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                game.doAction(new MagicAddStaticAction(it, ST1));
                game.doAction(new MagicAddStaticAction(it, ST2));
            });
        }
    }
]
