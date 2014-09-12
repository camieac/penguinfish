package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.newdawn.slick.util.xml.SlickXMLException;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLParser;

public class Settings {
	public static Settings settings;
	private XMLElement xmlSettings;
	static {
		settings = new Settings();
	}

	private int windowWidth;
	private int windowHeight;

	public Settings() {
		XMLParser xmlParser = new XMLParser();
		InputStream is = null;
		try {
			is = new FileInputStream(new File("res/xml/settings.xml"));
			xmlSettings = xmlParser.parse("settings.xml", is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SlickXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void saveSettingstoXML(){
		
	}

	public static XMLElement getInstance() {
		return settings.xmlSettings;
	}
}
