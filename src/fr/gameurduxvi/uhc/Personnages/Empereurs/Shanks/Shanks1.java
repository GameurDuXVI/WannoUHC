package fr.gameurduxvi.uhc.Personnages.Empereurs.Shanks;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fr.gameurduxvi.uhc.Main;
import fr.gameurduxvi.uhc.Particles.DefaultParticle;
import fr.gameurduxvi.uhc.Storage.PlayerData;
import fr.gameurduxvi.uhc.Storage.PrefixData;
import fr.gameurduxvi.uhc.SuperClasses.Personnage;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class Shanks1 extends Personnage {
	
	public boolean ignoreArmor = false;
	
	public Shanks1() {
		setId(12);
		setType(1);
		setPriority(3);
		setName("Shanks");
		setPrime(Main.getInstance().primeEmpereur);
		setAmount(0);
		setUltimateRecharge(60 * 7);
		setUltimateTime(10);
		setDescription("§d§l§nHaki des Rois\n" + 
				"\n" + 
				"§7Shanks grâce à un don rare, rend tous ses adversaires proches inconscient.\n" + 
				"\n" + 
				"§6Durée §f: §e10s\n" + 
				"§6Cooldown §f: §e7min\n" + 
				"\n" + 
				"§2✔ Effets bonus §f:\n" + 
				"§a⇨ §7§l-2§4❤ pour les adversaires dans une zone de §7§l10x10 §aautour de Shanks.\n" + 
				"§a⇨ §7§lStrenght 1 §aà Shanks.\n" + 
				"\n" + 
				"\n" + 
				"§4✖ Effets malus §f:\n" + 
				"§c⇨ §7§l-8§4❤ §cdurant §7§l7min §cà la fin de l'ulti.");
		
		ItemStack it = new ItemStack(Material.GHAST_TEAR);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName("§aChapeau de Paille");
		it.setItemMeta(itM);
		setItemUltimate(it);
		
		setShipFileName("ship");
		
		setFidelePrefix("§4Fidèle ");
		
		setUltimateSoundName("wano.roles.ultimates.empereurs.shanks.haki_des_rois");
	}

	@Override
	public void setPrefixData() {
		super.prefixData.add(new PrefixData("§4Shanks ", "", new int[]{-1}));
	}
	
	@Override
	public void ultimate(PlayerData pd) {
		super.ultimate(pd);
		pd.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * getUltimateTime(), 0, true, false), true);
		
		ignoreArmor = true;
		for(Entity e: pd.getPlayer().getWorld().getNearbyEntities(pd.getPlayer().getLocation(), 10, 10, 10)) {
			if(!(e instanceof Player)) continue;
			Player p = (Player) e;
			if(pd.getPlayer().getName().equals(p.getName())) continue;
			p.damage(4, pd.getPlayer());
		}
		ignoreArmor = false;

		Random r = new Random();
		for(int i = 0; i < getUltimateTime() * 10; i++) {
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					for(int k = 0; k < 10; k++) {
						double frontBack = (r.nextInt(8) / 10.0) - 0.5;
						double leftRight = (r.nextInt(10) / 10.0) - 0.5;
						double height = (r.nextInt(18) / 10.0) + 0.1;
						//Bukkit.broadcastMessage(frontBack + "   " + leftRight + "   " + height);
						new DefaultParticle(getFrontSide(getRightSide(pd.getPlayer().getLocation(), leftRight), frontBack).clone().add(0, height, 0), EnumParticle.SPELL_MOB, 255, 0, 0, null, 0).run();
					}
				}
			}, i * 2);
		}
	}
	
	private Location getRightSide(Location location, double distance) {
	    double angle = Math.toRadians(location.getYaw());
	    return location.clone().subtract(new Vector(Math.cos(angle), 0, Math.sin(angle)).normalize().multiply(distance));
	}
	
	private Location getFrontSide(Location loc, double distance) {
		double angle = Math.toRadians(loc.getYaw());
		return loc.clone().add(new Vector(-Math.sin(angle), 0, Math.cos(angle)).normalize().multiply(distance));
	}
	
	@Override
	public void ultimateEnd(PlayerData pd) {
		super.ultimateEnd(pd);
		//pd.getPlayer().setMaxHealth(pd.getMaxHealth() - 10);
		pd.setMalusHealth(-16);
	}
	
	@Override
	public void ultimateRecharge(PlayerData pd) {
		super.ultimateRecharge(pd);
		//pd.getPlayer().setMaxHealth(pd.getMaxHealth());
		pd.setMalusHealth(0);
	}
}
