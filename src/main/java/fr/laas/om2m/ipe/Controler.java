package fr.laas.om2m.ipe;

import fr.laas.mooc.helper.http.HTTPGet;
import fr.laas.mooc.helper.om2m.Deserializer;
import fr.laas.mooc.helper.virtual.Platform;
import fr.laas.mooc.helper.virtual.SensorManager;
import fr.laas.mooc.helper.virtual.T_Room;
import fr.laas.mooc.helper.virtual.TemperatureSensor;
import fr.laas.mooc.helper.virtual.VirtualSensor;

public class Controler {
	public static void main(String[] args) {
		/*Création objet Ipe*/
		IPE ipe = new IPE();
		/*Création de l'ACP */
		SensorManager sm = ipe.createSensorManager("SensorManager");
		/*Création de deux application entity*/
		ipe.createPlatform(sm.getId(), new Platform("Home_Platform", T_Room.BEDROOM));
		ipe.createPlatform(sm.getId(), new Platform("Garden_Platform", T_Room.LIVINGROOM));
		
		/*Création des containers, ce sont les senseurs qui auront les contents instances qui contiennent les données */
		/*les addSensor sont les capteur qui envoient les données  */
		ipe.addSensor(sm.getId(), "Home_Platform", new TemperatureSensor("Bedroom_Thermometer", T_Room.BEDROOM));
		ipe.addSensor(sm.getId(), "Home_Platform", new TemperatureSensor("Livingroom_Thermometer", T_Room.LIVINGROOM));
		/*Ajout d'un capteur de test*/
		ipe.addSensor(sm.getId(), "Home_Platform", new TemperatureSensor("TEST_Thermometer", T_Room.LIVINGROOM));
		ipe.addSensor(sm.getId(), "Home_Platform", new TemperatureSensor("Bedroom_Thermometer", T_Room.BEDROOM));
		ipe.addSensor(sm.getId(), "Garden_Platform", new TemperatureSensor("Garden_Thermometer", T_Room.GARDEN));
		/*les readSensor c'est l'IPE qui va chercher les données  */
		ipe.readSensor(sm.getId(), "Home_Platform", "Livingroom_Thermometer");
		ipe.readSensor(sm.getId(), "Garden_Platform", "Garden_Thermometer");
		
		System.out.println("Number of platforms : "+sm.getAllPlatforms().size());
		for(Platform p : sm.getAllPlatforms()){
			System.out.println("Pour "+p.getName()+" : "+p.getAllSensor().size()+" sensors");
		}
	}
}

