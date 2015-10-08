package alexndr.plugins.Machines.furnaces;

/**
 * @author AleXndrTheGr8st
 */
public enum FurnaceSlots {

	INPUT(0),
	FUEL(1),
	OUTPUT(2),
	UPGRADE1(3),
	UPGRADE2(4),
	UPGRADE3(5),
	UPGRADE4(6),
	UPGRADE5(7),
	UPGRADE6(8);
	
	public int id;
	private FurnaceSlots(int slotId) {
		this.id = slotId;
	}
}
