package de.graphioli.utils;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This class is responsible for creating unique identifications (IDs) by using the java.util.UUID package.
 * 
 * @author Graphioli
 */
public final class UIDManager {

	/**
	 * List of all generated unique IDs.
	 */
	private static ArrayList<UUID> uuids = new ArrayList<UUID>();


	private UIDManager() {
	}


	/**
	 * Generates a unique ID.
	 * 
	 * @return the generated unique ID
	 */
	public static UUID generateUniqueID() {

		UUID uuid;

		// Generate new unique ID and ensure uniqueness
		do {
			uuid = UUID.randomUUID();
		} while(!isUnique(uuid));

		// Add unique ID to list of generated IDs
		uuids.add(uuid);

		return uuid;

	}


	/**
	 * Helper method, which ensures that a generated ID is really unique.
	 * 
	 * @param uuid The generated ID to check for uniqueness
	 * @return <code>true</code> if the ID is really unique, <code>false</code> otherwise
	 */
	private static boolean isUnique(UUID uuid) {

		for (UUID currentUuid : uuids) {
			if (currentUuid.equals(uuid)) {
				return false;
			}
		}

		return true;

	}

}