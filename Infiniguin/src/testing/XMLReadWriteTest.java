package testing;

import org.newdawn.slick.util.xml.XMLParser;

import settings.Settings;

class XMLReadWriteTest {
	Settings settings;
	XMLParser xmlParser;
	
	private XMLReadWriteTest(){
		settings = new Settings();
		xmlParser = new XMLParser();
	}
	
}
