package net.lostsocket.ld42.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.entities.Player;

public class WaveCompletedUI extends UI {

	private Texture waveScreen = new Texture("WaveSurvived.png");
	private Texture spaceToContinue = new Texture("space_to_continue.png");
	
	private BitmapFont font = new BitmapFont();
	
	private enum ScreenStatus { CHOOSING_ACTION, ACTION_COMPLETE}
	private ScreenStatus currentScreenStatus = ScreenStatus.CHOOSING_ACTION;
	
	private enum Action { UPGRADE, FIND_WEAPON, REMOVE_BODIES, FIND_SURVIVORS, HEAL, NONE }
	private Action currentAction = Action.NONE;
	
	private UIButton btnUpgrade;
	private UIButton btnFindWeapon;
	private UIButton btnBodies;
	private UIButton btnBtnHeal;
	private UIButton btnSurvivors;
	private UIButton btnConfirm;
	
	private String currentMessage = "";
	
	public WaveCompletedUI() {
		int middleX = Gdx.graphics.getWidth() / 2 - 168 / 2;
		btnUpgrade = new UIButton("BtnUpgrade", middleX - 200, 310);
		btnUpgrade.setCallback(new UIButtonClick() {
			
			@Override
			public void onButtonClick() {
				setCurrentAction(Action.UPGRADE);
			}
		});
		btnUpgrade.setEnabled(!Player.instance.currentWeapon.isMaxLevel());
		
		btnFindWeapon = new UIButton("BtnFindWeapon", middleX, 310);
		btnFindWeapon.setCallback(new UIButtonClick() {
			
			@Override
			public void onButtonClick() {
				setCurrentAction(Action.FIND_WEAPON);
			}
		});
		
		btnFindWeapon.setEnabled(Player.instance.areThereMoreWeapons());
		
		btnBodies = new UIButton("BtnBodies", middleX + 200, 310);
		btnBodies.setCallback(new UIButtonClick() {
			
			@Override
			public void onButtonClick() {
				setCurrentAction(Action.REMOVE_BODIES);
			}
		});
		
		btnBtnHeal = new UIButton("BtnHeal", middleX - 120, 200);
		btnBtnHeal.setCallback(new UIButtonClick() {
			
			@Override
			public void onButtonClick() {
				setCurrentAction(Action.HEAL);
			}
		});
		
		btnSurvivors = new UIButton("BtnSurvivors", middleX + 120, 200);
		btnSurvivors.setCallback(new UIButtonClick() {
			
			@Override
			public void onButtonClick() {
				setCurrentAction(Action.FIND_SURVIVORS);
			}
		});
		
		btnConfirm = new UIButton("BtnConfirm", Gdx.graphics.getWidth() / 2 - 175, 50);
		btnConfirm.setCallback(new UIButtonClick() {
			
			@Override
			public void onButtonClick() {
				confirmAction();
			}
		});
		btnConfirm.setEnabled(false);
	}
	
	private void setCurrentAction(Action current) {
		currentAction  = current; 
		disableOtherButtons();
	}
	
	private void confirmAction() {
		
		if(currentAction == Action.NONE)
			return;
		
		switch (currentAction) {
		case FIND_SURVIVORS:
			break;
		case HEAL:
			currentMessage = Player.instance.heal();
			break;
		case FIND_WEAPON:
			currentMessage = Player.instance.tryFindWeapon();
			break;
		case REMOVE_BODIES:
			currentMessage = GameManager.instance.removeBodies();
			break;
		case UPGRADE:
			currentMessage = Player.instance.currentWeapon.levelUP();
			break;
		default:
			break;
		}
		
		currentScreenStatus = ScreenStatus.ACTION_COMPLETE;
	}
	
	private void disableOtherButtons() {
		btnUpgrade.setSelected(currentAction == currentAction.UPGRADE);
		btnFindWeapon.setSelected(currentAction == currentAction.FIND_WEAPON);
		btnBodies.setSelected(currentAction == currentAction.REMOVE_BODIES);
		btnBtnHeal.setSelected(currentAction == currentAction.HEAL);
		btnSurvivors.setSelected(currentAction == currentAction.FIND_SURVIVORS);

		btnConfirm.setEnabled(true);
	}
	
	@Override
	public void update(float delta) {
		if(currentScreenStatus == ScreenStatus.CHOOSING_ACTION) {
			btnUpgrade.update(delta);
			btnFindWeapon.update(delta);
			btnBodies.update(delta);
			btnBtnHeal.update(delta);
			btnSurvivors.update(delta);
			btnConfirm.update(delta);
		}else {
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				GameManager.instance.nextWaveLogic();
			}
		}

	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(waveScreen, 0, 0);
		if(currentScreenStatus == ScreenStatus.CHOOSING_ACTION) {
			btnBodies.render(batch);
			btnFindWeapon.render(batch);
			btnBtnHeal.render(batch);
			btnSurvivors.render(batch);
			btnUpgrade.render(batch);
			btnConfirm.render(batch);
		}else {
			font.getData().setScale(1);
			font.setColor(Color.GREEN);
			font.draw(batch, currentMessage, 310, Gdx.graphics.getHeight() / 2);
			batch.draw(spaceToContinue, 0, 80);
		}
	}

	@Override
	public void dispose() {
		waveScreen.dispose();
		btnBodies.dispose();
		btnFindWeapon.dispose();
		btnBtnHeal.dispose();
		btnSurvivors.dispose();
		btnUpgrade.dispose();
		btnConfirm.dispose();
		font.dispose();
		spaceToContinue.dispose();
	}


}
