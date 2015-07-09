package de.janhektor.community.game.states;

import java.util.ArrayList;
import java.util.List;

public class GameStateManager {

	private int pointer;
	private List<GameState> gamestates;
	
	public GameStateManager() {
		this.pointer = 0;
		this.gamestates = new ArrayList<>();
		
		this.gamestates.add(new GameStateLobby());
		this.gamestates.add(new GameStateStart());
		this.gamestates.add(new GameStateGracePeriod());
		this.gamestates.add(new GameStateGame());
		this.gamestates.add(new GameStateEnd());
		
		this.current().onStart();
	}
	
	public void previous() {
		if (this.pointer > 0) {
			this.current().onEnd();
			this.pointer--;
			this.current().onStart();
		}
	}
	
	public void next() {
		if (this.pointer < this.gamestates.size() - 1) {
			this.current().onEnd();
			this.pointer++;
			this.current().onStart();
		}
	}
	
	public void set(GameState gs) {
		for (int i = 0; i < this.gamestates.size(); i++) {
			if (gs == this.gamestates.get(i)) {
				this.pointer = i;
				break;
			}
		}
	}
	
	public GameState current() {
		return this.gamestates.get(this.pointer);
	}
	
	public GameState get(String name) {
		return this.gamestates.stream().filter(gs -> gs.getName().equals(name)).findFirst().get();
	}
}
