package fr.gameurduxvi.uhc.Personnages.Empereurs.Big_Mom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.gameurduxvi.uhc.Main;
import fr.gameurduxvi.uhc.Storage.PlayerData;
import fr.gameurduxvi.uhc.Storage.PrefixData;
import fr.gameurduxvi.uhc.SuperClasses.Personnage;

public class Big_MomP extends Personnage {
	public Big_MomP() {
		setId(13);
		setType(1);
		setPriority(3);
		setName("Big Mom");
		setPrime(Main.getInstance().primeEmpereur);
		setAmount(1);
		setUltimateRecharge(1);
		setUltimateTime(1);
		setDescription("Choix de ton ulti");
		
		ItemStack it = new ItemStack(Material.RED_ROSE);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName("�aChoix de ton ulti");
		it.setItemMeta(itM);
		setItemUltimate(it);
		
		setShipFileName("big_mom");
		
		setFidelePrefix("�5Fid�le ");
		

		setAttributedSoundName("wano.roles.attribution.empereurs.big_mom");
	}

	@Override
	public void setPrefixData() {
		super.prefixData.add(new PrefixData("�5Big Mom ", "", new int[]{-1}));
	}
	
	@Override
	public void ultimate(PlayerData pd) {
		Inventory inv = Bukkit.createInventory(null, 27, getName());
		
		ItemStack bg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
		ItemMeta bgM = bg.getItemMeta();
		bgM.setDisplayName("�a");
		bg.setItemMeta(bgM);
		
		for(int i = 0; i <= 26; i++) {
			inv.setItem(i, bg);
		}
		
		ItemStack it1 = new ItemStack(Material.ENDER_PORTAL_FRAME, 1);
		ItemMeta it1M = it1.getItemMeta();
		it1M.setDisplayName("�aCognac");
		it1.setItemMeta(it1M);
		inv.setItem(12, it1);
		
		ItemStack it2 = new ItemStack(Material.QUARTZ, 1);
		ItemMeta it2M = it2.getItemMeta();
		it2M.setDisplayName("�aIkoku");
		it2.setItemMeta(it2M);
		inv.setItem(14, it2);
		
		pd.getPlayer().openInventory(inv);
	}
}
