package org.densyakun.logmanage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Logmanage extends JavaPlugin implements Listener {
	static File lf = null;
	static BufferedReader br;
	static BufferedWriter bw;

	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		new File("./plugins/Logmanage/").mkdirs();
		lf = new File("./plugins/Logmanage/log.txt");
		try {
			if (!lf.exists())
				lf.createNewFile();
		} catch (IOException e) {
			System.out.println("[Logmanage] !Error!:\n" + e);
		}
		try {
			bw = new BufferedWriter(new FileWriter(lf));
		} catch (IOException e) {
			System.out.println("[Logmanage] !Error!:\n" + e);
		}
		try {
			br = new BufferedReader(new FileReader(lf));
		} catch (FileNotFoundException e) {
			System.out.println("[Logmanage] !Error!:\n" + e);
		}
	}

	public void onDisable() {
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("[Logmanage] !Error!:\n" + e);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			if (cmd.getName().equalsIgnoreCase("lm_clear")) {
				lf.delete();
				lf.createNewFile();
				sender.sendMessage(ChatColor.AQUA + "[Logmanage]Has been deleted");
				return true;
			}
			return false;
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "[Logmanage] !Error!:\n" + e.toString());
			System.out.println("[Logmanage] !Error!:\n" + e);
			return false;
		}
	}

	@EventHandler
	public void BlockBreak(BlockBreakEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockBreak] Player:" + evt.getPlayer().getDisplayName() + " World:"
				+ evt.getBlock().getWorld().getName() + " Block:" + evt.getBlock().getType() + " X:" + pl.getX() + " Y:"
				+ pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockBurn(BlockBurnEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockBurn] World:" + evt.getBlock().getWorld().getName() + " Block:"
				+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockDamage(BlockDamageEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockDamage] Player:" + evt.getPlayer().getDisplayName() + " World:"
				+ evt.getBlock().getWorld().getName() + " Block:" + evt.getBlock().getType() + " X:" + pl.getX() + " Y:"
				+ pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockDispense(BlockDispenseEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockDispense] World:" + evt.getBlock().getWorld().getName() + " Block:"
				+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + " Item:"
				+ evt.getItem().getType() + "\n");
	}

	@EventHandler
	public void BlockExp(BlockExpEvent evt) {
		if (evt.getExpToDrop() != 0) {
			Location pl = evt.getBlock().getLocation();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
			write(sdf.format(date) + " [BlockExp] World:" + evt.getBlock().getWorld().getName() + " Block:"
					+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + " Exp:"
					+ evt.getExpToDrop() + "\n");
		}
	}

	@EventHandler
	public void BlockGrow(BlockGrowEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockGrow] World:" + evt.getBlock().getWorld().getName() + " Block:"
				+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockIgnite(BlockIgniteEvent evt) {
		Location pl = evt.getIgnitingBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockIgnite] Player:" + evt.getPlayer().getDisplayName() + " World:"
				+ evt.getIgnitingBlock().getWorld().getName() + " Block:" + evt.getIgnitingBlock().getType() + " X:"
				+ pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockPistonExtend(BlockPistonExtendEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockPistonExtend] World:" + evt.getBlock().getWorld().getName() + " Block:"
				+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockPistonRetract(BlockPistonRetractEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockPistonRetract] World:" + evt.getBlock().getWorld().getName() + " Block:"
				+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockPlace(BlockPlaceEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockPlace] Player:" + evt.getPlayer().getDisplayName() + " World:"
				+ evt.getBlock().getWorld().getName() + " Block:" + evt.getBlock().getType() + " X:" + pl.getX() + " Y:"
				+ pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockRedstone(BlockRedstoneEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockRedstone] World:" + evt.getBlock().getWorld().getName() + " OldCurrent:"
				+ evt.getOldCurrent() + " NewCurrent:" + evt.getNewCurrent() + " Block:" + evt.getBlock().getType()
				+ " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void BlockSpread(BlockSpreadEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [BlockSpread] World:" + evt.getBlock().getWorld().getName() + " Block:"
				+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void LeavesDecay(LeavesDecayEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [LeavesDecay] World:" + evt.getBlock().getWorld().getName() + " Block:"
				+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void NotePlay(NotePlayEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [NotePlay] World:" + evt.getBlock().getWorld().getName() + " Block:"
				+ evt.getBlock().getType() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void SignChange(SignChangeEvent evt) {
		Location pl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [SignChange] Player:" + evt.getPlayer().getDisplayName() + " Line1:" + evt.getLine(0)
				+ " Line2:" + evt.getLine(1) + " Line3:" + evt.getLine(2) + " Line4:" + evt.getLine(3) + " World:"
				+ evt.getBlock().getWorld().getName() + " Block:" + evt.getBlock().getType() + " X:" + pl.getX() + " Y:"
				+ pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void EnchantItem(EnchantItemEvent evt) {
		Location pl = evt.getEnchantBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [EnchantItem] Player:" + evt.getEnchanter().getDisplayName() + " EnchantItem:"
				+ evt.getItem().getType() + " ExpLevelCost:" + evt.getExpLevelCost() + " World:"
				+ evt.getEnchantBlock().getWorld().getName() + " Block:" + evt.getEnchantBlock().getType() + " X:"
				+ pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void EntityBreakDoor(EntityBreakDoorEvent evt) {
		Location pl = evt.getEntity().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [EntityBreakDoor] Entity:" + evt.getEntityType() + " World:"
				+ evt.getEntity().getWorld().getName() + " X(Mob):" + pl.getX() + " Y(Mob):" + pl.getY() + " Z(Mob):"
				+ pl.getZ() + " Block:" + evt.getBlock().getType() + " X(Block):" + evt.getBlock().getX() + " Y(Block):"
				+ evt.getBlock().getY() + " Z(Block):" + evt.getBlock().getZ() + "\n");
	}

	@EventHandler
	public void EntityChangeBlock(EntityChangeBlockEvent evt) {
		Location pl = evt.getEntity().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [EntityChangeBlock] Entity:" + evt.getEntityType() + " World:"
				+ evt.getEntity().getWorld().getName() + " X(Mob):" + pl.getX() + " Y(Mob):" + pl.getY() + " Z(Mob):"
				+ pl.getZ() + " Block:" + evt.getBlock().getType() + " X(Block):" + evt.getBlock().getX() + " Y(Block):"
				+ evt.getBlock().getY() + " Z(Block):" + evt.getBlock().getZ() + "\n");
	}

	@EventHandler
	public void EntityExplode(EntityExplodeEvent evt) {
		Location pl = evt.getEntity().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [EntityExplode] Entity:" + evt.getEntityType() + " World:"
				+ evt.getEntity().getWorld().getName() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ()
				+ "\n");
	}

	@EventHandler
	public void PlayerBucket(PlayerBucketEvent evt) {
		Location pl = evt.getPlayer().getLocation();
		Location bl = evt.getBlockClicked().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [PlayerBucket] Player:" + evt.getPlayer().getDisplayName() + " Bucket:"
				+ evt.getBucket().name() + " World:" + evt.getPlayer().getWorld().getName() + " X(Player):" + pl.getX()
				+ " Y(Player):" + pl.getY() + " Z(Player):" + pl.getZ() + " X(Block):" + bl.getX() + " Y(Block):"
				+ bl.getY() + " Z(Block):" + bl.getZ() + "\n");
	}

	@EventHandler
	public void PlayerChangedWorld(PlayerChangedWorldEvent evt) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [PlayerChangedWorld] Player:" + evt.getPlayer().getDisplayName() + " OldWorld:"
				+ evt.getPlayer().getWorld().getName() + " NewWorld:" + evt.getFrom().getName() + "\n");
	}

	@EventHandler
	public void PlayerDropItem(PlayerDropItemEvent evt) {
		Location pl = evt.getPlayer().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [PlayerDropItem] Player:" + evt.getPlayer().getDisplayName() + " Item:"
				+ evt.getItemDrop().getItemStack().getType() + " World:" + evt.getPlayer().getWorld().getName() + " X:"
				+ pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void PlayerExpChange(PlayerExpChangeEvent evt) {
		Location pl = evt.getPlayer().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [PlayerExpChange] Player:" + evt.getPlayer().getDisplayName() + " Exp:"
				+ evt.getAmount() + " World:" + evt.getPlayer().getWorld().getName() + " X:" + pl.getX() + " Y:"
				+ pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void PlayerGameModeChange(PlayerGameModeChangeEvent evt) {
		Location pl = evt.getPlayer().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [PlayerGameModeChange] Player:" + evt.getPlayer().getDisplayName() + " GameMode:"
				+ evt.getNewGameMode() + " World:" + evt.getPlayer().getWorld().getName() + " X:" + pl.getX() + " Y:"
				+ pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	@EventHandler
	public void PlayerLogin(PlayerLoginEvent evt) {
		Location pl = evt.getPlayer().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [PlayerLogin] Player:" + evt.getPlayer().getDisplayName() + " World:"
				+ evt.getPlayer().getWorld().getName() + " X:" + pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ()
				+ "\n");
	}

	@EventHandler
	public void PlayerPickupItem(PlayerPickupItemEvent evt) {
		Location pl = evt.getPlayer().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [PlayerPickupItem] Player:" + evt.getPlayer().getDisplayName() + " Item:"
				+ evt.getItem().getItemStack().getType() + " World:" + evt.getPlayer().getWorld().getName() + " X:"
				+ pl.getX() + " Y:" + pl.getY() + " Z:" + pl.getZ() + "\n");
	}

	/*@EventHandler
	public void VehicleBlockCollision(VehicleBlockCollisionEvent evt) {
		Location vl = evt.getVehicle().getLocation();
		Location bl = evt.getBlock().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [VehicleBlockCollision] Vehicle:" + evt.getVehicle().getType() + " Block:"
				+ evt.getBlock().getType() + " World:" + evt.getVehicle().getWorld().getName() + " X(Vehicle):"
				+ vl.getX() + " Y(Vehicle):" + vl.getY() + " Z(Vehicle):" + vl.getZ() + " X(Block):" + bl.getX()
				+ " Y(Block):" + bl.getY() + " Z(Block):" + bl.getZ() + "\n");
	}

	@EventHandler
	public void VehicleCreate(VehicleCreateEvent evt) {
		Location vl = evt.getVehicle().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [VehicleCreate] Vehicle:" + evt.getVehicle().getType() + " World:"
				+ evt.getVehicle().getWorld().getName() + " X:" + vl.getX() + " Y:" + vl.getY() + " Z:" + vl.getZ()
				+ "\n");
	}

	@EventHandler
	public void VehicleDamage(VehicleDamageEvent evt) {
		Location vl = evt.getVehicle().getLocation();
		Location al = evt.getAttacker().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [VehicleDamage] Vehicle:" + evt.getVehicle().getType() + " World:"
				+ evt.getVehicle().getWorld().getName() + " X(Vehicle):" + vl.getX() + " Y(Vehicle):" + vl.getY()
				+ " Z(Vehicle):" + vl.getZ() + " X(Attacker):" + al.getX() + " Y(Attacker):" + al.getY()
				+ " Z(Attacker):" + al.getZ() + "\n");
	}

	@EventHandler
	public void VehicleDestroy(VehicleCollisionEvent evt) {
		Location vl = evt.getVehicle().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [VehicleDestroy] Vehicle:" + evt.getVehicle().getType() + " World:"
				+ evt.getVehicle().getWorld().getName() + " X:" + vl.getX() + " Y:" + vl.getY() + " Z:" + vl.getZ()
				+ "\n");
	}

	@EventHandler
	public void VehicleEnter(VehicleEnterEvent evt) {
		Location vl = evt.getVehicle().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [VehicleEnter] Vehicle:" + evt.getVehicle().getType() + " Entered" + evt.getEntered()
				+ " World:" + evt.getVehicle().getWorld().getName() + " X:" + vl.getX() + " Y:" + vl.getY() + " Z:"
				+ vl.getZ() + "\n");
	}

	@EventHandler
	public void VehicleEntityCollision(VehicleEntityCollisionEvent evt) {
		Location vl = evt.getVehicle().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [VehicleEntityCollision] Vehicle:" + evt.getVehicle().getType() + " Entity:"
				+ evt.getEntity().getType() + " World:" + evt.getVehicle().getWorld().getName() + " X:" + vl.getX()
				+ " Y:" + vl.getY() + " Z:" + vl.getZ() + "\n");
	}

	@EventHandler
	public void VehicleExit(VehicleExitEvent evt) {
		Location vl = evt.getVehicle().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [VehicleExit] Vehicle:" + evt.getVehicle().getType() + " Exited:" + evt.getExited()
				+ " World:" + evt.getVehicle().getWorld().getName() + " X:" + vl.getX() + " Y:" + vl.getY() + " Z:"
				+ vl.getZ() + "\n");
	}

	@EventHandler
	public void VehicleMove(VehicleMoveEvent evt) {
		Location vl = evt.getVehicle().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [VehicleMove] Vehicle:" + evt.getVehicle().getType() + " World:"
				+ evt.getVehicle().getWorld().getName() + " X:" + vl.getX() + " Y:" + vl.getY() + " Z:" + vl.getZ()
				+ "\n");
	}*/

	@EventHandler
	public void LightningStrike(LightningStrikeEvent evt) {
		Location l = evt.getLightning().getLocation();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [LightningStrike] World:" + evt.getLightning().getWorld().getName() + " X:"
				+ l.getX() + " Y:" + l.getY() + " Z:" + l.getZ() + "\n");
	}

	@EventHandler
	public void WeatherChange(WeatherChangeEvent evt) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		write(sdf.format(date) + " [WeatherChange] World:" + evt.getWorld() + "\n");
	}

	public static void write(String str) {
		try {
			bw.write(str);
		} catch (IOException e) {
			System.out.println("[Logmanage] !Error!:\n" + e);
		}
	}
}
