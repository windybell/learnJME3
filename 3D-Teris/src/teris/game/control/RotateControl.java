package teris.game.control;

import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 * 延Y轴旋转空间
 * @author yanmaoyuan
 *
 */
public class RotateControl extends AbstractControl {

	/**
	 * 完成1次旋转角度的周期，默认为0.1秒。
	 */
	private float time;
	private float scale;

	/**
	 * 总的旋转角度，默认90°。
	 */
	private float angleToRotate;

	/**
	 * 旋转方向：true顺时针旋转，false逆时针旋转。
	 */
	private boolean clockwise;
	
	/**
	 * 记录旋转偏移量，左转一次则-1，右转一次则+1。
	 */
	private int offset;
	
	/**
	 * 目前已经旋转的角度
	 */
	private float angleAlreadyRotated = 0f;

	/**
	 * 是否正在旋转
	 */
	private boolean isRotating = false;
	
	public RotateControl() {
		this.time = 0.1f;
		this.angleToRotate = FastMath.HALF_PI;

		this.scale = 1 / time;
		this.clockwise = true;
		this.isRotating = false;
		this.angleAlreadyRotated = 0f;
	}
	
	@Override
	protected void controlUpdate(float tpf) {
		// 旋转
		if (isRotating) {
			// 在0.1秒之内旋转90°
			float angle = angleToRotate * scale * tpf;
			angleAlreadyRotated += angle;
	
			// 判断是否已经完成旋转
			if (angleAlreadyRotated >= angleToRotate) {
				// 防止旋转过度
				angle -= angleAlreadyRotated - angleToRotate;
	
				// 已经完成了一个周期的旋转，将关键参数复位。
				angleAlreadyRotated = 0f;
				isRotating = false;
			}
	
			// 绕Y轴旋转
			if (clockwise) {
				spatial.rotate(0, -angle, 0);
			} else {
				spatial.rotate(0, angle, 0);
			}
		}
	}

	/**
	 * 旋转节点
	 * 
	 * @param clockwise
	 *            true顺时针旋转，false逆时针旋转。
	 */
	public void rotate(boolean clockwise) {
		if (this.isRotating == false) {
			this.isRotating = true;
			this.clockwise = clockwise;
			
			// 绕Y轴旋转
			if (clockwise) {
				offset++;
			} else {
				offset--;
			}
		}
	}
	
	public boolean isRotating() {
		return isRotating;
	}
	
	/**
	 * LogicStates类的move方法将会调用此方法
	 * @return
	 */
	public int getOffset() {
		int offset = this.offset % 4;
		if (offset < 0) offset += 4;
		return offset;
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {
	}

}
