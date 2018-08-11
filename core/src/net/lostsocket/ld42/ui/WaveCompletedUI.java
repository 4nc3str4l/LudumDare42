package net.lostsocket.ld42.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WaveCompletedUI extends UI {

	private Texture waveScreen = new Texture("WaveSurvived.png");
	
	private enum Action { UPGRADE, FIND_WEAPON, REMOVE_BODIES, FIND_SURVIVORS, HEAL, NONE }
	private Action currentAction = Action.NONE;
	
	private UIButton btnUpgrade;
	private UIButton btnFindWeapon;
	private UIButton btnBodies;
	private UIButton btnBtnHeal;
	private UIButton btnSurvivors;
	private UIButton btnConfirm;
	
	
	public WaveCompletedUI() {
		int middleX = Gdx.graphics.getWidth() / 2 - 168 / 2;
		btnUpgrade = new UIButton("BtnUpgrade", middleX - 200, 310);
		btnUpgrade.setCallback(new UIButtonClick() {
			
			@Override
			public void onButtonClick() {
				setCurrentAction(Action.UPGRADE);
			}
		});
		
		btnFindWeapon = new UIButton("BtnFindWeapon", middleX, 310);
		btnFindWeapon.setCallback(new UIButtonClick() {
			
			@Override
			public void onButtonClick() {
				setCurrentAction(Action.FIND_WEAPON);
			}
		});
		
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
			break;
		case FIND_WEAPON:
			break;
		case REMOVE_BODIES:
			break;
		case UPGRADE:
			break;
		default:
			break;
		}
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
		btnUpgrade.update(delta);
		btnFindWeapon.update(delta);
		btnBodies.update(delta);
		btnBtnHeal.update(delta);
		btnSurvivors.update(delta);
		btnConfirm.update(delta);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(waveScreen, 0, 0);
		btnBodies.render(batch);
		btnFindWeapon.render(batch);
		btnBtnHeal.render(batch);
		btnSurvivors.render(batch);
		btnUpgrade.render(batch);
		btnConfirm.render(batch);
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
	}


}
