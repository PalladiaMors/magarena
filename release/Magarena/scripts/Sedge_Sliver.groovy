[
    new MagicStatic(
        MagicLayer.ModPT,
        MagicTargetFilterFactory.SLIVER
    ) {
        @Override
        public void modPowerToughness(final MagicPermanent source,final MagicPermanent permanent,final MagicPowerToughness pt) {
            if (permanent.getController().controlsPermanent(MagicSubType.Swamp)) {
                pt.add(1,1);
            }
        }
    }
]
