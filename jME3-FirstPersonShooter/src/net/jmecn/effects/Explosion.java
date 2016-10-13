package net.jmecn.effects;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioData.DataType;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh.Type;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;

/**
 * ��ը��Ч
 * 
 * @author yanmaoyuan
 * 
 */
public class Explosion extends Node {

	private AssetManager assetManager;

	private float speed = 1f;
	private float time = 0;
	private int state = 0;
	private ParticleEmitter flame, flash, spark, roundspark, smoketrail, debris, shockwave;

	private AudioNode audio_bomb;
	public Explosion(AssetManager assetManager) {
		super("explostion");
		this.assetManager = assetManager;

		createSound();
		createFlame();
		createFlash();
		createSpark();
		createRoundSpark();
		createSmokeTrail();
		createDebris();
		createShockwave();
		setLocalScale(10f);

		addControl(new MyControl());
	}
	
	private void createSound() {
		audio_bomb = new AudioNode(assetManager, "Sound/weapons/explode3.wav", DataType.Buffer);
		audio_bomb.setPositional(true);
		audio_bomb.setLooping(false);
		audio_bomb.setRefDistance(50);
		audio_bomb.setReverbEnabled(true);
		audio_bomb.setVolume(1);
		audio_bomb.playInstance();
		attachChild(audio_bomb);
	}

	private void createFlame() {
		flame = new ParticleEmitter("Flame", Type.Point, 32);
		flame.setSelectRandomImage(true);
		flame.setStartColor(new ColorRGBA(1f, 0.4f, 0.05f, 1));
		flame.setEndColor(new ColorRGBA(.4f, .22f, .12f, 0f));
		flame.setStartSize(1.3f);
		flame.setEndSize(2f);
		flame.setShape(new EmitterSphereShape(Vector3f.ZERO, 1f));
		flame.setParticlesPerSec(0);
		flame.setGravity(0, -5, 0);
		flame.setLowLife(.4f);
		flame.setHighLife(.5f);
		flame.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 7, 0));
		flame.getParticleInfluencer().setVelocityVariation(1f);
		flame.setImagesX(2);
		flame.setImagesY(2);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
		mat.setBoolean("PointSprite", true);
		flame.setMaterial(mat);
		attachChild(flame);
	}

	private void createFlash() {
		flash = new ParticleEmitter("Flash", Type.Point, 24);
		flash.setSelectRandomImage(true);
		flash.setStartColor(new ColorRGBA(1f, 0.8f, 0.36f, 1));
		flash.setEndColor(new ColorRGBA(1f, 0.8f, 0.36f, 0f));
		flash.setStartSize(.1f);
		flash.setEndSize(3.0f);
		flash.setShape(new EmitterSphereShape(Vector3f.ZERO, .05f));
		flash.setParticlesPerSec(0);
		flash.setGravity(0, 0, 0);
		flash.setLowLife(.2f);
		flash.setHighLife(.2f);
		flash.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 5f, 0));
		flash.getParticleInfluencer().setVelocityVariation(1);
		flash.setImagesX(2);
		flash.setImagesY(2);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flash.png"));
		mat.setBoolean("PointSprite", true);
		flash.setMaterial(mat);
		attachChild(flash);
	}

	private void createRoundSpark() {
		roundspark = new ParticleEmitter("RoundSpark", Type.Point, 20);
		roundspark.setStartColor(new ColorRGBA(1f, 0.29f, 0.34f, 1));
		roundspark.setEndColor(new ColorRGBA(0, 0, 0, 0.5f));
		roundspark.setStartSize(1.2f);
		roundspark.setEndSize(1.8f);
		roundspark.setShape(new EmitterSphereShape(Vector3f.ZERO, 2f));
		roundspark.setParticlesPerSec(0);
		roundspark.setGravity(0, -.5f, 0);
		roundspark.setLowLife(1.8f);
		roundspark.setHighLife(2f);
		roundspark.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 3, 0));
		roundspark.getParticleInfluencer().setVelocityVariation(.5f);
		roundspark.setImagesX(1);
		roundspark.setImagesY(1);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/roundspark.png"));
		mat.setBoolean("PointSprite", true);
		roundspark.setMaterial(mat);
		attachChild(roundspark);
	}

	private void createSpark() {
		spark = new ParticleEmitter("Spark", Type.Triangle, 30);
		spark.setStartColor(new ColorRGBA(1f, 0.8f, 0.36f, 1));
		spark.setEndColor(new ColorRGBA(1f, 0.8f, 0.36f, 0f));
		spark.setStartSize(.5f);
		spark.setEndSize(.5f);
		spark.setFacingVelocity(true);
		spark.setParticlesPerSec(0);
		spark.setGravity(0, 5, 0);
		spark.setLowLife(1.1f);
		spark.setHighLife(1.5f);
		spark.getParticleInfluencer()
				.setInitialVelocity(new Vector3f(0, 20, 0));
		spark.getParticleInfluencer().setVelocityVariation(1);
		spark.setImagesX(1);
		spark.setImagesY(1);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture",
				assetManager.loadTexture("Effects/Explosion/spark.png"));
		spark.setMaterial(mat);
		attachChild(spark);
	}

	private void createSmokeTrail() {
		smoketrail = new ParticleEmitter("SmokeTrail", Type.Triangle, 22);
		smoketrail.setStartColor(new ColorRGBA(1f, 0.8f, 0.36f, 1.0f));
		smoketrail.setEndColor(new ColorRGBA(1f, 0.8f, 0.36f, 0f));
		smoketrail.setStartSize(.2f);
		smoketrail.setEndSize(1f);

		smoketrail.setFacingVelocity(true);
		smoketrail.setParticlesPerSec(0);
		smoketrail.setGravity(0, 1, 0);
		smoketrail.setLowLife(.4f);
		smoketrail.setHighLife(.5f);
		smoketrail.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 12, 0));
		smoketrail.getParticleInfluencer().setVelocityVariation(1);
		smoketrail.setImagesX(1);
		smoketrail.setImagesY(3);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture",
				assetManager.loadTexture("Effects/Explosion/smoketrail.png"));
		smoketrail.setMaterial(mat);
		attachChild(smoketrail);
	}

	private void createDebris() {
		debris = new ParticleEmitter("Debris", Type.Triangle, 15);
		debris.setSelectRandomImage(true);
		debris.setRandomAngle(true);
		debris.setRotateSpeed(FastMath.TWO_PI * 4);
		debris.setStartColor(new ColorRGBA(1f, 0.59f, 0.28f, 1.0f));
		debris.setEndColor(new ColorRGBA(.5f, 0.5f, 0.5f, 0f));
		debris.setStartSize(.2f);
		debris.setEndSize(.2f);

		debris.setParticlesPerSec(0);
		debris.setGravity(0, 12f, 0);
		debris.setLowLife(1.4f);
		debris.setHighLife(1.5f);
		debris.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 15, 0));
		debris.getParticleInfluencer().setVelocityVariation(.60f);
		debris.setImagesX(3);
		debris.setImagesY(3);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture",
				assetManager.loadTexture("Effects/Explosion/Debris.png"));
		debris.setMaterial(mat);
		attachChild(debris);
	}

	private void createShockwave() {
		shockwave = new ParticleEmitter("Shockwave", Type.Triangle, 1);
		shockwave.setFaceNormal(Vector3f.UNIT_Y);
		shockwave.setStartColor(new ColorRGBA(.48f, 0.17f, 0.01f, 0.8f));
		shockwave.setEndColor(new ColorRGBA(.48f, 0.17f, 0.01f, 0f));

		shockwave.setStartSize(0f);
		shockwave.setEndSize(7f);

		shockwave.setParticlesPerSec(0);
		shockwave.setGravity(0, 0, 0);
		shockwave.setLowLife(0.5f);
		shockwave.setHighLife(0.5f);
		shockwave.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 0, 0));
		shockwave.getParticleInfluencer().setVelocityVariation(0f);
		shockwave.setImagesX(1);
		shockwave.setImagesY(1);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/shockwave.png"));
		shockwave.setMaterial(mat);
		attachChild(shockwave);
	}

	private class MyControl extends AbstractControl {
		@Override
		public void controlUpdate(float tpf) {
			time += tpf / speed;
			if (time > 0f && state == 0) {
				flash.emitAllParticles();
				spark.emitAllParticles();
				smoketrail.emitAllParticles();
				debris.emitAllParticles();
				shockwave.emitAllParticles();
				state++;
			}
			if (time > 0f + .05f / speed && state == 1) {
				flame.emitAllParticles();
				roundspark.emitAllParticles();
				state++;
			}

			if (time > 4 / speed && state == 2) {

				flash.killAllParticles();
				spark.killAllParticles();
				smoketrail.killAllParticles();
				debris.killAllParticles();
				flame.killAllParticles();
				roundspark.killAllParticles();
				shockwave.killAllParticles();
				
				// �Ƴ����Control
				spatial.removeFromParent();
				removeControl(this);
			}
		}

		@Override
		protected void controlRender(RenderManager rm, ViewPort vp) {
		}
	}
}