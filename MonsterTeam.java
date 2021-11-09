/******
 * MonsterTeam
 * Author: Adam Curley
 * 
 * A custom class for an array of monsters that acts as their team
 * 
 ******/



public class MonsterTeam {
	private Monster[] _monsters;
	private int _monsterCount;
	
	public MonsterTeam(int count) {
		_monsterCount = count;
		_monsters = new Monster[count];
	}
	
	public int getMonsterCount() {
		return _monsterCount;
	}
	
	public void setMonsterCount(int count) {
		_monsterCount = count;
	}
	
	public void addMember(Monster m, int index) {
		_monsters[index] = m;
	}
	
	public Monster[] getTeam() {
		return _monsters;
	}
}
