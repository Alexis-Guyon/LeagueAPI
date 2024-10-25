package fr.bxcchus.bot.api.impl;

import fr.bxcchus.bot.ChampionStats ;
import fr.bxcchus.bot.api.BotService;
import fr.bxcchus.bot.api.response.BotResponse;

public class BotServiceImpl implements BotService {

    // Méthode pour récupérer les données du joueur actif depuis l'API
    @Override
    public BotResponse getActivePlayerData() {
        try {
            return BotService.super.getActivePlayerData();
        } catch (Exception e) {
            System.err.println("Error retrieving active player data: " + e.getMessage());
            return null;
        }
    }

    // Méthode pour mettre à jour les statistiques du bot
    @Override
    public void updateBotStats(ChampionStats bot) {
        try {
            BotResponse response = getActivePlayerData();
            if (response != null && response.getChampionStats() != null) {
                bot.setAbilityHaste(response.getChampionStats().getAbilityHaste());
                bot.setAbilityPower(response.getChampionStats().getAbilityPower());
                bot.setArmor(response.getChampionStats().getArmor());
                bot.setArmorPenetrationFlat(response.getChampionStats().getArmorPenetrationFlat());
                bot.setArmorPenetrationPercent(response.getChampionStats().getArmorPenetrationPercent());
                bot.setAttackDamage(response.getChampionStats().getAttackDamage());
                bot.setAttackRange(response.getChampionStats().getAttackRange());
                bot.setAttackSpeed(response.getChampionStats().getAttackSpeed());
                bot.setBonusArmorPenetrationPercent(response.getChampionStats().getBonusArmorPenetrationPercent());
                bot.setBonusMagicPenetrationPercent(response.getChampionStats().getBonusMagicPenetrationPercent());
                bot.setCritChance(response.getChampionStats().getCritChance());
                bot.setCritDamage(response.getChampionStats().getCritDamage());
                bot.setCurrentHealth(response.getChampionStats().getCurrentHealth());
                bot.setHealShieldPower(response.getChampionStats().getHealShieldPower());
                bot.setHealthRegenRate(response.getChampionStats().getHealthRegenRate());
                bot.setLifeSteal(response.getChampionStats().getLifeSteal());
                bot.setMagicLethality(response.getChampionStats().getMagicLethality());
                bot.setMagicPenetrationFlat(response.getChampionStats().getMagicPenetrationFlat());
                bot.setMagicPenetrationPercent(response.getChampionStats().getMagicPenetrationPercent());
                bot.setMagicResist(response.getChampionStats().getMagicResist());
                bot.setMaxHealth(response.getChampionStats().getMaxHealth());
                bot.setMoveSpeed(response.getChampionStats().getMoveSpeed());
                bot.setOmnivamp(response.getChampionStats().getOmnivamp());
                bot.setPhysicalLethality(response.getChampionStats().getPhysicalLethality());
                bot.setPhysicalVamp(response.getChampionStats().getPhysicalVamp());
                bot.setResourceMax(response.getChampionStats().getResourceMax());
                bot.setResourceRegenRate(response.getChampionStats().getResourceRegenRate());
                bot.setResourceType(response.getChampionStats().getResourceType());
                bot.setResourceValue(response.getChampionStats().getResourceValue());
                bot.setSpellVamp(response.getChampionStats().getSpellVamp());
                bot.setTenacity(response.getChampionStats().getTenacity());
                System.out.println("Bot stats updated successfully.");
            } else {
                System.err.println("Failed to retrieve or update bot stats.");
            }
        } catch (Exception e) {
            System.err.println("Error updating bot stats: " + e.getMessage());
        }
    }
}
