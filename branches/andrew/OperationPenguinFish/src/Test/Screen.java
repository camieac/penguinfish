package Test;
import java.awt.*;
import javax.swing.JFrame;

class Screen {
	private GraphicsDevice graphicsDevice;

	public Screen() {

		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		graphicsDevice = env.getDefaultScreenDevice();
	}

	public void setFullScreen(DisplayMode displayMode, JFrame frame) {
		frame.setUndecorated(true);
		frame.setResizable(false);
		graphicsDevice.setFullScreenWindow(frame);

		if (displayMode != null && graphicsDevice.isDisplayChangeSupported()) {
			try {
				graphicsDevice.setDisplayMode(displayMode);
			} catch (Exception e) {

			}

		}
	}
	
	public void setWindowedMode(DisplayMode dm, JFrame window){
		window.setUndecorated(false);
		window.setResizable(true);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setSize(800, 600);
		graphicsDevice.setFullScreenWindow(null);
		if(dm != null && graphicsDevice.isDisplayChangeSupported()){
			try{
				graphicsDevice.setDisplayMode(dm);
			}catch(Exception e) {}
		}
	}
	
	public Window getFullScreenWindow(){
		return graphicsDevice.getFullScreenWindow();
	}
	
	public void restoreScreen(){
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			window.dispose();
		}
	}
}
