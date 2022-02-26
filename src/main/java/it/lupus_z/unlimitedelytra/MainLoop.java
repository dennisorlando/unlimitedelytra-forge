package it.lupus_z.unlimitedelytra;

import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class MainLoop extends Thread{

	public static boolean exit;
	
	private static final int REFRESH_RATE = 60;
	private Minecraft minecraft;
	private Logger logger;
	
	public MainLoop(Minecraft instance, Logger logger) {
		this.minecraft = instance;
		this.logger = logger;
	}
	
	public void run() {
		
		while(!exit) {
			
			try {
				
				long checkpointA = System.currentTimeMillis();
				
				coolStuff(minecraft.player);
				long checkpointB = System.currentTimeMillis();
				
				try {
					sleep(Math.max(0, 1000L/REFRESH_RATE - (checkpointB-checkpointA)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			catch (NullPointerException e) {
				try {
					sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	
	}
	
	public void coolStuff(LocalPlayer p) {
		//raycast
		if (p.isSprinting() && p.isFallFlying()) {
			
			Inair.affect(p);
			
		}
	}
}
