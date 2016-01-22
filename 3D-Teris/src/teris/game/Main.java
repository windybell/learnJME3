package teris.game;

import teris.game.states.ThreeDLogicStates;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.system.AppSettings;

/**
 * 应用程序的主函数入口
 * @author yanmaoyuan
 *
 */
public class Main extends SimpleApplication {

	public Main() {
		super(new ThreeDLogicStates(),// 逻辑
			new ScreenshotAppState("")// 截图
		);
	}
	
	@Override
	public void simpleInitApp() {}
	
	public static void main(String[] args) {
		Main app = new Main();
		
		AppSettings settings = new AppSettings(true);
		settings.setTitle("3D Teris");
		settings.setResolution(480, 640);
		
		app.setSettings(settings);
		app.setShowSettings(false);
		app.setPauseOnLostFocus(false);
		
		app.start();
	}


}
