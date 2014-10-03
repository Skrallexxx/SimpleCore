package alexndr.api.content.items;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBowEffects 
{
	//No modifiers.
	/**
	 * Adds a flame effect to the arrows only when the arrow is critical.
	 * Modifier: none.
	 */
	public static final SimpleBowEffects critFlameEffect = new SimpleBowEffects();
	
	/**
	 * Adds a flame effect to the arrows.
	 * Modifier: none.
	 */
	public static final SimpleBowEffects flameEffect = new SimpleBowEffects();
	
	/**
	 * Allows a single arrow to be used infinitely, ie. without consuming any arrows each shot.
	 * Modifier: none.
	 */
	public static final SimpleBowEffects infiniteArrows = new SimpleBowEffects();
	
	//Float modifiers.
	/**
	 * Multiplier for the damage of the bow. Similar to the Power enchantment.
	 * Modifier: float damage multiplier (ie. 1.5F).
	 */
	public static final SimpleBowEffects damageEffect = new SimpleBowEffects();
	
	/**
	 * Multiplier for the knockback of the bow. Similar to the Punch enchantment.
	 * Modifier: float knockback multiplier (ie. 2.5F).
	 */
	public static final SimpleBowEffects knockbackEffect = new SimpleBowEffects();
	
	//Integer modifiers.
	/**
	 * Adds an efficiency to the bow, resulting in a chance that an arrow won't be consumed.
	 * Modifier: integer efficiency percentage (i.e 30 = 30%).
	 */
	public static final SimpleBowEffects efficiencyEffect = new SimpleBowEffects();
}
