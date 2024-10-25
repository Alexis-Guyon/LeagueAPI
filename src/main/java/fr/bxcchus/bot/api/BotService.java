package fr.bxcchus.bot.api;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.bot.ChampionStats;
import fr.bxcchus.bot.api.response.BotResponse;

public interface BotService extends IBot {

    // Méthode pour récupérer les données du joueur actif depuis l'API
    default BotResponse getActivePlayerData() {
        String url = "liveclientdata/activeplayer";
        HttpMethod method = HttpMethod.GET;

        // Utiliser botRequest pour obtenir l'objet complet de la réponse
        return request(url, method, null, BotResponse.class);
    }

    // Méthode pour mettre à jour le Bot avec les stats de championStats
    default void updateBotStats(ChampionStats bot) {
        BotResponse activePlayerData = getActivePlayerData();
        if (activePlayerData != null && activePlayerData.getChampionStats() != null) {
            ChampionStats stats = activePlayerData.getChampionStats();

            // Mise à jour de l'objet Bot avec les valeurs de ChampionStats
            bot.setAbilityHaste(stats.getAbilityHaste());
            bot.setAbilityPower(stats.getAbilityPower());
            bot.setArmor(stats.getArmor());
            bot.setArmorPenetrationFlat(stats.getArmorPenetrationFlat());
            bot.setArmorPenetrationPercent(stats.getArmorPenetrationPercent());
            bot.setAttackDamage(stats.getAttackDamage());
            bot.setAttackRange(stats.getAttackRange());
            bot.setAttackSpeed(stats.getAttackSpeed());
            bot.setBonusArmorPenetrationPercent(stats.getBonusArmorPenetrationPercent());
            bot.setBonusMagicPenetrationPercent(stats.getBonusMagicPenetrationPercent());
            bot.setCritChance(stats.getCritChance());
            bot.setCritDamage(stats.getCritDamage());
            bot.setCurrentHealth(stats.getCurrentHealth());
            bot.setHealShieldPower(stats.getHealShieldPower());
            bot.setHealthRegenRate(stats.getHealthRegenRate());
            bot.setLifeSteal(stats.getLifeSteal());
            bot.setMagicLethality(stats.getMagicLethality());
            bot.setMagicPenetrationFlat(stats.getMagicPenetrationFlat());
            bot.setMagicPenetrationPercent(stats.getMagicPenetrationPercent());
            bot.setMagicResist(stats.getMagicResist());
            bot.setMaxHealth(stats.getMaxHealth());
            bot.setMoveSpeed(stats.getMoveSpeed());
            bot.setOmnivamp(stats.getOmnivamp());
            bot.setPhysicalLethality(stats.getPhysicalLethality());
            bot.setPhysicalVamp(stats.getPhysicalVamp());
            bot.setResourceMax(stats.getResourceMax());
            bot.setResourceRegenRate(stats.getResourceRegenRate());
            bot.setResourceType(stats.getResourceType());
            bot.setResourceValue(stats.getResourceValue());
            bot.setSpellVamp(stats.getSpellVamp());
            bot.setTenacity(stats.getTenacity());

            System.out.println("Bot stats updated successfully!");
        } else {
            System.out.println("Failed to retrieve or update bot stats.");
        }
    }
}
