package MissingToeHalfHeart;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World.Spigot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.Overridden;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.mojang.brigadier.Message;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.source.doctree.HiddenTree;
import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

import net.minecraft.server.v1_16_R2.EntityAnimal;
import net.minecraft.server.v1_16_R2.NBTTagCompound;

public class Main extends JavaPlugin implements Listener{
	Player player;
 	Entity wolfEntity;
 	public static ArrayList<Player> punchers = new ArrayList<Player>();
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
				if(command.getName().equalsIgnoreCase("onepunch")) {
					punchers.add((Player) sender);
				}
			}
		return false;
	}
	@EventHandler
	public void Hit(EntityDamageByEntityEvent hit) {
		if(hit.getEntity() instanceof Player && hit.getDamager() instanceof Player) {
			Player victom = (Player) hit.getEntity();
			Player attacker = (Player) hit.getDamager();
			for(Player p : punchers) {
				if(p.getName() == attacker.getName()) {
					float s = 10f;
					Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
						float a = s;
						public void run() {

								if(a  >= 1) {
									victom.getLocation().setY(victom.getLocation().getY() + 0.1f);
									victom.setVelocity(attacker.getLocation().getDirection().multiply(a));
									a--;
								}

					    	}
						}, 0, 0);
					}

				}
			}
			
		}
		
	}


