[
    new MagicStatic(MagicLayer.Ability,MagicTargetFilterFactory.CREATURE) {
        @Override
        public void modAbilityFlags(final MagicPermanent source, final MagicPermanent permanent, final Set<MagicAbility> flags) {
            flags.remove(MagicAbility.Flying);
        }
    }
]
