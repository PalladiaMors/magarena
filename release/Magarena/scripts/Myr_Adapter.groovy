[
    new MagicStatic(MagicLayer.ModPT) {
        @Override
        public void modPowerToughness(final MagicPermanent source,final MagicPermanent permanent,final MagicPowerToughness pt) {
            if (permanent.isEquipped()) {
                final int amount = permanent.getEquipmentPermanents().size()
                pt.add(amount,amount);
            }
        }
    }
]
