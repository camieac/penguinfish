package sprites;

/**
 * @author Cameron A. Craig
 * @since 6 September 2014
 *
 */
public enum ItemType{
	
	/**
	 * 
	 */
	WEAPON("Weapon"),
	/**
	 * 
	 */
	CONSUMABLE("Consumable"),
	/**
	 * 
	 */
	CLOTHING("Clothing"),
	/**
	 * 
	 */
	COIN("Coin"),
	/**
	 * 
	 */
	HEALTH("Health");
	ItemType(String name){
		this.name = name;
	}
private String name;
	/**
	 * @param itemType The name of the item type as a String.
	 * @return The ItemType value associated with the string given.
	 */
	public static ItemType getItemType(String itemType) {
		for(ItemType iType : ItemType.values()){
			if(iType.name.equals(itemType)) return iType;
		}
		return null;
	}
	
}
