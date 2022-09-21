package fr.gameurduxvi.uhc.GameEvents;

import org.bukkit.Bukkit;

import fr.gameurduxvi.uhc.Main;
import fr.gameurduxvi.uhc.SuperClasses.GameEvent;

public class AntiDamageGameEvent extends GameEvent {
	public AntiDamageGameEvent() {
		setName("Temp de d'invicibilit�");
		setDescription("A partir de ce moment, \nles joueurs reprendront les d�gats normaux");
		setMinute(1);
		setSecond(0);
		setInScoreboard(true);
		setScoreboardText("�7D�g�ts : �f%m%�7:�f%s%");
	}
	public void run() {
		Bukkit.broadcastMessage("�bInfo �7>> �fLes d�g�ts sont activ�s");
		Main.getInstance().anyDamage = true;
	}
}
